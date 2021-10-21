package ch.zeiter.marvin.functions;

import ch.zeiter.marvin.other.RegisteredAccounts;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdminAction {

    private final Scanner scanner;

    private final Json json;
    private RegisteredAccounts registeredAccounts;

    public AdminAction(Scanner scanner, RegisteredAccounts registeredAccounts) {
        this.scanner = scanner;
        this.json = new Json();
        this.registeredAccounts = registeredAccounts;
    }

    public void approveAccount() {
        System.out.println("\nAll accounts waiting for approval\n");

        ListAccountsForApproval lafa = new ListAccountsForApproval();
        lafa.listAll();

        System.out.println("""
                \nEnter index to approve single account
                Enter 'all' to approve every account
                Enter nothing to approve no account""");
        String approveIndex = this.scanner.nextLine();
    }

    public void createAccount() {
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

    public void viewStats() {
        try {
            int accountsRegistered = this.json.getFromJson("Accounts/accounts.json").size();
            int accountsUnregistered = this.json.getFromJson("Accounts/registeredAccounts.json").size();
            System.out.printf("\nTotal count of accounts registered:\t %d\n" +
                            "Total count of accounts unregistered:\t %d\n\n",
                    accountsRegistered, accountsUnregistered);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
