/**
 * @author Zeiter Marvin
 * @title TBZ E-Banking
 * @version Alpha 1.0.0
 */

package ch.zeiter.marvin;

import ch.zeiter.marvin.io.Cli;

public class Main {

    public static void main(String[] args) {
        String name = "TBZ E-Banking";

        Cli cli = new Cli();
        cli.init(name);
    }
}
