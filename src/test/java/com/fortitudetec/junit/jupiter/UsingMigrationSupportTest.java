package com.fortitudetec.junit.jupiter;

import com.google.common.io.FileWriteMode;
import com.google.common.io.Files;
import lombok.extern.slf4j.Slf4j;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.migrationsupport.rules.EnableRuleMigrationSupport;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Example test showing the Jupiter migration support for JUnit 4 rules.
 *
 * @see <a href="https://junit.org/junit5/docs/current/user-guide/#migrating-from-junit4-rule-support">JUnit 4 rule migration support</a>
 */
@EnableRuleMigrationSupport
@Slf4j
class UsingMigrationSupportTest {

    @Rule
    public final TemporaryFolder tempFolder = new TemporaryFolder();

    private String rootFolderPath;

    @BeforeEach
    void beforeEach() {
        rootFolderPath = tempFolder.getRoot().getAbsolutePath();
    }

    @Test
    void needsTemporaryFolder() {
        LOG.info("Temporary folder path: {}", rootFolderPath);
    }

    @Test
    void usesTemporaryFolder() throws IOException {
        File file = tempFolder.newFile("foo.txt");

        Files.asCharSink(file, StandardCharsets.UTF_8, FileWriteMode.APPEND)
                .write(String.format("lorem ipsum%ssit dolor", System.lineSeparator()));

        List<String> lines = Files.readLines(file, StandardCharsets.UTF_8);
        assertThat(lines).containsExactly("lorem ipsum", "sit dolor");
    }

}
