package ttmm.database.models;

import io.ebean.annotation.DbDefault;
import io.ebean.annotation.Index;
import lombok.*;
import ttmm.database.enums.FriendshipStatus;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "friendships")
@EqualsAndHashCode(callSuper = true)
public class Friendship extends BaseModel {

    @Column(name = "friendship_id")
    @Index(unique = true)
    private String friendshipId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

    @ManyToOne
    @JoinColumn(name = "friend_id")
    private User friendId;

    @Enumerated(EnumType.STRING)
    @DbDefault("PENDING")
    private FriendshipStatus status;

}
