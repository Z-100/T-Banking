package ch.zeiter.marvin.Blueprints;

import lombok.Setter;

public class Account {

    private final String uuid;
    private final String iban;

    @Setter
    private String password;

    @Setter
    private double balance;

    private final boolean isAdmin;

    public Account(String uuid, String iban, String password, double balance, boolean isAdmin) {
        this.uuid = uuid;
        this.iban = iban;
        this.password = password;
        this.balance = balance;
        this.isAdmin = isAdmin;
    }

    public String getUuid() {
        return this.uuid;
    }

    public String getIban() { return this.iban; }

    public String getPassword() {
        return this.password;
    }

    public double getBalance() {
        return this.balance;
    }

    public boolean getIsAdmin() {
        return this.isAdmin;
    }
}
