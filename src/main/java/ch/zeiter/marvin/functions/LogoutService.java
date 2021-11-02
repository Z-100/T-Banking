package ch.zeiter.marvin.functions;

import ch.zeiter.marvin.other.Stages;
import javafx.stage.Stage;

/**
 * The logout service to destroy session and log out
 */
public class LogoutService {

	/**
	 * Method used to redirect or logout the user
	 *
	 * @param message The message used for user information & redirecting
	 * @param primaryStage The given stage (can be empty)
	 */
	public static boolean logout(String message, Stage... primaryStage) {
		Stages stages = new Stages();

		if (message.equals("logout_javafx")) {
			stages.changeStage(primaryStage[0], null, "Login");
			return true;
		} else {
			System.out.println(message);
			System.exit(0);
		}
		return false;
	}
}
