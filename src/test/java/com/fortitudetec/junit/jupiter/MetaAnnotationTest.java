package com.fortitudetec.junit.jupiter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MetaAnnotationTest {

    @Test
    @Fast
    void thunder() {
        assertTrue(true);
    }

    @FastTest
    void lightning() {
        assertFalse(false);
    }

}
