package ch.zeiter.marvin.controller;

import ch.zeiter.marvin.functions.LogoutService;
import ch.zeiter.marvin.functions.TransactionHandler;
import ch.zeiter.marvin.other.Stages;
import ch.zeiter.marvin.other.UserSession;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TransferalController {

	@FXML private Button depositButton;
	@FXML private Button withdrawButton;
	@FXML private Button accountButton;
	@FXML private Button logoutButton;
	@FXML private Label amountMoneyAvailableLabel;
	@FXML private TextField receiverIBanField;
	@FXML private TextField transferalAmountField;
	@FXML private Button confirmTransferalButton;

	private Stage primaryStage;

	public void init(Stage primaryStage, UserSession userSession, Stages stages) {

		this.primaryStage = primaryStage;

		depositButton.setOnAction(actionEvent ->
				stages.changeStage(this.primaryStage, userSession, "Deposit"));

		withdrawButton.setOnAction(actionEvent ->
				stages.changeStage(this.primaryStage, userSession, "Withdrawal"));

		accountButton.setOnAction(actionEvent ->
				stages.changeStage(this.primaryStage, userSession, "Main"));

		logoutButton.setOnAction(actionEvent ->
				LogoutService.logout("logout_javafx", primaryStage));

		amountMoneyAvailableLabel.setText(String.format("Money available: %s £",
				userSession.getLoggedUser().getBalance()));

		confirmTransferalButton.setOnAction(actionEvent -> {
			String receiverIban = receiverIBanField.getText();
			String transferAmountS = transferalAmountField.getText();

			double transferAmountD = Double.parseDouble(
					transferAmountS.equals("") ? "0.0" : transferAmountS);

			boolean nothingMissing = transferAmountD != 0.0 && !receiverIban.isEmpty();

			TransactionHandler transactionHandler = new TransactionHandler(userSession);
			if (nothingMissing) {
				String s = transactionHandler.newTransaction(transferAmountD, receiverIban);
				amountMoneyAvailableLabel.setText(s);
				stages.changeStage(this.primaryStage, userSession, "Transferal");
			}
			transactionHandler = null;
		});
	}
}
