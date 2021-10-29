package ch.zeiter.marvin.controller;

import ch.zeiter.marvin.blueprints.Account;
import ch.zeiter.marvin.functions.AccountHandler;
import ch.zeiter.marvin.functions.JsonActions;
import ch.zeiter.marvin.functions.LogoutService;
import ch.zeiter.marvin.other.Stages;
import ch.zeiter.marvin.other.UserSession;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.regex.Matcher;

public class ChangePWController {

	@FXML private Button depositButton;
	@FXML private Button withdrawButton;
	@FXML private Button accountButton;
	@FXML private Button logoutButton;
	@FXML private Label errorLabel;
	@FXML private PasswordField oldPasswordField;
	@FXML private PasswordField newPasswordField;
	@FXML private PasswordField newPasswordConfirmField;
	@FXML private Button confirmPwChangeButton;

	private AccountHandler accountHandler;

	private Stage primaryStage;

	public void init(Stage primaryStage, UserSession userSession, Stages stages) {

		this.accountHandler = new AccountHandler(null, userSession, new JsonActions());

		this.primaryStage = primaryStage;

		depositButton.setOnAction(actionEvent ->
				stages.changeStage(this.primaryStage, userSession, "Deposit"));

		withdrawButton.setOnAction(actionEvent ->
				stages.changeStage(this.primaryStage, userSession, "Withdrawal"));

		accountButton.setOnAction(actionEvent ->
				stages.changeStage(this.primaryStage, userSession, "Main"));

		logoutButton.setOnAction(actionEvent ->
				LogoutService.logout("logout_javafx", primaryStage));

		confirmPwChangeButton.setOnAction(actionEvent -> {
			String currentPassword = userSession.getLoggedUser().getPassword();

			if (oldPasswordField.getText()
					.equals(currentPassword)) {
			//TODO make it somehow show errorLabel
				String newPassword = newPasswordField.getText();
				String newPasswordConfirm = newPasswordField.getText();

				Matcher matcher = RegisterController
						.pattern.matcher(newPassword);

				if (newPassword.equals(currentPassword)) {
					errorLabel.setText("New password cannot be old password");
				} else if (!matcher.matches()) {
					errorLabel.setText("Enter valid password");
				} else if (!newPassword.equals(newPasswordConfirm)) {
					errorLabel.setText("Enter valid password");
				} else {
					accountHandler.updatePassword(newPassword);
					stages.changeStage(this.primaryStage, userSession, "Main");
				}
			}
		});
	}
}
