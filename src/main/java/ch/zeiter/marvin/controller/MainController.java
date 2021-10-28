package ch.zeiter.marvin.controller;

import ch.zeiter.marvin.functions.JsonActions;
import ch.zeiter.marvin.other.Stages;
import ch.zeiter.marvin.other.UserSession;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Controller for the Main.fxml file
 */
public class MainController {

	@FXML private Button depositButton;
	@FXML private Button withdrawButton;
	@FXML private Button transferButton;
	@FXML private Button logoutButton;
	@FXML private Label uuidLabel;
	@FXML private Label iBanLabel;
	@FXML private Label balanceLabel;
	@FXML private Button changePasswordButton;
	@FXML private Button deleteAccountButton;


	private UserSession userSession;
	private final JsonActions jsonActions;

	private Stage primaryStage;

	/**
	 * The constructor
	 */
	public MainController() {
		this.userSession = null;
		this.jsonActions = new JsonActions();
	}

	/**
	 * Method used to control the main process of the application
	 *
	 * @param primaryStage The stage used by the Application
	 * @param stages The given stages object to change in between stages
	 */
	public void init(Stage primaryStage, UserSession userSession, Stages stages) {

		this.primaryStage = primaryStage;

		depositButton.setOnAction((actionEvent) -> {
			stages.changeStage(this.primaryStage, userSession, "Deposit");
		});

		withdrawButton.setOnAction((actionEvent) -> {
			stages.changeStage(this.primaryStage, userSession, "Withdrawal");
		});

		transferButton.setOnAction((actionEvent) -> {
			stages.changeStage(this.primaryStage, userSession, "Transferal");
		});

		logoutButton.setOnAction((actionEvent) -> {
			// ! Logout logic
		});
	}
}
