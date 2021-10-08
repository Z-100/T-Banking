package ch.zeiter.marvin.functions;

import ch.zeiter.marvin.other.AccountPropertyGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateAccountTest {

    CreateAccount createAccount = new CreateAccount();

    @Mock
    AccountPropertyGenerator apg = createAccount.getAcg();

    @Test
    void createAccount() {

        String password = "password";

        var result = createAccount.createAccount(password);

        Assertions.assertAll(
                () -> assertEquals(apg.getUuid(), result.getUuid()),
                () -> assertEquals(apg.getIBan(), result.getIban()),
                () -> assertEquals(password, result.getPassword())
        );
    }
}