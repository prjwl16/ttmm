import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.CorsHandler;
import lombok.extern.slf4j.Slf4j;
import ttmm.controllers.apis.Apis;
import ttmm.controllers.auth.AuthRoutes;
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

            router.route("/oauth/*").subRouter(AuthRoutes.INSTANCE.router(vertx));
            router.route("/play/*").subRouter(Playground.INSTANCE.router(vertx));

            //All the APIs will need to be authorized with JWT
            router.route("/api/*").subRouter(Apis.INSTANCE.router(vertx));


            router.errorHandler(500, routingContext -> {
                routingContext.response().setStatusCode(500).end("Internal Server Error");
            });

            router.errorHandler(404, routingContext -> {
                routingContext.response().setStatusCode(404).end("Not Found");
            });

            vertx.createHttpServer().requestHandler(router).listen(ConfigManager.INSTANCE.getPort());

            log.info("Server started on port {}", ConfigManager.INSTANCE.getPort());

        } catch (Exception e) {
            log.error("Error starting verticle", e);
        }
    }

}
