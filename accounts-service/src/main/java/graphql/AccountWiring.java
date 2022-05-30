package graphql;

import dao.AccountsDAO;
import graphql.schema.idl.RuntimeWiring;
import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

public class AccountWiring {

    public RuntimeWiring getWiring() {
        AccountsDAO dao = new AccountsDAO();
        return RuntimeWiring
                .newRuntimeWiring()
                /* add wiring here */
                .type(
                        newTypeWiring("Accountsquery")
                                .dataFetcher("accounts", new AccountFetcher(dao).getAccountFetcher())
                                .dataFetcher("account", new AccountFetcher(dao).getAccountByIdFetcher())
                ).type(
                        newTypeWiring("AccountsMutation")
                                .dataFetcher("deletaccount", new AccountFetcher(dao).getDeleteAccountFetcher())
                                .dataFetcher("changeGroup", new AccountFetcher(dao).getChangeGroupFetcher())
                                .dataFetcher("createaccount", new AccountFetcher(dao).getaddAccountFetcher())
                ).build();
    }

}
