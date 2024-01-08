package ttmm.database.repositories;

import io.vertx.core.Future;
import io.vertx.sqlclient.SqlConnection;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ttmm.database.DB;

public class Finder<I, T> {

    private final Class<T> type;

    public Finder(Class<T> type) {
        this.type = type;
    }

    private Future<SqlConnection> DB() {
        return DB.INSTANCE.pool.getConnection();
    }
}
