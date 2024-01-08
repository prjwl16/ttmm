package ttmm.database.repositories;


import ttmm.database.models.User;

public interface UserRepo extends ReactiveCrudRepository<User, Long> {

    public Mono<User> findByEmail(String email);


}
