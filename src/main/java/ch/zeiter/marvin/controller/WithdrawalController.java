package ch.zeiter.marvin.controller;

import ch.zeiter.marvin.other.Stages;
import ch.zeiter.marvin.other.UserSession;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class WithdrawalController {

	@FXML private Button depositButton;
	@FXML private Button transferButton;
	@FXML private Button accountButton;
	@FXML private Button logoutButton;
	@FXML private Label amountMoneyAvailableLabel;
	@FXML private TextField withdrawalAmountField;
	@FXML private Button confirmWithdrawalButton;

	private Stage primaryStage;


	public void init(Stage primaryStage, UserSession userSession, Stages stages) {

		this.primaryStage = primaryStage;

		depositButton.setOnAction((actionEvent) -> {
			stages.changeStage(this.primaryStage, userSession, "Deposit");
		});

		transferButton.setOnAction((actionEvent) -> {
			stages.changeStage(this.primaryStage, userSession, "Transferal");
		});

		accountButton.setOnAction((actionEvent) -> {
			stages.changeStage(this.primaryStage, userSession, "Main");
		});

		logoutButton.setOnAction((actionEvent) -> {
			// ! Logout logic
		});
	}
}
