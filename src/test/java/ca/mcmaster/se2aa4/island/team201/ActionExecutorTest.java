package ca.mcmaster.se2aa4.island.team201;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ActionExecutorTest {

    private ActionExecutor actionExecutor;

    @BeforeEach
    void setUp() {
        LocationTracker locationTracker = new LocationTracker("N");
        Extras extras = new Extras();
        Map map = new Map();
        ActionTracker actionTracker = new ActionTracker();
        Battery battery = new Battery(100);

        StateController stateController = new StateController(actionTracker, locationTracker, map, extras, battery);
        actionExecutor = new ActionExecutor(stateController);
    }

    @Test
    void testExecuteStopAction() {
        JSONObject result = actionExecutor.execute(new Action("stop"));
        assertEquals("stop", result.getString("action"), "The action should be 'stop'.");
    }

    @Test
    void testExecuteFlyAction() {
        JSONObject result = actionExecutor.execute(new Action("fly"));
        assertEquals("fly", result.getString("action"), "The action should be 'fly'.");
    }

    @Test
    void testExecuteTurnAction() {
        String direction = "north";
        JSONObject result = actionExecutor.execute(new Action("heading", direction));
        assertEquals("heading", result.getString("action"), "The action should be 'heading'.");
        assertEquals(direction, result.getJSONObject("parameters").getString("direction"), "The direction should be 'north'.");
    }

    @Test
    void testExecuteEchoAction() {
        String direction = "east";
        JSONObject result = actionExecutor.execute(new Action("echo", direction));
        assertEquals("echo", result.getString("action"), "The action should be 'echo'.");
        assertEquals(direction, result.getJSONObject("parameters").getString("direction"), "The direction should be 'east'.");
    }

    @Test
    void testExecuteScanAction() {
        JSONObject result = actionExecutor.execute(new Action("scan"));
        assertEquals("scan", result.getString("action"), "The action should be 'scan'.");
    }
}
