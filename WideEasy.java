import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The type Wide easy.
 */
public class WideEasy implements LevelInformation {
    private static final int UNSIGNED_INT_LIMIT = 0xFFFFFF;
    //the range of the numbers to create a color
    private static final int BLOCK_WIDTH = 50;
    private static final int BLOCK_HEIGHT = 20;
    private static final int PADDLE_WIDTH = 600;
    private static final int START = 200;
    private static final int START_X = 25;
    private static final int MAX_COLUMNS = 15;
    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocityList = new ArrayList<>();
        for (int i = 0; i < numberOfBalls(); i++) {
            if (i < numberOfBalls() / 2) {
                velocityList.add(Velocity.fromAngleAndSpeed((i * 10) + 305,
                        6));
            } else {
                velocityList.add(Velocity.fromAngleAndSpeed((i * 10) + 315,
                        6));
            }
        }
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
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        return new WideEasyBackGround();
    }

    @Override
    public List<Block> blocks() {
        Random rand = new Random(); // create a random-number generator
        int color = rand.nextInt(UNSIGNED_INT_LIMIT);
        List<Block> blockList = new ArrayList<>();
        for (int i = 0; i < MAX_COLUMNS; i++) {
            Point p = new Point(START_X + i * BLOCK_WIDTH, START);
            Block block = new Block(new Rectangle(p, BLOCK_WIDTH,
                    BLOCK_HEIGHT), new Color(color + (i * 2000)));
            blockList.add(block);
        }
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}
