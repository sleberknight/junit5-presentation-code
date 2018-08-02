package com.fortitudetec.junit.jupiter

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows

private data class Person(val firstName: String,
                          val lastName: String,
                          val age: Int)

/**
 * Example test showing a basic JUnit Jupiter test in Kotlin.
 */
class FirstKotlinTest {

    @Test
    fun `first test`() {
        assertEquals(42, 4 * 10 + 2)
    }

    @Test
    fun `asserting all of them`() {
        val bob = Person("Bob", "Builder", 42)

        assertAll("bob",
                { assertEquals("Bob", bob.firstName) },
                { assertEquals("Builder", bob.lastName) },
                { assertEquals(42, bob.age) }
        )
    }

    @Test
    fun `testing exceptions`() {
        val exception = assertThrows<IllegalStateException>("should throw") {
            throw IllegalStateException("All your base are belong to us")
        }

        val message = exception.message ?: ""

        assertTrue(message.startsWith("All your base"))
    }

}