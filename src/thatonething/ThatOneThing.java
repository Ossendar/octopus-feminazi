package thatonething;

import thatonething.movement.Location;

public class ThatOneThing {
    
    public static void main(String[] args) {
        View camera = new View(new Location(0.0f, 0.1f, 0.0f, 0.0f, 0.0f));
        camera.gameLoop();
    }
}