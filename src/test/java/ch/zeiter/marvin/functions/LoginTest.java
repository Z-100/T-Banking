package ch.zeiter.marvin.functions;

import ch.zeiter.marvin.blueprints.Account;
import ch.zeiter.marvin.blueprints.InvalidPasswordException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LoginTest {

    JsonActionsTest jat = new JsonActionsTest();
    List<Account> accList = jat.getFromJson();

    String iBan = "admin";
    String password = "admin";

    boolean methodCorrect = false;


    LoginTest() throws IOException {
    }

    @Test
    void loginCheck() {
        for (Account account : accList) {
            if (iBan.equals(account.getIban())
                    && password.equals(account.getPassword())) {
                methodCorrect = true;
            } else
                methodCorrect = false;
        }

        assertTrue(methodCorrect);
    }
}