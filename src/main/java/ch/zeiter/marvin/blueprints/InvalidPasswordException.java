package ch.zeiter.marvin.blueprints;

/**
 * Exception thrown when a user enters something bizarre
 */
public class InvalidPasswordException extends Exception {

    /**
     * The exception thrown when the password entered is wrong
     *
     * @param s The information for debugging and the user
     */
    public InvalidPasswordException(String s) {
        super(s);
    }
}
