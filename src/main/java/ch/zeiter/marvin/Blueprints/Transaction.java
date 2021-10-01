package ch.zeiter.marvin.Blueprints;

import lombok.Getter;
import lombok.Setter;

public class Transaction {

    @Getter
    @Setter
    private double balanceOne;
    @Getter
    @Setter
    private double balanceTwo;
    @Getter
    @Setter
    private double transferAmount;


    public Transaction(double balanceOne, double balanceTwo, double transferAmount) {
        this.balanceOne = balanceOne;
        this.balanceTwo = balanceTwo;
        this.transferAmount = transferAmount;
    }
}
