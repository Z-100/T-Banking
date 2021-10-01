package ch.zeiter.marvin.functions;

import ch.zeiter.marvin.Blueprints.Account;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;

public class SaveJson {

    private ArrayList<Account> accounts;

    private JSONParser jsonParser;

    public SaveJson() {
        accounts = new ArrayList<>();
        jsonParser = new JSONParser();
    }

    public void saveToJson(Account newAccount, ArrayList<Account> newAccounts, String inputStream)
            throws IOException, ParseException {

        JSONArray results = (JSONArray) jsonParser.parse(new InputStreamReader(
                getClass().getClassLoader().getResourceAsStream(inputStream)));

        results.forEach(objObject -> {
            JSONObject jsnObj = (JSONObject) objObject;
            this.accounts.add(new Account(
                    (String) jsnObj.get("uuid"),
                    (String) jsnObj.get("iban"),
                    (String) jsnObj.get("password"),
                    (double) jsnObj.get("balance"),
                    (boolean) jsnObj.get("isAdmin")
            ));
        });
        this.accounts.add(newAccount);

        JSONArray accounts = new JSONArray();

        this.accounts.forEach(acc -> {
            JSONObject account = new JSONObject();
            account.put("uuid", acc.getUuid());
            account.put("iban", acc.getIban());
            account.put("password", acc.getPassword());
            account.put("balance", acc.getBalance());
            account.put("isAdmin", acc.getIsAdmin());
            accounts.add(account);
        });

        FileWriter fileWriter = new FileWriter(inputStream);
        fileWriter.write(accounts.toJSONString());
        fileWriter.flush();
    }
}
