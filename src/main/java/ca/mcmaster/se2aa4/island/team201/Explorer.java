package ca.mcmaster.se2aa4.island.team201;

import java.io.StringReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eu.ace_design.island.bot.IExplorerRaid;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Explorer implements IExplorerRaid {

    private final Logger logger = LogManager.getLogger();

    private DroneState droneState;
    private Navigator navigator;
    private Map map;



    @Override
    public void initialize(String s) {
        JSONObject info = new JSONObject(new JSONTokener(new StringReader(s)));
        int batteryLevel = info.getInt("budget");
        Position initialPosition = new Position(info.getInt("x"), info.getInt("y"));
        String heading = info.getString("heading");

        this.droneState = new DroneState(initialPosition, heading, batteryLevel);

        this.map = new Map(100,100);

        this.navigator = new Navigator(this.map, this.droneState);

        logger.info("** Initializing with Heading: {}, Battery Level: {}, Coordinates: ({}, {})",
                heading, batteryLevel, initialPosition.getX(), initialPosition.getY());
    }

    @Override
    public String takeDecision() {
        JSONObject decision = navigator.decideNextAction();

        logger.info("** Decision taken: {}", decision.toString());
        return decision.toString();
    }

    @Override
    public void acknowledgeResults(String s) {
        JSONObject results = new JSONObject(new JSONTokener(new StringReader(s)));

        droneState.updateState(results);

        // Pass the results to the Navigator to update the map and drone's understanding of the environment
        navigator.updateMap(results);

        logger.info("** Response received: {}", results.toString(2));
        logger.info("** Action cost: {}, Remaining battery: {}",
                results.getInt("cost"), droneState.getBatteryLevel());
    }

    @Override
    public String deliverFinalReport() {
        return "final report";
    }

}
