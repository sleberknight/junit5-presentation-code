package com.fortitudetec.junit.jupiter;

import com.fortitudetec.junit.jupiter.extensions.TempDirectory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Example test using {@link ExtendWith} to register an extension.
 *
 * @see <a href="https://junit.org/junit5/docs/current/user-guide/#extensions">extensions</a>
 * @see <a href="https://junit.org/junit5/docs/current/user-guide/#extensions-registration-declarative">declarative extension registration</a>
 */
@ExtendWith(TempDirectory.class)
@Slf4j
class UsingExtendWithOnClassTest {

    private static List<Path> tempDirectories;

    private Path tempDirectory;

    @BeforeAll
    static void beforeAll() {
        tempDirectories = Collections.synchronizedList(new ArrayList<>());
    }

    @BeforeEach
    void setUp(@TempDirectory.Root Path tempDir) {
        this.tempDirectory = tempDir;

        tempDirectories.add(tempDir);
    }

    @AfterAll
    static void afterAll() {
        for (Path path : tempDirectories) {
            LOG.info("{} exists? {}", path, Files.exists(path));
        }
    }

    @Test
    void testSomething(TestInfo testInfo) {
        LOG.info("Temp directory for {}: {}", testInfo.getDisplayName(), tempDirectory);
    }

    @Test
    void testSomethingElse(TestInfo testInfo) {
        LOG.info("Temp directory for {}: {}", testInfo.getDisplayName(), tempDirectory);
    }

    @Test
    void testOneMoreThing(TestInfo testInfo) {
        LOG.info("Temp directory for {}: {}", testInfo.getDisplayName(), tempDirectory);
    }

}
