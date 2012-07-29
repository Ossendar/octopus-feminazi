package thatonething.geom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.lwjgl.opengl.GL11;

public class Triangle extends Shape {

    List<Vertex> vertices = new ArrayList();

    public Triangle(Vertex[] vertices) {
        this.vertices.addAll(Arrays.asList(vertices));
    }

    @Override
    public void setVertices(List<Vertex> vertices) {
        this.vertices = vertices;
    }

    @Override
    public void draw() {
        GL11.glColor3f(0.0f, 1.0f, 0.0f);
        GL11.glBegin(GL11.GL_TRIANGLES);                  // Start Drawing Triangles
        for (int i = 0; i < 3; i++) {
            float x_m = vertices.get(i).x; // X Vertex Of 1st Point
            float y_m = vertices.get(i).y; // Y Vertex Of 1st Point
            float z_m = vertices.get(i).z; // Z Vertex Of 1st Point
            float u_m = vertices.get(i).u; // U Texture Coord Of 1st Point
            float v_m = vertices.get(i).v; // V Texture Coord Of 1st Point
            GL11.glTexCoord2f(u_m, v_m);
            GL11.glVertex3f(x_m, y_m, z_m); // Set The TexCoord And Vertice
        }
        GL11.glEnd();                        // Done Drawing Triangles
    }

    @Override
    public List<Vertex> getVertices() {
        return vertices;
    }
}