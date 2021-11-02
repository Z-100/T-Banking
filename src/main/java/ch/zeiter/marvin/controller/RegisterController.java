package ch.zeiter.marvin.controller;

import ch.zeiter.marvin.other.RegisteredAccount;
import ch.zeiter.marvin.other.Stages;
import ch.zeiter.marvin.other.UserSession;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Controller for the Register.fxml file
 */
public class RegisterController {

    @FXML
    private PasswordField createPasswordField;
    @FXML
    private Button registerButton;
    @FXML
    private Label loginInsteadButton;
    @FXML
    private Label errorLabel;

    private final RegisteredAccount registeredAccount;
    private Stage primaryStage;

    private boolean awaitingApproval;
    public static final Pattern pattern = Pattern.compile(
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");

    /**
     * The constructor
     */
    public RegisterController() {
        this.registeredAccount = new RegisteredAccount();
        this.awaitingApproval = false;
    }

    /**
     * Method executed when the controller is being loaded
     *
     * @param primaryStage The given JavaFX:Stage
     * @param userSession The given session created by login
     * @param stages the stages object to change between stages
     */
    public void init(Stage primaryStage, UserSession userSession, Stages stages) {
        this.primaryStage = primaryStage;

        registerButton.setOnAction(actionEvent ->
                passwordCheck(createPasswordField.getText()));

        loginInsteadButton.setOnMouseClicked(mouseEvent ->
                stages.changeStage(this.primaryStage, userSession, "Login"));
    }

    /**
     * Method used to send the password to the backend
     * which will then validate it
     *
     * @param password The entered password
     */
    private void passwordCheck(String password) {
        Matcher matcher = pattern.matcher(password);
        while (!this.awaitingApproval) {
            if (matcher.matches()) {
                String s = this.registeredAccount.addRegisteredAccount(password, "Accounts/registeredAccounts.json");
                System.out.println(s);
                this.awaitingApproval = true;
                errorLabel.setVisible(false);
            } else {
                errorLabel.setVisible(true);
                errorLabel.setText("Please enter valid password");
            }
        }
        registerButton.setDisable(true);
        createPasswordField.setEditable(false);
        errorLabel.setText("Your account is awaiting approval");
    }
}
