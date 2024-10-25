package ttmm.controllers.apis.v1.friend;

import io.reactivex.rxjava3.core.Single;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import lombok.extern.slf4j.Slf4j;
import ttmm.controllers.CommonController;
import ttmm.database.enums.FriendshipStatus;
import ttmm.database.models.Friendship;
import ttmm.database.models.User;
import ttmm.database.repos.FriendshipRepo;
import ttmm.middlewares.ResponseHelper;
import ttmm.utils.Response;

@Slf4j
public enum RespondFriendShipRequest implements CommonController {
    INSTANCE;

    @Override
    public void handle(RoutingContext event) {
        Single.just(event)
            .map(this::map)
            .subscribe(
                response -> ResponseHelper.INSTANCE.writeJsonResponse(event, response),
                error -> ResponseHelper.INSTANCE.handleError(event, error)
            ).dispose();
    }

    private Response map(RoutingContext context) {
        Response response;
        // Get the body of the request
        JsonObject body = context.body().asJsonObject();
        Long friendId = body.getLong("friendId");
        boolean decision = body.getBoolean("decision");
        User user = context.get("user");

        // check if the friend request exists of not if yes then update the status as per the decision

        Friendship friendship = FriendshipRepo.INSTANCE.findFriendshipByUserAndFriendId(user.getId(), friendId);

        if (friendship != null && friendship.getStatus() == FriendshipStatus.PENDING) {
            if (decision) {
                //TODO: Add this in a transaction for atomicity
                Friendship newFriendShip = new Friendship();
                newFriendShip.setUserId(user);
                newFriendShip.setFriendId(user);
                newFriendShip.save();
                friendship.setStatus(FriendshipStatus.ACCEPTED);
                friendship.save();
                response = new Response("Friend request accepted", 200, true);
            } else {
                friendship.setStatus(FriendshipStatus.REJECTED);
                friendship.save();
                response = new Response("Friend request rejected", 200, true);
            }
        } else {
            response = new Response("Friend request not found", 404, false);
        }



        return response;
    }
}
