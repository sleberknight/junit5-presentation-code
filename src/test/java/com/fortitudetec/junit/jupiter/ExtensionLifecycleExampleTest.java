package com.fortitudetec.junit.jupiter;

import com.fortitudetec.junit.jupiter.extensions.LoggingExtension;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(LoggingExtension.class)
@Slf4j
class ExtensionLifecycleExampleTest {

    @BeforeAll
    static void beforeAll() {
        LOG.info("beforeAll");
    }

    @BeforeEach
    void beforeEach() {
        LOG.info("beforeEach");
    }

    @AfterEach
    void afterEach() {
        LOG.info("afterEach");
    }

    @AfterAll
    static void afterAll() {
        LOG.info("afterAll");
    }

    @Test
    void normalTest() {
        assertThat(21 * 2).isEqualTo(42);
    }

    // Note this test (intentionally) fails
    @Test
    void exceptionThrowingTest() {
        throw new RuntimeException("oops");
    }

    @Test
    void ignoredExceptionThrowingTest() {
        throw new RuntimeException("this exception will be ignored");
    }

}
