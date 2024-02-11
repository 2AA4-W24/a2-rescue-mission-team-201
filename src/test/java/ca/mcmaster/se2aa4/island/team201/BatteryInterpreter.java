package ca.mcmaster.se2aa4.island.team201;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BatteryInterpreterTest {
    private Drone mockDrone;
    private BatteryInterpreter batteryInterpreter;

    @BeforeEach
    void setUp() {
        // Setup mock Drone
        mockDrone = mock(Drone.class);
        batteryInterpreter = new BatteryInterpreter(mockDrone);

        when(mockDrone.getBattery()).thenReturn(100); // Assuming 100 units of battery for testing
        when(mockDrone.getActionCount()).thenReturn(10); // Assuming drone has made 10 actions
    }

    @Test
    void testHasEnoughBatteryForFly() {
        boolean result = batteryInterpreter.hasEnoughBatteryFor("fly");
        assertTrue(result, "Should have enough battery for flying.");
    }

    @Test
    void testHasEnoughBatteryForStop() {
        //drone does not have enough battery
        when(mockDrone.getBattery()).thenReturn(10); // Not enough battery to return home after flying
        boolean result = batteryInterpreter.hasEnoughBatteryFor("fly");
        assertFalse(result, "Should not have enough battery for flying.");
    }
}

