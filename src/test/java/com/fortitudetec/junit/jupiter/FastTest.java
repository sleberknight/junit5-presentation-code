package com.fortitudetec.junit.jupiter;

import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A meta-annotation that can be used to annotate test methods which should be tagged as "fast".
 *
 * @see <a href="https://junit.org/junit5/docs/current/user-guide/#writing-tests-meta-annotations">meta annotations</a>
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Test
@Fast
public @interface FastTest {
}
