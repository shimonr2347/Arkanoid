import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Green 3 background.
 */
public class Green3BackGround implements Sprite {
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 600;

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.GREEN);
        d.fillRectangle(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        d.fillRectangle(26, 582, 749, 5);
    }

    @Override
    public void timePassed() {

    }
}
