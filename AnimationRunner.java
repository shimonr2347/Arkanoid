import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;


/**
 * The type Animation runner.
 */
public class AnimationRunner {
    private final GUI gui;
    private final int framesPerSecond;
    private final Sleeper sleeper;
    private static final int MILLISECONDS_PER_FRAME = 1000;

    /**
     * Instantiates a new Animation runner.
     *
     * @param gui the gui
     */
    public AnimationRunner(GUI gui) {
        this.gui = gui;
        this.framesPerSecond = 60;
        this.sleeper = new Sleeper();
    }

    /**
     * Run.
     *
     * @param animation the animation
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = MILLISECONDS_PER_FRAME / framesPerSecond; // timing
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis();
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**
     * Gets gui.
     * return the gui
     *
     * @return the gui
     */
    public GUI getGui() {
        return gui;
    }
}