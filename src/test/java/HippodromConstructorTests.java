import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HippodromConstructorTests {

    Hippodrome hippodrome;

    @AfterEach
    void reset() {
        hippodrome = null;
    }

    @Test
    void shouldThrowExceptionIfHorsesIsNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> hippodrome = new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionIfHorsesIsEmpty() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(Collections.emptyList()));
        assertEquals("Horses cannot be empty.", e.getMessage());
    }
}
