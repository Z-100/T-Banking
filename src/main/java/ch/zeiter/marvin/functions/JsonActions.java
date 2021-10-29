package ch.zeiter.marvin.functions;

import ch.zeiter.marvin.blueprints.Account;
import ch.zeiter.marvin.other.EncryptionDecryptionService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Objects;

public class JsonActions {

    private EncryptionDecryptionService eds;
    private final JSONParser jsonParser;
    private ArrayList<Account> accounts;

    public JsonActions() {
        this.eds = new EncryptionDecryptionService();
        this.jsonParser = new JSONParser();
        this.accounts = new ArrayList<>();
    }

    /**
     * Method used to save Accounts to the specified jsonfile
     *
     * @param jsonAccount Used for registering
     * @param inputStream Path to the json file
     * @throws IOException Thrown exception
     * @throws ParseException Thrown exception
     */
    public void saveToJson(Account jsonAccount, String inputStream, String action)
            throws IOException {

        switch (action) {
            case "newUser" -> this.accounts.add(jsonAccount);
            case "deleteUser" -> this.accounts.removeIf(acc ->
                    acc.getUuid().equals(jsonAccount.getUuid()));
            case "changeUser" -> {
                this.accounts.removeIf(acc ->
                        acc.getUuid().equals(jsonAccount.getUuid()));
                this.accounts.add(jsonAccount);
            }
        }

        JSONArray jsonArrayAccounts = new JSONArray();

        this.accounts.forEach(acc -> {
            if (acc.getUuid().equals(jsonAccount.getUuid()))
                acc.setBalance(jsonAccount.getBalance());

            try {
                Account accEnc = eds.encrypt(new Account(
                        acc.getUuid(), acc.getIban(), acc.getPassword(),
                        acc.getBalance(), acc.isAdmin(), acc.isApproved()));

                JSONObject account = new JSONObject();

                account.put("uuid", accEnc.getUuid());
                account.put("iban", accEnc.getIban());
                account.put("password", accEnc.getPassword());
                account.put("balance", accEnc.getBalance());
                account.put("isAdmin", accEnc.isAdmin());
                account.put("isApproved", accEnc.isApproved());

                jsonArrayAccounts.add(account);
            } catch (GeneralSecurityException | UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        });

        FileWriter fileWriter = new FileWriter("src/main/resources/" + inputStream);
        fileWriter.write(jsonArrayAccounts.toJSONString());
        fileWriter.flush();
    }

    /**
     * Method used to get Objects of the type Account from the specified inputStream
     *
     * @param inputStream Path to the json file
     * @return An Arraylist filled with all accounts in jsonfile
     * @throws IOException Thrown exception
     */
    public ArrayList<Account> getFromJson(String inputStream)
            throws IOException {

        this.accounts.clear();
        JSONArray results;

        try {
            results = (JSONArray) jsonParser.parse(new InputStreamReader(
                    Objects.requireNonNull( // ? Crash as soon as possible
                            getClass().getClassLoader().getResourceAsStream(inputStream))));
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

        results.forEach(objObject -> {
            JSONObject jsnObj = (JSONObject) objObject;

            try {
                this.accounts.add(eds.decrypt(new Account(
                        (String) jsnObj.get("uuid"),
                        (String) jsnObj.get("iban"),
                        (String) jsnObj.get("password"),
                        (double) jsnObj.get("balance"),
                        (boolean) jsnObj.get("isAdmin"),
                        (boolean) jsnObj.get("isApproved")
                )));
            } catch (GeneralSecurityException | UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        });
        return this.accounts;
    }
}
