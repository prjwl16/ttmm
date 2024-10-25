package ttmm.database.models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_balance")
public class UserBalance extends BaseModel {
    @Column(name = "user_id")
    private long userId;

    @Column(name = "friend_id")
    private long friendId;

    private double balance;
}
