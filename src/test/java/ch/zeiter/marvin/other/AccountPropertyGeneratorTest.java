package ch.zeiter.marvin.other;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountPropertyGeneratorTest {

    @Test
    void canCreateUuid() {
        AccountPropertyGenerator apg = new AccountPropertyGenerator();

        assertEquals(36, apg.getUuid().length());
        assertEquals(9, apg.getIBan().length());
    }
}