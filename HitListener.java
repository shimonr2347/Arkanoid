/**
 * The interface Hit listener.
 *
 * @author Shimon Rahamim
 */
public interface HitListener {
    /**
     * Hit event.
     * the method update the game fields after block being hit
     *
     * @param beingHit the block that being hit
     * @param hitter   the ball
     */
// This method is called whenever the beingHit object is hit.
    // The hitter parameter is the Ball that's doing the hitting.
    void hitEvent(Block beingHit, Ball hitter);
}
