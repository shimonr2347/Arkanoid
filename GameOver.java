import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The type Game over.
 */
public class GameOver implements Animation {
    private final KeyboardSensor keyboard;
    private final boolean stop;
    private final Counter currentScore;

    /**
     * Instantiates a new Game over.
     * the method initiate new game over screen
     *
     * @param k     the keyboard
     * @param score the score
     */
    public GameOver(KeyboardSensor k, Counter score) {
        this.keyboard = k;
        this.stop = false;
        this.currentScore = score;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2,
                "Game Over. Your score is" + " " + currentScore.getValue(), 32);
        d.drawText(10, d.getHeight() * 2 / 3, "press space to continue",
                20);
    }


    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}

