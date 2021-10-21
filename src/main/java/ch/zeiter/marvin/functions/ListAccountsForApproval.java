package ch.zeiter.marvin.functions;

import ch.zeiter.marvin.Blueprints.Account;

import java.io.IOException;
import java.util.ArrayList;

public class ListAccountsForApproval {

    public void listAll() {
        Json json = new Json();
        try {
            ArrayList<Account> accounts = json.getFromJson("Accounts/registeredAccounts.json");
            for (int i = 0; i < accounts.size(); i++) {
                System.out.printf("\n[%d] -> %s", i,  accounts.get(i).getUuid());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
