package ca.mcmaster.se2aa4.island.team201;
import org.json.JSONObject;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StateControllerTest {
    private StateController stateController;
    private Battery battery; // Declare battery as a field
    private LocationTracker mockLocationTracker;
    private ActionTracker mockActionTracker;
    private Extras extras;

    @BeforeEach
    void setUp() {
        mockLocationTracker = mock(LocationTracker.class);
        mockActionTracker = mock(ActionTracker.class);
        Battery battery = new Battery(100); // Assuming Battery doesn't need to be mocked for this test
        Extras extras = new Extras(); // Assuming Extras doesn't need to be mocked for this test
        Map map = new Map(); // Assuming Map is a simple data class that doesn't need to be mocked
        stateController = new StateController(mockActionTracker, mockLocationTracker, map, extras, battery);
    }


}

