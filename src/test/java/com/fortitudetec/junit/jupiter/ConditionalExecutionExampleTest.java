package com.fortitudetec.junit.jupiter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.DisabledOnJre;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;
import static org.assertj.core.api.Assertions.assertThat;

class ConditionalExecutionExampleTest {

    @Test
    @EnabledOnOs(OS.MAC)
    void runsOnlyOnMacs() {
        assertThat("Foo Bar").isEqualToIgnoringCase("foo bar");
    }

    @Test
    @DisabledOnOs({OS.MAC, OS.WINDOWS})
    void doesNotRunOnMacsOrWindows() {
        assertThat("PassWROD").isEqualToIgnoringCase("passwrod");
    }
    
    @Test
    @EnabledOnOs(OS.LINUX)
    void runsOnlyOnLinux() {
        assertThat(newArrayList("foo", "bar")).containsExactlyInAnyOrder("bar", "foo");
    }

    @Test
    @EnabledOnJre({JRE.JAVA_8, JRE.JAVA_9})
    void runsOnJava8And9Only() {
        assertThat(newHashSet("foo", "bar", "baz"))
                .contains("foo")
                .doesNotContain("quux");
    }

    @Test
    @DisabledOnJre({JRE.JAVA_8, JRE.JAVA_9})
    void runsOnJava10And11Only() {
        assertThat(newHashSet("qux", "quux", "quux"))
                .contains("qux")
                .doesNotContain("bar", "foo");
    }

    @Test
    @EnabledIfSystemProperty(named = "os.arch", matches = "x86.*")
    void runsOnX86Arch() {
        assertThat("the quick brown fox jumps over the lazy dog")
                .containsPattern("the .* jumps over the .* dog");
    }

    @Test
    @EnabledIfEnvironmentVariable(named = "SHELL", matches = ".*/bash")
    void runsIfShellIsBash() {
        assertThat(System.getenv("SHELL")).endsWith("bash");
    }

    @Test
    @DisabledIfEnvironmentVariable(named = "SHELL", matches = ".*/sh")
    void doesNotRunIfShellIsSh() {
        assertThat(System.getenv("SHELL")).doesNotEndWith("/sh");
    }

}
