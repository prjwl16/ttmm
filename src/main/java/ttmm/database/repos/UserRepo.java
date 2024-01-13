package ttmm.database.repos;

import ttmm.database.SqlFinder;
import ttmm.database.models.User;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public enum UserRepo {
    INSTANCE;

    public final SqlFinder<Long, User> userFinder = new SqlFinder<>(User.class);

    public User getUserById(Long id) {
        return userFinder.query().where().eq("id", id).findOne();
    }

    public User getUserByEmail(String email) {
        return userFinder.query().where().eq("email", email).findOne();
    }

    public CompletableFuture<User> getUserByEmailFuture(String email) {
        return CompletableFuture.supplyAsync(() -> getUserByEmail(email));
    }

    public CompletionStage<User> createUserFuture(String email, String firstName, String lastName, String avatar) {
        return CompletableFuture.supplyAsync(() -> {
            User user = new User();
            user.setEmail(email);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setAvatar(avatar);
            user.save();
            return user;
        });
    }
}
