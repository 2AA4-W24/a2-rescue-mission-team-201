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
    public Action lastAction() {
        return actionTracker.lastAction();
    }
    public Echo lastEcho() {
        return extras.lastEcho();
    }
    public int getBattery() {
        return battery.getLevel();
    }
    
    // Note that the most recent echos will be on the right (end of array), similar to how.
    // they are stored in extras
    public Echo[] lastNumEchos(int num) {
        ArrayList<Echo> echos = extras.getEchos();
        if (num > echos.size()) {
            num = echos.size();
        }
        Echo[] lastEchos = new Echo[num];

        
        for (int i=0; i<num; i++) {
            lastEchos[num-i-1] = echos.get(echos.size()-i-1);
        }
        return lastEchos;
    }
    public Coordinate getCurrent() {
        return locationTracker.getCurrent();
    }

}
