package ch.zeiter.marvin.Blueprints;

public class Transaction {

    private final double balanceOne;
    private final double balanceTwo;


    public Transaction(double balanceOne, double balanceTwo) {
        this.balanceOne = balanceOne;
        this.balanceTwo = balanceTwo;
    }

    public boolean verifyTransaction() {
        return true;
    }
}
