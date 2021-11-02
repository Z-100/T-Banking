package ch.zeiter.marvin.other;

import ch.zeiter.marvin.blueprints.Account;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test for the EcryptionDecryptionService
 */
class EncryptionDecryptionServiceTest {

    // ? The data for the Mock account
    String uuid = "uuid";
    String iban = "iban";
    String password = "password";
    double balance = 420.69;
    boolean isAdmin = false;
    boolean isApproved = true;

    // ? The Mock account used for testing
    Account account = new Account(uuid, iban, password, balance, isAdmin, isApproved);

    EncryptionDecryptionService eds = new EncryptionDecryptionService();

    /**
     * Method used to test the encryption feature
     *
     * @throws GeneralSecurityException Exception thrown by Google Tink
     * @throws UnsupportedEncodingException Exception thrown by Google Tink
     */
    @Test
    void canEncryptAccount() throws GeneralSecurityException, UnsupportedEncodingException {
        assertNotNull(eds.encrypt(account));
    }

    /**
     * Method used to test the decryption feature
     *
     * @throws GeneralSecurityException Exception thrown by Google Tink
     * @throws UnsupportedEncodingException Exception thrown by Google Tink
     */
    @Test
    void canDecryptAccount() throws GeneralSecurityException, UnsupportedEncodingException {
        Account decryptedAccount = eds.decrypt(account);
        assertAll(
                () -> assertEquals(uuid, decryptedAccount.getUuid()),
                () -> assertEquals(iban, decryptedAccount.getIban()),
                () -> assertEquals(password, decryptedAccount.getPassword()),
                () -> assertEquals(balance, decryptedAccount.getBalance()),
                () -> assertEquals(isAdmin, decryptedAccount.isAdmin()),
                () -> assertEquals(isApproved, decryptedAccount.isApproved())
        );
    }
}