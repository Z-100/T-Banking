/**
 * @author Zeiter Marvin
 * @title TBZ E-Banking
 * @version Alpha 1.0.0
 */

package ch.zeiter.marvin;

import ch.zeiter.marvin.controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {


    /**
     * Method used to start the program
     * @param args Starting argument
     */
    public static void main(String[] args) {
//        String name = "TBZ E-Banking";
//
//        Cli cli = new Cli();
//        cli.init(name);
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        loginpage(primaryStage);
    }

    public void loginpage(Stage primaryStage ) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Screens/fxml/Login.fxml"));
            Parent root = fxmlLoader.load();
            LoginController controller = fxmlLoader.getController();
            controller.init(primaryStage);

            primaryStage.setScene(new Scene(root));
            primaryStage.setResizable(false);
            primaryStage.setTitle("TBZ E-Banking");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
