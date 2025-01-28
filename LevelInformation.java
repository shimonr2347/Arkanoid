import java.util.List;

/**
 * The interface Level information.
 */
public interface LevelInformation {
    /**
     * Number of balls int.
     * the method return the number of the balls in the game
     *
     * @return the int
     */
    int numberOfBalls();

    /**
     * Initial ball velocities list.
     * the method return new list of the ball's velocities
     *
     * @return the list
     */
// The initial velocity of each ball
    // Note that initialBallVelocities().size() == numberOfBalls()
    List<Velocity> initialBallVelocities();

    /**
     * Paddle speed int.
     * the method return the speed of the paddle
     *
     * @return the int
     */
    int paddleSpeed();

    /**
     * Paddle width int.
     * the method return the width of the paddle
     *
     * @return the int
     */
    int paddleWidth();

    /**
     * Level name string.
     * the method return the level's name
     *
     * @return the string
     */
// the level name will be displayed at the top of the screen.
    String levelName();

    /**
     * Gets background.
     * the method return the background of the game
     *
     * @return the background
     */
// Returns a sprite with the background of the level
    Sprite getBackground();

    /**
     * Blocks list.
     * the method return list of the block
     *
     * @return the list
     */
// The Blocks that make up this level, each block contains
    // its size, color and location.
    List<Block> blocks();

    /**
     * Number of blocks to remove int.
     * the method return the number of the blocks that needed to remove
     *
     * @return the int
     */
// Number of blocks that should be removed
    // before the level is considered to be "cleared".
    // This number should be <= blocks.size();
    int numberOfBlocksToRemove();
}