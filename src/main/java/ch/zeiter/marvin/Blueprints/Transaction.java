package ch.zeiter.marvin.Blueprints;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Transaction {
    private String iBan;
    private double transferAmount;
}
