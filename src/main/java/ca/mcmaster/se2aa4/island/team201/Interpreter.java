package ca.mcmaster.se2aa4.island.team201;

import java.util.ArrayList;

import javax.tools.JavaFileManager.Location;

public class Interpreter {
    private ActionTracker actionTracker;
    private LocationTracker locationTracker;
    private Extras extras;
    private Map map;
    private Battery battery;

    public Interpreter(ActionTracker actionTracker, LocationTracker locationTracker, Map map, Extras extras, Battery battery){ 
        this.actionTracker = actionTracker; 
        this.locationTracker = locationTracker;
        this.extras = extras;
        this.map = map;
        this.battery = battery;
    }
    public int numberOfActions() {
        return actionTracker.numberOfActions();
    }
    public String facing() {
        return locationTracker.getDirection();
    }
    public String getRightDirection() {
        return locationTracker.getRightDirection();
    }
    public String getLeftDirection() {
        return locationTracker.getLeftDirection();
    }
    public String lastAction() {
        return actionTracker.lastAction();
    }
    public Echo mostRecentEcho() {
        return extras.mostRecentEcho();
    }
    public int getBattery() {
        return battery.getLevel();
    }
    public Echo[] lastThreeEchos() {
        ArrayList<Echo> echos = extras.getEchos();
        int last = echos.size()-1;
        Echo lastEcho = echos.get(last);
        Echo secondLastEcho = echos.get(last-1);
        Echo thirdLastEcho = echos.get(last-2);
        return new Echo[] {thirdLastEcho, secondLastEcho, lastEcho};
    }

}
