package ch.zeiter.marvin.blueprints;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Blueprint of all transactions
 */
@Data
@AllArgsConstructor
public class Transaction {
    private String iBan;
    private double transferAmount;
}