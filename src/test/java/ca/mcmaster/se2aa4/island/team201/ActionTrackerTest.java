package ca.mcmaster.se2aa4.island.team201;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
public class ActionTrackerTest {
    @Test
    void incrementsActionCountCorrectly() {
        ActionTracker tracker = new ActionTracker();
        tracker.incrementActionsCompleted();
        assertEquals(1, tracker.numberOfActions());
    }

    // @Test
    // void recordsLastActionCorrectly() {
    //     ActionTracker tracker = new ActionTracker();
    //     tracker.setLastAction("turn", "N");
    //     assertArrayEquals(new String[]{"turn", "N"}, tracker.lastAction());
    // }


}
