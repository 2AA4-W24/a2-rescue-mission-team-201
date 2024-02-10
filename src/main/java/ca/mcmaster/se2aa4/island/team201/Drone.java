package ca.mcmaster.se2aa4.island.team201;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

// Singleton pattern
public class Drone {
        private final static Logger logger = LogManager.getLogger();

        private static Drone drone;
        int actionNum = 0;
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
}
