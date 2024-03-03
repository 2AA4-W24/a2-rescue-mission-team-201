package ca.mcmaster.se2aa4.island.team201;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Battery {
    private final Logger logger = LogManager.getLogger();
  
    private int level;

    public Battery(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void decreaseLevelBy(int cost) {
        this.level -= cost;
    }
}
