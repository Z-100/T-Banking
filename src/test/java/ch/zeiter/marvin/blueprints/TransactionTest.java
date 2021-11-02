package ch.zeiter.marvin.blueprints;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {

    @Test
    void returnsCorrectInformation() {
        String iBan = "420";
        double transferAmount = 6.9;

        Transaction transaction = new Transaction(iBan, transferAmount);

        Assertions.assertAll(
                () -> assertEquals(transaction.getIBan(), iBan),
                () -> assertEquals(transaction.getTransferAmount(), transferAmount)
        );
    }
}
