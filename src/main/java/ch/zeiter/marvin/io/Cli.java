package ch.zeiter.marvin.io;

import ch.zeiter.marvin.blueprints.Account;
import ch.zeiter.marvin.functions.*;
import ch.zeiter.marvin.other.RegisteredAccounts;
import ch.zeiter.marvin.other.UserSession;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Deprecated // This is the CLI page of the whole application
public class Cli {


    private final Scanner scanner;

    private boolean awaitingApproval;
    private int choice;

    //    private UserChoice choice;
    private UserSession userSession;
    private final RegisteredAccounts registeredAccounts;
    private final JsonActions jsonActions;

    private final Pattern pattern;

    /**
     * The constructor
     */
    public Cli() {
        this.scanner = new Scanner(System.in);
        this.awaitingApproval = false;
        this.userSession = null;
        this.registeredAccounts = new RegisteredAccounts();
        jsonActions = new JsonActions();
        this.pattern = Pattern.compile(
                "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
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
                else if (this.userSession.getLoggedUser().isAdmin())
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
     * The CLI interface of Main.fxml
     *
     * @param bankName The applications name
     * @throws Exception Thrown when user enters invalid choice
     */
    private void cliPageUser(String bankName) throws Exception {
        UserAction userAction = new UserAction(
                this.userSession, this.scanner);
        AccountHandler accountHandler = new AccountHandler(
                this.scanner, this.userSession, this.jsonActions);

        System.out.printf("----%s----\n" +
                "[0] View balance\n" +
                "[1] Withdraw\n" +
                "[2] Deposit\n" +
                "[3] Transfer\n" +
                "[4] Update password\n" +
                "[5] Log out\n" +
                "[6] Delete account\n", bankName);

        this.choice = Integer.parseInt(this.scanner.nextLine()); /*UserChoice.values()[
                Integer.parseInt(this.scanner.nextLine()) - 2];*/

        switch (this.choice) {
            case 0 -> userAction.balance();
            case 1 -> userAction.withdraw();
            case 2 -> userAction.deposit();
            case 3 -> userAction.transfer();
            case 4 -> accountHandler.updatePasswordConfirmation(this.pattern);
            case 5 -> LogoutService.logout("You have been logged out");
            case 6 -> LogoutService.logout(accountHandler.deleteAccountConfirmation( // * The ? is showing off my knowledge once again
                    this.userSession) ? "Your account has been deleted" : "");
            default -> throw new Exception();
        }
        userAction = null;
        accountHandler = null;
    }

    /**
     * The CLI interface process
     *
     * @param bankName The applications name
     * @throws Exception Thrown when user enters invalid choice
     */
    private void cliPageAdmin(String bankName) throws Exception {
        AdminAction adminAction = new AdminAction(
                this.scanner, this.registeredAccounts);
        AccountHandler accountHandler = new AccountHandler(
                this.scanner, this.userSession, this.jsonActions);

        System.out.printf("----%s----\n" +
                "[0] Create account\n" +
                "[1] Approve account\n" +
                "[2] View statistics\n" +
                "[3] Update password\n" +
                "[4] Log out\n" +
                "[5] Delete account\n", bankName);

        this.choice = Integer.parseInt(this.scanner.nextLine()); /*UserChoice.values()[
                Integer.parseInt(this.scanner.nextLine())];*/

        switch (this.choice) {
            case 0 -> adminAction.createAccount();
            case 1 -> adminAction.approveAccount();
            case 2 -> adminAction.viewStats();
            case 3 -> accountHandler.updatePasswordConfirmation(this.pattern);
            case 4 -> LogoutService.logout("You have been logged out");
            case 5 -> LogoutService.logout(accountHandler.deleteAccountConfirmation(
                    this.userSession) ? "Your account has been deleted" : "");
            default -> throw new Exception();
        }
        adminAction = null;
        accountHandler = null;
    }

    private void login() {
        Login login = new Login(this.jsonActions);
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
                    login = null;
                }
                break;
            } else if (accessCounter <= 0) {
                System.out.println("Too many attempts, try again later");
                System.exit(0);
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

            System.out.println("""
                    Please choose a password
                    1. 8-32 characters
                    2. Must at least have:
                    \ti.   One letter (UPPER & lower)
                    \tii.  One Number
                    \tiii. One special character""");

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
}
