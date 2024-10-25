package ttmm.database.models;


import io.ebean.annotation.DbDefault;
import io.ebean.annotation.NotNull;
import lombok.*;
import ttmm.database.enums.Currency;
import ttmm.database.enums.TransactionType;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transactions")
public class Transaction extends BaseModel {

    private String description;
    private double amount=0.0;
    private Date date;

    @Enumerated(EnumType.STRING)
    @DbDefault("EXPENSE")
    @NotNull
    private TransactionType type;

    @Enumerated(EnumType.STRING)
    @DbDefault("INR")
    @NotNull
    private Currency currency;

    //Relationships
    @ManyToOne
    @Column(name = "category_id")
    private Category category;



//    @ManyToOne
//    @Column(name = "account_id")
//    private Account account;

//    @ManyToOne
//    @Column(name = "group_id")
//    private Group group;
//
//    @ManyToOne
//    @JoinColumn(name = "recurring_id")
//    private RecurringTransaction recurringTransaction;

}
