package ch.zeiter.marvin.functions;

import ch.zeiter.marvin.other.UserSession;

import org.json.simple.parser.ParseException;

import java.io.IOException;

import java.util.Scanner;

public class AccountHandler {

    private final Scanner scanner;
    private final UserSession userSession;
    private final JsonActions jsonActions;

    public AccountHandler(Scanner scanner, UserSession userSession, JsonActions jsonActions) {
        this.scanner = scanner;
        this.userSession = userSession;
        this.jsonActions = jsonActions;
    }

    public boolean deleteAccountConfirmation(UserSession userSession) {
        String[] really = new String[]{
                "\nDo you really wanna do this?",
                "You wanna delete your account?",
                "Like actually? It will be gone forever"};

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

    private boolean deleteAccount(UserSession userSession) {
        try {
            jsonActions.saveToJson(userSession.getLoggedUser(),
                    "Accounts/accounts.json", "deleteUser");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
                           
    public void updatePasswordConfirmation() {
        System.out.println("""
                Enter your current password""");
        String currentPassword = scanner.nextLine();

        if (currentPassword.equals(
                this.userSession.getLoggedUser().getPassword())) {

            System.out.println("""
                    Enter new password""");
            String newPassword = scanner.nextLine();
            System.out.println("""
                    Repeat new password""");
            String newPasswordConfirm = scanner.nextLine();

            if (newPassword.equals(currentPassword)) {
                System.out.println("New password cannot be old password");
                return;
            } else if (!newPassword.equals(newPasswordConfirm)) {
                System.out.println("Passwords entered did not match");
                return;
            }
            updatePassword(newPassword);
        }
    }

    private void updatePassword(String newPassword) {
        try {
            userSession.getLoggedUser().setPassword(newPassword);
            jsonActions.saveToJson(userSession.getLoggedUser(),
                    "Accounts/accounts.json", "changeUser");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
