package ch.zeiter.marvin.functions;

import ch.zeiter.marvin.Blueprints.Account;
import ch.zeiter.marvin.Blueprints.Transaction;
import ch.zeiter.marvin.other.UserSession;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class TransactionHandler {

    private final Json json;
    private final Account account;

    private double oldBalance;

    /**
     * The constructor
     */
    public TransactionHandler(UserSession userSession) {
        this.json = new Json();
        this.account = userSession.getLoggedUser();
    }

    /**
     * Method used to deposit, withdraw or transfer money to or from an account
     *
     * @param amountOfMoney The amount of money to be deposited, withdrawn or transferred
     * @return Useful information for the user
     */
    public String newTransaction(double amountOfMoney) {
        if (verifyTransaction(new Transaction(amountOfMoney, account.getBalance(), 0))) {
            try {
                json.saveToJson(this.account, "Accounts/accounts.json");
                return "Transaction successful";
            } catch (IOException | ParseException e) {
                e.printStackTrace();
                return "Something went wrong";
            }
        } else {
            account.setBalance(this.oldBalance);
            return "Something went wrong";
        }
    }

    /**
     * Method used to verify the proper execution of a transaction
     *
     * @param transaction The to be checked transaction
     * @return true if the transaction was successful
     */
    private boolean verifyTransaction(Transaction transaction) {
        this.oldBalance = transaction.getBalanceTwo();
        account.setBalance(
                transaction.getBalanceTwo() + transaction.getBalanceOne());

        return account.getBalance() - transaction.getBalanceOne() == this.oldBalance;
    }
}
