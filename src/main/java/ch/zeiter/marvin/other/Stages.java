package ch.zeiter.marvin.other;

import ch.zeiter.marvin.controller.LoginController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Stages {

	public void changeStage(Stage primaryStage, String fxmlFile) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(
					getClass().getResource(String.format("Screens/fxml/%s.fxml", fxmlFile)));

			Parent root = fxmlLoader.load();

			LoginController controller = fxmlLoader.getController();
			controller.init(primaryStage);

			primaryStage.setScene(new Scene(root));
			primaryStage.setResizable(false);
			primaryStage.setTitle(String.format("TBZ E-Banking - %s", fxmlFile));
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
