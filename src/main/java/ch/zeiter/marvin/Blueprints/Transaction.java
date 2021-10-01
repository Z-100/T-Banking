package ch.zeiter.marvin.Blueprints;

public class Transaction {

    private final double balanceOne;
    private final double balanceTwo;
    private final double transferAmount;


    public Transaction(double balanceOne, double balanceTwo, double transferAmount) {
        this.balanceOne = balanceOne;
        this.balanceTwo = balanceTwo;
        this.transferAmount = transferAmount;
    }
}
