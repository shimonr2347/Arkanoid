import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * The type Paddle.
 *
 * @author Shimon Rahamim
 */
public class Paddle implements Collidable, Sprite {
    private static final double EPSILON = 0.000001; //threshold value
    private static final double MIN = 25;
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rectangle;
    private final Color color;
    private int step;

    /**
     * Instantiates a new Paddle.
     * the method initialize the paddle
     *
     * @param rectangle the rectangle
     * @param color     the color
     */
    public Paddle(Rectangle rectangle, Color color) {
        this.rectangle = rectangle;
        this.color = color;
    }

    /**
     * Move left.
     * the method move the paddle left on the screen
     */
    public void moveLeft() {
        if (this.rectangle.getUpperLeft().getX() - step < MIN) {
            this.rectangle = new Rectangle(new Point(MIN,
                    this.rectangle.getUpperLeft().getY()),
                    this.rectangle.getWidth(), this.rectangle.getHeight());
        } else {
            this.rectangle = new Rectangle(new Point(this.rectangle.getUpperLeft()
                    .getX() - step, this.rectangle.getUpperLeft().getY()),
                    this.rectangle.getWidth(), this.rectangle.getHeight());

        }
    }

    /**
     * Move right.
     * the method move the paddle right on the screen
     */
    public void moveRight() {
        int max = 775 - (int) rectangle.getWidth();
        if (this.rectangle.getUpperLeft().getX() + step > max) {
            this.rectangle = new Rectangle(new Point(max, this.rectangle.
                    getUpperLeft().getY()), this.rectangle.getWidth(),
                    this.rectangle.getHeight());
        } else {
            this.rectangle = new Rectangle(new Point(this.rectangle.getUpperLeft()
                    .getX() + step, this.rectangle.getUpperLeft().getY()),
                    this.rectangle.getWidth(), this.rectangle.getHeight());
        }
    }

    // Sprite
    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }

    /**
     * Gets color.
     * the method return the color of the paddle
     *
     * @return the color
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(getColor());
        d.fillRectangle((int) this.rectangle.getUpperLeft().getX(),
                (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(),
                (int) this.rectangle.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.rectangle.getUpperLeft().getX(),
                (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(),
                (int) this.rectangle.getHeight());
    }

    // Collidable
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint,
                        Velocity currentVelocity) {
        if (collisionPoint == null) {
            return null;
        } else {
            double size = this.rectangle.getWidth() / 5;
            double speed = Math.sqrt((currentVelocity.getDx()
                    * currentVelocity.getDx()) + (currentVelocity.getDy() * currentVelocity.getDy()));
            double x = collisionPoint.getX();
            double rectangleSize = this.rectangle.getUpperLeft().getX();
            if (isOnSegment(this.rectangle.getLeft(), collisionPoint)) {
                currentVelocity = Velocity.fromAngleAndSpeed(300, speed);
            }
            if (isOnSegment(this.rectangle.getRight(), collisionPoint)) {
                currentVelocity = Velocity.fromAngleAndSpeed(60, speed);
            }
            if (x > rectangleSize && x < rectangleSize + size) {
                currentVelocity = Velocity.fromAngleAndSpeed(300, speed);
            }
            if (x > rectangleSize + size && x < rectangleSize + (size * 2)) {
                currentVelocity = Velocity.fromAngleAndSpeed(330, speed);
            }
            if (x > rectangleSize + (size * 2) && x < rectangleSize + (size * 3)) {
                currentVelocity.setDy(-currentVelocity.getDy());
            }
            if (x > rectangleSize + (size * 3) && x < rectangleSize + (size * 4)) {
                currentVelocity = Velocity.fromAngleAndSpeed(30, speed);
            }
            if (x > rectangleSize + (size * 4) && x < rectangleSize + (size * 5)) {
                currentVelocity = Velocity.fromAngleAndSpeed(60, speed);
            }
        }
        return currentVelocity;
    }

    /**
     * Add to game.
     * the method add the paddle to the game
     *
     * @param gameLevel the game
     */
// Add this paddle to the game.
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addCollidable(this);
        gameLevel.addSprite(this);
    }

    /**
     * Sets keyboard.
     * the method set the keyboard that move the paddle
     *
     * @param keyboard the keyboard
     */
    public void setKeyboard(KeyboardSensor keyboard) {
        this.keyboard = keyboard;
    }

    /**
     * Is on segment boolean.
     * the method check if the point is on the line segment
     *
     * @param l     the line
     * @param point the point
     * @return the boolean
     */
    public static boolean isOnSegment(Line l, Point point) {
        double x = point.getX();
        double y = point.getY();
        Point start = l.start();
        Point end = l.end();
        double minX = Math.min(start.getX(), end.getX());
        double maxX = Math.max(start.getX(), end.getX());
        double minY = Math.min(start.getY(), end.getY());
        double maxY = Math.max(start.getY(), end.getY());
        return x >= minX - EPSILON && x <= maxX + EPSILON
                && y >= minY - EPSILON && y <= maxY + EPSILON;
    }

    /**
     * Sets step.
     * the method set the movement for the paddle
     *
     * @param step the step
     */
    public void setStep(int step) {
        this.step = step;
    }
}