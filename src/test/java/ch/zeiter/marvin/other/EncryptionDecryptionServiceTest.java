package ch.zeiter.marvin.other;

import ch.zeiter.marvin.blueprints.Account;
import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

import static org.junit.jupiter.api.Assertions.*;

class EncryptionDecryptionServiceTest {

	String uuid = "uuid";
	String iban = "iban";
	String password = "password";
	double balance = 420.69;
	boolean isAdmin = false;
	boolean isApproved = true;

	Account account = new Account(uuid, iban, password, balance, isAdmin, isApproved);
	EncryptionDecryptionService eds = new EncryptionDecryptionService();

	@Test void encrypt() throws GeneralSecurityException, UnsupportedEncodingException {
		assertNotNull(eds.encrypt(account));
	}

	@Test void decrypt() throws GeneralSecurityException, UnsupportedEncodingException {
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