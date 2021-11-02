package ch.zeiter.marvin.functions;

import ch.zeiter.marvin.blueprints.Account;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Class used to approve and list accounts
 */
public class accountsAwaitingApproval {

    private JsonActions jsonActions;

    private ArrayList<Account> accounts;

    public void listAll() {
        jsonActions = new JsonActions();
        try {
            accounts = jsonActions.getFromJson("Accounts/registeredAccounts.json");
            for (int i = 0; i < accounts.size(); i++) {
                System.out.printf("\n[%d] -> %s", i,  accounts.get(i).getUuid());
            }
            jsonActions = null;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void approveAll() {
        jsonActions = new JsonActions();
        try {
            jsonActions.getFromJson("Accounts/accounts.json");
            ArrayList<Account> accounts = jsonActions.getFromJson("Accounts/registeredAccounts.json");

            accounts.forEach(acc -> {
                try {
                    jsonActions.saveToJson(acc, "Accounts/accounts.json", "newUser");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
        jsonActions = null;
        wipeJson();
    }

    //TODO
    private void wipeJson() {
        jsonActions = new JsonActions();

        accounts.forEach(acc -> {
            try {
                jsonActions.saveToJson(
                        acc, "Accounts/registeredAccounts.json", "deleteUser");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        jsonActions = null;
    }
}
