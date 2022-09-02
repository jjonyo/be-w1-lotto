package lotto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class MainTest {

    @Test
    void main() {
        assertThat(Main.getHello()).isEqualTo("Hello");
    }
}