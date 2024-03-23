package ca.mcmaster.se2aa4.island.team201;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import ca.mcmaster.se2aa4.island.team201.phases.BasicGridSearch;

public class BasicGridSearchTest {
    private ActionExecutor mockExecutor;
    private Interpreter mockInterpreter;
    private BasicGridSearch basicGridSearch;

    @BeforeEach
    void setUp() {
        mockExecutor = mock(ActionExecutor.class);
        mockInterpreter = mock(Interpreter.class);
        basicGridSearch = new BasicGridSearch(mockExecutor, mockInterpreter);
    }

    @Test
    public void testInitialization() {
        JSONObject info = new JSONObject();
        basicGridSearch.initialize(info);
        // Verify that interpreter's directionOfLines is set correctly
        verify(mockInterpreter).setDirectionOfLines(any());
    }
}
