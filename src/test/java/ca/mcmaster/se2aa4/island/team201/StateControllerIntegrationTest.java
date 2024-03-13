package ca.mcmaster.se2aa4.island.team201;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class StateControllerIntegrationTest {

    private StateController stateController;
    private LocationTracker locationTracker;
    private Extras extras;
    private Map map;
    private Battery battery;
    private ActionTracker actionTracker;

    @BeforeEach
    void setup() {
        locationTracker = new LocationTracker("N");
        extras = new Extras();
        map = new Map();
        actionTracker = new ActionTracker();
        battery = new Battery(100);

        stateController = new StateController(actionTracker, locationTracker, map, extras, battery);
    }

    @Test
    void testFlyActionEffect() {
        // Initial conditions
        Coordinate initialCoordinate = locationTracker.getCurrent();

        // Perform action
        stateController.fly();

        // Verify LocationTracker was updated
        Coordinate newCoordinate = locationTracker.getCurrent();
        assertNotNull(newCoordinate);
        assertEquals(initialCoordinate.y() + 1, newCoordinate.y(), "Y coordinate should increase by 1 after flying North.");

        // Verify ActionTracker was updated
        assertEquals(1, actionTracker.numberOfActions(), "Number of actions should be 1 after flying.");
        assertEquals("fly", actionTracker.lastAction().toString(), "Last action should be 'fly'.");
    }

    @Test
    void testHandleResultsWithCostUpdatesBattery() {
        // Given
        JSONObject response = new JSONObject().put("cost", 10);

        // When
        stateController.handleResults(response);

        // Then
        assertEquals(90, battery.getLevel(), "Battery level should decrease by 10 after handling results with a cost of 10.");
    }

    @Test
    void testEchoUpdatesExtrasCorrectly() {
        // Prepare echo action
        stateController.echo("N");
        JSONObject response = new JSONObject().put("extras", new JSONObject().put("range", 5).put("found", "tree"));
        stateController.handleResults(response);

        // Verify Extras was updated
        Echo mostRecentEcho = extras.lastEcho();
        assertNotNull(mostRecentEcho, "Most recent echo should not be null.");
        assertEquals(5, mostRecentEcho.range(), "Echo range should be 5.");
        assertEquals("tree", mostRecentEcho.found(), "Echo found should be 'tree'.");
        // Assuming the direction is properly set based on the action's context, which may require additional setup
    }
}
