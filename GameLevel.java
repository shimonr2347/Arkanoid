import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Game.
 *
 * @author Shimon Rahamim
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private final Counter currentScore;

    private final AnimationRunner runner;
    private boolean running;
    private final KeyboardSensor keyboard;
    private final LevelInformation levelInformation;
    private Paddle paddle;
    private static final int PADDLE_HEIGHT = 18;
    private static final int BALL_SIZE = 5;

    /**
     * Instantiates a new Game level.
     *
     * @param levelInfo       the level info
     * @param keyboardSensor  the keyboard sensor
     * @param animationRunner the animation runner
     * @param currentScore    the current score
     */
    public GameLevel(LevelInformation levelInfo,
                     KeyboardSensor keyboardSensor, AnimationRunner animationRunner, Counter currentScore) {
        levelInformation = levelInfo;
        keyboard = keyboardSensor;
        runner = animationRunner;
        this.currentScore = currentScore;
    }

    /**
     * Add collidable.
     * the method that add new collidable to the game environment
     *
     * @param c the c
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * Add sprite.
     * the method that add new sprite to the sprites collection
     *
     * @param s the s
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Initialize.
     * the method that initialize the game components
     */
// Initialize a new game: create the Blocks and Ball (and Paddle)
    // and add them to the game.
    public void initialize() {
        this.running = true;
        sprites = new SpriteCollection(new ArrayList<>());
        environment = new GameEnvironment(new ArrayList<>());
        remainingBlocks = new Counter();
        remainingBalls = new Counter();
        sprites.addSprite(levelInformation.getBackground());
        ScoreIndicator scoreIndicator = new ScoreIndicator(currentScore);
        scoreIndicator.addToGame(this);
        sprites.addSprite(new GameName(levelInformation.levelName()));
        HitListener blockRemover = new BlockRemover(this, remainingBlocks);
        HitListener score = new ScoreTrackingListener(currentScore);
        createPaddle();
        createBalls();
        createBorders();
        createDeathRegion();
        List<Block> blockList = levelInformation.blocks();
        for (Block b : blockList) {
            b.addToGame(this);
            b.addHitListener(blockRemover);
            b.addHitListener(score);
            remainingBlocks.increase(1);
        }
    }

    /**
     * Create balls.
     */
    public void createBalls() {
        List<Velocity> velocityList = levelInformation.initialBallVelocities();
        for (int i = 0; i < levelInformation.numberOfBalls(); i++) {
            Ball ball = new Ball(400, 550, BALL_SIZE, Color.WHITE);
            ball.setVelocity(velocityList.get(i));
            ball.setGameEnvironment(environment);
            ball.addToGame(this);
            ball.setPaddle(paddle);
            remainingBalls.increase(1);
        }
    }

    /**
     * Create borders.
     */
    public void createBorders() {
        Block top = new Block(new Rectangle(new Point(0, 18), 800,
                25), Color.GRAY);
        Block left = new Block(new Rectangle(new Point(0, 43), 25,
                575), Color.GRAY);
        Block right = new Block(new Rectangle(new Point(775, 43), 25,
                575), Color.GRAY);
        top.addToGame(this);
        left.addToGame(this);
        right.addToGame(this);
    }

    /**
     * Create paddle.
     */
    public void createPaddle() {
        int x = levelInformation.paddleWidth();
        this.paddle = new Paddle(new Rectangle(new Point((int) ((800 - x) / 2),
                564),
                x, PADDLE_HEIGHT), Color.ORANGE);
        this.paddle.addToGame(this);
        paddle.setKeyboard(keyboard);
        paddle.setStep(levelInformation.paddleSpeed());
    }

    /**
     * Create death region.
     */
    public void createDeathRegion() {
        HitListener ballRemover = new BallRemover(this, remainingBalls);
        Block deathRegion = new Block(new Rectangle(new Point(25, 582), 750,
                1), Color.GRAY);
        deathRegion.addToGame(this);
        deathRegion.addHitListener(ballRemover);
    }

    /**
     * Run.
     * the method that display and run the game
     */
// Run the game -- start the animation loop.
    public void run() {
        this.running = true;
        this.runner.run(this);
    }

    /**
     * Remove collidable.
     * the method remove the requested collidable
     *
     * @param c the collidable
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);

    }

    /**
     * Remove sprite.
     * the method remove the requested sprite
     *
     * @param s the sprite
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (this.keyboard.isPressed("p")) {
            Animation pauseScreen = new PauseScreen(keyboard);
            Animation stoppable =
                    new KeyPressStoppableAnimation(keyboard, "space",
                            pauseScreen);
            this.runner.run(stoppable);
        }
        if (remainingBalls.getValue() == 0) {
            this.running = false;
        }
        if (remainingBlocks.getValue() == 0) {
            currentScore.increase(100);
            this.running = false;
        }

    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * Gets remaining balls.
     *
     * @return the remaining balls
     */
    public Counter getRemainingBalls() {
        return remainingBalls;
    }

}