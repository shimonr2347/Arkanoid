import biuoop.GUI;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Shimon Rahamim
 */
public class Game {
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 600;

    /**
     * The entry point of application.
     * the start of thr game
     *
     * @param args the input arguments of the levels
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid", SCREEN_WIDTH, SCREEN_HEIGHT);
        biuoop.KeyboardSensor keyboard = gui.getKeyboardSensor();
        AnimationRunner runner = new AnimationRunner(gui);
        GameFlow gameFlow = new GameFlow(runner, keyboard);
        List<LevelInformation> levels = new ArrayList<>();
        for (String arg : args) {
            if (arg.equals("1")) {
                levels.add(new DirectHit());
            }
            if (arg.equals("2")) {
                levels.add(new WideEasy());
            }
            if (arg.equals("3")) {
                levels.add(new Green3());
            }
        }
        if (levels.size() == 0) {
            levels.add(new DirectHit());
            levels.add(new WideEasy());
            levels.add(new Green3());
        }
        gameFlow.runLevels(levels);
    }
}
