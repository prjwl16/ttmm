package ttmm.controllers.apis.v1.expense.friend.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Shares {
    private long userId;
    private long amount;
}
