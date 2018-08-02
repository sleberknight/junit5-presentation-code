package com.fortitudetec.junit.jupiter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;

import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UsingDisplayNamesTest {

    @Test
    @DisplayName("All Your Base Are Belong To Us™")
    void allYourBase() {
        assertAll(
                () -> assertTrue(true),
                () -> assertEquals("42", "4" + "2"),
                () -> assertTimeout(ofSeconds(1), () -> 42 * 42)
        );
    }

    @Test
    @DisplayName("To ∞ & beyond...")
    void toInfinity() {
        Integer result = assertDoesNotThrow(() ->
                ThreadLocalRandom.current().nextInt(42));
        assertTrue(result < 42);
    }

}
