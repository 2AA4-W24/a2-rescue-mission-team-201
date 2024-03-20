package ca.mcmaster.se2aa4.island.team201;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ActionTrackerTest {

    private ActionTracker actionTracker;

    @BeforeEach
    void setUp() {
        actionTracker = new ActionTracker();
    }

   @Test
   void testIncrementActions() {
        assertEquals(0, actionTracker.numberOfActions());
        actionTracker.incrementActionsCompleted();
        assertEquals(1, actionTracker.numberOfActions());
   }
}