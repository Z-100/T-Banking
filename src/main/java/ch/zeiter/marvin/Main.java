/**
 * @author Zeiter Marvin
 * @title TBZ E-Banking
 * @version Alpha 1.0.0
 */

package ch.zeiter.marvin;

import ch.zeiter.marvin.io.Cli;

public class Main {

    /**
     * Method used to start the program
     * @param args Starting argument
     */
    public static void main(String[] args) {
        String name = "TBZ E-Banking";

        Cli cli = new Cli();
        cli.init(name);
    }
}
