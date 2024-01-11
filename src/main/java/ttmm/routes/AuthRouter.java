package ttmm.routes;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.oauth2.OAuth2Auth;
import io.vertx.ext.auth.oauth2.providers.GoogleAuth;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.OAuth2AuthHandler;
import lombok.extern.slf4j.Slf4j;
import retrofit2.Call;
import ttmm.database.models.User;
import ttmm.network.Google;
import ttmm.utils.ConfigManager;
import ttmm.utils.SubRouterProtocol;

import java.io.IOException;
import java.util.List;


@Slf4j
public enum AuthRouter implements SubRouterProtocol {

    INSTANCE;

    public Router router(Vertx vertx) {
        Router router = Router.router(vertx);
        String clientId = ConfigManager.INSTANCE.getAuthConfig().getString("client_id");
        String clientSecret = ConfigManager.INSTANCE.getAuthConfig().getString("client_secret");
        String redirectUri = ConfigManager.INSTANCE.getAuthConfig().getString("redirect_uri");

        OAuth2Auth authProvider = GoogleAuth.create(vertx, clientId, clientSecret);
        OAuth2AuthHandler authHandler = OAuth2AuthHandler.create(vertx, authProvider, redirectUri).withScopes(List.of("email", "profile"));
        authHandler.setupCallback(router.get("/callback"));

        router.route("/login").handler(authHandler).failureHandler(ctx -> {
            System.out.println("err: " + ctx.failure());
            ctx.response().end("error");
        });

        router.get("/google/callback").handler(ctx -> {
                try {
                    String code = ctx.request().getParam("code");
                    authProvider.authenticate(new JsonObject().put("code", code).put("redirectUri", redirectUri)).onSuccess(user -> {
                        Call<com.google.gson.JsonObject> call  = Google.INSTANCE.getBase().getUserInfo("Bearer " + user.principal().getString("access_token"));
                        com.google.gson.JsonObject userInfo = null;
                        try {
                            userInfo = call.execute().body();
                            System.out.println("userInfo: " + userInfo);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        //Create user if not exists

                        ctx.response().end("ok");
                    }).onFailure(err -> {
                        System.out.println("err: " + err);
                        ctx.response().end("error");
                    });

                    //redirect to /health
//                ctx.response().putHeader("Location", "/health").setStatusCode(302).end();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });

        return router;
    }
}
