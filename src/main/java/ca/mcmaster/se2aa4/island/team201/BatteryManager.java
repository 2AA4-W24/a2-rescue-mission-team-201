package ca.mcmaster.se2aa4.island.team201;

public class BatteryManager {
    private int batteryLevel;

    public BatteryManager(int initialBatteryLevel) {
        this.batteryLevel = initialBatteryLevel;
    }

    public boolean canProceed(int requiredBattery) {
        return batteryLevel > requiredBattery;
    }

    public void consumeBattery(int cost) {
        this.batteryLevel -= cost;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }
}


