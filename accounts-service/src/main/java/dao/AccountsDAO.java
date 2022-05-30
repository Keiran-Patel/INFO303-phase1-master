package dao;

import domain.Account;
import java.util.ArrayList;
//import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class AccountsDAO {

    private static final Map<String, Account> accounts = new TreeMap<>();

    //private static final Map<String, Account> accounts = new HashMap<>();

    /*
	 * Some dummy data for testing
     */
    public AccountsDAO() {
        if (accounts.isEmpty()) {
            accounts.put("kp123", new Account("kp123", "k@gmail.com", "Patel01", "Keiran", "Patel", "VIP Customers"));
            accounts.put("js1234", new Account("js1234", "jerrysmith@hotmail.com", "smith01", "Jerry", "Smith", "Regular Customers"));

        }
    }

    /**
     * A client needs to be able to add a new customer account.
     *
     * @param account
     */
    public Account addAccount(Account account) {
        accounts.put(account.getId(), account);
        return account;
    }

    /**
     * A client needs to be able to get all customer accounts.
     *
     * @return All account via the id
     */
    public List<Account> getAccounts() {
        return new ArrayList<>(accounts.values());
    }

    /**
     * A client needs to be able to get a specific customer account (identified
     * by the customer’s ID).
     *
     * @param id
     * @return The product matching the given ID, or null if no match found.
     */
    public Account getById(String id) {
        return accounts.get(id);
    }

    /**
     * A client needs to be able to change the group of an existing account to a
     * new value (identified by the customer’s ID).
     *
     * @param id The ID of the group to replace.
     * @param updatedAccount The group to replace it with.
     */
    public Account changeGroup(String id, String newGroup) {
        Account acc = accounts.get(id);
        acc.setGroup(newGroup);
        return acc;

    }

    /**
     * A client needs to be able to delete a customer account (identified by the
     * customer’s ID).
     *
     * @param id The ID of the account to delete.
     */
    public Account delete(String id) {
        return accounts.remove(id);
    }

    /**
     * Need to check if an account exists.
     *
     * @param id The ID of the account being checked.
     * @return true if product does exists and false if it doesn't exist.
     */
    public boolean exists(String id) {
        return accounts.containsKey(id);
    }

}
