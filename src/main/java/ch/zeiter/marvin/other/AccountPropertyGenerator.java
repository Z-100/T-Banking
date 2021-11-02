package ch.zeiter.marvin.other;

import lombok.Getter;

import java.util.Random;
import java.util.UUID;

/**
 * Class used to randomly generate account information
 */
@Getter
public class AccountPropertyGenerator {

    private final String uuid;
    private final String iBan;

    /**
     * The constructor, which generates a random
     * UUID and IBan
     */
    public AccountPropertyGenerator() {
        this.uuid = UUID.randomUUID().toString();
        this.iBan = String.valueOf(10000000 + new Random().nextInt(900000000));
    }
}
