package com.fortitudetec.junit.jupiter;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
