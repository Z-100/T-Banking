package ch.zeiter.marvin.other;

import ch.zeiter.marvin.blueprints.Account;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class UserSessionTest {

    @Mock
    Account account;
    UserSession session;

    @BeforeEach
    void setUp() {
        session = new UserSession(account);
    }

    @AfterEach
    void tearDown() {
        session = null;
    }

    @Test
    void session() {
        assertEquals(session, UserSession.session(account));
    }

    @Test
    void getLoggedUser() {
        Assertions.assertAll(
                () -> assertEquals(session.getLoggedUser().getUuid(), account.getUuid()),
                () -> assertEquals(session.getLoggedUser().getIban(), account.getIban()),
                () -> assertEquals(session.getLoggedUser().getPassword(), account.getPassword()),
                () -> assertEquals(session.getLoggedUser().getBalance(), account.getBalance()),
                () -> assertEquals(session.getLoggedUser().isAdmin(), account.isAdmin()),
                () -> assertEquals(session.getLoggedUser().isApproved(), account.isApproved())
        );
    }
}