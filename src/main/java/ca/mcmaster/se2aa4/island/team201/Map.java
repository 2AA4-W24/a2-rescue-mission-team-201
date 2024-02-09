package ca.mcmaster.se2aa4.island.team201;

import org.json.JSONObject;
import org.json.JSONArray;

import java.util.List;
import java.util.ArrayList;

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


    public void updateMapBasedOnScan(Position position, JSONObject extras) {
        // Handle biomes, creeks, and sites found in scan results
        if (extras.has("biomes")) {
            // Assuming we are updating the cell at the drone's current position
            // might have to update multiple cells based on the scan range
            JSONArray biomes = extras.getJSONArray("biomes");
            if (biomes.length() > 0) {
                // Just an example: set the terrain type of the current cell based on the first biome found
                String biome = biomes.getString(0);
                TerrainType terrainType = TerrainType.valueOf(biome.toUpperCase());
                updateCell(position.getX(), position.getY(), terrainType);
            }
        }
        if (extras.has("creeks")) {
            JSONArray creeks = extras.getJSONArray("creeks");
            // Example: Mark the current cell as having a creek if any creeks are found
            if (creeks.length() > 0) {
                this.map[position.getX()][position.getY()].setHasCreek(true);
            }
        }
        if (extras.has("sites")) {
            JSONArray sites = extras.getJSONArray("sites");
            if (sites.length() > 0) {
                this.map[position.getX()][position.getY()].setHasSite(true);
            }
        }
    }
}
