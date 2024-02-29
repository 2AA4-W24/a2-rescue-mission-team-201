package ca.mcmaster.se2aa4.island.team201;

public class ActionExecutor {
    private StateController stateController;

    public ActionExecutor(StateController stateController){
        this.stateController = stateController;
    }

    public String takeAction() {
        return "{action}";
    }
}
