import biuoop.DrawSurface;

/**
 * The interface Animation.
 */
public interface Animation {
    /**
     * Do one frame.
     * the method draw the sprites on the draw surface
     *
     * @param d the draw surface
     */
    void doOneFrame(DrawSurface d);

    /**
     * Should stop boolean.
     * the method return the boolean if the program needs to stop
     *
     * @return the boolean
     */
    boolean shouldStop();
}