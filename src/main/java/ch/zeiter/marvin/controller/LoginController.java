package ch.zeiter.marvin.controller;

import ch.zeiter.marvin.blueprints.Account;
import ch.zeiter.marvin.functions.JsonActions;
import ch.zeiter.marvin.functions.Login;
import ch.zeiter.marvin.other.Stages;
import ch.zeiter.marvin.other.UserSession;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * The FX-Controller of the Login screen
 */
public class LoginController {

	@FXML private TextField usernameField;
	@FXML private PasswordField passwordField;
	@FXML private Button loginButton;
	@FXML private Label registerInsteadButton;
	@FXML private Label errorLabel;

	private UserSession userSession;

	private double accessCounter = 5;

	private Stage primaryStage;

	/**
	 * The constructor
	 */
	public LoginController() {
		this.userSession = null;
	}

	/**
	 * Method used to initialize the Login page
	 * and send the entered login information
	 * to the backend
	 *
	 * @param primaryStage The stage used by the Application
	 * @param stages The given stages object to change in between stages
	 */
	public void init(Stage primaryStage, UserSession userSession, Stages stages) {
		this.primaryStage = primaryStage;

		if (userSession != null) {
			this.accessCounter = userSession.getLoggedUser().getBalance();
			errorLabel.setText(String.format(
					"Nice try, but no. It doesn't work like that.\n%1.0f tries left", accessCounter));
		}

		loginButton.setOnAction(actionEvent -> {
			if (loginCheck(usernameField.getText(), passwordField.getText()))
				stages.changeStage(primaryStage, this.userSession, "Main");
		});

		registerInsteadButton.setOnMouseClicked(mouseEvent -> stages.changeStage(this.primaryStage,
				new UserSession(new Account(
						null, null, null, accessCounter, false, false)),
				"Register"));
	}

	/**
	 * Method used to send the entered login information to
	 * the backend and validate the login process in the frontend
	 *
	 * @param iBan The entered iBan to be validated
	 * @param password The entered password to be validated
	 * @return true if the login worked
	 */
	private boolean loginCheck(String iBan, String password) {
		Login login = new Login();

		Account account = login.loginCheck(iBan, password);

		if (account != null) {
			if (account.isApproved()) {
				this.userSession = UserSession.session(account);
				login = null;
				System.out.printf("[INFO]\tUserSession created with UUID {%s}%n",
						this.userSession.getLoggedUser().getUuid());
				return true;
			}
		} else if (accessCounter <= 0) {
			errorLabel.setText("Too many attempts, try again later");
			usernameField.setEditable(false);
			passwordField.setEditable(false);
			loginButton.cancelButtonProperty();
		} else {
			errorLabel.setText(String.format("Either IBAN or password is wrong.\n%1.1f tries left", accessCounter));
			accessCounter--;
		}
		return false;
	}
}
