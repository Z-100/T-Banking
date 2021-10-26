package ch.zeiter.marvin.controller;

import ch.zeiter.marvin.functions.JsonActions;
import ch.zeiter.marvin.other.Stages;
import ch.zeiter.marvin.other.UserSession;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Controller for the Main.fxml file
 */
public class MainController {

	@FXML private Button depositButton;
	@FXML private Button withdrawButton;
	@FXML private Button transferButton;
	@FXML private Button accountButton;
	@FXML private Button logoutButton;

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
	public void init(Stage primaryStage, Stages stages) {


	}
}
