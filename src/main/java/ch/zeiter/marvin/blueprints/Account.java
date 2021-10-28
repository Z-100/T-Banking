package ch.zeiter.marvin.blueprints;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

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
}
