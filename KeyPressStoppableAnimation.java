import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The type Key press stoppable animation.
 */
public class KeyPressStoppableAnimation implements Animation {
    private final KeyboardSensor keyboard;
    private final String key;
    private boolean stop;
    private final Animation animation;
    private boolean isAlreadyPressed;


    /**
     * Instantiates a new Key press stoppable animation.
     * the method initiate new key press stoppable animation
     *
     * @param sensor    the keyboard sensor
     * @param key       the requested key
     * @param animation the animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key,
                                      Animation animation) {
        this.stop = false;
        keyboard = sensor;
        this.key = key;
        this.animation = animation;
        this.isAlreadyPressed = true;

    }

    @Override
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);
        if (this.keyboard.isPressed(key)) {
            if (!isAlreadyPressed) {
                this.stop = true;
            }
        } else {
            isAlreadyPressed = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return stop;
    }
    // ...
    // think about the implementations of doOneFrame and shouldStop.
}