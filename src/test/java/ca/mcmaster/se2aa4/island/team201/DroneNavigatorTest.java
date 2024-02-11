package ca.mcmaster.se2aa4.island.team201;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DroneNavigatorTest {
    private Drone mockDrone;
    private DroneStateController mockStateController;
    private DroneNavigator droneNavigator;

    @BeforeEach
    void setUp() {
        // Mock dependencies
        mockDrone = mock(Drone.class);
        mockStateController = mock(DroneStateController.class);

        droneNavigator = new DroneNavigator(mockDrone, mockStateController);
    }

    @Test
    void testTakeDecisionWithFlyAction() {
        // Setup mock to simulate a scenario where flying is the next action
        when(mockDrone.getActionCount()).thenReturn(5); // Early in mission
        when(mockDrone.getBattery()).thenReturn(100); // Enough battery to perform actions

        String decision = droneNavigator.takeDecision();
        assertTrue(decision.contains("\"action\":\"fly\""), "Decision should be to fly.");
    }

    @Test
    void testTakeDecisionWithStopAction() {
        // simulate a scenario where stopping is the only option
        when(mockDrone.getActionCount()).thenReturn(111); // Later in the mission, beyond action threshold
        when(mockDrone.getBattery()).thenReturn(30); // Limited battery

        String decision = droneNavigator.takeDecision();
        assertTrue(decision.contains("\"action\":\"stop\""), "Decision should be to stop due to battery or action limit.");
    }
}

