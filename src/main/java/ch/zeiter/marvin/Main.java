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

        EncryptionDecryptionService eds = new EncryptionDecryptionService();
        try {
            Account encrypt = eds.encrypt(new Account("uuid", "iban", "pw", 10, true, true));
            System.out.println(encrypt.toString());
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

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
