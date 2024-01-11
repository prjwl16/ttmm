package ttmm.database.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ttmm.database.enums.Currency;
import ttmm.database.enums.TransactionType;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transactions")
public class Transaction extends BaseModel {

    private String description;
    private String amount;
    private String date;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    @Column(name = "is_shared")
    private boolean isShared;

    @Column(name = "is_recurring")
    private boolean isRecurring;

    //Relationships
    @ManyToOne
    @Column(name = "category_id")
    private Category category;

    @ManyToOne
    @Column(name = "account_id")
    private Account account;

    @ManyToOne
    @Column(name = "group_id")
    private Group group;

}
