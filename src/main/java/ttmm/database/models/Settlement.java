package ttmm.database.models;

import io.ebean.annotation.DbDefault;
import io.ebean.annotation.NotNull;
import lombok.*;
import ttmm.database.enums.Currency;
import ttmm.database.enums.PaymentMethod;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "settlements")
@EqualsAndHashCode(callSuper = true)
public class Settlement extends BaseModel {
    private double amount;
    @Enumerated(EnumType.STRING)
    @DbDefault("INR")
    @NotNull
    private Currency currency;
    private Date date;

    //Relationships

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user_id;

    @ManyToOne
    @JoinColumn(name = "friend_id")
    private User friendId;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method")
    private PaymentMethod paymentMethod;

}
