package thatonething.geom;

import java.util.ArrayList;
import java.util.List;

public class Sector {
    
    private List<Triangle> triangles = new ArrayList();
    private List<Tile> tiles = new ArrayList();

    public Sector() {
    }
    
    public void addShape(Triangle triangle) {
        triangles.add(triangle);
    }
    
    public void addShape(Tile tile) {
        tiles.add(tile);
    }
    
    public List<Tile> getTiles() {
        return tiles;
    }
    
    public List<Triangle> getTriangles() {
        return triangles;
    }
}