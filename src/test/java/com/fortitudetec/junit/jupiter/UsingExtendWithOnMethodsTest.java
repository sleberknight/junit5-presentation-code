package com.fortitudetec.junit.jupiter;

import com.fortitudetec.junit.jupiter.extensions.TempDirectory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;

import java.nio.file.Path;

/**
 * Example test using {@link ExtendWith} to register an extension.
 *
 * @see <a href="https://junit.org/junit5/docs/current/user-guide/#extensions">extensions</a>
 * @see <a href="https://junit.org/junit5/docs/current/user-guide/#extensions-registration-programmatic">programmatic extension registration</a>
 */
@Slf4j
class UsingExtendWithOnMethodsTest {

    private TestInfo testInfo;

    @BeforeEach
    void setUp(TestInfo info) {
        testInfo = info;
    }

    @ExtendWith(TempDirectory.class)
    @Test
    void needsTempDirectory(@TempDirectory.Root Path tempDir) {
        LOG.info("Temp directory for {}: {}", testInfo.getDisplayName(), tempDir);
    }

    @Test
    void doesNotNeedTempDirectory() {
        LOG.info("{} does not need a temp dir", testInfo.getDisplayName());
    }

    @ExtendWith(TempDirectory.class)
    @Test
    void alsoNeedsTempDirectory(@TempDirectory.Root Path tempDir) {
        LOG.info("Temp directory for {}: {}", testInfo.getDisplayName(), tempDir);
    }

}
