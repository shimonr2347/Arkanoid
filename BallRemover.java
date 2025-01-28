/**
 * The type Ball remover.
 *
 * @author Shimon Rahamim
 */
public class BallRemover implements HitListener {
    // a BlockRemover is in charge of removing blocks from the game, as well as keeping count
// of the number of blocks that remain.

    private final GameLevel gameLevel;
    private final Counter remainingBalls;

    /**
     * Instantiates a new Ball remover.
     * the method remove the ball from the game
     *
     * @param gameLevel         the game
     * @param removedBalls the removed balls
     */
    public BallRemover(GameLevel gameLevel, Counter removedBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = removedBalls;
    }

    // Blocks that are hit should be removed
    // from the game. Remember to remove this listener from the block
    // that is being removed from the game.
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeHitListener(this);
        hitter.removeFromGame(gameLevel);
        remainingBalls.decrease(1);
    }
}


