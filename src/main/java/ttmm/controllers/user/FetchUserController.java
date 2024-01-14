package ttmm.controllers.user;


import io.reactivex.rxjava3.core.Single;
import io.vertx.ext.web.RoutingContext;
import lombok.extern.slf4j.Slf4j;
import ttmm.controllers.CommonController;
import ttmm.database.models.User;
import ttmm.middlewares.Jwt;
import ttmm.middlewares.ResponseHelper;
import ttmm.utils.Response;

@Slf4j
public enum FetchUserController implements CommonController {

    INSTANCE;

    public void handle(RoutingContext event) {
        try {
            Single.just(event).map(this::map).subscribe(
                response -> ResponseHelper.INSTANCE.writeJsonResponse(event, new Response("User fetched successfully", response, 200, true)),
                error -> {
                    ResponseHelper.INSTANCE.writeJsonResponse(event, new Response(error.getMessage(), "", 400, false));
                }
            ).dispose();
        } catch (Exception e) {
            log.error("Error fetching user " + e.getMessage());
            fail(e.getMessage());
        }
    }


    private User map(RoutingContext context) {
        return context.get("user");
    }
}
