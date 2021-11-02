package ch.zeiter.marvin.functions;

import ch.zeiter.marvin.blueprints.Account;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class JsonActionsTest {

    JsonActions jsonActions = new JsonActions();

    @Test
    void canEncryptAndDecryptAndSaveAndReadToAndFromJson() {
        assertAll(
                () -> assertEquals(getFromJson().get(0).getUuid(), "admin"),
                () -> assertEquals(getFromJson().get(0).getIban(), "admin"),
                () -> assertEquals(getFromJson().get(0).getPassword(), "admin"),
                () -> assertEquals(getFromJson().get(0).getBalance(), 69420.0),
                () -> assertTrue(getFromJson().get(0).isAdmin()),
                () -> assertTrue(getFromJson().get(0).isApproved())
        );
    }

    ArrayList<Account> getFromJson() throws IOException {
        return jsonActions.getFromJson("Accounts/accounts.json");
    }
}