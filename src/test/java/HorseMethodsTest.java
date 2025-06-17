import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.MockedStatic;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mockStatic;

class HorseMethodsTest {

    // MENTOR FEEDBACK
    /*
    We use attributes in test classes to reuse objects in different test methods when those objects have
    a common setup for different test methods.
    That common setup is set in a method annotated with @BeforeEach
    In this case we don't have such common setup. So we don't really need a class scope Horse reference.
    We can create a local Horse reference on each test method and remove the need of setting the horse instance to null
    This also will prevent errors in the test execution when test methods run concurrently on the same test class instance
    (not usually the case in JUnit 5 within maven projects, but possible in other execution environments).
     */

    @Test
    void getNameTest() {
        String name = "HorsesName";
        Horse horse = new Horse(name, 1.0, 1.0);
        assertEquals(name, horse.getName());
    }

    @Test
    void getSpeedTest() {
        double speed = 2.5;
        Horse horse = new Horse("name", speed, 1.0);
        assertEquals(speed, horse.getSpeed());
    }

    @Test
    void getDistanceTest() {
        double distance = 3.5;
        Horse horse = new Horse("name", 1.0, distance);
        assertEquals(distance, horse.getDistance());
    }

    @Test
    void getDefaultDistanceTest() {
        Horse horse = new Horse("name", 1.0);
        assertEquals(0.0, horse.getDistance());
    }

    @Test
    void moveShouldCallGetRandomDoubleWithProperArgs() {
        try (MockedStatic<Horse> mocked = mockStatic(Horse.class)) {
            Horse horse = new Horse("name", 3.0, 5.0);
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
            Horse horse = new Horse("name", speed, distance);
            horse.move();
            assertEquals(expected, horse.getDistance());
        }
    }
}
