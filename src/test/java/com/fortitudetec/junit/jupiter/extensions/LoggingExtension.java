package com.fortitudetec.junit.jupiter.extensions;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;

/**
 * A simple JUnit Jupiter extension that implements all the extension callback interfaces.
 */
@Slf4j
public class LoggingExtension implements
        BeforeAllCallback, BeforeEachCallback, BeforeTestExecutionCallback,
        AfterTestExecutionCallback, AfterEachCallback, AfterAllCallback,
        TestExecutionExceptionHandler {

    @Override
    public void beforeAll(ExtensionContext context) {
        LOG.trace("beforeAll");
    }

    @Override
    public void beforeEach(ExtensionContext context) {
        LOG.trace("beforeEach");
    }

    @Override
    public void beforeTestExecution(ExtensionContext context) {
        LOG.trace("beforeTestExecution");
    }

    @Override
    public void afterTestExecution(ExtensionContext context) {
        LOG.trace("afterTestExecution");
    }

    @Override
    public void afterEach(ExtensionContext context) {
        LOG.trace("afterEach");
    }

    @Override
    public void afterAll(ExtensionContext context) {
        LOG.trace("afterAll");
    }

    @Override
    public void handleTestExecutionException(ExtensionContext context,
                                             Throwable throwable) throws Throwable {

        LOG.trace("handleTestExecutionException");

        if (throwable.getMessage().contains("ignore")) {
            LOG.info("Ignoring {} with message: {}",
                    throwable.getClass().getName(), throwable.getMessage());
            return;
        }

        throw throwable;
    }
}
