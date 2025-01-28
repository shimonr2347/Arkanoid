/**
 * The type Score tracking listener.
 *
 * @author Shimon Rahamim
 */
public class ScoreTrackingListener implements HitListener {
    private final Counter currentScore;

    /**
     * Instantiates a new Score tracking listener.
     * the method instance new score tracking listener
     *
     * @param scoreCounter the score counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(5);
    }
}