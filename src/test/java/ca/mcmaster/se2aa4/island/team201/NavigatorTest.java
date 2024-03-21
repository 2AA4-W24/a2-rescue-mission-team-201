package ca.mcmaster.se2aa4.island.team201;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.json.JSONObject;

public class NavigatorTest {
    private Phase[] phases;
    private Navigator navigator;

    @BeforeEach
    void setUp() {
        Phase phase1 = mock(Phase.class);
        Phase phase2 = mock(Phase.class);
    
        when(phase1.done()).thenReturn(false, true);
        when(phase2.done()).thenReturn(false);

        JSONObject phase1Results = new JSONObject();
        when(phase1.results()).thenReturn(phase1Results);
        JSONObject phase1Decision = new JSONObject().put("action", "Phase1 Action");
        when(phase1.takeDecision()).thenReturn(phase1Decision);
        
        JSONObject phase2Results = new JSONObject();
        when(phase2.results()).thenReturn(phase2Results);
        JSONObject phase2Decision = new JSONObject().put("action", "Phase2 Action");
        when(phase2.takeDecision()).thenReturn(phase2Decision);
        
        phases = new Phase[]{phase1, phase2};
        navigator = new Navigator(phases);
    }
}
