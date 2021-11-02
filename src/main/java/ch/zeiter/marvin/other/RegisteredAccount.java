package ch.zeiter.marvin.other;

import ch.zeiter.marvin.blueprints.Account;
import ch.zeiter.marvin.functions.AccountCreator;
import ch.zeiter.marvin.functions.JsonActions;
import org.json.simple.parser.ParseException;

import java.io.IOException;

/**
 * Used to create and save new accounts
 */
public class RegisteredAccount {

    private final JsonActions jsonActions;
    private final AccountCreator accountCreator;

    /**
     * Constructor for RegisteredAccounts
     */
    public RegisteredAccount() {
        this.jsonActions = new JsonActions();
        this.accountCreator = new AccountCreator();
    }

    /**
     * Method which creates and sends a newly created account to saveToJson
     *
     * @param inputPassword The password, the account later will be using
     * @param path The path to the file
     * @return Message for the user
     */
    public String addRegisteredAccount(String inputPassword, String path) {
        try {
            Account newAcc = accountCreator.createAccount(inputPassword);

            this.jsonActions.getFromJson(path);
            this.jsonActions.saveToJson(newAcc, path, "newUser");

            return String.format(
                    "Account successfully created.\nYour new IBan is: '%s'",
                    newAcc.getIban());
        } catch (IOException e) {
            e.printStackTrace();
            return "Something went wrong. Try again later";
        }
    }
}
