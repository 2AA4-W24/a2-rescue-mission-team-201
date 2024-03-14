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
}
