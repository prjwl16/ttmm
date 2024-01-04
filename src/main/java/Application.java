import io.vertx.core.AbstractVerticle;

public class Application extends AbstractVerticle {


    public static void main(String[] args) {
        Application application = new Application();
        try {
            application.start();
        } catch (Exception e) {
            System.out.println(e);
        }
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
