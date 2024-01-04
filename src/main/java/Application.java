import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;

public class Application extends AbstractVerticle {


    public static void main(String[] args) {
      Vertx vertx = Vertx.vertx();
      vertx.deployVerticle(new Application());
    }

    @Override
    public void start() throws Exception {
        super.start();
        try {
            vertx.deployVerticle(HttpRouter.class.getName());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
