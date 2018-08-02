package com.fortitudetec.junit.jupiter;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Simple test showing dependency injection for methods.
 *
 * @see <a href="https://junit.org/junit5/docs/current/user-guide/#writing-tests-dependency-injection">dependency injection</a>
 */
@Slf4j
class FirstParameterTest {

    @BeforeEach
    void setUp(TestInfo info) {
        LOG.info("Executing test: {} tagged with {}",
                info.getDisplayName(), info.getTags());
    }

    @Test
    @Tag("fast")
    void multiply() {
        assertEquals(42, 21 * 2);
    }

}
