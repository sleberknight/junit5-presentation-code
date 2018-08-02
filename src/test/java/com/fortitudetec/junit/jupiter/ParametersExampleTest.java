package com.fortitudetec.junit.jupiter;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.DayOfWeek;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParametersExampleTest {

    @ParameterizedTest
    @ValueSource(ints = {2, 4, 6, 8, 10})
    void evens(int value) {
        assertEquals(0, value % 2,
                () -> String.format("Expected %d mod 2 == 0", value));
    }

    @ParameterizedTest
    @CsvFileSource(resources = {"/people.csv"}, numLinesToSkip = 1)
    void people(String first, String last, int age, String state) {
        assertNotNull(first);
        assertNotNull(last);
        assertTrue(age > 0,
                () -> String.format("Age %d not > 0", age));
        assertTrue("VA".equals(state) || "MD".equals(state));
    }

    @ParameterizedTest
    @MethodSource("supplyOdds")
    void odds(int value) {
        assertEquals(1, value % 2);
    }

    private static Stream<Integer> supplyOdds() {
        return Stream.of(1, 3, 5, 7, 9, 11);
    }

    @ParameterizedTest
    @EnumSource(value = DayOfWeek.class,
            mode = EnumSource.Mode.MATCH_ALL,
            names = "^S.*")
    void weekend(DayOfWeek dayOfWeek) {
        assertTrue(dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY);
    }

}
