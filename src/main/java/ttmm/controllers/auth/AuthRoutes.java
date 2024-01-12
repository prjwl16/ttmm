package ttmm.controllers.auth;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.oauth2.OAuth2Auth;
import io.vertx.ext.auth.oauth2.providers.GoogleAuth;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.OAuth2AuthHandler;
import lombok.extern.slf4j.Slf4j;
import ttmm.controllers.CommonController;
import ttmm.database.models.User;
import ttmm.middlewares.Jwt;
import ttmm.utils.ConfigManager;
import ttmm.utils.SubRouterProtocol;

import java.util.List;


/*
    Need to keep this all-in-one file for now because of the way the vertx router works
 */

@Slf4j
public enum AuthRoutes implements SubRouterProtocol, CommonController {

    INSTANCE;

    private OAuth2AuthHandler authHandler;
    private OAuth2Auth authProvider;

    @Override
    public Router router(Vertx vertx) {
        Router router = Router.router(vertx);
        initAuth(vertx);

        authHandler.setupCallback(router.get("/callback"));

        router.route("/google").handler(authHandler).failureHandler(ctx -> {
            ctx.response().end("error");
            fail(ctx.failure().getMessage());
        });
        router.get("/google/callback").handler(this::handle);

        return router;
    }

    @Override
    public void handle(RoutingContext ctx) {
        try {
            String code = ctx.request().getParam("code");
            String redirectUri = ConfigManager.INSTANCE.getAuthConfig().getString("redirect_uri");
            String frontEndUriTokenRedirect = ConfigManager.INSTANCE.getFrontEndUri() + ConfigManager.INSTANCE.getAuthConfig().getString("token_redirect");
            String frontEndUriErrorRedirect = ConfigManager.INSTANCE.getFrontEndUri() + ConfigManager.INSTANCE.getAuthConfig().getString("error_redirect");

            log.info(frontEndUriErrorRedirect);
            log.info(frontEndUriTokenRedirect);

            authProvider.authenticate(new JsonObject().put("code", code).put("redirectUri", redirectUri))
                .onSuccess(user -> {
                    authProvider.userInfo(user).onSuccess(userInfo -> {
                        log.info("User info: {}", userInfo);
                        //TODO: Create User if not exists
                        if(userInfo != null){
                            User newUser = new User();
                            newUser.setEmail(userInfo.getString("email"));
                            newUser.setFirstName(userInfo.getString("given_name"));
                            newUser.setLastName(userInfo.getString("family_name"));
                            newUser.setAvatar(userInfo.getString("picture"));
                            newUser.save();
                        }


                        String token = Jwt.INSTANCE.generateToken(userInfo.getString("email"));
                        ctx.response().putHeader("Location", frontEndUriTokenRedirect + token).setStatusCode(302).end();
                    }).onFailure(err -> {
                        log.error("Error getting user info", err);
                        ctx.response().putHeader("Location", frontEndUriErrorRedirect).setStatusCode(302).end();
                    });
                }).onFailure(err -> {
                    ctx.response().putHeader("Location", frontEndUriErrorRedirect).setStatusCode(302).end();
                });
        } catch (Exception e) {
            log.error("Error handling auth callback", e);
            fail(e.getMessage());
        }
    }

    void initAuth(Vertx vertx) {
        String clientId = ConfigManager.INSTANCE.getAuthConfig().getString("client_id");
        String clientSecret = ConfigManager.INSTANCE.getAuthConfig().getString("client_secret");
        String redirectUri = ConfigManager.INSTANCE.getAuthConfig().getString("redirect_uri");

        authProvider = GoogleAuth.create(vertx, clientId, clientSecret);
        authHandler = OAuth2AuthHandler.create(vertx, authProvider, redirectUri).withScopes(List.of("email", "profile"));
    }
}
