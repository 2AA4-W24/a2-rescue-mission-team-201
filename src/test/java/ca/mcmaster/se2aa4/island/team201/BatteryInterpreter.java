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

        // Mock Drone's behavior for the getBattery method
        when(mockDrone.getBattery()).thenReturn(100); // Assuming 100 units of battery for testing
        when(mockDrone.getActionCount()).thenReturn(10); // Assuming drone has made 10 actions
    }

    @Test
    void testHasEnoughBatteryForFly() {
        // Assume flying costs 7 units and returning home costs 70 units (10 actions * 7 units each)
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

