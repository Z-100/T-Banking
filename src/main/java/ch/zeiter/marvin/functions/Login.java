package ch.zeiter.marvin.functions;

import ch.zeiter.marvin.blueprints.Account;

import java.io.IOException;
import java.util.ArrayList;

public class Login {

    private final JsonActions jsonActions;
    private final ArrayList<Account> accounts;

    /**
     * Constructor of Login
     */
    public Login(JsonActions jsonActions) {
        this.jsonActions = jsonActions;
        accounts = getAccountFromJson();
    }

    /**
     * Method used to check if entered data matches an Account from json
     *
     * @param iBan input of the users iban
     * @param password input of the users password
     * @return the matched account
     */
    public Account loginCheck(String iBan, String password) {
        for (Account account : this.accounts) {
            if (iBan.equals(account.getIban())
                    && password.equals(account.getPassword())) {
                return account;
            }
        }
        return null;
    }

    /**
     * Method used to get all (verified) accounts from json
     *
     * @return ArrayList with all (verified) account from json
     */
    private ArrayList<Account> getAccountFromJson() {
        try {
            return jsonActions.getFromJson("Accounts/accounts.json");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
