package ch.zeiter.marvin.io;

import ch.zeiter.marvin.functions.JsonActions;
import ch.zeiter.marvin.other.UserSession;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record AccountHandler(Scanner scanner, UserSession userSession, JsonActions jsonActions) {

    public boolean deleteAccountConfirmation() {
        String[] really = new String[] { "\nDo you really wanna do this?", "You wanna delete your account?",
                "Like actually? It will be gone forever" };

        System.out.println("""
                Enter your password to delete your account
                Enter nothing to keep your account""");
        String password = scanner.nextLine();

        if (password.equals(userSession.getLoggedUser().getPassword())) {
            for (String rly : really) {
                System.out.println(rly + "\nIf so, enter 'yes'");
                String yes = scanner.nextLine();

                if (!yes.equals("yes"))
                    return false;
            }
            return deleteAccount(userSession);
        }
        return false;
    }

    public boolean deleteAccount(UserSession userSession) {
        try {
            jsonActions.saveToJson(userSession.getLoggedUser(), "Accounts/accounts.json", "deleteUser");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void updatePasswordConfirmation(Pattern pattern) {
        System.out.println("Enter your current password");
        String currentPassword = scanner.nextLine();

        if (currentPassword.equals(this.userSession.getLoggedUser().getPassword())) {

            System.out.println("""
                    Please choose a password
                    1. 8-32 characters
                    2. Must at least have:
                    \ti.   One letter (UPPER & lower)
                    \tii.  One Number
                    \tiii. One special character""");

            System.out.println("Enter new password");
            String newPassword = scanner.nextLine();

            Matcher matcher = pattern.matcher(newPassword);

            if (newPassword.equals(currentPassword)) {
                System.out.println("New password cannot be old password");
                return;
            } else if (!matcher.matches()) {
                System.out.println("Enter valid password");
                return;
            }

            System.out.println("Repeat new password");
            String newPasswordConfirm = scanner.nextLine();

            if (!newPassword.equals(newPasswordConfirm)) {
                System.out.println("Passwords entered did not match");
                return;
            }
            updatePassword(newPassword);
        }
    }

    public void updatePassword(String newPassword) {
        try {
            userSession.getLoggedUser().setPassword(newPassword);
            jsonActions.saveToJson(userSession.getLoggedUser(), "Accounts/accounts.json", "changeUser");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
