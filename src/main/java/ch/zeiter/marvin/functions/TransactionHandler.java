package ch.zeiter.marvin.functions;

import ch.zeiter.marvin.blueprints.Account;
import ch.zeiter.marvin.blueprints.Transaction;
import ch.zeiter.marvin.other.UserSession;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;

public class TransactionHandler {

    private final JsonActions jsonActions;
    private Account account = null;
    private Account transferAccount = null;

    private double oldBalance;
    private double oldBalanceT;

    /**
     * The constructor
     */
    public TransactionHandler(UserSession userSession) {
        this.jsonActions = new JsonActions();
        this.account = userSession.getLoggedUser();
    }

    /**
     * Method used to execute account modification or transfer actions
     *
     * @param amountOfMoney The amount of money to be deposited, withdrawn or transferred
     * @param iBan (If used) The iBan of the account to be transferred to.
     * @return Useful information for the user
     */
    public String newTransaction(double amountOfMoney, String iBan) {
        if (iBan != null)
            return transfer(amountOfMoney, iBan);
        else
            return accountModification(amountOfMoney);
    }

    private final Function<Transaction, Boolean> verifyTransaction = transaction -> {
        this.oldBalance = account.getBalance();
        this.account.setBalance(
                account.getBalance() + transaction.getTransferAmount());

        Boolean success = account.getBalance() - transaction.getTransferAmount() == this.oldBalance;

        if (success && this.transferAccount != null) {
            this.oldBalanceT = this.transferAccount.getBalance();
            this.transferAccount.setBalance(
                    transferAccount.getBalance() - transaction.getTransferAmount());

            success = transferAccount.getBalance() + transaction.getTransferAmount() == this.oldBalanceT;
        }

        return success;
    };

    private String transfer(double amountOfMoney, String iBan) {
        try {
            List<Account> list = jsonActions.getFromJson("Accounts/accounts.json")
                    .stream()
                    .filter(acc -> acc.getIban().equals(iBan))
                    .toList();
            this.transferAccount = list.get(0);
        } catch (IOException | ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            return "Something went wrong";
        }

        return saveActions(verifyTransaction
                .apply(new Transaction(iBan, (-1) * amountOfMoney)));
    }

    private String accountModification(double amountOfMoney) {
        return saveActions(verifyTransaction
                .apply(new Transaction(null, amountOfMoney)));
    }

    /**
     * Method used to save the account(s) to the jsonfile
     *
     * @param success used to pre-verify the transaction
     * @return Useful information for the user
     */
    private String saveActions(boolean success) {
        if (success) {
            try {
                jsonActions.getFromJson("Accounts/accounts.json");
                jsonActions.saveToJson(this.account, "Accounts/accounts.json", "ICanTypeWhatEverIWant");
                if (this.transferAccount != null)
                    jsonActions.saveToJson(this.transferAccount, "Accounts/accounts.json", "github.com/z-100");
                return "Transaction successful";
            } catch (IOException e) {
                e.printStackTrace();
                return "Something went wrong";
            }
        } else {
            account.setBalance(this.oldBalance);
            if (this.transferAccount != null)
                transferAccount.setBalance(this.oldBalanceT);
            return "Something went wrong";
        }
    }
}
