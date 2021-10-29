package ch.zeiter.marvin.controller;

import ch.zeiter.marvin.io.AccountHandler;
import ch.zeiter.marvin.functions.JsonActions;
import ch.zeiter.marvin.other.Stages;
import ch.zeiter.marvin.other.UserSession;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.util.Stack;

public class DeleteController {

    @FXML
    private Label deleteAccountTextLabel;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button goBackButton;
    @FXML
    private Button deleteAccountConfirmButton;

    private Stage primaryStage;
    private JsonActions jsonActions;

    private final Stack<String> really = new Stack<>();

    private int i = 0;

    public void init(Stage primaryStage, UserSession userSession, Stages stages) {
        this.jsonActions = new JsonActions();
        this.primaryStage = primaryStage;

        deleteAccountTextLabel.setText("You're about to\ndelete your account");

        really.push("Enter pw to confirm");
        really.push("It will be gone forever");
        really.push("Like actually?");
        really.push("U wanna del ur account?");
        really.push("U rly wanna do this?");

        goBackButton.setOnAction(actionEvent ->
                stages.changeStage(this.primaryStage, userSession, "Main"));

        deleteAccountConfirmButton.setOnAction(actionEvent -> {
            deleteAccountTextLabel.setText(really.peek() + "\nIf so, press confirm");
            really.pop();

            i++;
            if (i > 3)
                passwordField.setVisible(true);


            if (i > 4) {
                if (deleteAccountConfirmation(userSession)) {
                    stages.changeStage(this.primaryStage, null, "Login");
                } else
                    stages.changeStage(this.primaryStage, userSession, "Main");
            }
        });
    }

    public boolean deleteAccountConfirmation(UserSession userSession) {
        if (passwordField.getText().equals(userSession.getLoggedUser().getPassword())) {
            AccountHandler accountHandler = new AccountHandler(null, userSession, jsonActions);
            return (accountHandler.deleteAccount(userSession));
        }
        return false;
    }
}
