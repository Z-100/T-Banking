package ch.zeiter.marvin.other;

import ch.zeiter.marvin.blueprints.Account;
import com.google.crypto.tink.subtle.AesGcmJce;
import org.apache.commons.lang3.ArrayUtils;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Iterator;
import java.util.Stack;

/**
 * Class used to encrypt and decrypt an Account
 */
public class EncryptionDecryptionService {

    private final String aad;
    private final String key;

    private boolean isAdmin;
    private boolean isApproved;

    /**
     * The constructor
     */
    public EncryptionDecryptionService() {
        this.aad = "thisProjectShouldntHaveTakenThatLongToProgram";
        this.key = "iWillBeExtremelySadIfThisProjectIsGettingABadGrade!";
    }

    /**
     * Method used to read uuid, iban & password
     * from an Account into a Stack
     *
     * @param account The given Account
     * @return The Stack filled with the information
     */
    private Stack<String> readStack(Account account) {
        Stack<String> textAccInformation = new Stack<>();

        textAccInformation.push(account.getUuid());
        textAccInformation.push(account.getIban());
        textAccInformation.push(account.getPassword());
        this.isAdmin = account.isAdmin();
        this.isApproved = account.isApproved();

        return textAccInformation;
    }

    /**
     * Method used to encrypt an Account
     *
     * @param account The given account
     * @return The encrypted account
     * @throws GeneralSecurityException The exception thrown by AesGcmJce
     * @throws UnsupportedEncodingException The exception thrown by getStringFromByteArray()
     */
    public Account encrypt(Account account) throws GeneralSecurityException, UnsupportedEncodingException {
        Stack<Object> encryptedAccInformation = new Stack<>();
        Stack<String> plaintextAccInformation = readStack(account);

        AesGcmJce agjEncryption = new AesGcmJce(key.getBytes());

        Iterator<String> iterator = plaintextAccInformation.iterator();
        while (iterator.hasNext()) {
            Byte[] encryptedByte = ArrayUtils.toObject(
                    agjEncryption.encrypt(plaintextAccInformation.peek().getBytes(), aad.getBytes()));

            encryptedAccInformation.push(encryptedByte);
            plaintextAccInformation.pop();
        }

        return getInformation(encryptedAccInformation, account.getBalance(), true);
    }

    /**
     * Method used to decrypt an Account
     *
     * @param account The given account
     * @return The decrypted account
     * @throws GeneralSecurityException The exception thrown by AesGcmJce
     * @throws UnsupportedEncodingException The exception thrown by getStringFromByteArray()
     */
    public Account decrypt(Account account) throws GeneralSecurityException, UnsupportedEncodingException {
        Stack<Object> decryptedAccInformation = new Stack<>();
        Stack<String> encryptedAccInformation = readStack(account);

        AesGcmJce agjDecryption = new AesGcmJce(key.getBytes());

        Iterator<String> iterator2 = encryptedAccInformation.iterator();
        while (iterator2.hasNext()) {
            Byte[] decryptedByte = ArrayUtils.toObject(
                    agjDecryption.decrypt(encryptedAccInformation.peek().getBytes("ISO-8859-1"), aad.getBytes()));

            decryptedAccInformation.push(decryptedByte);
            encryptedAccInformation.pop();
        }

        return getInformation(decryptedAccInformation, account.getBalance(), false);
    }

    /**
     * Method used to get Information from Stack
     *
     * @param stack The given Stack filled with information
     * @param balance The balance of the account to be 'encrypted'
     * @return The en/de-crypted Account
     * @throws UnsupportedEncodingException The exception thrown by getStringFromByteArray
     */
    private Account getInformation(Stack<Object> stack, double balance, boolean isEnc)
            throws UnsupportedEncodingException {

        Stack<Object> temp = new Stack<>();
        double bal;

        if (isEnc)
            bal = balance / 69.420;
        else
            bal = balance * 69.420;

        String password = getStringFromByteArray(stack);
        temp.push(stack.peek());
        stack.pop();

        String iBan = getStringFromByteArray(stack);
        temp.push(stack.peek());
        stack.pop();

        String uuid = getStringFromByteArray(stack);
        temp.push(stack.peek());
        stack.pop();

        return new Account(uuid, iBan, password, bal, this.isAdmin, this.isApproved);
    }

    /**
     * Method used to convert Object to String with encoding
     *
     * @param stack The given Stack
     * @return The read String from the given stack
     * @throws UnsupportedEncodingException The exception thrown by the charSet
     */
    private String getStringFromByteArray(Stack<Object> stack) throws UnsupportedEncodingException {
        return new String((byte[]) ArrayUtils.toPrimitive(
                stack.peek()), "ISO-8859-1");
    }
}
