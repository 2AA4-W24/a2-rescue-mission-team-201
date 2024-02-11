package ca.mcmaster.se2aa4.island.team201;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BatteryInterpreter {
    Drone drone;
    private final static Logger logger = LogManager.getLogger();

    public BatteryInterpreter(Drone drone) {
        this.drone = drone;
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
        logger.info("Projected battery if we went home after this operation {}",batteryProjectedAfterStop);
        return hasEnough;
    }
}
