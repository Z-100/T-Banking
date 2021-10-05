package ch.zeiter.marvin.functions;

import ch.zeiter.marvin.Blueprints.Account;
import ch.zeiter.marvin.other.AccountPropertyGenerator;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;

public class CreateAccount {

    public Account createAccount(String inputPassword) {
        AccountPropertyGenerator acg = new AccountPropertyGenerator();
        Json json = new Json();


        try {
            for (Account acc : json.getFromJson("Accounts/accounts.json")) {
                if (acc.getIban() == acg.getIBan() || acc.getUuid() == acg.getUuid()) {
                    acg = new AccountPropertyGenerator();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return new Account(acg.getUuid(), acg.getIBan(),
                inputPassword, 0, false, false);
    }
}
