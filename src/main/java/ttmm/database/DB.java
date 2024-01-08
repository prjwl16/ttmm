package ttmm.database;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.pgclient.PgBuilder;
import io.vertx.pgclient.PgConnectOptions;
import io.vertx.sqlclient.Pool;
import io.vertx.sqlclient.PoolOptions;
import ttmm.utils.ConfigManager;

public enum DB {

    INSTANCE;

    public Pool pool;


    public void init(Vertx vertx) {
        try {
            if (pool != null) {
                return;
            }

            JsonObject config = ConfigManager.INSTANCE.getDbConfig();
            PgConnectOptions connectOptions = new PgConnectOptions()
                .setPort(5432)
                .setHost(config.getString("host"))
                .setDatabase(config.getString("database"))
                .setUser(config.getString("username"))
                .setPassword(config.getString("password"));

            PoolOptions poolOptions = new PoolOptions()
                .setMaxSize(5);

            pool = PgBuilder
                .pool()
                .with(poolOptions)
                .connectingTo(connectOptions)
                .using(vertx)
                .build();

            if(pool != null)
                System.out.println("DB Connected");

        } catch (Exception e) {
            System.out.println("DB Error:: " + e);
        }
    }


}
