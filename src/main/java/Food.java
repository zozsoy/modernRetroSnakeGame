import com.jogamp.opengl.GL2;

public class Food {
    private int x;
    private int y;

    public Food() {
        respawn();
    }

    public void respawn() {
        x = (int) (Math.random() * 20);
        y = (int) (Math.random() * 20);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void draw(GL2 gl) {
        gl.glColor3f(1, 0, 0); // Set color to red
        gl.glBegin(GL2.GL_POINTS);
        gl.glVertex2i(this.x, this.y);
        gl.glEnd();
    }
}
