package ch.zeiter.marvin.Blueprints;

import lombok.Getter;
import lombok.Setter;

public class Account {

    @Getter
    private final String uuid;
    @Getter
    private final String iban;
    @Getter
    @Setter
    private String password;
    @Getter
    @Setter
    private double balance;
    @Getter
    private final boolean isAdmin;

    /**
     * Constructor for the Account class
     *
     * @param uuid randomly generated id, used as a session key
     * @param iban randomly generated IBAN, used for login and account transfers
     * @param password User defined, changeable password
     * @param balance adjustable balance of the account
     * @param isAdmin determines if an account has admin rights or not.
     */
    public Account(String uuid, String iban, String password, double balance, boolean isAdmin) {
        this.uuid = uuid;
        this.iban = iban;
        this.password = password;
        this.balance = balance;
        this.isAdmin = isAdmin;
    }
}
