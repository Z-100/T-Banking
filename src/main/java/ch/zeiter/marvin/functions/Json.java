package ch.zeiter.marvin.functions;

import ch.zeiter.marvin.Blueprints.Account;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;

public class Json {

    private ArrayList<Account> accounts;

    private final JSONParser jsonParser;

    public Json() {
        accounts = new ArrayList<>();
        jsonParser = new JSONParser();
    }

    /**
     * Method used to save Accounts to the specified jsonfile
     *
     * @param newAccount  Used for registering
     * @param inputStream Path to the json file
     * @throws IOException    Thrown exception
     * @throws ParseException Thrown exception
     */
    public void saveToJson(Account newAccount, String inputStream)
            throws IOException, ParseException {

        this.accounts.clear();
        this.accounts = getFromJson(inputStream);
        this.accounts.add(newAccount);

        JSONArray accounts = new JSONArray();

        this.accounts.forEach(acc -> {
            JSONObject account = new JSONObject();
            account.put("uuid", acc.getUuid());
            account.put("iban", acc.getIban());
            account.put("password", acc.getPassword());
            account.put("balance", acc.getBalance());
            account.put("isAdmin", acc.isAdmin());
            account.put("isApproved", acc.isApproved());
            accounts.add(account);
        });

        FileWriter fileWriter = new FileWriter("src/main/resources/" + inputStream);
        fileWriter.write(accounts.toJSONString());
        fileWriter.flush();
    }

    /**
     * Method used to get Objects of the type Account from the specified inputStream
     *
     * @param inputStream Path to the json file
     * @return An Arraylist filled with all accounts in jsonfile
     * @throws IOException    Thrown exception
     * @throws ParseException Thrown exception
     */
    public ArrayList<Account> getFromJson(String inputStream)
            throws IOException, ParseException {

        this.accounts.clear();

        JSONArray results = (JSONArray) jsonParser.parse(new InputStreamReader(
                getClass().getClassLoader().getResourceAsStream(inputStream)));

        results.forEach(objObject -> {
            JSONObject jsnObj = (JSONObject) objObject;

            this.accounts.add(new Account(
                    (String) jsnObj.get("uuid"),
                    (String) jsnObj.get("iban"),
                    (String) jsnObj.get("password"),
                    (double) jsnObj.get("balance"),
                    (boolean) jsnObj.get("isAdmin"),
                    (boolean) jsnObj.get("isApproved")
            ));
        });
        return this.accounts;
    }
}
