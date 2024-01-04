package ttmm.controllers;

import com.google.gson.Gson;
import io.reactivex.rxjava3.core.Single;
import io.vertx.ext.web.RoutingContext;

import java.util.HashMap;

public enum UserController implements CommonController {

    INSTANCE;


    public void handle(RoutingContext context) {
        System.out.println("UserController");
        Single.just(context).map(this::map).subscribe(res->{
            System.out.println(res);
            context.response().putHeader("content-type","application/json").end(new Gson().toJson(res));
        },err->{
            context.response().end(err.getMessage());
        });
    }

    public HashMap<String, String> map(RoutingContext context) {
        HashMap<String, String> map = new HashMap<>();
        map.put("id", context.request().getParam("id"));
        return map;
    }
}
