package ch.zeiter.marvin.controller;

import ch.zeiter.marvin.other.RegisteredAccounts;
import ch.zeiter.marvin.other.Stages;
import ch.zeiter.marvin.other.UserSession;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterController {

	@FXML PasswordField createPasswordField;
//	@FXML Checkbox termsAndAgreementsCheckbox;
	@FXML Button registerButton;
	@FXML Label loginInsteadButton;
	@FXML Label errorLabel;

	private final RegisteredAccounts registeredAccounts;

	private boolean awaitingApproval;

	public static final Pattern pattern = Pattern.compile(
			"^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");;
	private Stage primaryStage;

	public RegisterController() {
		this.registeredAccounts = new RegisteredAccounts();
		this.awaitingApproval = false;
	}

	public void init(Stage primaryStage, UserSession userSession, Stages stages) {
		this.primaryStage = primaryStage;

//		termsAndAgreementsCheckbox.addItemListener(listener -> {
//			if (termsAndAgreementsCheckbox.getState())
//				registerButton.setDisable(false);
//		});

		registerButton.setOnAction(actionEvent ->
				passwordCheck(createPasswordField.getText()));

		loginInsteadButton.setOnMouseClicked(mouseEvent ->
				stages.changeStage(this.primaryStage, userSession, "Login"));
	}

	private void passwordCheck(String password) {
		Matcher matcher = pattern.matcher(password);
		while (!this.awaitingApproval) {
			if (matcher.matches()) {
				System.out.println(
						this.registeredAccounts.addRegisteredAccount(password, "Accounts/registeredAccounts.json"));
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
