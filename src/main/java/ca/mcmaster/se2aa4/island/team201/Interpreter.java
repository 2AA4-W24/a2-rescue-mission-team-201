package ca.mcmaster.se2aa4.island.team201;

import javax.tools.JavaFileManager.Location;

public class Interpreter {
    private LocationTracker locationTracker;
    private Extras extras;
    private Map map;
    private Battery battery;

    public Interpreter(LocationTracker locationTracker, Map map, Extras extras, Battery battery){ 
        this.locationTracker = locationTracker;
        this.extras = extras;
        this.map = map;
        this.battery = battery;
    }
}
