package ttmm.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import io.vertx.ext.web.RoutingContext;
import ttmm.database.DB;
import ttmm.database.models.User;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

public enum UserController implements CommonController {

    INSTANCE;

    public void handle(RoutingContext context) {
        System.out.println("UserController");

        DB.INSTANCE.pool.query("SELECT * FROM users WHERE id = 1").execute().onSuccess(res -> {
            AtomicReference<User> user = new AtomicReference<>(new User());
            res.iterator().forEachRemaining(row -> {
                System.out.println("row: "+row);
                ObjectMapper mapper =  new ObjectMapper();
                user.set(mapper.convertValue(row, User.class));
            });
            context.response().putHeader("content-type", "application/json").send(new Gson().toJson(user.get()));
        }).onFailure(err -> {
            System.out.println("err: "+err.getMessage());
            context.response().end(err.getMessage());
        });

//        Single.just(context).map(this::map).subscribe(res -> {
//            System.out.println(res);
//            context.response().putHeader("content-type", "application/json").end(new Gson().toJson(res));
//        }, err -> {
//            fail(err.getMessage());
//            context.response().end(err.getMessage());
//        }).dispose();
    }

    public HashMap<String, String> map(RoutingContext context) {
        HashMap<String, String> map = new HashMap<>();
        map.put("id", context.request().getParam("id"));
        return map;
    }
}
