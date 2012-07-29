package thatonething.movement;

import javax.vecmath.Vector3f;

public class Location {
    
    float x;
    float y;
    float z;
    float yaw;
    float pitch;
    
    public Location(float x, float y, float z, float yaw, float pitch) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
    }
    
    public Location(int x, int y, int z, float yaw, float pitch) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
    }
    
    public float getX() {
        return x;
    }
    
    public float getY() {
        return y;
    }
    
    public float getZ() {
        return z;
    }
    
    public float getYaw() {
        return yaw;
    }
    
    public float getPitch() {
        return pitch;
    }
    
    public void set(Location location) {
        this.x = location.getX();
        this.y = location.getY();
        this.z = location.getZ();
        this.pitch = location.getPitch();
        this.yaw = location.getYaw();
    }
    
    public void set(Vector3f location) {
        this.x = location.x;
        this.y = location.y;
        this.z = location.z;
    }
    
    public void setX(float value) {
        this.x = value;
    }
    
    public void setY(float value) {
        this.y = value;
    }
    
    public void setZ(float value) {
        this.z = value;
    }
    
    public void setYaw(float value) {
        this.yaw = value;
    }
    
    public void setPitch(float value) {
        this.pitch = value;
    }
}