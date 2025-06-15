import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HorseConstructorTests {

    Horse horse;
    @AfterEach
    void reset() {
        horse = null;
    }

    @Test
    void shouldThrowExceptionIfNameIsNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> horse = new Horse(null, 1.0, 1.0));
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n"})
    void shouldThrowExceptionIfNameIsBlank(String name) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> horse = new Horse(name, 1.0, 1.0));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionIfSpeedIsNegative() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> horse = new Horse("Name", -1.0, 1.0));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionIfDistanceIsNegative() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> horse = new Horse("Name", 1.0, -1.0));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }
}
