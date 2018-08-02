package com.fortitudetec.junit.jupiter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Example test showing how to use {@link Nested} in tests.
 *
 * @see <a href="https://junit.org/junit5/docs/current/user-guide/#writing-tests-nested">nested tests</a>
 */
@DisplayName("An ArrayList")
class NestedExampleTest {

    private ArrayList<String> list;

    @Test
    @DisplayName("is instantiated with new ArrayList()")
    void instantiatedWithNew() {
        new ArrayList<>();
    }

    @Nested
    @DisplayName("when new")
    class WhenNew {

        @BeforeEach
        void setUp() {
            list = new ArrayList<>();
        }

        @Test
        @DisplayName("is empty")
        void isEmpty() {
            assertTrue(list.isEmpty());
        }

        @Test
        @DisplayName("throws exception when non-existent element is accessed")
        void throwsExceptionWhenIllegallyAccessed() {
            assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
        }

        @Nested
        @DisplayName("after adding an element")
        class AfterAddElement {

            String item = "an item";

            @BeforeEach
            void setUp() {
                list.add(item);
            }

            @Test
            @DisplayName("is no longer empty")
            void isNotEmpty() {
                assertFalse(list.isEmpty());
            }

            @Test
            @DisplayName("returns the added element")
            void returnsAddedElement() {
                assertEquals(item, list.get(0));
            }

        }

    }

}
