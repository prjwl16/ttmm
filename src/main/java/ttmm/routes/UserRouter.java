package ttmm.routes;

import io.ebean.Finder;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import org.slf4j.Logger;
import ttmm.controllers.UserController;

import ttmm.database.models.User;
import ttmm.utils.SubRouterProtocol;


public enum UserRouter implements SubRouterProtocol {

    INSTANCE;

    private final Logger logger = org.slf4j.LoggerFactory.getLogger(UserRouter.class);

    @Override
    public Router router(Vertx vertx) {
        Router router = Router.router(vertx);
        router.get("/").handler(context -> {
            try{
                Finder<Long, User> finder = new Finder<>(User.class);
                User user = finder.byId(1L);
                System.out.println("user: "+user);
                context.response().end("ok");
            }catch (Exception e){
                System.out.println("err: "+e);
                context.response().end(e.getMessage());
            }
        });

        router.get("/:id").handler(UserController.INSTANCE::handle);

        return router;
    }

}
