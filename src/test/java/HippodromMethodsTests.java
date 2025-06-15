import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class HippodromMethodsTests {

    Hippodrome hippodrome;

    @AfterEach
    void reset() {
        hippodrome = null;
    }

    @Test
    void getHorsesShouldReturnExactListPassedToConstructor() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("Horse " + i, i + 0.1));
        }
        hippodrome = new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    void moveShouldCallMoveOnAllHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Horse mockHorse = mock(Horse.class);
            horses.add(mockHorse);
        }

        hippodrome = new Hippodrome(horses);
        hippodrome.move();

        for (Horse horse : horses) {
            verify(horse).move();
        }
    }

    @Test
    void getWinnerShouldReturnHorseWithGreatestDistance() {
        Horse h1 = new Horse("A", 1, 10);
        Horse h2 = new Horse("B", 1, 20);
        Horse h3 = new Horse("C", 1, 30);
        hippodrome = new Hippodrome(List.of(h1, h2, h3));

        assertEquals(h3, hippodrome.getWinner());
    }
}
