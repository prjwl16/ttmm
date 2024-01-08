package ttmm.routes;

import io.smallrye.mutiny.Uni;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import jakarta.persistence.Persistence;
import org.hibernate.reactive.mutiny.Mutiny;
import org.slf4j.Logger;
import ttmm.controllers.UserController;
import ttmm.database.DB;
import ttmm.database.models.User;
import ttmm.utils.SubRouterProtocol;

import java.util.concurrent.atomic.AtomicReference;

import static io.smallrye.mutiny.helpers.spies.Spy.onItem;

public enum UserRouter implements SubRouterProtocol {

    INSTANCE;

    private final Logger logger = org.slf4j.LoggerFactory.getLogger(UserRouter.class);

    @Override
    public Router router(Vertx vertx) {
        Router router = Router.router(vertx);
        router.get("/").handler(context -> {
            try {
                DB.INSTANCE.dev.withSession(session -> session.find(User.class)).subscribe().with(users -> {
                    System.out.println("users: " + users);
                    context.response().end("users: " + users);
                });
                System.out.println("UserRouter");
                context.response().end("UserRouter");
            } catch (Exception e) {
                System.out.println("ERR " + e);
                context.response().end("ERR " + e);
            }
        });

        router.get("/:id").handler(UserController.INSTANCE::handle);

        return router;
    }

}
