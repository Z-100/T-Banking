package ch.zeiter.marvin.controller;

import ch.zeiter.marvin.blueprints.Transaction;
import ch.zeiter.marvin.functions.LogoutService;
import ch.zeiter.marvin.functions.TransactionHandler;
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

		depositButton.setOnAction(actionEvent -> 
				stages.changeStage(this.primaryStage, userSession, "Deposit"));

		transferButton.setOnAction(actionEvent -> 
				stages.changeStage(this.primaryStage, userSession, "Transferal"));

		accountButton.setOnAction(actionEvent -> 
				stages.changeStage(this.primaryStage, userSession, "Main"));

		logoutButton.setOnAction(actionEvent ->
				LogoutService.logout("logout_javafx", primaryStage));

		amountMoneyAvailableLabel.setText(String.format("Money available: %s £",
				userSession.getLoggedUser().getBalance()));

		confirmWithdrawalButton.setOnAction(actionEvent -> {
			String withdrawalAmount = withdrawalAmountField.getText();
			double data = Double.parseDouble(
					withdrawalAmount.isEmpty() ? "0.0" : withdrawalAmount);

			if (data > 0) {
				TransactionHandler transactionHandler = new TransactionHandler(userSession);
				String s = transactionHandler.newTransaction(
						(-1) * data);
				if (s.contains("success"))
					stages.changeStage(this.primaryStage, userSession, "Withdrawal");
				else
					amountMoneyAvailableLabel.setText(s);
			} else
				amountMoneyAvailableLabel.setText("Invalid number");
		});
	}
}
