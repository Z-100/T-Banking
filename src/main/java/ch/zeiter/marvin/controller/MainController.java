package ch.zeiter.marvin.controller;

import ch.zeiter.marvin.functions.LogoutService;
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

    @FXML
    private Button depositButton;
    @FXML
    private Button withdrawButton;
    @FXML
    private Button transferButton;
    @FXML
    private Button logoutButton;
    @FXML
    private Label iBanLabel;
    @FXML
    private Label balanceLabel;
    @FXML
    private Button changePasswordButton;
    @FXML
    private Button deleteAccountButton;

    private Stage primaryStage;

    /**
     * Method used to control the main process of the application
     *
     * @param primaryStage The stage used by the Application
     * @param stages The given stages object to change in between stages
     */
    public void init(Stage primaryStage, UserSession userSession, Stages stages) {
        this.primaryStage = primaryStage;

        iBanLabel.setText(String.format("Your IBan:\t%s",
                userSession.getLoggedUser().getIban()));

        balanceLabel.setText(String.format("Your balance:\t%s £",
                userSession.getLoggedUser().getBalance()));

        depositButton.setOnAction(actionEvent ->
                stages.changeStage(this.primaryStage, userSession, "Deposit"));

        withdrawButton.setOnAction(actionEvent ->
                stages.changeStage(this.primaryStage, userSession, "Withdrawal"));

        transferButton.setOnAction(actionEvent ->
                stages.changeStage(this.primaryStage, userSession, "Transferal"));

        logoutButton.setOnAction(actionEvent ->
                LogoutService.logout("logout_javafx", primaryStage));

        deleteAccountButton.setOnAction(actionEvent ->
                stages.changeStage(this.primaryStage, userSession, "Delete"));

        changePasswordButton.setOnAction(actionEvent ->
                stages.changeStage(this.primaryStage, userSession, "ChangePW"));
    }
}
