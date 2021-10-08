package ch.zeiter.marvin.io;

import ch.zeiter.marvin.Blueprints.Account;
import ch.zeiter.marvin.functions.Login;
import ch.zeiter.marvin.functions.TransactionHandler;
import ch.zeiter.marvin.other.RegisteredAccounts;
import ch.zeiter.marvin.other.UserChoice;
import ch.zeiter.marvin.other.UserSession;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cli {

    private final RegisteredAccounts registeredAccounts;
    private TransactionHandler transactionHandler;

    private final Scanner scanner;

    private UserChoice choice;
    private UserSession userSession;


    /**
     * The constructor
     */
    public Cli() {
        this.registeredAccounts = new RegisteredAccounts();
        this.scanner = new Scanner(System.in);
        this.userSession = null;
    }

    /**
     * Method used to start CLI interface process for the user
     *
     * @param bankName The applications name
     */
    public void init(String bankName) {
        //   TODO Change system for an admin
        while (true) {
            try {
                int modifier;
                if (this.userSession == null)
                    loginPage(bankName);
                else
                    cliPage(bankName);
            } catch (Exception e) {
                System.out.println("Enter valid stuff" + e);
            }
        }
    }

    /**
     * The login / register page process
     *
     * @param bankName The applications name
     * @throws Exception Thrown when user enters invalid choice
     */
    private void loginPage(String bankName) throws Exception {
        System.out.printf("-----%s-----\n" +
                "[0] Login\n" +
                "[1] Register\n", bankName);

        this.choice = UserChoice.values()[
                Integer.parseInt(this.scanner.nextLine())];

        switch (this.choice) {
            case LOGIN -> login();
            case REGISTER -> register();
            default -> throw new Exception();
        }
    }

    /**
     * The CLI interface process
     *
     * @param bankName The applications name
     * @throws Exception Thrown when user enters invalid choice
     */
    private void cliPage(String bankName) throws Exception {
        System.out.printf("----%s----\n" +
                "[0] Withdraw\n" +
                "[1] Deposit\n" +
                "[2] Transfer\n" +
                "[3] Log out\n", bankName);

        this.choice = UserChoice.values()[
                Integer.parseInt(this.scanner.nextLine())];

        switch (this.choice) {
            case WITHDRAW -> withdraw();
            case DEPOSIT -> deposit();
            case TRANSFER -> transfer();
            case LOGOUT -> logout();
            default -> throw new Exception();
        }
    }

    private void login() {
        Login login = new Login();
        int accessCounter = 5;

        while (true) {
            System.out.println("\n-----LOGIN-----\nEnter IBAN");
            String inputIban = this.scanner.nextLine();

            System.out.println("Enter password");
            String inputPassword = this.scanner.nextLine();

            Account account = login.loginCheck(inputIban, inputPassword);

            if (account != null) {
                this.userSession = UserSession.session(account);
                login = null; // ? "Deletes" login object, as it's not used anymore
                break;
            } else if (accessCounter <= 0) {
                System.exit(0); // ? Exits program
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
            System.exit(0);


        System.out.println("Please choose a password\n" +
                "1. 8-32 characters\n" +
                "2. Must at least have:\n\t" +
                "i.   One letter (UPPER & lower)\n\t" +
                "ii.  One Number\n\t" +
                "iii. One special character");

        Pattern pattern = Pattern.compile(
                "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
        Matcher matcher;

        while (true) {
            System.out.print("Password: ");
            String inputPassword = this.scanner.nextLine();

            matcher = pattern.matcher(inputPassword);

            if (matcher.matches()) {
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
