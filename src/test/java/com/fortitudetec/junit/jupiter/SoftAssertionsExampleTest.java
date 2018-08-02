package com.fortitudetec.junit.jupiter;

import com.fortitudetec.junit.jupiter.extensions.SoftAssertionsExtension;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

class SoftAssertionsExampleTest {

    @RegisterExtension
    final SoftAssertionsExtension softly = new SoftAssertionsExtension();

    @Test
    void addingAndMultiplying() {
        softly.assertThat(1 + 1).isEqualTo(2);
        softly.assertThat(1 * 1).isEqualTo(1);

        softly.assertThat(2 + 2).isEqualTo(4);
        softly.assertThat(2 * 2).isEqualTo(4);

        softly.assertThat(3 + 3).isEqualTo(6);
        softly.assertThat(3 * 3).isEqualTo(9);
    }

    @Test
    void substrings() {
        String str = "The quick brown fox jumped over the lazy dog";

        softly.assertThat(str).contains("quick");
        softly.assertThat(str).contains("brown");
        softly.assertThat(str).contains("fox");
        softly.assertThat(str).contains("lazy");
        softly.assertThat(str).contains("dog");
    }

    @Test
    @Disabled("Manually enable to see test failures reported")
    void containsSomeErrors() {
        String str = "The quick brown fox jumped over the lazy dog";

        softly.assertThat(str).startsWith("An");  // fails
        softly.assertThat(str).contains("quick");
        softly.assertThat(str).contains("fast");  // fails
        softly.assertThat(str).contains("jumped over");
        softly.assertThat(str).endsWith("dog");

    }

}
