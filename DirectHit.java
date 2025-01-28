import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The type Direct hit.
 */
public class DirectHit implements LevelInformation {

    private static final int UNSIGNED_INT_LIMIT = 0xFFFFFF;
    //the range of the numbers to create a color
    private static final int PADDLE_WIDTH = 75;

    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocityList = new ArrayList<>();
        velocityList.add(Velocity.fromAngleAndSpeed(0,
                6));
        return velocityList;
    }

    @Override
    public int paddleSpeed() {
        return 8;
    }

    @Override
    public int paddleWidth() {
        return PADDLE_WIDTH;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        return new DirectHitBackGround();
    }

    @Override
    public List<Block> blocks() {
        Random rand = new Random(); // create a random-number generator
        int color = rand.nextInt(UNSIGNED_INT_LIMIT);
        List<Block> blockList = new ArrayList<>();
        Block block = new Block(new Rectangle(new Point(390, 100), 30, 30),
                new Color(color));
        blockList.add(block);
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }

}
