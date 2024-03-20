package ca.mcmaster.se2aa4.island.team201;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
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

    @Test
    void testWest() {
        coordinate = coordinate.west();
        assertEquals(9, coordinate.x());
    }

    @Test
    void testEquals() {
        Coordinate coordinate2 = new Coordinate(10, 10);
        assertTrue(coordinate.equals(coordinate2));
        Coordinate coordinate3 = new Coordinate(11, 11);
        assertFalse(coordinate.equals(coordinate3));
    }
}
