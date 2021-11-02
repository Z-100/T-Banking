package ch.zeiter.marvin.blueprints;

/**
 * Exception thrown when a user enters something bizarre
 */
public class InvalidJsonOptionException extends Exception {

    /**
     * The exception thrown when the json option is wrong
     *
     * @param s The information for debugging and the user
     */
    public InvalidJsonOptionException(String s) {
        super(s);
    }
}
