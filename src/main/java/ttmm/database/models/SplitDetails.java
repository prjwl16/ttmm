package ttmm.database.models;

import io.ebean.annotation.DbDefault;
import io.ebean.annotation.Index;
import io.ebean.annotation.NotNull;
import lombok.*;
import ttmm.database.enums.SplitType;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "split_details")
@EqualsAndHashCode(callSuper = true)
public class SplitDetails extends BaseModel{

    @Index(unique = true)
    @Column(name = "transaction_id")
    private long transactionId;

    @Column(name = "user_id")
    private long userId;

    private double amount;

    @Enumerated(EnumType.STRING)
    @DbDefault("EQUAL")
    @NotNull
    @Column(name = "split_type")
    private SplitType splitType;

}
