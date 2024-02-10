package ca.mcmaster.se2aa4.island.team201;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

// Singleton pattern
public class Drone {
        private final static Logger logger = LogManager.getLogger();

        private static Drone drone;
        int actionNum = 1;
        Direction facing;
        int battery;
        private Drone()
        {
            System.out.println("Drone is Instantiated.");
        }
        public static Drone getInstance()
        {
            if (drone == null)
                drone = new Drone();
            return drone;
        }
        public void changeFacing(Direction facing) {
            this.facing = facing;
        }
        public int getBattery() {
            return battery;
        }
        public int getActionCount() {
            return actionNum;
        }
        public void setBattery(int num) {
            battery = num;
        }
        public void incrementActions() {
            actionNum++;
        }
        public void changeDirection(String direction) {
            switch(direction) {
                case "N":
                this.facing = Direction.north;
                break;
                case "E":
                this.facing = Direction.east;
                break;
                case "S":
                this.facing = Direction.south;
                break;
                case "W":
                this.facing = Direction.west;
                break;

            }
            logger.info("changing direction to " + direction);    
        }
}
