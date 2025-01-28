import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The type Green 3.
 */
public class Green3 implements LevelInformation {

    private static final int UNSIGNED_INT_LIMIT = 0xFFFFFF;
    //the range of the numbers to create a color
    private static final int BLOCK_WIDTH = 50;
    private static final int BLOCK_HEIGHT = 20;
    private static final int PADDLE_WIDTH = 75;
    private static final int START = 100;
    private static final int START_X = 175;
    private static final int MAX_ROWS = 6;
    private static final int MAX_COLUMNS = 12;

    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        ArrayList<Velocity> velocityList = new ArrayList<>();
        velocityList.add(Velocity.fromAngleAndSpeed(330,
                6));
        velocityList.add(Velocity.fromAngleAndSpeed(30,
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
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        return new Green3BackGround();
    }

    @Override
    public List<Block> blocks() {
        Random rand = new Random(); // create a random-number generator
        int color = rand.nextInt(UNSIGNED_INT_LIMIT);
        ArrayList<Block> blockList = new ArrayList<>();
        for (int i = 0; i < MAX_ROWS; i++) {
            for (int j = i; j < MAX_COLUMNS; j++) {
                Point p = new Point(START_X + j * BLOCK_WIDTH,
                        START + i * BLOCK_HEIGHT);
                Block block = new Block(new Rectangle(p, BLOCK_WIDTH,
                        BLOCK_HEIGHT), new Color(color + (i * 2000)));
                blockList.add(block);
            }
        }
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}
