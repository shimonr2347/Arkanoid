import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Score indicator.
 *
 * @author Shimon Rahamim
 */
public class ScoreIndicator implements Sprite {
    private final Counter currentScore;

    /**
     * Instantiates a new Score indicator.
     * the method instance new score indicator
     *
     * @param score the score
     */
    public ScoreIndicator(Counter score) {
        this.currentScore = score;
    }

    /**
     * Add to game.
     * the method add the score indicator to the game
     *
     * @param gameLevel the game
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);

    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.fillRectangle(0, 0, 800, 18);
        d.setColor(Color.BLACK);
        d.drawText(400, 15, "Score:" + " " + currentScore.getValue(), 15);
    }

    @Override
    public void timePassed() {

    }
}
