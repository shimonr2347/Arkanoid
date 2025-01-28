import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The type You win.
 */
public class YouWin implements Animation {
    private final KeyboardSensor keyboard;
    private final boolean stop;
    private final Counter currentScore;

    /**
     * Instantiates a new You win.
     * the method initiate new you win screen
     *
     * @param k     the keyboard
     * @param score the score
     */
    public YouWin(KeyboardSensor k, Counter score) {
        this.keyboard = k;
        this.stop = false;
        this.currentScore = score;
    }


    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2,
                "You Win! Your score is" + " " + currentScore.getValue(), 32);
        d.drawText(10, d.getHeight() * 2 / 3, "press space to continue",
                20);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
