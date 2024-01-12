package ttmm.middlewares;


import io.reactivex.rxjava3.core.Single;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.PubSecKeyOptions;
import io.vertx.ext.auth.jwt.JWTAuth;
import io.vertx.ext.auth.jwt.JWTAuthOptions;
import io.vertx.ext.web.RoutingContext;
import lombok.extern.slf4j.Slf4j;
import ttmm.database.repos.UserRepo;

@Slf4j
public enum Jwt {

    INSTANCE;
    private JWTAuth provider = null;

    public void inti(Vertx vertx) {

        if (provider != null) {

            return;
        }

        provider = JWTAuth.create(vertx, new JWTAuthOptions()
            .addPubSecKey(new PubSecKeyOptions()
                .setAlgorithm("HS256")
                .setBuffer("keyboard cat")));

    }

    public String generateToken(String email) {
        try {
            return provider.generateToken(new JsonObject().put("email", email));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Single<RoutingContext> validateToken(RoutingContext context) {
        return Single.just(context).map(ctx -> {
            String token = ctx.request().getHeader("Authorization");
            if (token == null) {
                throw new RuntimeException("Token is required");
            }
            token = token.replace("Bearer ", "");
            provider.authenticate(new JsonObject().put("token", token)).onSuccess(user -> {
                ctx.put("user", UserRepo.INSTANCE.getUserByEmail(user.principal().getString("email")));
            }).onFailure(error -> {
                log.error(error.getMessage());
                throw new RuntimeException("Token is invalid");
            });
            return ctx;
        });
    }

}
