package ttmm.database;

import io.ebean.DatabaseFactory;
import io.ebean.annotation.Platform;
import io.ebean.config.DatabaseConfig;
import io.ebean.datasource.DataSourceConfig;
import io.ebean.dbmigration.DbMigration;
import io.ebean.migration.MigrationConfig;
import io.ebean.migration.MigrationRunner;
import io.vertx.core.json.JsonObject;
import ttmm.utils.ConfigManager;

import java.io.IOException;

public enum DataBaseFactory {

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

    public void runMigration() {
        try {
            DbMigration dbMigration = DbMigration.create();
            dbMigration.setPlatform(Platform.POSTGRES);
            dbMigration.generateMigration();

            MigrationConfig migrationConfig = new MigrationConfig();
            migrationConfig.setDbUrl(ConfigManager.INSTANCE.getDbConfig().getString("url"));
            migrationConfig.setDbUsername(ConfigManager.INSTANCE.getDbConfig().getString("username"));
            migrationConfig.setDbPassword(ConfigManager.INSTANCE.getDbConfig().getString("password"));
            migrationConfig.setDbSchema(ConfigManager.INSTANCE.getDbConfig().getString("schema"));

            MigrationRunner migrationRunner = new MigrationRunner(migrationConfig);
            migrationRunner.run();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
