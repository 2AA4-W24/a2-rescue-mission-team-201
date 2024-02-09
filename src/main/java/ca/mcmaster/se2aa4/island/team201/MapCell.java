package ca.mcmaster.se2aa4.island.team201;

public class MapCell {
    private TerrainType terrainType;
    private boolean isExplored;
    private boolean hasCreek;
    private boolean hasSite;

    public MapCell() {
        this.terrainType = TerrainType.UNEXPLORED;
        this.isExplored = false;
        this.hasCreek = false;
        this.hasSite = false;
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

    public boolean hasCreek() {
        return hasCreek;
    }

    public void setHasCreek(boolean hasCreek) {
        this.hasCreek = hasCreek;
    }

    public boolean hasSite() {
        return hasSite;
    }

    public void setHasSite(boolean hasSite) {
        this.hasSite = hasSite;
    }
}
