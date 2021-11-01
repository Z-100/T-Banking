package ch.zeiter.marvin.functions;

import ch.zeiter.marvin.blueprints.Account;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class JsonActionsTest {

    JsonActions jsonActions = new JsonActions();

    @Test
    void saveToJson() {
        try {
            assertEquals(getFromJson().get(0).getIban(), "uuid");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    ArrayList<Account> getFromJson() throws IOException {

        return jsonActions.getFromJson("Accounts/accounts.json");
    }
}