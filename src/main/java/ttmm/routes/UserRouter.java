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

    @Override
    public Router router(Vertx vertx) {
        Router router = Router.router(vertx);
        router.get().handler(context -> {
            try{
                Finder<Long, User> finder = new Finder<>(User.class);
                finder.all().forEach(user->{
                    System.out.println(user.getEmail());
                });
                context.response().end("ok");
            }catch (Exception e){
                System.out.println("err: "+e);
                context.response().end(e.getMessage());
            }
        });

        router.get("/:id").handler(UserController.INSTANCE::handle);

        router.post("/").handler(UserController.INSTANCE::handlePost);

        return router;
    }

}
