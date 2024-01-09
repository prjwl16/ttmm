import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.ext.web.Router;
import io.vertx.pgclient.PgConnectOptions;
import ttmm.routes.AuthRouter;
import ttmm.routes.UserRouter;
import ttmm.utils.ConfigManager;

public class HttpRouter extends AbstractVerticle {

  @Override
  public void start() {
    try {
        super.start();

        Router router = Router.router(vertx);

        router.get("/").handler(req -> {
            req.response()
                .putHeader("content-type", "text/plain")
                .end("Hello from Vert.x!");
        });

        router.get("/health").handler(req -> {
            req.response()
                .putHeader("content-type", "text/plain")
                .end("OK");
        });

        router.route("/user/*").subRouter(UserRouter.INSTANCE.router(vertx));

        router.route("/oauth/*").subRouter(AuthRouter.INSTANCE.router(vertx));

        vertx.createHttpServer().requestHandler(router).listen(ConfigManager.INSTANCE.getPort());

        System.out.println("Application started on port "+ ConfigManager.INSTANCE.getPort());
    } catch (Exception e) {
      System.out.println("ERRROR::: " + e);
    }
  }

}
