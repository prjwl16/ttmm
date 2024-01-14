package ttmm.controllers.apis;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import ttmm.controllers.user.FetchUserController;
import ttmm.controllers.user.UserRoutes;
import ttmm.middlewares.Jwt;
import ttmm.utils.SubRouterProtocol;

public enum Apis implements SubRouterProtocol {
    INSTANCE;


    @Override
    public Router router(Vertx vertx) {

        Router router = Router.router(vertx);
        router.route().handler(Jwt.INSTANCE::validateToken);

        router.route("/user/*").subRouter(UserRoutes.INSTANCE.router(vertx));

        return router;
    }
}
