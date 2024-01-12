package ttmm.controllers.user;

import io.vertx.ext.web.RoutingContext;
import ttmm.controllers.CommonController;
import ttmm.middlewares.Jwt;

public enum GetUserController implements CommonController {

    INSTANCE;

    @Override
    public void handle(RoutingContext event) {
        Jwt.INSTANCE.validateToken(event).subscribe(
            response -> {
                System.out.println(response);
            },
            error -> {
                System.out.println(error);
            }
        );
    }
}
