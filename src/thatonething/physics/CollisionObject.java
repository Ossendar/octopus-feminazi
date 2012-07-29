package thatonething.physics;

import java.util.logging.Level;
import javax.vecmath.Vector3f;
import thatonething.movement.Location;
import thatonething.util.Log;

public abstract class CollisionObject {
    private Vector3f[] vertices;
    
    public Vector3f[] getVertices() {
        return vertices;
    }
    
    public abstract void setVertices(Vector3f[] vertices);
}