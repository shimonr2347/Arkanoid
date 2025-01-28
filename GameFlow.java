import biuoop.KeyboardSensor;

import java.util.List;

/**
 * The type Game flow.
 */
public class GameFlow {
    private final AnimationRunner animationRunner;
    private final KeyboardSensor keyboardSensor;
    private final Counter currentScore;


    /**
     * Instantiates a new Game flow.
     * the method initiate new game flow
     *
     * @param ar the animation runner
     * @param ks the keyboard
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        animationRunner = ar;
        keyboardSensor = ks;
        currentScore = new Counter();

    }

    /**
     * Run levels.
     * the method run the levels
     *
     * @param levels the levels
     */
    public void runLevels(List<LevelInformation> levels) {
        boolean win = true;
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor,
                    this.animationRunner, this.currentScore);
            level.initialize();

            while (!level.shouldStop()) {
                level.run();
            }
            if (level.getRemainingBalls().getValue() == 0) {
                win = false;
                break;
            }
        }
        if (win) {
            Animation youWin = new YouWin(keyboardSensor, currentScore);
            Animation stoppable =
                    new KeyPressStoppableAnimation(keyboardSensor, "space",
                            youWin);
            animationRunner.run(stoppable);
        } else {
            Animation gameOver = new GameOver(keyboardSensor, currentScore);
            Animation stoppable =
                    new KeyPressStoppableAnimation(keyboardSensor, "space",
                            gameOver);
            animationRunner.run(stoppable);
        }
        this.animationRunner.getGui().close();
    }
}

