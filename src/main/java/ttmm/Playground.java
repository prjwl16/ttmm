package ttmm;

import com.google.gson.Gson;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import ttmm.database.models.User;
import ttmm.database.repos.UserRepo;
import ttmm.utils.SubRouterProtocol;

public enum Playground implements SubRouterProtocol {

    INSTANCE;


    @Override
    public Router router(Vertx vertx) {
        Router router = Router.router(vertx);
        router.get().handler(context -> {
            User user = UserRepo.INSTANCE.getUserById(1L);
            System.out.println("user: " + user);
            context.response().putHeader("content-type","application/json").end(new Gson().toJson(user));
        });

        return router;

    }

}
