package ch.zeiter.marvin.functions;

import ch.zeiter.marvin.other.UserSession;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Scanner;

public class AccountHandler {

    private final Scanner scanner;
    private final JsonActions jsonActions;

    public AccountHandler(Scanner scanner, JsonActions jsonActions) {
        this.scanner = scanner;
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

    public void updatePassword(UserSession userSession) {

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
}
