import com.jogamp.newt.event.WindowAdapter;
import com.jogamp.newt.event.WindowEvent;
import com.jogamp.newt.opengl.GLWindow;
import com.jogamp.opengl.*;
import com.jogamp.opengl.util.FPSAnimator;

public class Screen {

    private static volatile Screen instance;
    private GLWindow window;

    private Screen() {
        if (instance != null) {
            throw new RuntimeException();
        }

        GLProfile.initSingleton();
        GLCapabilities caps = new GLCapabilities(GLProfile.get(GLProfile.GL2));
        window = GLWindow.create(caps);
        GameArea gameArea = new GameArea();
        window.addGLEventListener(gameArea);
        window.setSize(800, 600);
        window.setTitle("Snake Game");

        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowDestroyNotify(WindowEvent e) {
                System.exit(0);
            }
        });

        window.addKeyListener(gameArea);

        FPSAnimator animator = new FPSAnimator(window, 60);
        animator.start();

        window.setVisible(true);
        window.display();
        window.requestFocus();
    }

    public static void create() {
        if (instance == null) {
            synchronized (Screen.class) {
                if (instance == null) {
                    instance = new Screen();
                }
            }
        }
    }
}
