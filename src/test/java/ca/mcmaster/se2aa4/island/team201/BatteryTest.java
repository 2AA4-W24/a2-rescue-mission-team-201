package ca.mcmaster.se2aa4.island.team201;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BatteryTest {
    private Battery battery;

    @BeforeEach
    void setUp() {
        battery = new Battery(100); // Initialize with a level of 100
    }

    @Test
    void testDecreaseLevelBy() {
        battery.decreaseLevelBy(10); // Decrease level by 10
        assertEquals(90, battery.getLevel(), "Battery level should decrease by 10.");
    }
}
