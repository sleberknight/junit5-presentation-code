package com.fortitudetec.junit.jupiter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FirstJupiterTest {

    @Test
    void theAnswer() {
        assertEquals(42, 22 + 20);
    }

}
