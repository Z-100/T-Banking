package ch.zeiter.marvin.controller;

import ch.zeiter.marvin.functions.AccountHandler;
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

	@FXML private Label deleteAccountTextLabel;
	@FXML private PasswordField passwordField;
	@FXML private Button goBackButton;
	@FXML private Button deleteAccountConfirmButton;

	private Stage primaryStage;
	private JsonActions jsonActions;

	private final Stack<String> really = new Stack<>();

	public DeleteController() {
		jsonActions = new JsonActions();
	}

	public void init(Stage primaryStage, UserSession userSession, Stages stages) {
		this.primaryStage = primaryStage;
		really.push("Enter password to confirm");
		really.push("Like actually? It will be gone forever");
		really.push("You wanna delete your account?");
		really.push("Do you really wanna do this?");

		goBackButton.setOnAction(actionEvent -> {
			stages.changeStage(this.primaryStage, userSession, "Main");
		});

		deleteAccountConfirmButton.setOnAction(actionEvent -> {
			for (String s : really) {
				deleteAccountTextLabel.setText(really.peek() + "If so, press confirm");
				really.pop();
			}

			if (deleteAccountConfirmation(userSession))
				stages.changeStage(this.primaryStage, null, "Login");
			else
				stages.changeStage(this.primaryStage, userSession, "Main");
		});
	}

	public boolean deleteAccountConfirmation(UserSession userSession) {
		passwordField.setVisible(true);
		deleteAccountConfirmButton.setDisable(true);

		if (passwordField.getText().equals(userSession.getLoggedUser().getPassword())) {
			AccountHandler accountHandler = new AccountHandler(null, userSession, jsonActions);
			return accountHandler.deleteAccount(userSession);
		}
		return false;
	}
}
