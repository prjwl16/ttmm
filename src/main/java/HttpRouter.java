import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import ttmm.routes.UserRouter;

public class HttpRouter extends AbstractVerticle {

  @Override
  public void start() throws Exception {
    super.start();
    Router router = Router.router(vertx);
    router.get("/").handler(req -> {
      req.response()
        .putHeader("content-type", "text/plain")
        .end("Hello from Vert.x!");
    });

    router.mountSubRouter("/user", UserRouter.INSTANCE.router(vertx));

    vertx.createHttpServer().requestHandler(router).listen(8080);

    System.out.println("Application started on port 8080");
  }


}
