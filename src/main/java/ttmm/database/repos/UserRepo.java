package ttmm.database.repos;

import ttmm.database.SqlFinder;
import ttmm.database.models.User;

public enum UserRepo {
    INSTANCE;

    public SqlFinder<Long, User> userFinder = new SqlFinder<>(User.class);

    public User getUserById(Long id) {
        return userFinder.query().where().eq("id", id).findOne();
    }

    public User getUserByEmail(String email) {
        return userFinder.query().where().eq("email", email).findOne();
    }

}
