package ttmm.controllers.apis.v1.expense.friend.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaidBy {
    private long userId;
    private long amount;
}
