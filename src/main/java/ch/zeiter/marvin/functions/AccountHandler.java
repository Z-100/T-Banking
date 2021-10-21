package ch.zeiter.marvin.functions;

import ch.zeiter.marvin.other.UserSession;

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

            updatePassword();
        }
    }

    private void updatePassword() {
        try {
            jsonActions.saveToJson(userSession.getLoggedUser(),
                    "Accounts/accounts.json", "changeUser");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
