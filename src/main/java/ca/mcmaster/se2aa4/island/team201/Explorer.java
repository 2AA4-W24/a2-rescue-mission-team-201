package ca.mcmaster.se2aa4.island.team201;

import java.io.StringReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eu.ace_design.island.bot.IExplorerRaid;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Explorer implements IExplorerRaid {

    private final Logger logger = LogManager.getLogger();
    private Navigator navigator;
    private StateController stateController;

    @Override
    public void initialize(String s) {
        logger.info("** Initializing the Exploration Command Center");
        JSONObject info = new JSONObject(new JSONTokener(new StringReader(s)));
        logger.info("** Initialization info:\n {}",info.toString(2));

        // Instantiate related classes here
        Direction direction = new Direction(info.getString("heading"));
        Battery battery = new Battery(info.getInt("budget"));
        Map map = new Map();
        stateController = new StateController(direction, map, battery);
        Interpreter interpreter = new Interpreter(direction, map, battery);
        ActionExecutor actionExecutor = new ActionExecutor(stateController);
        Phase phase = new Phase(actionExecutor, interpreter);
        navigator = new Navigator(phase);

        // Logging the direction and battery level, as per your existing code
        logger.info("The drone is facing {}", direction.getHeading());
        logger.info("Battery level is {}", battery.getLevel());
    }

    @Override
    public String takeDecision() {
        logger.info("==============START==========");
        return navigator.takeDecision();
    }

    @Override
    public void acknowledgeResults(String s) {
        JSONObject response = new JSONObject(new JSONTokener(new StringReader(s)));
        stateController.handleStateChange(response);
        logger.info("==============END==========");
    }

    @Override
    public String deliverFinalReport() {
        return "no creek found";
    }

}
