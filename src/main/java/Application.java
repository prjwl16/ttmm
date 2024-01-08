import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import org.apache.commons.io.FileUtils;

import ttmm.database.DB;
import ttmm.utils.ConfigManager;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Application extends AbstractVerticle {


    public static void main(String[] args) {
      Vertx vertx = Vertx.vertx();
      vertx.deployVerticle(new Application());
    }

    @Override
    public void start() throws Exception {
        super.start();
        try {
            JsonObject config = null;
            try{
                File file = new File("src/main/resources/config.json");
                if(file.exists() && !file.isDirectory()){
                    String json = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
                    config = new JsonObject(json);
                    System.out.println("config: "+config);
                }else{
                    throw new Exception("ERR Config file not found");
                }
            }catch (IOException e){
                System.out.println("ERR Config "+e);
                throw new Exception("ERR Config "+e);
            }

            ConfigManager.INSTANCE.init(config);

            DB.INSTANCE.init(vertx);
            vertx.deployVerticle(HttpRouter.class.getName());
        } catch (Exception e) {
            System.out.println("ERR "+e);
            throw new Exception("ERR "+e);
        }
    }

    public static Connection getConnection() throws SQLException {
        String jdbcUrl = ConfigManager.INSTANCE.getDbConfig().getString("url");
        String username = ConfigManager.INSTANCE.getDbConfig().getString("username");
        String password = ConfigManager.INSTANCE.getDbConfig().getString("password");

        try {
            return DriverManager.getConnection(jdbcUrl, username, password);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("JDBC Driver not found", e);
        }
    }
}
