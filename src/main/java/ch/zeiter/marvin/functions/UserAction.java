package ch.zeiter.marvin.functions;

import ch.zeiter.marvin.other.UserSession;

import java.util.Scanner;

public class UserAction {

    private final UserSession userSession;
    private final Scanner scanner;

    private TransactionHandler transactionHandler;

    public UserAction(UserSession userSession, Scanner scanner) {
        this.userSession = userSession;
        this.scanner = scanner;
    }

    public void balance() {
        System.out.printf("\n----BALANCE----\n" +
                "Your current account balance is:\n" +
                "%s", this.userSession.getLoggedUser().getBalance());
    }

    public void withdraw() {
        System.out.println("\n----WITHDRAW----\n" +
                "Enter amount of money to be withdrawn");
        double withdrawAmount = Double.parseDouble(this.scanner.nextLine());

        transactionHandler = new TransactionHandler(this.userSession);
        System.out.println(transactionHandler.newTransaction((-1) * withdrawAmount));
        transactionHandler = null;
    }

    public void deposit() {
        System.out.println("\n----DEPOSIT----\n" +
                "Enter amount of money to be deposited");
        double depositAmount = Double.parseDouble(this.scanner.nextLine());

        transactionHandler = new TransactionHandler(this.userSession);
        System.out.println(transactionHandler.newTransaction(depositAmount));
        transactionHandler = null;
    }

    public void transfer() {

    }
}
