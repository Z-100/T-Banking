package ch.zeiter.marvin.io;

import ch.zeiter.marvin.functions.JsonActions;
import ch.zeiter.marvin.functions.accountsAwaitingApproval;
import ch.zeiter.marvin.other.RegisteredAccount;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The CLI interface for the admin
 */
public class AdminAction {

    private final Scanner scanner;

    private final JsonActions jsonActions;
    private RegisteredAccount registeredAccount;

    /**
     * The constructor
     *
     * @param scanner The given scanner
     * @param registeredAccount The given RegisteredAccount object
     */
    public AdminAction(Scanner scanner, RegisteredAccount registeredAccount) {
        this.scanner = scanner;
        this.jsonActions = new JsonActions();
        this.registeredAccount = registeredAccount;
    }

    /**
     * CLI interface to approve unregistered accounts
     */
    public void approveAccount() {
        System.out.println("\nAll accounts waiting for approval");

        accountsAwaitingApproval lafa = new accountsAwaitingApproval();
        lafa.listAll();

        System.out.println("""
                
                Enter 'all' to approve every account
                Enter nothing to approve no account""");
        String approveIndex = this.scanner.nextLine();

        if (approveIndex.equals("all"))
            lafa.approveAll();
    }

    /**
     * CLI interface for account creation
     */
    public void createAccount() {
        System.out.println("""
                Please choose a password
                1. 8-32 characters
                2. Must at least have:
                \ti.   One letter (UPPER & lower)
                \tii.  One Number
                \tiii. One special character""");

        Pattern pattern = Pattern.compile(
                "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
        Matcher matcher;

        while (true) {
            System.out.print("Password: ");
            String inputPassword = this.scanner.nextLine();

            matcher = pattern.matcher(inputPassword);

            if (matcher.matches()) {
                System.out.println(this.registeredAccount.addRegisteredAccount(inputPassword, "Accounts/accounts.json"));
                break;
            } else {
                System.out.println("Enter valid password\n");
            }
        }
    }

    /**
     * CLI interface to view statistics
     */
    public void viewStats() {
        try {
            int accountsRegistered = this.jsonActions.getFromJson("Accounts/accounts.json").size();
            int accountsUnregistered = this.jsonActions.getFromJson("Accounts/registeredAccounts.json").size();

            System.out.printf("""
                            Total count of accounts registered:  \t %d
                            Total count of accounts unregistered:\t %d
                            """,
                    accountsRegistered, accountsUnregistered);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
