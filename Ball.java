import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Ball.
 *
 * @author Shimon Rahamim
 */
public class Ball implements Sprite, HitNotifier {
    // constructor
    private Point center;
    private final int radius;
    private final Color color;
    private Velocity velocity;
    private GameEnvironment environment;
    private Paddle paddle;
    private List<HitListener> hitListeners;

    /**
     * Instantiates a new Ball.
     * the program create new ball and gives it its values
     *
     * @param x     the x value of the ball's center
     * @param y     the y value of the ball's center
     * @param r     the radius of the ball
     * @param color the color of the ball
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Instantiates a new Ball.
     * the program create new ball and gives it its values
     *
     * @param center the center of the ball
     * @param r      the radius of the ball
     * @param color  the color of the ball
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;

    }

    /**
     * Sets paddle.
     *
     * @param paddle the paddle
     */
    public void setPaddle(Paddle paddle) {
        this.paddle = paddle;
    }

    /**
     * Gets center.
     * the program return the ball's center
     *
     * @return the center
     */
    public Point getCenter() {
        return this.center;
    }

    /**
     * Gets x.
     * the program return the ball's center x value
     *
     * @return the x
     */
// accessors
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Gets y.
     * the program return the ball's center y value
     *
     * @return the y
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Gets size.
     * the program return the ball's radius size
     *
     * @return the size
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * Gets color.
     * the program return the ball's color
     *
     * @return the color
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Draw on.
     * the program draw the ball
     *
     * @param surface the surface that the ball draws on
     */
// draw the ball on the given DrawSurface
    public void drawOn(DrawSurface surface) {
        surface.setColor(getColor());
        surface.fillCircle(this.getX(), this.getY(), this.getSize());
    }

    /**
     * Time passed.
     * the method call "moveOneStep" to update the ball location
     */
    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * Sets velocity.
     * the program sets the ball's velocity
     *
     * @param v the v
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Sets velocity.
     * the program sets the ball's velocity dx,dy values
     *
     * @param dx the dx
     * @param dy the dy
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Gets velocity.
     * the program return the ball's velocity
     *
     * @return the velocity
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Move one step.
     * the method move the ball
     */
    public void moveOneStep() {
        Line trajectory = new Line(this.getCenter(),
                this.velocity.applyToPoint(this.center));
        CollisionInfo collision = environment.getClosestCollision(trajectory);
        if (collision == null) {
            this.center = this.getVelocity().applyToPoint(this.center);
        } else {
            if (collision.collisionObject().getCollisionRectangle().getLeft()
                    .containsPoint(collision.collisionPoint())) {
                double x = collision.collisionPoint().getX() - this.getSize();
                double y = (trajectory.getSlope() * x)
                        + trajectory.getYintercept();
                this.center = new Point(x, y);
                this.velocity = collision.collisionObject()
                        .hit(this, collision.collisionPoint(), this.velocity);
            }
            if (collision.collisionObject().getCollisionRectangle().getRight()
                    .containsPoint(collision.collisionPoint())) {
                double x = collision.collisionPoint().getX() + this.getSize();
                double y = (trajectory.getSlope() * x)
                        + trajectory.getYintercept();
                this.center = new Point(x, y);
                this.velocity = collision.collisionObject()
                        .hit(this, collision.collisionPoint(), this.velocity);
            }
            if (collision.collisionObject().getCollisionRectangle().getBottom()
                    .containsPoint(collision.collisionPoint())) {
                if (trajectory.getSlope() == Double.POSITIVE_INFINITY) {
                    this.center = new Point(this.center.getX(),
                            collision.collisionPoint().getY() - this.getSize());
                } else {
                    double x;
                    if (trajectory.getSlope() < 0) {
                        x = collision.collisionPoint().getX() - this.getSize();
                    } else {
                        x = collision.collisionPoint().getX() + this.getSize();
                    }
                    double y = (trajectory.getSlope() * x)
                            + trajectory.getYintercept();
                    this.center = new Point(x, y);
                }
                this.velocity = collision.collisionObject()
                        .hit(this, collision.collisionPoint(), this.velocity);
            }
            if (collision.collisionObject().getCollisionRectangle().getTop()
                    .containsPoint(collision.collisionPoint())) {
                if (trajectory.getSlope() == Double.POSITIVE_INFINITY) {
                    this.center = new Point(this.center.getX(),
                            collision.collisionPoint().getY() + this.getSize());
                } else {
                    double x;
                    if (trajectory.getSlope() > 0) {
                        x = collision.collisionPoint().getX() - this.getSize();
                    } else {
                        x = collision.collisionPoint().getX() + this.getSize();
                    }
                    double y = (trajectory.getSlope() * x)
                            + trajectory.getYintercept();
                    this.center = new Point(x, y);
                    if (this.paddle.getCollisionRectangle().isInside(this.center)) {
                        this.center = new Point(this.center.getX(),
                                this.center.getY() + this.paddle.getCollisionRectangle().getHeight() + this.getSize());
                    }
                    this.velocity = collision.collisionObject()
                            .hit(this, collision.collisionPoint(),
                                    this.velocity);
                }
            }

        }
    }


    /**
     * Sets game environment.
     * the method set the game environment for the ball
     *
     * @param gameEnvironment the game environment
     */
    public void setGameEnvironment(GameEnvironment gameEnvironment) {
        this.environment = gameEnvironment;
    }

    /**
     * Add to game.
     * the method add the ball to the sprite collection
     *
     * @param gameLevel the game
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);

    }

    /**
     * Remove from game.
     * the method remove the ball from the game
     *
     * @param gameLevel the game
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

}
