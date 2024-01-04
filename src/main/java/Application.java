import io.vertx.core.AbstractVerticle;

public class Application extends AbstractVerticle {

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
