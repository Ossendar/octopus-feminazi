package thatonething;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import thatonething.geom.Sector;
import thatonething.geom.Tile;
import thatonething.geom.Triangle;
import thatonething.geom.Vertex;

public class MapLoader {

    private List<Sector> sectors = new ArrayList();

    public List<Sector> getSectors() {
        return sectors;
    }

    public void loadMap(String mapName) {
        String mapLoc = System.getProperty("user.home") + File.separatorChar + "ThatOneThing"
                + File.separatorChar + "maps"
                + File.separatorChar + mapName;
        File dir = new File(mapLoc);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(dir, "map.tot");
        loadMap(file, dir);
    }

    public void loadMap(File mapFile, File dir) {
        try {
            Scanner scanner = new Scanner(mapFile);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                if (line.startsWith("SECTOR")) {
                    String[] split = line.split(": ");
                    File sectorFile = new File(dir, split[1] + ".sector");
                    loadSector(sectorFile);
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MapLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadSector(File sectorFile) {
        try {
            Scanner scanner = new Scanner(sectorFile);
            Sector sector = new Sector();
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                if (line.startsWith("TRIANGLE")) {
                    String[] split = line.split(": ");
                    String coords = split[1];
                    String[] seperated = coords.split("|");
                    Vertex[] vertices = new Vertex[3];
                    // Step Through Each Vertex In Triangle
                    for (int vertloop = 0; vertloop < 3; vertloop++) // Loop Through All The Vertices
                    {
                        String[] values = seperated[vertloop].split(",");
                        float x = Float.parseFloat(values[0]);
                        float y = Float.parseFloat(values[1]);
                        float z = Float.parseFloat(values[2]);
                        float u = Float.parseFloat(values[3]);
                        float v = Float.parseFloat(values[4]);

                        // Store Values Into Respective Vertices
                        Vertex vertex = new Vertex(x, y, z, u, v);
                        vertices[vertloop] = vertex;
                    }
                    Triangle triangle = new Triangle(vertices);
                    sector.addShape(triangle);
                } else if (line.startsWith("TILE")) {
                    String[] split = line.split(": ");
                    String coords = split[1];
                    String[] seperated = coords.split(";");
                    Vertex[] vertices = new Vertex[4];
                    // Step Through Each Vertex In Triangle
                    for (int vertloop = 0; vertloop < 4; vertloop++) // Loop Through All The Vertices
                    {
                        String[] values = seperated[vertloop].split(",");
                        float x = Float.parseFloat(values[0]);
                        float y = Float.parseFloat(values[1]);
                        float z = Float.parseFloat(values[2]);
                        float u = Float.parseFloat(values[3]);
                        float v = Float.parseFloat(values[4]);

                        // Store Values Into Respective Vertices
                        Vertex vertex = new Vertex(x, y, z, u, v);
                        vertices[vertloop] = vertex;
                    }
                    Tile tile = new Tile(vertices);
                    sector.addShape(tile);
                }
            }
            sectors.add(sector);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MapLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}