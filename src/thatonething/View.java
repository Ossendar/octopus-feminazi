package thatonething;

import java.util.List;
import javax.vecmath.Vector3f;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Timer;
import org.lwjgl.util.glu.GLU;
import thatonething.geom.Sector;
import thatonething.geom.Tile;
import thatonething.geom.Triangle;
import thatonething.movement.Location;

public class View {
    //3d vector to store the camera's position in

    private Location location;
    //the rotation around the Y axis of the camera
    private float yaw = 0.0f;
    //the rotation around the X axis of the camera
    private float pitch = 0.0f;
    private float ms;
    /**
     * is VSync Enabled
     */
    private boolean vsync;
    private MapLoader mapL;
    private boolean forward;
    private boolean backward;
    private boolean right;
    private boolean left;
    //private RigidBody player;

    //Constructor that takes the starting x, y, z location of the camera
    public View(Location location) {
        //instantiate position Vector3f to the x y z params.
        this.location = location;
        this.mapL = new MapLoader();
    }

    //increment the camera's current yaw rotation
    public void yaw(float amount) {
        //increment the yaw by the amount param
        yaw += amount;
        location.setPitch(yaw);
    }

    //increment the camera's current pitch rotation
    public void pitch(float amount) {
        //increment the pitch by the amount param
        pitch -= amount;
        location.setPitch(pitch);
    }

    //moves the camera forward relative to its current rotation (yaw)
    public void walkForward(float distance) {
        float posX = location.getX();
        float posZ = location.getZ();
        posX -= distance * ((float) Math.sin(Math.toRadians(yaw)));
        posZ += distance * ((float) Math.cos(Math.toRadians(yaw)));
        posX = (float) Math.round(posX * 100000) / 100000;
        posZ = (float) Math.round(posZ * 100000) / 100000;
        Vector3f vect = new Vector3f(posX, location.getY(), posZ);
        location.set(vect);
        Display.setTitle("X: " + location.getX() + " Z: " + location.getZ());
    }

    //moves the camera backward relative to its current
    public void walkBackwards(float distance) {
        float posX = location.getX();
        float posZ = location.getZ();
        posX += distance * ((float) Math.sin(Math.toRadians(yaw)));
        posZ -= distance * ((float) Math.cos(Math.toRadians(yaw)));
        posX = (float) Math.round(posX * 100000) / 100000;
        posZ = (float) Math.round(posZ * 100000) / 100000;
        Vector3f vect = new Vector3f(posX, location.getY(), posZ);
        location.set(vect);
        Display.setTitle("X: " + location.getX() + " Z: " + location.getZ());
    }
    //strafes the camera left relitive to its current rotation (yaw)

    public void strafeLeft(float distance) {
        float posX = location.getX();
        float posZ = location.getZ();
        posX -= distance * ((float) Math.sin(Math.toRadians(yaw - 90)));
        posZ += distance * ((float) Math.cos(Math.toRadians(yaw - 90)));
        posX = (float) Math.round(posX * 100000) / 100000;
        posZ = (float) Math.round(posZ * 100000) / 100000;
        Vector3f vect = new Vector3f(posX, location.getY(), posZ);
        location.set(vect);
        Display.setTitle("X: " + location.getX() + " Z: " + location.getZ());
    }
    //strafes the camera right relitive to its current rotation (yaw) 

    public void strafeRight(float distance) {
        float posX = location.getX();
        float posZ = location.getZ();
        posX -= distance * ((float) Math.sin(Math.toRadians(yaw + 90)));
        posZ += distance * ((float) Math.cos(Math.toRadians(yaw + 90)));
        posX = (float) Math.round(posX * 100000) / 100000;
        posZ = (float) Math.round(posZ * 100000) / 100000;
        Vector3f vect = new Vector3f(posX, location.getY(), posZ);
        location.set(vect);
        Display.setTitle("X: " + location.getX() + " Z: " + location.getZ());
    }

    //translates and rotate the matrix so that it looks through the camera
    //this does basic what gluLookAt() does
    public void updateCamera() {
        //roatate the pitch around the X axis
        GL11.glRotatef(pitch, 1.0f, 0.0f, 0.0f);
        //roatate the yaw around the Y axis
        GL11.glRotatef(yaw, 0.0f, 1.0f, 0.0f);
        //translate to the position vector's location
        GL11.glTranslatef(location.getX(), location.getY() - 1.95f, location.getZ());
        System.out.print(location.getX() + ", " + location.getY() + ", " + location.getZ() + ".\n");
    }

