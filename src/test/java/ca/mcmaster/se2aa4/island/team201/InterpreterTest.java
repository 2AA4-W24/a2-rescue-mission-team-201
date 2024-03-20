package ca.mcmaster.se2aa4.island.team201;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InterpreterTest {

    private Interpreter interpreter;
    private LocationTracker locationTracker;

    private ActionTracker actionTracker;

    @BeforeEach
    void setup(){
        actionTracker = new ActionTracker();
        locationTracker = new LocationTracker("N");
        Map map = new Map();
        Extras extras = new Extras();
        Battery battery = new Battery(100);

        interpreter = new Interpreter(actionTracker, locationTracker, map, extras, battery);
    }

    @Test
    void testFacingDirection() {
        assertEquals("N", interpreter.facing(), "Facing direction should be North.");
    }

    @Test
    void testBatteryLevel() {
        assertEquals(100, interpreter.getBattery(), "Battery level should be 100.");
    }

    @Test
    void testNumberOfActions() {
        actionTracker.incrementActionsCompleted();
        actionTracker.incrementActionsCompleted();
        assertEquals(2, interpreter.numberOfActions(), "Number of actions should be 2");
    }

    @Test
    void testCurrentCoordinate() {
        locationTracker.moveForward();
        Coordinate expectedCoordinate = new Coordinate(0, 1); // Assuming starting at (0,0)
        assertEquals(expectedCoordinate, interpreter.getCurrent(), "Current coordinates should be (0,1)");
    }





}
