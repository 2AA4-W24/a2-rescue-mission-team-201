package ca.mcmaster.se2aa4.island.team201;

public record Action(String name, String direction) {

    public Action(String name) {
        this(name,"none");
    }
    
}