    /**
     * Set the display mode to be used
     *
     * @param width The width of the display required
     * @param height The height of the display required
     * @param fullscreen True if we want fullscreen mode
     */
    public void setDisplayMode(int width, int height, boolean fullscreen) {

        // return if requested DisplayMode is already set
        if ((Display.getDisplayMode().getWidth() == width)
                && (Display.getDisplayMode().getHeight() == height)
                && (Display.isFullscreen() == fullscreen)) {
            return;
        }

        try {
            DisplayMode targetDisplayMode = null;

            if (fullscreen) {
                DisplayMode[] modes = Display.getAvailableDisplayModes();
                int freq = 0;

                for (int i = 0; i < modes.length; i++) {
                    DisplayMode current = modes[i];

                    if ((current.getWidth() == width) && (current.getHeight() == height)) {
                        if ((targetDisplayMode == null) || (current.getFrequency() >= freq)) {
                            if ((targetDisplayMode == null) || (current.getBitsPerPixel() > targetDisplayMode.getBitsPerPixel())) {
                                targetDisplayMode = current;
                                freq = targetDisplayMode.getFrequency();
                            }
                        }

                        // if we've found a match for bpp and frequence against the 
                        // original display mode then it's probably best to go for this one
                        // since it's most likely compatible with the monitor
                        if ((current.getBitsPerPixel() == Display.getDesktopDisplayMode().getBitsPerPixel())
                                && (current.getFrequency() == Display.getDesktopDisplayMode().getFrequency())) {
                            targetDisplayMode = current;
                            break;
                        }
                    }
                }
            } else {
                targetDisplayMode = new DisplayMode(width, height);
            }

            if (targetDisplayMode == null) {
                System.out.println("Failed to find value mode: " + width + "x" + height + " fs=" + fullscreen);
                return;
            }

            Display.setDisplayMode(targetDisplayMode);
            Display.setFullscreen(fullscreen);

        } catch (LWJGLException e) {
            System.out.println("Unable to setup mode " + width + "x" + height + " fullscreen=" + fullscreen + e);
        }
    }

    public long getTime() {
        return System.nanoTime() / 1000000;
    }

    private static boolean initGL() {
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();

//        Calculate the aspect ratio of the window
        GLU.gluPerspective(45.0f, ((float) 800) / ((float) 600), 0.1f, 100.0f);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glLoadIdentity();

        GL11.glEnable(GL11.GL_TEXTURE_2D);                                    // Enable Texture Mapping ( NEW )
        GL11.glShadeModel(GL11.GL_SMOOTH);
        GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        GL11.glClearDepth(1.0f);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glDepthFunc(GL11.GL_LEQUAL);
        GL11.glHint(GL11.GL_PERSPECTIVE_CORRECTION_HINT, GL11.GL_NICEST);
        return true;
    }

    public void gameLoop() {
        float dx = 0.0f;
        float dy = 0.0f;
        float dt = 0.0f; //length of frame
        float lastTime = 0.0f; // when the last frame was
        float time = 0.0f;

        float mouseSensitivity = 0.25f;
        float movementSpeed = 4.0f; //move 10 units per second

        try {
            Display.setDisplayMode(new DisplayMode(800, 600));
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            System.exit(0);
        }

        //hide the mouse
        Mouse.setGrabbed(true);

        // init OpenGL
        initGL();

        mapL.loadMap("toot");

        // keep looping till the display window is closed the ESC key is down
        while (!Display.isCloseRequested()
                && !Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
            Timer.tick();
            time = getTime();
            dt = (time - lastTime) / 1000;
            lastTime = time;

            //distance in mouse movement from the last getDX() call.
            dx = Mouse.getDX();

            //distance in mouse movement from the last getDY() call.
            dy = Mouse.getDY();

            Mouse.setCursorPosition(400, 300);

            //controll camera yaw from x movement fromt the mouse
            yaw(dx * mouseSensitivity);

            //controll camera pitch from y movement fromt the mouse
            pitch(dy * mouseSensitivity);

            //when passing in the distance to move
            //we times the movementSpeed with dt this is a time scale
            //so if its a slow frame u move more then a fast frame
            //so on a slow computer you move just as fast as on a fast computer

            //Vector3f movement = new Vector3f(0.0f, 0.0f, 0.0f);

            if (Keyboard.isKeyDown(Keyboard.KEY_A)) {//strafe left
                left = true;
                strafeLeft(movementSpeed * dt);
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_D)) {//strafe right
                right = true;
                strafeRight(movementSpeed * dt);
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_W)) {//move forward
                forward = true;
                walkForward(movementSpeed * dt);
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_S)) {//move backwards
                backward = true;
                walkBackwards(movementSpeed * dt);
            }

            forward = false;
            backward = false;
            left = false;
            right = false;

            //System.out.print(position.x + ", " + position.y + ", " + position.z + "\n");

            while (Keyboard.next()) {
                if (Keyboard.getEventKeyState()) {
                    if (Keyboard.getEventKey() == Keyboard.KEY_F) {
                        setDisplayMode(800, 600, !Display.isFullscreen());
                    } else if (Keyboard.getEventKey() == Keyboard.KEY_V) {
                        vsync = !vsync;
                        Display.setVSyncEnabled(vsync);
                    }
                }
            }

            //set the modelview matrix back to the identity
            GL11.glLoadIdentity();

            //look through the camera before you draw anything
            //this.lookThrough();
            updateCamera();

            GL11.glClearColor(0.75f, 0.75f, 1.0f, 0.5f);

            //draw scene
            List<Sector> sectors = mapL.getSectors();
            for (int i = 0; i < sectors.size(); i++) {
                drawGLScene(sectors.get(i));
            }

            //draw the buffer to the screen
            Display.update();
        }
        Display.destroy();
    }

    public void drawGLScene(Sector sector) // Draw The OpenGL Scene
    {

        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

        GL11.glPushMatrix();                 // Reset The Current Matrix

        List<Triangle> triangles = sector.getTriangles();

        // Process Each Triangle
        for (int loop_m = 0; loop_m < triangles.size(); loop_m++) // Loop Through All The Triangles
        {
            Triangle triangle = triangles.get(loop_m);
            triangle.draw();
        }

        List<Tile> tiles = sector.getTiles();
        // Process Each Tile
        for (int loop_m = 0; loop_m < tiles.size(); loop_m++) // Loop Through All The Triangles
        {
            Tile tile = tiles.get(loop_m);
            tile.draw();
        }
        GL11.glPopMatrix();
    }
}