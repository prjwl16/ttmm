import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import ttmm.middlewares.Jwt;
import ttmm.utils.SubRouterProtocol;


public enum Playground implements SubRouterProtocol {

    INSTANCE;


    @Override
    public Router router(Vertx vertx) {
        try {
            Router router = Router.router(vertx);
            router.get().handler(context -> {
                System.out.println("playground");
                String token1 = Jwt.INSTANCE.generateToken("email");
                System.out.println("token1: " + token1);
                context.response().end("token1");
            });
            return router;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
