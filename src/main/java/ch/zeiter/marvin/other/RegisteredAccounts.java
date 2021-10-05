package ch.zeiter.marvin.other;

import ch.zeiter.marvin.Blueprints.Account;
import ch.zeiter.marvin.functions.Json;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class RegisteredAccounts {

    private final Json json;

    /**
     * Constructor for RegisteredAccounts
     */
    public RegisteredAccounts() {
        this.json = new Json();
    }

    /**
     * Method which creates and sends a newly created account to SaveJson
     *
     * @param inputPassword The password, the account later will be using
     * @return Message for the user
     */
    public String addRegisteredAccount(String inputPassword) {
        try {
            this.json.saveToJson(new Account(
                            null, null, inputPassword, 0, false),
                    "Accounts/accounts.json");

            return "Account successfully registered. Waiting approval";
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return "Something went wrong. Try again later";
        }
    }
}
