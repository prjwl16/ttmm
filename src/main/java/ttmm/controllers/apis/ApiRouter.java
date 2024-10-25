package ttmm.controllers.apis;

import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import ttmm.controllers.apis.v1.V1Router;
import ttmm.middlewares.Jwt;
import ttmm.utils.SubRouterProtocol;

public enum ApiRouter implements SubRouterProtocol {
    INSTANCE;

    @Override
    public Router router(Vertx vertx) {
        Router router = Router.router(vertx);
        router.route().handler(Jwt.INSTANCE::validateToken);
        router.route("/v1/*").subRouter(V1Router.INSTANCE.router(vertx));
        return router;
    }
}
