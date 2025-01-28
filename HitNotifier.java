/**
 * The interface Hit notifier.
 *
 * @author Shimon Rahamim
 */
public interface HitNotifier {
    /**
     * Add hit listener.
     * the method add new hit listener
     *
     * @param hl the hit listener
     */
// Add hl as a listener to hit events.
    void addHitListener(HitListener hl);

    /**
     * Remove hit listener.
     * the method remove the requested hit listener
     *
     * @param hl the hl
     */
// Remove hl from the list of listeners to hit events.
    void removeHitListener(HitListener hl);
}