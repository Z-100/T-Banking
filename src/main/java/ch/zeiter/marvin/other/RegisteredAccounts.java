package ch.zeiter.marvin.other;

import ch.zeiter.marvin.functions.AccountCreator;
import ch.zeiter.marvin.functions.JsonActions;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class RegisteredAccounts {

    private final JsonActions jsonActions;
    private final AccountCreator accountCreator;

    /**
     * Constructor for RegisteredAccounts
     */
    public RegisteredAccounts() {
        this.jsonActions = new JsonActions();
        this.accountCreator = new AccountCreator();
    }

    /**
     * Method which creates and sends a newly created account to SaveJson
     *
     * @param inputPassword The password, the account later will be using
     * @param path The path to the file
     * @return Message for the user
     */
    public String addRegisteredAccount(String inputPassword, String path) {
        try {
            this.jsonActions.saveToJson(accountCreator.createAccount(inputPassword), path, "newUser");

            return "Account successfully registered. Waiting approval";
        } catch (IOException e) {
            e.printStackTrace();
            return "Something went wrong. Try again later";
        }
    }
}
