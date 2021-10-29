/**
 * @author Zeiter Marvin
 * @title TBZ E-Banking
 * @version Alpha 1.0.0
 */

package ch.zeiter.marvin;

import ch.zeiter.marvin.other.Stages;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main class
 */
public class Main extends Application {

    /**
     * Method used to start the program
     * @param args Starting argument
     */
    public static void main(String[] args) {
        String name = "TBZ E-Banking";

//        Cli cli = new Cli();
//        cli.init(name);
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Stages stage = new Stages();
        stage.changeStage(primaryStage, null,"Login");
    }
}
