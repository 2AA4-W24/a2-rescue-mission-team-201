package ca.mcmaster.se2aa4.island.team201;

public class Interpreter {
    private Direction direction;
    private Map map;
    private Battery battery;

    public Interpreter(Direction direction, Map map, Battery battery){
        this.direction = direction;
        this.map = map;
        this.battery = battery;
    }
}
