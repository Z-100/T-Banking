package ch.zeiter.marvin.io;

import ch.zeiter.marvin.functions.TransactionHandler;
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
        System.out.println(transactionHandler.newTransaction((-1) * withdrawAmount, null));

        transactionHandler = null;
    }

    public void deposit() {
        System.out.println("\n----DEPOSIT----\n" +
                "Enter amount of money to be deposited");
        double depositAmount = Double.parseDouble(this.scanner.nextLine());

        transactionHandler = new TransactionHandler(this.userSession);
        System.out.println(transactionHandler.newTransaction(depositAmount, null));

        transactionHandler = null;
    }

    public void transfer() {
        System.out.println("\n----TRANSFER----\n"
                + "Enter iBan of the account to be transferred to");
        String transferIBan = String.valueOf(this.scanner.nextLine());

        System.out.println("\nEnter amount of money to be transferred");
        double transferAmount = Double.parseDouble(this.scanner.nextLine());

        transactionHandler = new TransactionHandler(this.userSession);
        System.out.println(transactionHandler.newTransaction(transferAmount, transferIBan));

        transactionHandler = null;
    }
}
