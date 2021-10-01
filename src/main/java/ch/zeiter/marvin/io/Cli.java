package ch.zeiter.marvin.io;

import ch.zeiter.marvin.Blueprints.Transaction;
import ch.zeiter.marvin.functions.TransactionHandler;
import ch.zeiter.marvin.other.RegisteredAccounts;
import ch.zeiter.marvin.other.UserChoice;

import java.util.Scanner;

public class Cli {

    private RegisteredAccounts registeredAccounts;
    private TransactionHandler transactionHandler;

    private final Scanner scanner;

    private boolean isLogged; // ! Change to singleton session
    private UserChoice choice;


    public Cli() {
        this.registeredAccounts = new RegisteredAccounts();
        this.scanner = new Scanner(System.in);
        this.isLogged = false;
    }

    public void init(String bankName) {
         TODO Change system for an admin
        while (true) {
            int modifier;
            if (!this.isLogged) {
                System.out.printf("-----%s-----\n" +
                        "[0] Login\n" +
                        "[1] Register\n", bankName);


            } else if (this.isLogged) {
                System.out.printf("----%s----\n" +
                        "[2] Withdraw\n" +
                        "[3] Deposit\n" +
                        "[4] Transfer\n" +
                        "[5] Log out\n", bankName);
            }

            this.choice = UserChoice.values()[
                    Integer.parseInt(this.scanner.nextLine())];

            switch (this.choice) {
                case LOGIN -> login();
                case REGISTER -> register();
                case WITHDRAW -> withdraw();
                case DEPOSIT -> deposit();
                case TRANSFER -> transfer();
                case LOGOUT -> logout();
            }
        }
    }

    private void login() {
        int accessCounter = 5;

        while (true) {
            System.out.println("\n-----LOGIN-----\nEnter IBAN");
            String inputIban = this.scanner.nextLine();

            System.out.println("Enter password");
            String inputPassword = this.scanner.nextLine();

            //TODO Change to accountpw from json
            if (inputIban.equals("5") && inputPassword.equals("5")) {
                this.isLogged = true;
                break;
            } else if (accessCounter <= 0) {
                System.exit(0);
            } else {
                System.out.println("\nEither IBAN or password is wrong\n");
                accessCounter--;
                System.out.printf("%d tries left\n", accessCounter);
            }
        }
    }

    private void register() {
        System.out.println("\n-----REGISTER-----\n" +
                "By proceeding you agree with\n" +
                "our terms and agreements\n\n" +
                "Write 'continue', to continue");

        String termsAndAgreementAccepted = this.scanner.nextLine();
        if (!termsAndAgreementAccepted.equals("continue"))
            System.exit(0); // TODO Change this to something different

        System.out.println("Please choose a password\n" +
                "1. 8-32 characters\n" +
                "2. Must at least have:\n\t" +
                "i.   One letter\n\t" +
                "ii.  One Number\n\t" +
                "iii. One special character");

        while (true) {
            System.out.print("Password: ");
            String inputPassword = this.scanner.nextLine();

            if (inputPassword.contains("")) {//TODO add sum regex
                System.out.println(this.registeredAccounts.
                        addRegisteredAccount(inputPassword));
                break;
            } else {
                System.out.println("Enter valid password\n");
            }
        }
    }

    public void withdraw() {
        System.out.println("\n----WITHDRAW----\n" +
                "Enter amount of money to be withdrawn");
        double withdrawAmount = Double.parseDouble(this.scanner.nextLine());

        transactionHandler = new TransactionHandler();
        System.out.println(transactionHandler.newTransaction((-1) * withdrawAmount));
    }

    private void deposit() {
        System.out.println("\n----DEPOSIT----\n" +
                "Enter amount of money to be deposited");
        double depositAmount = Double.parseDouble(this.scanner.nextLine());

        transactionHandler = new TransactionHandler();
        System.out.println(transactionHandler.newTransaction(depositAmount));
    }

    private void transfer() {

    }

    private void logout() {
        // * Destroy singleton here
    }
}
