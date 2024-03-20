package ca.mcmaster.se2aa4.island.team201;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CoordinateTest {
    private Coordinate coordinate;

    @BeforeEach
    void setUp() {
        coordinate = new Coordinate(10, 10);
    }

    @Test
    void testReturnX() {
        assertEquals(10, coordinate.x());
    }

    @Test
    void testReturnY() {
        assertEquals(10, coordinate.y());
    }

    @Test
    void testNorth() {
        coordinate = coordinate.north();
        assertEquals(11, coordinate.y());
    }

    @Test
    void testEast() {
        coordinate = coordinate.east();
        assertEquals(11, coordinate.x());
    }

    @Test
    void testSouth() {
        coordinate = coordinate.south();
        assertEquals(9, coordinate.y());
    }
}
