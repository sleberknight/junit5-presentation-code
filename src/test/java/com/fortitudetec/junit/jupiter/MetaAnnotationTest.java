package com.fortitudetec.junit.jupiter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Example test using meta-annotations.
 *
 * @see <a href="https://junit.org/junit5/docs/current/user-guide/#writing-tests-meta-annotations">meta annotations</a>
 */
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
