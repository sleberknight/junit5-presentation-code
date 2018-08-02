package com.fortitudetec.junit.jupiter;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.TestInfo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Sample test showing {@link RepeatedTest}.
 *
 * @see <a href="https://junit.org/junit5/docs/current/user-guide/#writing-tests-repeated-tests">repeated tests</a>
 */
@Slf4j
class RepeatedExampleTest {

    @BeforeEach
    void setUp(TestInfo testInfo,
               RepetitionInfo repetitionInfo) {

        String testMethodName = testInfo.getTestMethod()
                .orElseThrow(IllegalStateException::new)
                .getName();
        LOG.info("Execute test {} : repetition {} / {}",
                testMethodName,
                repetitionInfo.getCurrentRepetition(),
                repetitionInfo.getTotalRepetitions());
    }

    @DisplayName("Simple repeated test")
    @RepeatedTest(value = 5, name = "{displayName} {currentRepetition} of {totalRepetitions}")
    void repeatedTest() {
        assertTrue(true);
    }

    @RepeatedTest(value = 10)
    void repeatedTestWithInfo(RepetitionInfo repetitionInfo) {
        assertTrue(repetitionInfo.getCurrentRepetition() <=
                repetitionInfo.getTotalRepetitions());

        assertEquals(10, repetitionInfo.getTotalRepetitions());
    }

}
