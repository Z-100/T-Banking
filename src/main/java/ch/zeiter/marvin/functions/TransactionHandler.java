package ch.zeiter.marvin.functions;

import ch.zeiter.marvin.Blueprints.Transaction;

public class TransactionHandler {

    private Transaction transaction;

    public TransactionHandler(Transaction transaction) {
        this.transaction = transaction;
    }

    public boolean verifyTransaction() {
        return true;
    }

}
