package ch.zeiter.marvin.other;

import ch.zeiter.marvin.blueprints.Account;

/**
 * Creates a "session" with an Account
 */
public record UserSession(Account loggedUser) {

    public static UserSession userSession;

    /**
     * Method used to create or return the entire session
     *
     * @param account The given account
     * @return The given account
     */
    public static UserSession session(Account account) {
        if (userSession == null)
            userSession = new UserSession(account);
        return userSession;
    }

    /**
     * Returns the logged user
     *
     * @return The logged user
     */
    public Account getLoggedUser() {
        return loggedUser;
    }
}
