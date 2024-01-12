package ttmm.controllers.user;

import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;

import ttmm.utils.SubRouterProtocol;


public enum UserRoutes implements SubRouterProtocol {

    INSTANCE;

    @Override
    public Router router(Vertx vertx) {
        Router router = Router.router(vertx);

        router.get().handler(FetchUserController.INSTANCE::handle);
        router.post().handler(CreateUserController.INSTANCE::handle);

        return router;
    }

}
