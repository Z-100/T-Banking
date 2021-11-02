package ch.zeiter.marvin.io;

import ch.zeiter.marvin.functions.TransactionHandler;
import ch.zeiter.marvin.other.UserSession;

import java.util.Scanner;

/**
 * CLI interface for 'normal'-user actions
 */
public class UserAction {

    private final UserSession userSession;
    private final Scanner scanner;

    private TransactionHandler transactionHandler;

    /**
     * The constructor
     *
     * @param userSession The given session
     * @param scanner An already existing scanner
     */
    public UserAction(UserSession userSession, Scanner scanner) {
        this.userSession = userSession;
        this.scanner = scanner;
    }

    /**
     * CLI for viewing balance screen
     */
    public void balance() {
        System.out.printf("\n----BALANCE----\n" +
                "Your current account balance is:\n" +
                "%s", this.userSession.getLoggedUser().getBalance());
    }

    /**
     * CLI for withdrawal screen
     */
    public void withdraw() {
        System.out.println("\n----WITHDRAW----\n" +
                "Enter amount of money to be withdrawn");
        double withdrawAmount = Double.parseDouble(this.scanner.nextLine());

        transactionHandler = new TransactionHandler(this.userSession);
        System.out.println(transactionHandler.newTransaction((-1) * withdrawAmount, null));

        transactionHandler = null;
    }

    /**
     * CLI for deposit screen
     */
    public void deposit() {
        System.out.println("\n----DEPOSIT----\n" +
                "Enter amount of money to be deposited");
        double depositAmount = Double.parseDouble(this.scanner.nextLine());

        transactionHandler = new TransactionHandler(this.userSession);
        System.out.println(transactionHandler.newTransaction(depositAmount, null));

        transactionHandler = null;
    }

    /**
     * CLI for transferal screen
     */
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
