package com.fortitudetec.junit.jupiter;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

@Slf4j
class PerMethodTestLifecycleTest {

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
