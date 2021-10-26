package ch.zeiter.marvin.other;

import ch.zeiter.marvin.blueprints.Account;

/**
 * Creates a "session" with an Account
 */
public record UserSession(Account loggedUser) {

    public static UserSession userSession;

    public static UserSession session(Account account) {
        if (userSession == null)
            userSession = new UserSession(account);
        return userSession;
    }

    public Account getLoggedUser() {
        return loggedUser;
    }
}
