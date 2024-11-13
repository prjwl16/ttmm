package ttmm.database.models;

import io.ebean.annotation.Index;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "paid_by")
@EqualsAndHashCode(callSuper = true)
public class PaidBy extends BaseModel{

    @Index(unique = true)
    @Column(name = "transaction_id")
    private long transactionId;
    @Column(name = "user_id")
    private long userId;

    private double amount;

}
