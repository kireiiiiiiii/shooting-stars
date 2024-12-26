/*
 * Author: Matěj Šťastný
 * Date created: 7/23/2024
 * Github link:  https://github.com/kireiiiiiiii/ShootingStars
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package kireiiiiiiii.shooting_stars.common;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Taskbar;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import kireiiiiiiii.shooting_stars.App;
import kireiiiiiiii.shooting_stars.interfaces.Interactable;
import kireiiiiiiii.shooting_stars.interfaces.Renderable;

/**
 * <h2>GPanel</h2>
 * {@code GPanel} is a custom {@code JPanel} object that handles rendering of
 * graphical elements using an internal Renderer, running on a separate thread.
 * It integrates with a {@code JFrame} to manage window properties.
 * </p>
 * <hr>
 * <br>
 * <h3>Constructor</h3>
 * {@code GPanel} will be upon costruction set with these values:
 * <ul>
 * <li><b>FPS</b> - interval, at which will the renderer calculate new
 * frames</li>
 * <li><b>Window width & window height</b> - dimensions of the
 * {@code JFrame}</li>
 * <li><b>Resizable</b> - makes the window fixed size or resizable by user</li>
 * <li><b>App title</b> - text displayed on the {@code JFrame} as the app
 * title</li>
 * </ul>
 * The {@code JFrame} will be put in the middle of the user screen by default.
 * It will also have the default icon, that can be changed separatly by using
 * the {@code setIcon()} method. If the project is being packaged, change the
 * style the icon is being accesed. The app
 * will be made visible, and the rendering prosess will start.
 * </p>
 * <hr>
 * <h3>UI Elements</h3>
 * This class supports adding
 * renderable objects that are drawn in a layered order based on their
 * {@code z-index}. The object must implement the interface {@code Renderable}.
 * This is how the objects are added:
 * 
 * <pre>
 * <code>
 * public void add(Renderable renderable); 
 * </code>
 * </pre>
 * 
 * </p>
 * <hr>
 * <h3>Rendering</h3>
 * The rendering loop can be controlled
 * with {@code start()} and {@code stop()} methods.
 * </p>
 * The Renderer class inside GPanel controls the rendering loop, adjusting
 * its interval based on the provided frames-per-second value.
 * </p>
 * <hr>
 * <h3>Action listeners</h3>
 * This class implements {@codeMouseListener} and {@code MouseMotionListener} to
 * handle mouse interaction events. Implementation of these methods should be
 * handeled by the user of this class, depending on the project needs.
 * <hr>
 * 
 * @author Matěj Šťastný
 * @since 7/23/2024
 */
public class GPanel extends JPanel implements MouseListener, MouseMotionListener, KeyListener {

    /////////////////
    // Variables
    ////////////////

    private JFrame appFrame;
    private Renderer renderer;
    private boolean isRendering;
    private ArrayList<Renderable> widgets;

    /////////////////
    // Constructor
    ////////////////

    /**
     * Standart constructor
     * 
     * @param owner - {JFrame} object, that owns this {@code JPanel}.
     */
    public GPanel(int fps, int windowWidth, int windowHeight, boolean resizable, String appTitle) {
        // ---- Variable Initiliazition ----
        this.widgets = new ArrayList<Renderable>();
        this.appFrame = new JFrame();
        this.renderer = new Renderer(fps);
        this.isRendering = false;

        // ---- JFrame setup ----
        this.appFrame.setSize(windowWidth, windowHeight);
        this.appFrame.setResizable(resizable);
        this.appFrame.setTitle(appTitle);
        this.appFrame.setLocationRelativeTo(null);
        this.appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.appFrame.setVisible(true);
        this.appFrame.add(this);

        // ---- Action listeners setup ----
        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this);
        requestFocusInWindow();

