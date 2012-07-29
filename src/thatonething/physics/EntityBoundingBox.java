package thatonething.physics;

import java.util.logging.Level;
import javax.vecmath.Vector3f;
import thatonething.movement.Location;
import thatonething.util.Log;

public class EntityBoundingBox extends CollisionObject {
    
    private Vector3f[] vertices;
    
    private Location location;
    
    public EntityBoundingBox(Location location, float halfWidth, float height, float halfLength) {
        this.location = location;
        float x = location.getX();
        float y = location.getX();
        float z = location.getX();
        vertices = new Vector3f[8];
        vertices[0] = new Vector3f(x + halfWidth, y + height, z + halfLength);
        vertices[1] = new Vector3f(x + halfWidth, y + height, z - halfLength);
        vertices[2] = new Vector3f(x - halfWidth, y + height, z + halfLength);
        vertices[3] = new Vector3f(x - halfWidth, y + height, z - halfLength);
        vertices[4] = new Vector3f(x + halfWidth, y, z + halfLength);
        vertices[5] = new Vector3f(x + halfWidth, y, z - halfLength);
        vertices[6] = new Vector3f(x - halfWidth, y, z + halfLength);
        vertices[7] = new Vector3f(x - halfWidth, y, z - halfLength);
    }
    
    @Override
    public void setVertices(Vector3f[] vertices) {
        if (vertices.length < 8) {
            Log log = new Log();
            log.log(Level.SEVERE, "Not enough vertices to create EntityBoundingBox.");
        } else {
            this.vertices = vertices;
        }
    }
}