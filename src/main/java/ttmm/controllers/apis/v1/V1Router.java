package ttmm.controllers.apis.v1;

import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import ttmm.controllers.apis.v1.category.CategoryRouter;
import ttmm.controllers.apis.v1.expense.ExpenseRouter;
import ttmm.controllers.apis.v1.friend.FriendRouter;
import ttmm.controllers.apis.v1.user.UserRouter;
import ttmm.utils.SubRouterProtocol;

public enum V1Router implements SubRouterProtocol {
    INSTANCE;

    @Override
    public Router router(Vertx vertx) {
        Router router = Router.router(vertx);
        router.route("/user/*").subRouter(UserRouter.INSTANCE.router(vertx));
        router.route("/friend/*").subRouter(FriendRouter.INSTANCE.router(vertx));
        router.route("/expense/*").subRouter(ExpenseRouter.INSTANCE.router(vertx));
        router.route("/category/*").subRouter(CategoryRouter.INSTANCE.router(vertx));

        return router;
    }
}
