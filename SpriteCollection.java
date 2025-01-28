import biuoop.DrawSurface;

import java.util.ArrayList;

/**
 * The type Sprite collection.
 *
 * @author Shimon Rahamim
 */
public class SpriteCollection {
    private final ArrayList<Sprite> sprites;

    /**
     * Instantiates a new Sprite collection.
     * the method instance new array list of sprites
     *
     * @param sprites the sprites
     */
    public SpriteCollection(ArrayList<Sprite> sprites) {
        this.sprites = sprites;
    }

    /**
     * Add sprite.
     * the method add new sprite to the list
     *
     * @param s the s
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * Remove sprite.
     * the method remove the requested sprite from the list
     *
     * @param s the s
     */
    public void removeSprite(Sprite s) {
        this.sprites.remove(s);
    }

    /**
     * Notify all time passed.
     * the method update the sprites that the time passed
     */
// call timePassed() on all sprites.
    public void notifyAllTimePassed() {
        int size = sprites.size();
        for (int i = 0; i < sprites.size(); i++) {
            sprites.get(i).timePassed();
            if (size != sprites.size()) {
                i--;
                size = sprites.size();
            }
        }
    }


    /**
     * Draw all on.
     * the method draw all the sprites
     *
     * @param d the d
     */
// call drawOn(d) on all sprites.
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : sprites) {
            s.drawOn(d);
        }
    }
}