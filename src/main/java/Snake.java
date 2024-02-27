import com.jogamp.nativewindow.util.Point;
import com.jogamp.opengl.GL2;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    private List<Point> body;
    private Direction direction;

    public Snake() {
        body = new ArrayList<>();
        direction = Direction.RIGHT;
        body.add(new Point(10, 10));
    }

    public void move() {
        Point head = body.get(0);
        Point newHead = new Point(head.getX() + direction.getDx(), head.getY() + direction.getDy());
        body.add(0, newHead);
        if (body.size() > 1) {
            body.remove(body.size() - 1);
        }
    }

    public boolean eat(Food food) {
        Point head = body.get(0);
        return head.getX() == food.getX() && head.getY() == food.getY();
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public boolean collidesWithWall() {
        Point head = body.get(0);
        int x = head.getX();
        int y = head.getY();
        int gridWidth = 800;
        int gridHeight = 600;

        return x < 0 || x >= gridWidth || y < 0 || y >= gridHeight;
    }

    public boolean collidesWithItself() {
        List<Point> body = this.body;
        int headX = body.get(0).getX();
        int headY = body.get(0).getY();

        for (int i = 1; i < body.size(); i++) {
            if (headX == body.get(i).getX() && headY == body.get(i).getY()) {
                return true;
            }
        }

        return false;
    }

    public void draw(GL2 gl) {
        gl.glPointSize(35);
        gl.glColor3f(0, 1, 0); // Set color to green
        gl.glBegin(GL2.GL_POINTS);

        List<Point> body = this.body;
        for (Point p : body) {
            gl.glVertex2i(p.getX(), p.getY());
        }

        gl.glEnd();
    }
}
