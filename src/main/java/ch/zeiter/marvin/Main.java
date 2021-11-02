/**
 * @author Zeiter Marvin
 * @title TBZ E-Banking
 * @version Alpha 1.0.0
 */

package ch.zeiter.marvin;

import ch.zeiter.marvin.blueprints.Account;
import ch.zeiter.marvin.io.Cli;
import ch.zeiter.marvin.other.EncryptionDecryptionService;
import ch.zeiter.marvin.other.Stages;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

/**
 * Main class
 */
public class Main extends Application {

    /**
     * Method used to start the program
     * @param args Starting argument
     */
    public static void main(String[] args) {
        // ? Not needed
        printEncDecService();

        String name = "TBZ E-Banking";

//        Cli cli = new Cli();
//        cli.init(name);

        launch(args);
    }

    /**
     * The method used to start the JavaFX Stage
     *
     * @param primaryStage The stage used by FX
     */
    @Override
    public void start(Stage primaryStage) {
        Stages stage = new Stages();
        stage.changeStage(primaryStage, null,"Login");
    }

    // ? not needed
    static void printEncDecService() {
        EncryptionDecryptionService eds = new EncryptionDecryptionService();
        try {
            Account encrypt = eds.encrypt(new Account("admin", "admin", "admin", 69420.0, true, true));
            System.out.println("encrypted: " + encrypt.toString());
            System.out.println("decrypted: " + eds.decrypt(encrypt).toString());
        } catch (GeneralSecurityException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
