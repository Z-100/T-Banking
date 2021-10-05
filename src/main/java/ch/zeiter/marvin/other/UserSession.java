package ch.zeiter.marvin.other;

import ch.zeiter.marvin.Blueprints.Account;

public class UserSession {

    public static UserSession userSession;

    private Account loggedUser;

    public UserSession(Account loggedUser) {
        this.loggedUser = loggedUser;
    }

    public static UserSession session(Account account) {
        if (userSession == null)
            userSession = new UserSession(account);
        return userSession;
    }

    public Account getLoggedUser() {
        return loggedUser;
    }
}
