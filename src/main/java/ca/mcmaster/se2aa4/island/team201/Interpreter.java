package ca.mcmaster.se2aa4.island.team201;

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

    public String getRightDirection() {
        return locationTracker.getRightDirection();
    }
    public String getLeftDirection() {
        return locationTracker.getLeftDirection();
    }
}
