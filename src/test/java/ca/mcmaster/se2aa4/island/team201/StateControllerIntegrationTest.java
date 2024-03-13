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
        Coordinate initialCoordinate = locationTracker.getCurrent();

        stateController.fly();

        // check if LocationTracker was updated
        Coordinate newCoordinate = locationTracker.getCurrent();
        assertNotNull(newCoordinate);
        assertEquals(initialCoordinate.y() + 1, newCoordinate.y(), "Y coordinate should increase by 1 after flying North.");

        // Verify ActionTracker was updated
        assertEquals(1, actionTracker.numberOfActions(), "Number of actions should be 1 after flying.");
    //    assertEquals("fly", actionTracker.lastAction(), "Last action should be 'fly'."); //comparing string to action again so doesnt work
    }

    @Test
    void testHandleResultsWithCostUpdatesBattery() {
        // Given
        JSONObject response = new JSONObject()
                .put("cost", 10)
                .put("extras", new JSONObject());

        // when
        stateController.handleResults(response);

        //then
        assertEquals(90, battery.getLevel(), "Battery level should decrease by 10 after handling results with a cost of 10.");
    }


}
