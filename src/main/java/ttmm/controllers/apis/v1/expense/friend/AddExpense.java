package ttmm.controllers.apis.v1.expense.friend;

import io.ebean.DB;
import io.reactivex.rxjava3.core.Single;
import io.vertx.ext.web.RoutingContext;
import lombok.extern.slf4j.Slf4j;
import ttmm.controllers.CommonController;
import ttmm.controllers.apis.v1.expense.friend.request.AddExpenseWithFriend;
import ttmm.database.models.Transaction;
import ttmm.middlewares.ResponseHelper;
import ttmm.utils.Response;

@Slf4j
public enum AddExpense implements CommonController {

    INSTANCE;

    @Override
    public void handle(RoutingContext event) {

        Single.just(event)
            .map(this::map)
            .subscribe(
                response -> ResponseHelper.INSTANCE.writeJsonResponse(event, new Response("Expense added", response, 200, true)),
                err -> ResponseHelper.INSTANCE.handleError(event, err)
            ).dispose();

    }

    private boolean map(RoutingContext context) {
        io.ebean.Transaction txn = DB.beginTransaction();
        try {
            Transaction transaction = new Transaction();
            AddExpenseWithFriend request = context.body().asPojo(AddExpenseWithFriend.class);

            txn.commit();
            return true;
        } catch (Exception e) {
            txn.rollback();
            log.error("Error while adding expense", e);
            return false;
        } finally {
            txn.end();
        }
    }
}
