package ch.zeiter.marvin.controller;

import ch.zeiter.marvin.functions.Login;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicInteger;

public class LoginController {

	@FXML
	private TextField usernameField;
	@FXML
	private PasswordField passwordField;
	@FXML
	private Button loginBtn;
	@FXML
	private Label registerBtn;

	public void init(Stage primaryStage) {

		loginBtn.setText("Enter iBan");



		AtomicInteger i = new AtomicInteger();

		usernameField.requestFocus();

		loginBtn.setOnAction(actionEvent -> {
			if (i.get() >= 5)
				Platform.exit();

			loginCheck(usernameField.getText(), passwordField.getText());

			i.getAndIncrement();
		});

		registerBtn.setOnMouseClicked(mouseEvent -> {
			//TODO add logic to change page
		});
	}

	private void loginCheck(String iBan, String password) {
		Login login = new Login(null);
		login.loginCheck(iBan, password);
	}
}
