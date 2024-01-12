import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.CorsHandler;
import lombok.extern.slf4j.Slf4j;
import ttmm.controllers.auth.AuthRoutes;
import ttmm.controllers.user.UserRoutes;
import ttmm.utils.ConfigManager;

@Slf4j
public class HttpRouter extends AbstractVerticle {

    @Override
    public void start() {
        try {
            super.start();
            Router router = Router.router(vertx);

            //Router config
            router.route().handler(CorsHandler.create()
                .allowedMethod(io.vertx.core.http.HttpMethod.GET)
                .allowedMethod(io.vertx.core.http.HttpMethod.POST)
                .allowedMethod(io.vertx.core.http.HttpMethod.PUT)
                .allowedMethod(io.vertx.core.http.HttpMethod.DELETE)
                .allowedMethod(io.vertx.core.http.HttpMethod.PATCH)
                .allowedMethod(io.vertx.core.http.HttpMethod.HEAD)
                .allowedMethod(io.vertx.core.http.HttpMethod.OPTIONS)
                .allowedHeader("Access-Control-Allow-Method")
                .allowedHeader("Access-Control-Allow-Origin")
                .allowedHeader("Access-Control-Allow-Credentials")
                .allowedHeader("Content-Type")
                .allowedHeader("Authorization")
            );

            router.get("/").handler(req -> {
                req.response()
                    .putHeader("content-type", "text/plain")
                    .end("..Hello from Vert.x");
            });
            router.get("/health").handler(req -> {
                req.response()
                    .putHeader("content-type", "text/plain")
                    .end("OK??");
            });

            router.route("/api/user/*").subRouter(UserRoutes.INSTANCE.router(vertx));
            router.route("/oauth/*").subRouter(AuthRoutes.INSTANCE.router(vertx));
            router.route("/play/*").subRouter(Playground.INSTANCE.router(vertx));

            vertx.createHttpServer().requestHandler(router).listen(ConfigManager.INSTANCE.getPort());

            log.info("Server started on port {}", ConfigManager.INSTANCE.getPort());

        } catch (Exception e) {
            log.error("Error starting verticle", e);
        }
    }

}
