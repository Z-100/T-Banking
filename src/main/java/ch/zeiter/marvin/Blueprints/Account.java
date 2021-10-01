package ch.zeiter.marvin.Blueprints;

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

    public Account(String uuid, String iban, String password, double balance, boolean isAdmin) {
        this.uuid = uuid;
        this.iban = iban;
        this.password = password;
        this.balance = balance;
        this.isAdmin = isAdmin;
    }
}
