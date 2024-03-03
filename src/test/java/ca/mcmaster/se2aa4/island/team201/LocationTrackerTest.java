package ca.mcmaster.se2aa4.island.team201;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocationTrackerTest {
    @Test
    void changesDirectionCorrectly() {
        LocationTracker tracker = new LocationTracker("N");
        tracker.setHeading("E");
        assertEquals("E", tracker.getDirection());
    }

    @Test
    void movesForwardCorrectly() {
        LocationTracker tracker = new LocationTracker("N");
        tracker.moveForward();
        assertEquals(new Coordinate(0, 1), tracker.getCurrent());
    }
}
