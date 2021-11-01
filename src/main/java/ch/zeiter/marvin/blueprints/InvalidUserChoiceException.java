package ch.zeiter.marvin.blueprints;

/**
 * Exception thrown when a user enters something bizarre
 */
public class InvalidUserChoiceException extends Exception {
    //TODO EXCEPTION
    public InvalidUserChoiceException(String s) {
        super(s);
    }
}
