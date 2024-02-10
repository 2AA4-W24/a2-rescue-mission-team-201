package ca.mcmaster.se2aa4.island.team201;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BatteryManager {
    Drone drone;
    private final static Logger logger = LogManager.getLogger();

    public BatteryManager(Drone drone) {
        this.drone = drone;
    }

    public void useBattery(int cost) {
        int current = drone.getBattery();
        int newBattery = current - cost;
        logger.info("Using battery {} -> {}", current, newBattery);
        drone.setBattery(newBattery);
    }
    public int batteryToReturnHome() {
        return drone.getActionCount()*7;
    }
    public boolean hasEnoughBatteryFor(String action) {
        int current = drone.getBattery();
        int batteryProjectedAfterStop = current;
        switch(action) {
            case "fly":
                batteryProjectedAfterStop = current - 7 - batteryToReturnHome();
                break;
                
        }
        boolean hasEnough = (batteryProjectedAfterStop > 0) ? true : false;
        logger.info("Projected battery if we went home after this operation", current, batteryProjectedAfterStop);
        return hasEnough;
    }
}
