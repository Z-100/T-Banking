package ch.zeiter.marvin.functions;

import ch.zeiter.marvin.other.Stages;
import javafx.stage.Stage;

public class LogoutService {

	public static void logout(String message, Stage... primaryStage) {
		Stages stages = new Stages();

		if (message.equals("logout_javafx"))
			stages.changeStage(primaryStage[0], null, "Login");
		else {
			System.out.println(message);
			System.exit(0);
		}
	}
}
