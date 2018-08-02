package com.fortitudetec.junit.jupiter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Very simple first JUnit Jupiter test.
 */
class FirstJupiterTest {

    @Test
    void theAnswer() {
        assertEquals(42, 22 + 20);
    }

}
