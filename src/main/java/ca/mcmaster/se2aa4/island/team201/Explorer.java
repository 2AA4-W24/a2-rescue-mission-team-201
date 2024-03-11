package ca.mcmaster.se2aa4.island.team201;

import java.io.StringReader;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

import eu.ace_design.island.bot.IExplorerRaid;
import org.json.JSONObject;
import org.json.JSONTokener;

import ca.mcmaster.se2aa4.island.team201.phases.BasicGridSearch;
import ca.mcmaster.se2aa4.island.team201.phases.FindIsland;
import ca.mcmaster.se2aa4.island.team201.phases.GoToIsland;
import ca.mcmaster.se2aa4.island.team201.phases.GridSearch;

public class Explorer implements IExplorerRaid {

    private final Logger logger = LogManager.getLogger();
    
    private Navigator navigator;
    private StateController stateController;
    private Report report; 
    @Override
    public void initialize(String s) {
        logger.info("** Initializing the Exploration Command Center");
        JSONObject info = new JSONObject(new JSONTokener(new StringReader(s)));
        logger.info("** Initialization info:\n {}",info.toString(2));
        String heading = info.getString("heading");
        Battery battery = new Battery(info.getInt("budget"));
        Map map = new Map();
        LocationTracker location = new LocationTracker(heading);
        Extras extras = new Extras();
        ActionTracker actionTracker = new ActionTracker();
        stateController = new StateController(actionTracker, location, map, extras, battery);
        Interpreter interpreter = new Interpreter(actionTracker, location, map, extras, battery);
        ActionExecutor actionExecutor = new ActionExecutor(stateController);
        Phase FindIsland = new FindIsland(actionExecutor, interpreter);
        Phase GoToIsland = new GoToIsland(actionExecutor, interpreter);
        Phase BasicGridSearch = new BasicGridSearch(actionExecutor, interpreter);
        Phase[] phaseList = {FindIsland,GoToIsland,BasicGridSearch};
        navigator = new Navigator(phaseList);
        report = new Report(interpreter);
        logger.info("The drone is facing {}", heading);


        logger.info("The drone is facing direction");
        logger.info("Battery level is {}", battery.getLevel());
    }

    @Override
    public String takeDecision() {
        return navigator.takeDecision();
    }

    @Override
    public void acknowledgeResults(String s) {
        JSONObject response = new JSONObject(new JSONTokener(new StringReader(s)));
        stateController.handleResults(response);
        logger.info("");
        logger.info("========================");
        logger.info("");
    }

    @Override
    public String deliverFinalReport() {
        return "no creek found";
    }

}
