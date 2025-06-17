import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

// MENTOR FEEDBACK
/*
    See feedback on the HorseMethodsTest class
 */
class HorseConstructorTests {
    @Test
    void shouldThrowExceptionIfNameIsNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1.0, 1.0));
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n"})
    void shouldThrowExceptionIfNameIsBlank(String name) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, 1.0, 1.0));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionIfSpeedIsNegative() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse("Name", -1.0, 1.0));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionIfDistanceIsNegative() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse("Name", 1.0, -1.0));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }
}
