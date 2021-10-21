package ch.zeiter.marvin.other;

import ch.zeiter.marvin.Blueprints.Account;

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
