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

    /**
     * The constructor
     *
     * @param balanceOne The first balance to be changed
     * @param balanceTwo The second balance to be changed
     * @param transferAmount The amount of money to be transferred
     */
    public Transaction(double balanceOne, double balanceTwo, double transferAmount) {
        this.balanceOne = balanceOne;
        this.balanceTwo = balanceTwo;
        this.transferAmount = transferAmount;
    }
}
