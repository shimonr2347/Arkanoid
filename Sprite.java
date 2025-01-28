import biuoop.DrawSurface;

/**
 * The interface Sprite.
 *
 * @author Shimon Rahamim
 */
public interface Sprite {
    /**
     * Draw on.
     * the method draw the sprite on the screen
     *
     * @param d the d
     */
// draw the sprite to the screen
    void drawOn(DrawSurface d);

    /**
     * Time passed.
     * the method update the game components after the time pass
     */
// notify the sprite that time has passed
    void timePassed();
}