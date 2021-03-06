package ch.zeiter.marvin.functions;

import ch.zeiter.marvin.blueprints.Account;
import ch.zeiter.marvin.other.AccountPropertyGenerator;
import lombok.Getter;

import java.io.IOException;
import java.util.function.Predicate;

/**
 * Class for account creation
 */
public class AccountCreator {

    @Getter
    private AccountPropertyGenerator acg = new AccountPropertyGenerator();

    /**
     * Method used to create a new user account
     *
     * @param inputPassword The user chosen password
     * @return The newly created account
     */
    public Account createAccount(String inputPassword) {
        JsonActions jsonActions = new JsonActions();

        // * Could be done without the predicate initialization, but I want to show off my knowledge
        Predicate<Account> ibanPredicate = acc -> acc.getIban().equals(acg.getIBan());
        Predicate<Account> uuidPredicate = acc -> acc.getUuid().equals(acg.getUuid());

        try {
            // * Could also be done without streams, but I still want to show off my knowledge
            jsonActions.getFromJson("Accounts/accounts.json").stream()
                    .filter(ibanPredicate)
                    .filter(uuidPredicate)
                    .forEach(acc -> acg = new AccountPropertyGenerator());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Account(acg.getUuid(), acg.getIBan(),
                inputPassword, 0, false, false);
    }
}
