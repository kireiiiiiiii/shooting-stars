/*
 * Author: Matěj Šťastný
 * Date created: 5/9/2024
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
 */

package kireiiiiiiii.shooting_stars.common;

import java.util.Timer;
import java.util.TimerTask;

/**
 * An timer object with the ability to pause, resume and completely stop.
 * 
 */
public class PausableTimer {

    /////////////////
    // Variables
    ////////////////

    private Timer timer;
    private long delay;
    private boolean isPaused;
    private boolean isRunning;
    private int executions;
    private int currentExecution;
    private Runnable onFinishTask;
    private Runnable onExecution;

    /////////////////
    // Constructors
    ////////////////

    /**
     * Default contructor. Sets the given parameters.
     * </p>
     * DOESN'T AUTOMATICALLY START THE TIMER!
     * 
     * @param delay
     * @param executions
     * @param onFinishTask
     * @param onExecution
     */
    public PausableTimer(long delay, int executions, Runnable onFinishTask, Runnable onExecution) {
        this.delay = delay;
        this.timer = new Timer();
        this.isPaused = false;
        this.isRunning = false;
        this.executions = executions;
        this.currentExecution = 0;
        this.onFinishTask = onFinishTask;
        this.onExecution = onExecution;
    }

    /////////////////
    // Timer controlls
    ////////////////

    /**
     * Starts excecuing the timer.
     * 
     */
    public void start() {
        if (!isRunning) {
            isRunning = true;
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    if (!isPaused) {
                        // Do whatever you want the timer to do
                        onExecution.run();
                        currentExecution++;
                        if (currentExecution == executions) {
                            stop();
                        }
                    }
                }
            }, 0, delay);
        }
    }

    /**
     * Pauses the timer.
     * 
     */
    public void pause() {
        isPaused = true;
    }

    public void resume() {
        isPaused = false;
    }

    public void stop() {
        timer.cancel();
        isRunning = false;
        if (onFinishTask != null) {
            onFinishTask.run();
        }
    }

    public void forceStop() {
        timer.cancel();
        isRunning = false;
    }

    /////////////////
    // Accesors
    ////////////////

    public long getTimeRemaining() {
        if (!isRunning) {
            return 0; // Timer is not running
        }
        long currentTime = System.currentTimeMillis();
        long nextExecutionTime = currentTime + (executions - currentExecution - 1) * delay;
        return nextExecutionTime - currentTime;
    }
}
