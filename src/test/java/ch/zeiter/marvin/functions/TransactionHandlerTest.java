package ch.zeiter.marvin.functions;

import ch.zeiter.marvin.blueprints.Account;
import ch.zeiter.marvin.other.UserSession;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransactionHandlerTest {

    TransactionHandler th = new TransactionHandler(new UserSession(
            new Account("a", "a", "a", 600, false, true)));

    @Test
    void newTransaction() {
        assertEquals(th.newTransaction(500, null), "Transaction successful");
        assertEquals(th.newTransaction(500, "asdasd"), "Something went wrong");
    }
}