package ch.zeiter.marvin.functions;

import ch.zeiter.marvin.Blueprints.Account;

@Deprecated
public class AccountHandler {

    private Account account;

    public AccountHandler(Account account) {
        this.account = account;
    }

    public void updatePassword(String uuid, String password) {

    }

    public void updateBalance(String uuid, double balance) {

    }
}
