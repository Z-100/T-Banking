package ch.zeiter.marvin.functions;

import ch.zeiter.marvin.blueprints.Account;
import ch.zeiter.marvin.blueprints.Transaction;
import ch.zeiter.marvin.other.UserSession;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.function.Function;

public class TransactionHandler {

    private final JsonActions jsonActions;
    private final Account account;

    private double oldBalance;

    /**
     * The constructor
     */
    public TransactionHandler(UserSession userSession) {
        this.jsonActions = new JsonActions();
        this.account = userSession.getLoggedUser();
    }

    /**
     * Method used to deposit, withdraw or transfer money to or from an account
     *
     * @param amountOfMoney The amount of money to be deposited, withdrawn or transferred
     * @return Useful information for the user
     */
    public String newTransaction(double amountOfMoney) {
        Function<Transaction, Boolean> verifyTransaction = transaction -> {
            this.oldBalance = transaction.getBalanceTwo();
            this.account.setBalance(
                    transaction.getBalanceTwo() + transaction.getBalanceOne());

            return account.getBalance() - transaction.getBalanceOne() == this.oldBalance;
        };

        Boolean transactionSuccess = verifyTransaction.apply(
                new Transaction(amountOfMoney, account.getBalance(), 0));


        if (transactionSuccess) {
            try {
                jsonActions.saveToJson(this.account, "Accounts/accounts.json", "idkICanTypeWhateverIWant");
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
}
