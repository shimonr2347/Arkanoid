import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Game name.
 */
public class GameName implements Sprite {
    private final String name;

    /**
     * Instantiates a new Game name.
     *
     * @param name the name
     */
    public GameName(String name) {
        this.name = name;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(100, 15, name, 15);
    }

    @Override
    public void timePassed() {

    }
}
