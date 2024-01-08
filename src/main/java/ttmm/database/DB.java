package ttmm.database;

import io.ebean.Database;
import io.ebean.DatabaseFactory;
import io.ebean.Finder;
import io.ebean.config.DatabaseConfig;
import io.ebean.datasource.DataSourceConfig;
import io.vertx.core.json.JsonObject;
import ttmm.database.models.User;
import ttmm.utils.ConfigManager;

public enum DB {

    INSTANCE;

    public void initializeDatabase() {
        try {
            JsonObject config = ConfigManager.INSTANCE.getDbConfig();
            DataSourceConfig dataSourceConfig = new DataSourceConfig();
            dataSourceConfig.setUsername(config.getString("username"));
            dataSourceConfig.setPassword(config.getString("password"));
            dataSourceConfig.setUrl(config.getString("url"));
            DatabaseConfig databaseConfig = new DatabaseConfig();
            databaseConfig.setDataSourceConfig(dataSourceConfig).setName("dev");
            databaseConfig.setDefaultServer(true);

            DatabaseFactory.create(databaseConfig);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("DB Error:: " + e);
        }
    }
}
