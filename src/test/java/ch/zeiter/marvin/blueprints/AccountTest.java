package ch.zeiter.marvin.blueprints;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void returnsCorrectInformation() {
        String uuid = "test";
        String iban = "test";
        String password = "test";
        double balance = 5.0;
        boolean isAdmin = false;
        boolean isApproved = true;

        Account account = new Account(uuid, iban, password, balance, isAdmin, isApproved);

        Assertions.assertAll(
                () -> assertEquals(uuid, account.getUuid()),
                () -> assertEquals(iban, account.getIban()),
                () -> assertEquals(password, account.getPassword()),
                () -> assertEquals(balance, account.getBalance()),
                () -> assertFalse(account.isAdmin()),
                () -> assertTrue(account.isApproved())
        );
    }
}