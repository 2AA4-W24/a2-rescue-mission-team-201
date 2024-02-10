package ca.mcmaster.se2aa4.island.team201.tactics;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.island.team201.BatteryManager;
import ca.mcmaster.se2aa4.island.team201.Drone;
import ca.mcmaster.se2aa4.island.team201.Tactic;

public class IslandFinder implements Tactic {
    private final static Logger logger = LogManager.getLogger();
    BatteryManager battery;
    Drone drone; 
    public IslandFinder(Drone drone, BatteryManager battery) {
        this.drone = drone;
        this.battery = battery;
    }
    @Override
    public Boolean tacticComplete() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'tacticComplete'");
    }

    public String decideAction() {
        int actionNum = drone.getActionCount();
        logger.info("action num is " + Integer.toString(actionNum));
        if (actionNum < 10) {
            if (battery.hasEnoughBatteryFor("fly")) {
                return "fly";
            } else {
                return "stop";
            }
        } else {
            return "stop";
        }
    }
}
