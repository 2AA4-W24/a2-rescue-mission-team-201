package ca.mcmaster.se2aa4.island.team201;

public class MapCell {
    private TerrainType terrainType;
    private boolean isExplored;

    public MapCell() {
        this.terrainType = TerrainType.UNEXPLORED;
        this.isExplored = false;
    }

    public TerrainType getTerrainType() {
        return terrainType;
    }

    public void setTerrainType(TerrainType terrainType) {
        this.terrainType = terrainType;
    }

    public boolean isExplored() {
        return isExplored;
    }

    public void setExplored(boolean explored) {
        isExplored = explored;
    }
}

