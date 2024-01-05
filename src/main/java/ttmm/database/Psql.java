package ttmm.database;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.pgclient.PgBuilder;
import io.vertx.pgclient.PgConnectOptions;
import io.vertx.sqlclient.*;

public enum Psql {

  INSTANCE;

  public void init(Vertx vertx) {
    try {
      PgConnectOptions connectOptions = new PgConnectOptions()
        .setPort(5432)
        .setHost("34.93.134.213")
        .setDatabase("postgres")
        .setUser("prjwl")
        .setPassword("U[M`EvozL`5$;Cc$");
      // Pool options
      PoolOptions poolOptions = new PoolOptions()
        .setMaxSize(5);

      Pool pool = PgBuilder
        .pool()
        .with(poolOptions)
        .connectingTo(connectOptions)
        .using(vertx)
        .build();


      pool.getConnection().compose(conn -> {
        //get * from test

        conn.query("SELECT * FROM test").execute().onSuccess(res -> {
          System.out.println("Data :" + res.iterator().next());
        }).onFailure(err -> {
          System.out.println("ERRROR::: " + err);
        });

        return conn.begin();

      }).onSuccess(res -> {
        System.out.println("Success");
      }).onFailure(err -> {
        System.out.println("ERRROR::: " + err);

      });

//      pool.getConnection().compose(conn -> {
//        System.out.println("Connected");
//        conn.close();
//        return null;
//      }).onFailure(err -> {
//        System.out.println("ERRROR::: " + err);
//      });

    } catch (Exception e) {
      System.out.println("ERRROR::: " + e);
    }

  }


}
