package ch.zeiter.marvin.blueprints;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {

    @Test
    void returnsCorrectInformation() {
        double balanceOne = 69420;
        double balanceTwo = 420;
        double transferAmount = 6.9;

        Transaction transaction = new Transaction(balanceOne, balanceTwo, transferAmount);

        Assertions.assertAll(
                () -> assertEquals(transaction.getBalanceOne(), balanceOne),
                () -> assertEquals(transaction.getBalanceTwo(), balanceTwo),
                () -> assertEquals(transaction.getTransferAmount(), transferAmount)
        );
    }
}
