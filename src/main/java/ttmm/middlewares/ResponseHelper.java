package ttmm.middlewares;

import io.vertx.ext.web.RoutingContext;
import ttmm.utils.Mapper;
import ttmm.utils.Response;

public enum ResponseHelper {
    INSTANCE;

    public void writeJsonResponse(RoutingContext context, Response response) {
        System.out.println("Response: " + Mapper.INSTANCE.getGson().toJson(response));
        context.response()
            .putHeader("content-type", "application/json")
            .end(Mapper.INSTANCE.getGson().toJson(response));
    }
}
