import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.KeyListener;
import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.gl2.GLUgl2;
import com.jogamp.opengl.util.gl2.GLUT;

public class GameArea implements GLEventListener, KeyListener {
    private Snake snake;
    private Food food;
    private Boolean gameOver;

    public GameArea() {
        snake = new Snake();
        food = new Food();
        gameOver = false;
    }

    @Override
    public void init(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClearColor(0, 0, 0, 0);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        GLUgl2 glu = new GLUgl2();
        glu.gluOrtho2D(0, 800, 0, 600);
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {

    }

    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);

        if (!gameOver) {
            snake.move();

            if (snake.eat(food)) {
                food.respawn();
            }

            if (snake.collidesWithWall() || snake.collidesWithItself()) {
                gameOver = true;
            }
        }

        snake.draw(gl);
        food.draw(gl);

        if (gameOver) {
            gl.glColor3f(1, 0, 0);
            gl.glRasterPos2f(800 / 2 - 50, 600 / 2);
            GLUT glut = new GLUT();
            glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18, "Game Over");
        }

        gl.glFlush();
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                if (snake.getDirection() != Direction.DOWN) {
                    snake.setDirection(Direction.UP);
                }
                break;
            case KeyEvent.VK_DOWN:
                if (snake.getDirection() != Direction.UP) {
                    snake.setDirection(Direction.DOWN);
                }
                break;
            case KeyEvent.VK_LEFT:
                if (snake.getDirection() != Direction.RIGHT) {
                    snake.setDirection(Direction.LEFT);
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (snake.getDirection() != Direction.LEFT) {
                    snake.setDirection(Direction.RIGHT);
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