        // ---- Start rendering ----
        startRendering();

    }

    /////////////////
    // Renderer cotrol methods
    ////////////////

    /**
     * Starts the rendering loop in it's own thread. If already rendering will do
     * nothing.
     * 
     */
    public void startRendering() {
        if (!this.isRendering) {
            this.renderer.start();
            this.isRendering = true;
        }
    }

    /**
     * Stops the rendering by terminating the thread. If not rendering will do
     * nothing.
     * 
     */
    public void stopRendering() {
        if (this.isRendering) {
            this.renderer.stop();
            this.isRendering = false;
        }
    }

    /////////////////
    // JPanel override methods
    ////////////////

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g = (Graphics2D) graphics;
        // ------------------------------------------------

        for (int i = 0; i < this.widgets.size(); i++) {
            Renderable renderable = this.widgets.get(i);
            if (renderable.isVisible()) {
                renderable.render(g, this.appFrame.getFocusCycleRootAncestor());
            }
        }
    }

    /*
     * Size methods return the measurments of the owning {@JFrame} object.
     * Methods return zero, if owner wasn't initialized (is {@code null}).
     */

    @Override
    public int getWidth() {
        return this.appFrame == null ? 0 : this.appFrame.getWidth();
    }

    @Override
    public int getHeight() {
        return this.appFrame == null ? 0 : this.appFrame.getHeight();
    }

    @Override
    public Dimension getSize() {
        return new Dimension(getWidth(), getHeight());
    }

    /////////////////
    // Event override methods
    ////////////////

    @Override
    public void mouseDragged(MouseEvent e) {
        App.game.mouseDragged(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        App.game.mouseMoved(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        App.game.mouseClicked(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        App.game.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        App.game.mouseReleased(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        App.game.mouseEntered(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        App.game.mouseExited(e);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        App.game.keyTyped(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        App.game.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        App.game.keyReleased(e);
    }

    /////////////////
    // Accessor methods
    ////////////////

    /**
     * Accesor method of the {@code JFrame} owner of this {@code JPanel}.
     * 
     * @return - {JFrame} object of the owner.
     */
    public JFrame getAppFrame() {
        return this.appFrame;
    }

    /**
     * Makes a list of widgets of given class.
     * 
     * @param <T>
     * @param targetClass - target class.
     * @return list of {@code T} objects.
     */
    public <T> ArrayList<T> getWidgetsByClass(Class<T> targetClass) {
        ArrayList<T> list = new ArrayList<>();
        for (Renderable r : this.widgets) {
            if (targetClass.isInstance(r)) {
                list.add(targetClass.cast(r));
            }
        }
        return list;
    }

    /**
     * This method makes a list of {@code Renderable} objects in the {@code widgets}
     * list that are also implementing the {@code Interacteble} inteface.
     * 
     * @return - list of {@code Renderable} object, that are implementing the
     *         {@code Interactable} interface.
     */
    public ArrayList<Renderable> getInteractables() {
        ArrayList<Renderable> interactables = new ArrayList<Renderable>();
        for (Renderable r : this.widgets) {
            if (r instanceof Interactable) {
                interactables.add(r);
            }
        }
        return interactables;
    }

    /////////////////
    // Modifier methods
    ////////////////

    /**
     * Add method, that will add an element into the list of elements rendered by
     * this {@code GPanel} object.
     * 
     * @param renderable
     */
    public void add(Renderable renderable) {
        int value = renderable.getZIndex();
        int i = 0;

        // Find the correct position to insert the value
        while (i < this.widgets.size() && value < this.widgets.get(i).getZIndex()) {
            i++;
        }

        // Insert the value at the found position
        this.widgets.add(i, renderable);
    }

    /**
     * Adds all the widgets in a list using the {@code add()} method for all of
     * them.
     * 
     * @param widgets - list of {@code Renderable} objects.
     */
    public void add(List<Renderable> widgets) {
        for (Renderable r : widgets) {
            this.add(r);
        }
    }

    /**
     * Changes the window icon.
     *
     * @param path - path of the icon
     */
    public void setIcon(Image icon) {
        Taskbar taskbar = Taskbar.getTaskbar();
        taskbar.setIconImage(icon);
    }

    /////////////////
    // UI visibility methods
    ////////////////

    /**
     * Prevents a widget being rendered.
     * 
     * @param index - index of the widget in the array.
     */
    public void hide(int index) {
        if (index >= 0 && index < this.widgets.size() - 1) {
            this.widgets.get(index).hide();
        }
    }

    /**
     * Sets a widget to be rendered.
     * 
     * @param index - index of the object in the array.
     */
    public void show(int index) {
        if (index >= 0 && index < this.widgets.size() - 1) {
            this.widgets.get(index).show();
        }
    }

    /**
     * Prevents all widgets to be rendered.
     * 
     */
    public void hideAllWidgets() {
        for (Renderable r : this.widgets) {
            r.hide();
        }
    }

    /**
     * Sets all widgets to be rendered.
     * 
     */
    public void showAllWidgets() {
        for (Renderable r : this.widgets) {
            r.show();
        }
    }

    /**
     * Prevents all widgets with target tag to be rendered.
     * 
     * @param tag - target tag.
     */
    public void hideTaggedWidgets(String tag) {
        for (Renderable r : this.widgets) {
            for (String currTag : r.getTags()) {
                if (currTag.equals(tag)) {
                    r.hide();
                }
            }
        }
    }

    /**
     * Sets all widgets with target tag to be rendered.
     * 
     * @param tag - target tag.
     */
    public void showTaggedWidgets(String tag) {
        for (Renderable r : this.widgets) {
            for (String currTag : r.getTags()) {
                if (currTag.equals(tag)) {
                    r.show();
                }
            }
        }
    }

    /////////////////
    // Render class
    ////////////////

    /**
     * Renderer class, that renders the GUI every set interval. This interval is
     * determined by the {@code fps} value.
     * </p>
     * <b>This class has two contol methods: </b>
     * <ul>
     * <li>{@code start()} that starts the render loop in its own thread. The fps
     * value
     * can be changed while the loop is running, and the interval will be
     * automatically changed
     * <li>{@code stop()} that terminates the thread with the rendering loop.
     * <ul>
     * 
     */
    private class Renderer implements Runnable {

        private boolean running = false;
        private int targetFPS;

        /**
         * Constructor, sets the target fps the renderer should work at.
         * 
         * @param fps - fps value.
         */
        public Renderer(int fps) {
            setFps(fps);
        }

        public void start() {
            running = true;
            Thread renderThread = new Thread(this, "Render Thread");
            renderThread.start();
        }

        public void stop() {
            running = false;
        }

        @Override
        public void run() {
            while (running) {

                long optimalTime = 1000000000 / targetFPS; // In nanoseconds
                long startTime = System.nanoTime();

                render();

                long elapsedTime = System.nanoTime() - startTime;
                long sleepTime = optimalTime - elapsedTime;

                if (sleepTime > 0) {
                    try {
                        Thread.sleep(sleepTime / 1000000, (int) (sleepTime % 1000000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        /**
         * Rerenders the JPanel.
         * 
         */
        private void render() {
            SwingUtilities.invokeLater(() -> {
                GPanel.this.repaint();
            });
        }

        /**
         * Sets a new FPS value.
         * 
         * @param value - new value.
         */
        public void setFps(int value) {
            targetFPS = value;
        }
    }

}
