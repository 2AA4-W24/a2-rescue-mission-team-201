package ca.mcmaster.se2aa4.island.team201;

public class Phase {
    private ActionExecutor executor;
    private Interpreter interpreter;

    public Phase (ActionExecutor executor, Interpreter interpreter){
        this.executor = executor;
        this.interpreter = interpreter;
    }

    public String takeDecision() {
        return executor.takeAction();
    }

}
