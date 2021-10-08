package ch.zeiter.marvin.other;

import lombok.Getter;

import java.util.Random;
import java.util.UUID;

public class AccountPropertyGenerator {

    @Getter
    private final String uuid;
    @Getter
    private final String iBan;

    public AccountPropertyGenerator() {
        this.uuid = UUID.randomUUID().toString();
        this.iBan = String.valueOf(10000000 + new Random().nextInt(900000000));
    }
}
