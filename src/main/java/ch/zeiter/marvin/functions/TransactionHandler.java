package ch.zeiter.marvin.functions;

import ch.zeiter.marvin.Blueprints.Transaction;

public class TransactionHandler {

    public String newTransaction(double amountOfMoney) {
        if (verifyTransaction(new Transaction(amountOfMoney, /*Replace with balance of logged user*/ 0, 0)))
            return "Transaction successful";
        else
            return "Something went wrong";
    }

    private boolean verifyTransaction(Transaction transaction) {
        double oldBalance = transaction.getBalanceTwo();
        transaction.setBalanceTwo( /*Replace with logged user acc*/
                transaction.getBalanceTwo() + transaction.getBalanceOne());

        return transaction.getBalanceTwo() - transaction.getBalanceOne() == oldBalance;
    }

}
