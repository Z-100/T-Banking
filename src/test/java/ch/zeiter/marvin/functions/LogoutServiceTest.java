package ch.zeiter.marvin.functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LogoutServiceTest {

    @Test
    void canLogout() {
        assertFalse(LogoutService.logout("whydoihavetodothis"));
    }
}