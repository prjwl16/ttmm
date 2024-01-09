import io.ebean.DB;
import io.ebean.Database;
import io.ebean.Finder;
import io.ebean.annotation.Platform;
import io.ebean.config.DatabaseConfig;
import io.ebean.datasource.DataSourceConfig;
import io.ebean.dbmigration.DbMigration;
import io.ebean.migration.MigrationConfig;
import io.ebean.migration.MigrationRunner;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import org.apache.commons.io.FileUtils;


import ttmm.database.DataBaseFactory;
import ttmm.database.models.User;
import ttmm.utils.ConfigManager;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Application extends AbstractVerticle {


    public static void main(String[] args) {
        try {
            Vertx vertx = Vertx.vertx();
            vertx.deployVerticle(new Application());



        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void start() throws Exception {
        super.start();
        try {
            JsonObject config = null;
            try {
                File file = new File("src/main/resources/config.json");
                if (file.exists() && !file.isDirectory()) {
                    String json = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
                    config = new JsonObject(json);
                    System.out.println("config: " + config);
                } else {
                    throw new Exception("ERR Config file not found");
                }
            } catch (IOException e) {
                System.out.println("ERR Config " + e);
                throw new Exception("ERR Config " + e);
            }

            ConfigManager.INSTANCE.init(config);
            DataBaseFactory.INSTANCE.initializeDatabase();

            vertx.deployVerticle(HttpRouter.class.getName());

//            Finder<Long, User> finder = new Finder<>(User.class);
//            User user = finder.byId(1L);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERR " + e);
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
