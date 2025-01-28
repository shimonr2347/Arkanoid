import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The type Pause screen.
 */
public class PauseScreen implements Animation {
    private final KeyboardSensor keyboard;
    private boolean stop;

    /**
     * Instantiates a new Pause screen.
     * the method initiate new pause screen
     *
     * @param k the keyboard
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}