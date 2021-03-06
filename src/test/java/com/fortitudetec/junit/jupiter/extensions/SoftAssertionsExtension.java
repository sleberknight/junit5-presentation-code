package com.fortitudetec.junit.jupiter.extensions;

import com.google.common.annotations.Beta;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

/**
 * A JUnit Jupiter extension that lets you use AssertJ's {@link SoftAssertions} facility.
 * This extension automatically calls the {@link SoftAssertions#assertAll()} after each
 * test to verify all the assertions.
 * <p>
 * This will ONLY work using the default "per method" test instance lifecycle.
 */
@Beta
@Slf4j
public class SoftAssertionsExtension extends SoftAssertions implements AfterEachCallback {

    public SoftAssertionsExtension() {
        LOG.trace("Constructed new SoftAssertionsExtension instance");
    }

    public void afterEach(ExtensionContext context) {
        LOG.trace("Asserting all soft assertions");
        this.assertAll();
    }

}
