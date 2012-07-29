package thatonething.geom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.lwjgl.opengl.GL11;

public class Tile extends Shape {

    List<Vertex> vertices = new ArrayList();

    public Tile(Vertex[] vertices) {
        this.vertices.addAll(Arrays.asList(vertices));
    }

    @Override
    public void setVertices(List<Vertex> vertices) {
        this.vertices = vertices;
    }

    @Override
    public void draw() {
        GL11.glColor3f(0.0f, 1.0f, 0.0f);
        GL11.glBegin(GL11.GL_TRIANGLE_STRIP);                  // Start Drawing Tiles
        for (int n = 0; n < 2; n++) {
            for (int i = 0; i < 3; i++) {
                float x_m = vertices.get(i + n).x; // X Vertex Of 1st Point
                float y_m = vertices.get(i + n).y; // Y Vertex Of 1st Point
                float z_m = vertices.get(i + n).z; // Z Vertex Of 1st Point
                float u_m = vertices.get(i + n).u; // U Texture Coord Of 1st Point
                float v_m = vertices.get(i + n).v; // V Texture Coord Of 1st Point
                GL11.glTexCoord2f(u_m, v_m);
                GL11.glVertex3f(x_m, y_m, z_m); // Set The TexCoord And Vertice
                if (n > 0 && i == 2) {
                    x_m = vertices.get(0).x; // X Vertex Of 1st Point
                    y_m = vertices.get(0).y; // Y Vertex Of 1st Point
                    z_m = vertices.get(0).z; // Z Vertex Of 1st Point
                    u_m = vertices.get(0).u; // U Texture Coord Of 1st Point
                    v_m = vertices.get(0).v; // V Texture Coord Of 1st Point
                    GL11.glTexCoord2f(u_m, v_m);
                    GL11.glVertex3f(x_m, y_m, z_m); // Set The TexCoord And Vertice
                }
            }
        }
        GL11.glEnd();                        // Done Drawing Tiles
    }

    @Override
    public List<Vertex> getVertices() {
        return vertices;
    }
}