package ca.mcmaster.se2aa4.island.team201;
import java.util.ArrayList;
import java.util.List;

public class Map {
    private MapCell[][] map;

    public Map(int width, int height) {
        this.map = new MapCell[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                this.map[i][j] = new MapCell();
            }
        }
    }

    public MapCell getCell(int x, int y) {
        return this.map[x][y];
    }

    public void updateCell(int x, int y, TerrainType terrainType) {
        this.map[x][y].setTerrainType(terrainType);
        this.map[x][y].setExplored(true);
    }

    public List<MapCell> getUnexploredCells() {
        List<MapCell> unexplored = new ArrayList<>();
        for (int i = 0; i < this.map.length; i++) {
            for (int j = 0; j < this.map[i].length; j++) {
                if (!this.map[i][j].isExplored()) {
                    unexplored.add(this.map[i][j]);
                }
            }
        }
        return unexplored;
    }


}