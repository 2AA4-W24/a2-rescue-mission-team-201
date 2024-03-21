package ca.mcmaster.se2aa4.island.team201;

import java.util.LinkedList;
import java.util.Queue;

import org.json.JSONObject;

public abstract class AerialPhase implements Phase {
    protected Queue<Action> actionQueue = new LinkedList<Action>();
    protected Interpreter interpreter;
    protected ActionExecutor executor;
    public AerialPhase(ActionExecutor executor, Interpreter interpreter) {
        this.executor = executor;
        this.interpreter = interpreter;
    }
    protected void echoRight() {
        actionQueue.add(new Action("echo","right"));
    }
    protected void echo() {
        actionQueue.add(new Action("echo","front"));
    }
    protected void turnRight() {
        actionQueue.add(new Action("heading","right"));
    }
    protected void turnLeft() {

        actionQueue.add(new Action("heading","left"));
    }
    protected void stop() {
        actionQueue.add(new Action("stop"));
    }
    protected Action addDirection(Action action) {
        Action actionWithDirection;
        String direction = action.direction();
        switch (direction) {
            case "left":
                actionWithDirection = new Action(action.name(), interpreter.getLeftDirection());
                break;
            case "right":
                actionWithDirection = new Action(action.name(), interpreter.getRightDirection());
                break;
            case "front":
                actionWithDirection = new Action(action.name(), interpreter.facing());
                break;
            case "none":
                actionWithDirection = action;
                break;
            default: 
                actionWithDirection = action;
                break;
        }

        return actionWithDirection;
    }
    protected void scan() {
        actionQueue.add(new Action("scan"));
    }
    protected void echoLeft() {
        actionQueue.add(new Action("echo","left"));
    }
    protected void fly() {
        actionQueue.add(new Action("fly"));
    }
    protected void flyForwardBy(int blocks) {
        for (int i=0; i<blocks; i++) {
            actionQueue.add(new Action("fly"));
        }
    }
    public void stopDroneIfNoBatteryLeft() {
        if (interpreter.getBattery() < 50) {
            actionQueue.clear();
            stop();
        }
    }

    
    public JSONObject nextAction() {
        Action actionToDo = actionQueue.remove();
        Action actionWithDirection = addDirection(actionToDo);
        
        return executor.execute(actionWithDirection);
    }

}
