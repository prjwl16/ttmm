package ttmm.controllers;


import io.vertx.ext.web.RoutingContext;
import ttmm.database.models.User;


import java.util.HashMap;

public enum UserController implements CommonController {

    INSTANCE;

    public void handle(RoutingContext context) {
        context.response().send("OK");
    }

    public HashMap<String, String> map(RoutingContext context) {
        HashMap<String, String> map = new HashMap<>();
        map.put("id", context.request().getParam("id"));
        return map;
    }

    public void handlePost(RoutingContext context) {

    }
}
