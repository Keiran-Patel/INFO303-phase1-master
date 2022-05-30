package service;

import graphql.AccountWiring;
import io.jooby.Jooby;
import io.jooby.ServerOptions;
import io.jooby.graphql.GraphQLModule;
import io.jooby.graphql.GraphiQLModule;
import io.jooby.json.GsonModule;
import java.io.IOException;

public class AccountsService extends Jooby {

    public AccountsService() {

        setServerOptions(new ServerOptions().setPort(8082));

        install(new GsonModule());
        install(new GraphQLModule(new AccountWiring().getWiring()));
        install(new GraphiQLModule());

        get("/", (ctx) -> ctx.sendRedirect("/graphql"));
    }

    public static void main(String[] args) throws IOException {
        System.out.println("\n\n****** Accounts Service ******\n\n");
        new AccountsService().start();
    }

}
