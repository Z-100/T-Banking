package ch.zeiter.marvin.io;

import ch.zeiter.marvin.Blueprints.Account;
import ch.zeiter.marvin.functions.Json;
import ch.zeiter.marvin.functions.ListAccountsForApproval;
import ch.zeiter.marvin.functions.Login;
import ch.zeiter.marvin.functions.TransactionHandler;
import ch.zeiter.marvin.other.RegisteredAccounts;
import ch.zeiter.marvin.other.UserSession;
import lombok.Getter;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cli {

    private final RegisteredAccounts registeredAccounts;
    private TransactionHandler transactionHandler;
    private final Json json;

    private final Scanner scanner;

//    private UserChoice choice;
    private int choice;
    @Getter
    private UserSession userSession;

    private boolean awaitingApproval;


    /**
     * The constructor
     */
    public Cli() {
        this.registeredAccounts = new RegisteredAccounts();
        this.json = new Json();
        this.scanner = new Scanner(System.in);
        this.userSession = null;
        this.awaitingApproval = false;
    }

    /**
     * Method used to start CLI interface process for the user
     *
     * @param bankName The applications name
     */
    public void init(String bankName) {
        while (true) {
            try {
                int modifier;
                if (this.userSession == null)
                    loginPage(bankName);
                else
                    if (this.userSession.getLoggedUser().isAdmin())
                        cliPageAdmin(bankName);
                    else
                        cliPageUser(bankName);
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

        this.choice = Integer.parseInt(this.scanner.nextLine()); /* UserChoice.values()[
                Integer.parseInt(this.scanner.nextLine())];*/

        switch (this.choice) {
            case 0 -> login();
            case 1 -> register();
            default -> throw new Exception();
        }
    }

    /**
     * The CLI interface process
     *
     * @param bankName The applications name
     * @throws Exception Thrown when user enters invalid choice
     */
    private void cliPageUser(String bankName) throws Exception {
        System.out.printf("----%s----\n" +
                "[0] Withdraw\n" +
                "[1] Deposit\n" +
                "[2] Transfer\n" +
                "[3] Log out\n", bankName);

        this.choice = Integer.parseInt(this.scanner.nextLine()); /*UserChoice.values()[
                Integer.parseInt(this.scanner.nextLine()) - 2];*/

        switch (this.choice) {
            case 0 -> withdraw();
            case 1 -> deposit();
            case 2 -> transfer();
            case 3 -> logout();
            default -> throw new Exception();
        }
    }

    /**
     * The CLI interface process
     *
     * @param bankName The applications name
     * @throws Exception Thrown when user enters invalid choice
     */
    private void cliPageAdmin(String bankName) throws Exception {
        System.out.printf("----%s----\n" +
                "[0] Create account\n" +
                "[1] Approve account\n" +
                "[2] View statistics\n" +
                "[3] Update password\n" +
                "[4] Log out\n" +
                "[5] Delete account\n", bankName);

        this.choice = Integer.parseInt(this.scanner.nextLine()); /*UserChoice.values()[
                Integer.parseInt(this.scanner.nextLine())];*/

        // TODO make it work
        switch (this.choice) {
            case 0 -> createAccount();
            case 1 -> approveAccount();
            case 2 -> viewStats();
            case 3 -> updatePassword();
            case 4 -> logout();
            case 5 -> deleteAccount();
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
                if (account.isApproved()) {
                    this.userSession = UserSession.session(account);
                    login = null; // ? "Deletes" login object, as it's not used anymore
                }
                break;
            } else if (accessCounter <= 0) {
                System.out.println("Too many attempts, try again later");
                System.exit(0); // ? Exits program
            } else {
                System.out.println("\nEither IBAN or password is wrong\n");
                accessCounter--;
                System.out.printf("%d tries left\n", accessCounter);
            }
        }
    }

    private void register() {
        if (this.awaitingApproval)
            System.out.println("Please wait until your account has been approved");
        else {
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
                        addRegisteredAccount(inputPassword, "Accounts/registeredAccounts.json"));
                this.awaitingApproval = true;
                break;
                } else {
                    System.out.println("Enter valid password\n");
                }
            }
        }
    }

    private void balance() {
        System.out.printf("\n----BALANCE----\n" +
                "Your current account balance is:\n" +
                "%s", this.userSession.getLoggedUser().getBalance());
    }

    private void withdraw() {
        System.out.println("\n----WITHDRAW----\n" +
                "Enter amount of money to be withdrawn");
        double withdrawAmount = Double.parseDouble(this.scanner.nextLine());

        transactionHandler = new TransactionHandler(this.userSession);
        System.out.println(transactionHandler.newTransaction((-1) * withdrawAmount));
        transactionHandler = null;
    }

    private void deposit() {
        System.out.println("\n----DEPOSIT----\n" +
                "Enter amount of money to be deposited");
        double depositAmount = Double.parseDouble(this.scanner.nextLine());

        transactionHandler = new TransactionHandler(this.userSession);
        System.out.println(transactionHandler.newTransaction(depositAmount));
        transactionHandler = null;
    }

    private void transfer() {

    }

    private void logout() {
        this.userSession = null;
        System.out.println("You have been logged out");
        System.exit(0);
    }

    private void approveAccount() {
        System.out.println("\nAll accounts waiting for approval\n");

        ListAccountsForApproval lafa = new ListAccountsForApproval();
        lafa.listAll();

        System.out.println("""
                \nEnter index to approve single account\n
                Enter 'all' to approve every account\n
                Enter nothing to approve no account""");
        String approveIndex = this.scanner.nextLine();

    }

    private void deleteAccount() {

    }

    private void updatePassword() {

    }

    private void createAccount() {
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
                System.out.println(this.registeredAccounts.addRegisteredAccount(inputPassword, "Accounts/accounts.json"));
                break;
            } else {
                System.out.println("Enter valid password\n");
            }
        }
    }

    private void viewStats() {
        try {
            int accountsRegistered = this.json.getFromJson("Accounts/accounts.json").size();
            int accountsUnregistered = this.json.getFromJson("Accounts/registeredAccounts.json").size();
            System.out.printf("\nTotal count of accounts registered:\t %d\n" +
                    "Total count of accounts unregistered:\t %d\n\n",
                    accountsRegistered, accountsUnregistered);

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
