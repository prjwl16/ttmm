import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;


import ttmm.database.DataBaseFactory;
import ttmm.utils.ConfigManager;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
public class Application extends AbstractVerticle {

    public static void main(String[] args) {
        try {
            Vertx vertx = Vertx.vertx();
            vertx.deployVerticle(new Application());
        } catch (Exception e) {
            log.error(e.getMessage());
            System.exit(1);
        }

    }

    @Override
    public void start() throws Exception {
        super.start();
        initialize();
        vertx.deployVerticle(HttpRouter.class.getName());
    }

    private static void initialize() throws Exception{
        try {
            String jsonString = "{\n" +
                "    \"database\": {\n" +
                "        \"host\": \"34.93.134.213\",\n" +
                "        \"username\": \"prjwl\",\n" +
                "        \"password\": \"U[M`EvozL`5$;Cc$\",\n" +
                "        \"database\": \"dev\",\n" +
                "        \"url\": \"jdbc:postgresql://34.93.134.213:5432/dev\",\n" +
                "        \"schema\": \"public\"\n" +
                "    },\n" +
                "    \n" +
                "    \"gauth\": {\n" +
                "        \"client_id\": \"778139559269-jenpkci84rcj8s46e0mhncojfrfibgrv.apps.googleusercontent.com\",\n" +
                "        \"client_secret\": \"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\",\n" +
                "        \"redirect_uri\": \"http://onthecloud.live/oauth/google/callback\"\n" +
                "    },\n" +
                "    \"port\": 8080\n" +
                "}\n";

            JsonObject config = new JsonObject(jsonString);

//            try {
//                File file = new File(Application.class.getClassLoader().getResource("config.json").getFile());
//                if (file.exists() && !file.isDirectory()) {
//                    String json = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
//                    config = new JsonObject(json);
//                    System.out.println("config: " + config);
                    ConfigManager.INSTANCE.init(config);
//                } else {
//                    throw new Exception("ERR Config file not found");
//                }
//            } catch (IOException e) {
//                System.out.println("ERR Config " + e);
//                throw new Exception("ERR Config " + e);
//            }

            DataBaseFactory.INSTANCE.initializeDatabase();
            DataBaseFactory.INSTANCE.runMigration();

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            throw new Exception("ERR " + e);
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
