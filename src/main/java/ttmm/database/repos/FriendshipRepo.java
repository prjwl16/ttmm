package ttmm.database.repos;

import lombok.extern.slf4j.Slf4j;
import ttmm.database.SqlFinder;
import ttmm.database.models.Friendship;

@Slf4j
public enum FriendshipRepo {
    INSTANCE;
    public final SqlFinder<Long, Friendship> friendshipFinder = new SqlFinder<>(Friendship.class);

    public Friendship findFriendshipByUserAndFriendId(Long userId, Long friendId) {
        return friendshipFinder.query()
            .where()
            .eq("user_id", userId)
            .and().eq("friend_id", friendId).findOne();
    }

}
