package ch.zeiter.marvin.blueprints;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * The blueprint for Accounts
 */
@Getter
@AllArgsConstructor
public class Account {
    private final String uuid;
    private final String iban;
    @Setter
    private String password;
    @Setter
    private double balance;
    private final boolean isAdmin;
    @Setter
    private boolean isApproved;

    @Override
    public String toString() {
        return "Account{" +
                "uuid='" + uuid + '\'' +
                ", iban='" + iban + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                ", isAdmin=" + isAdmin +
                ", isApproved=" + isApproved +
                '}';
    }
}