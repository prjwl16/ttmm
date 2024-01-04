package ttmm.controllers;

import io.vertx.ext.web.RoutingContext;
import ttmm.exceptions.RoutingError;

public interface CommonController {


    void handle(RoutingContext event);

    default void fail(String e) throws RoutingError {
        throw new RoutingError(e);
    }
}
