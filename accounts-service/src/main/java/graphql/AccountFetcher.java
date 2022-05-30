package graphql;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.AccountsDAO;
import domain.Account;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import java.util.Map;

public class AccountFetcher {

    /* add data fetcher methods here */
    private final AccountsDAO dao;

    public AccountFetcher(AccountsDAO dao) {
        this.dao = dao;
    }

    /* 3. get Account by id */
    public DataFetcher getAccountByIdFetcher() {
        return (DataFetchingEnvironment dfe) -> {
            String id = dfe.getArgument("id");
            return dao.getById(id);

        };
    }

    /* 1. add A new Acount by id*/
    public DataFetcher getaddAccountFetcher() {
        return (DataFetchingEnvironment dfe) -> {
            Map map = dfe.getArgument("newaccount");
            ObjectMapper mapper = new ObjectMapper();
            Account account = mapper.convertValue(map, Account.class);
            return dao.addAccount(account);
        };
    }

    /* 2. get all Accounts */
    public DataFetcher getAccountFetcher() {
        return (DataFetchingEnvironment dfe) -> {
            return dao.getAccounts();

        };
    }

    /* 4. Change the group*/
    
          public DataFetcher getChangeGroupFetcher() {
        return (DataFetchingEnvironment dfe) -> {
                String id = dfe.getArgument("id");
                String newGroup = dfe.getArgument("newGroup");
                return dao.changeGroup(id, newGroup);
        };
}

    /* 5. delete account via id */
    public DataFetcher getDeleteAccountFetcher() {
        return (DataFetchingEnvironment dfe) -> {
            String id = dfe.getArgument("id");
            return dao.delete(id);
            

        };
    }
}
