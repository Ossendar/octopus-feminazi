package thatonething.entities;

import thatonething.entities.enums.LiveEntityType;
import thatonething.movement.Location;
import thatonething.physics.EntityBoundingBox;

public abstract class LiveEntity {
    
    private Location location;
    private LiveEntityType type;
    private String name;
    private int health;
    private int level;
    private boolean isVital;
    private EntityBoundingBox boundingBox;
    
    public LiveEntity(Location location, LiveEntityType type, String name, int health, int level, boolean isVital) {
        this.location = location;
        this.type = type;
        this.name = name;
        this.health = health;
        this.level = level;
        this.isVital = isVital;
        boundingBox = createBoundingBox(location, type);
    }
    
    public EntityBoundingBox getBoundingBox() {
        return boundingBox;
    }
    
    public void setBoundingBox(EntityBoundingBox boundingBox) {
        this.boundingBox = boundingBox;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isVital() {
        return isVital;
    }

    public void setIsVital(boolean isVital) {
        this.isVital = isVital;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LiveEntityType getType() {
        return type;
    }

    public void setType(LiveEntityType type) {
        this.type = type;
    }
    
    public Location getLocation() {
        return location;
    }
    
    public void setLocation(Location location) {
        this.location = location;
    }

    private EntityBoundingBox createBoundingBox(Location location, LiveEntityType type) {
        EntityBoundingBox bounds = null;
        switch(type) {
            case ANIMAL_VERY_LARGE: {
                bounds = new EntityBoundingBox(location, 3.0f, 4.0f, 2.0f);
                break;
            }
            case ANIMAL_LARGE: {
                bounds = new EntityBoundingBox(location, 1.5f, 2.0f, 1.0f);
                break;
            }
            case ANIMAL_MEDIUM: {
                bounds = new EntityBoundingBox(location, 0.75f, 1.0f, 0.5f);
                break;
            }
            case ANIMAL_SMALL: {
                bounds = new EntityBoundingBox(location, 0.375f, 0.5f, 0.25f);
                break;
            }
            case ANIMAL_VERY_SMALL: {
                bounds = new EntityBoundingBox(location, 0.1875f, 0.25f, 0.125f);
                break;
            }
            case CUBOID_VERY_LARGE: {
                bounds = new EntityBoundingBox(location, 2.0f, 4.0f, 2.0f);
                break;
            }
            case CUBOID_LARGE: {
                bounds = new EntityBoundingBox(location, 1.5f, 3.0f, 1.5f);
                break;
            }
            case CUBOID_MEDIUM: {
                bounds = new EntityBoundingBox(location, 1.0f, 2.0f, 1.0f);
                break;
            }
            case CUBOID_SMALL: {
                bounds = new EntityBoundingBox(location, 0.5f, 1.0f, 0.5f);
                break;
            }
            case CUBOID_VERY_SMALL: {
                bounds = new EntityBoundingBox(location, 0.25f, 0.5f, 0.25f);
                break;
            }
            case HUMANOID_VERY_LARGE: {
                bounds = new EntityBoundingBox(location, 1.0f, 4.0f, 1.0f);
                break;
            }
            case HUMANOID_LARGE: {
                bounds = new EntityBoundingBox(location, 0.75f, 3.0f, 0.75f);
                break;
            }
            case HUMANOID_MEDIUM: {
                bounds = new EntityBoundingBox(location, 0.5f, 2.0f, 0.5f);
                break;
            }
            case HUMANOID_SMALL: {
                bounds = new EntityBoundingBox(location, 0.25f, 1.0f, 0.25f);
                break;
            }
            case HUMANOID_VERY_SMALL: {
                bounds = new EntityBoundingBox(location, 0.125f, 0.5f, 0.125f);
                break;
            }
        }
        return bounds;
    }
}