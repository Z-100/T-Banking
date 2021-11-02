package ch.zeiter.marvin.other;

import ch.zeiter.marvin.controller.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Used to switch between stages easily and super simple
 */
public class Stages {

    /**
     * Method used to load fxml files and their corresponding controllers
     *
     * @param primaryStage The stage used by the Application
     * @param fxmlFile The name of the fxml file (dynamic)
     */
    public void changeStage(Stage primaryStage, UserSession userSession, String fxmlFile) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    getClass().getResource(String.format("/screens/fxml/%s.fxml", fxmlFile)));

            Parent root = fxmlLoader.load();

            switch (fxmlFile) {
                case "Login" -> {
                    LoginController controller = fxmlLoader.getController();
                    controller.init(primaryStage, userSession, this);
                }
                case "Register" -> {
                    RegisterController controller = fxmlLoader.getController();
                    controller.init(primaryStage, userSession, this);
                }
                case "Main" -> {
                    MainController controller = fxmlLoader.getController();
                    controller.init(primaryStage, userSession, this);
                }
                case "Deposit" -> {
                    DepositController controller = fxmlLoader.getController();
                    controller.init(primaryStage, userSession, this);
                }
                case "Withdrawal" -> {
                    WithdrawalController controller = fxmlLoader.getController();
                    controller.init(primaryStage, userSession, this);
                }
                case "Transferal" -> {
                    TransferalController controller = fxmlLoader.getController();
                    controller.init(primaryStage, userSession, this);
                }
                case "Delete" -> {
                    DeleteController controller = fxmlLoader.getController();
                    controller.init(primaryStage, userSession, this);
                }
                case "ChangePW" -> {
                    ChangePWController controller = fxmlLoader.getController();
                    controller.init(primaryStage, userSession, this);
                }
                default -> System.exit(1);
            }

            primaryStage.setScene(new Scene(root));
            primaryStage.setResizable(false);
            primaryStage.setTitle(String.format("TBZ E-Banking - %s", fxmlFile));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
