package ttmm.routes;

import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import ttmm.controllers.UserController;
import ttmm.utils.SubRouterProtocol;

public enum UserRouter implements SubRouterProtocol {

  INSTANCE;

  @Override
  public Router router(Vertx vertx) {
    Router router = Router.router(vertx);

    router.get("/").handler(context -> {
        System.out.println("UserRouter");
        context.response().putHeader("content-type", "application/json").end();
    });

    router.get("/:id").handler(UserController.INSTANCE::handle);

    return router;
  }

}
