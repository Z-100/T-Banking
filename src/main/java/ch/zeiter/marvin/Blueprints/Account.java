package ch.zeiter.marvin.Blueprints;

public class Account {

    private final String uuid;
    private String password;
    private double balance;
    private final boolean isAdmin;

    public Account(String uuid, String password, double balance, boolean isAdmin) {
        this.uuid = uuid;
        this.password = password;
        this.balance = balance;
        this.isAdmin = isAdmin;
    }

    public String getUuid() {
        return this.uuid;
    }

    public String getPassword() {
        return this.password;
    }

    public double getBalance() {
        return this.balance;
    }

    public boolean getIsAdmin() {
        return this.isAdmin;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
