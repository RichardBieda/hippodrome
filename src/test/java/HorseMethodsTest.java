import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.MockedStatic;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mockStatic;

class HorseMethodsTest {

    Horse horse;
    @AfterEach
    void reset() {
        horse = null;
    }

    @Test
    void getNameTest() {
        String name = "HorsesName";
        horse = new Horse(name, 1.0, 1.0);
        assertEquals(name, horse.getName());
    }

    @Test
    void getSpeedTest() {
        double speed = 2.5;
        horse = new Horse("name", speed, 1.0);
        assertEquals(speed, horse.getSpeed());
    }

    @Test
    void getDistanceTest() {
        double distance = 3.5;
        horse = new Horse("name", 1.0, distance);
        assertEquals(distance, horse.getDistance());
    }

    @Test
    void getDefaultDistanceTest() {
        horse = new Horse("name", 1.0);
        assertEquals(0.0, horse.getDistance());
    }

    @Test
    void moveShouldCallGetRandomDoubleWithProperArgs() {
        try (MockedStatic<Horse> mocked = mockStatic(Horse.class)) {
            horse = new Horse("name", 3.0, 5.0);
            mocked.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.5);
            horse.move();
            mocked.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

/*
   random * speed + distance = expected
 */
    @ParameterizedTest
    @CsvSource({
            "1.0, 1.0, 0.5, 1.5",
            "2.0, 3.0, 0.3, 3.6",
            "5.0, 0.0, 0.9, 4.5"
    })
    void moveShouldUpdateDistanceCorrectly(double speed, double distance, double random, double expected) {
        try (MockedStatic<Horse> mocked = mockStatic(Horse.class)) {
            mocked.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(random);
            horse = new Horse("name", speed, distance);
            horse.move();
            assertEquals(expected, horse.getDistance());
        }
    }
}
