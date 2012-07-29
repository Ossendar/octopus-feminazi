package thatonething.geom;

import java.util.List;

public abstract class Shape {
    
    public abstract void draw();
    
    public abstract List<Vertex> getVertices();
    
    public abstract void setVertices(List<Vertex> vertices);
}