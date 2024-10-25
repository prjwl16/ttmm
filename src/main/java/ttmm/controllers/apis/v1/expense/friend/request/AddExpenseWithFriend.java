package ttmm.controllers.apis.v1.expense.friend.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class AddExpenseWithFriend {
    private String description;
    private String date;
    private List<PaidBy> paidByList;
    private List<Shares> shares;
}
