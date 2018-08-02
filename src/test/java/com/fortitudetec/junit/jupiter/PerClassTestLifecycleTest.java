package com.fortitudetec.junit.jupiter;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

/**
 * Test demonstrating the "per class" test instance lifecycle.
 *
 * @see <a href="https://junit.org/junit5/docs/current/user-guide/#writing-tests-test-instance-lifecycle">test instance lifecycle</a>
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Slf4j
class PerClassTestLifecycleTest {

    private int sum = 0;

    @Test
    void add5() {
        sum += 5;
    }

    @Test
    void add10() {
        sum += 10;
    }

    @AfterEach
    void tearDown() {
        LOG.info("The current sum is: {}", sum);
    }

}
