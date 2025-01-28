import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Block.
 *
 * @author Shimon Rahamim
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private static final double EPSILON = 0.000001; //threshold value
    /**
     * The Hit listeners.
     */
    private final List<HitListener> hitListeners;
    private final Rectangle rectangle;
    private final Color color;

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
            if (isOnSegment(rectangle.getRight(), collisionPoint)
                    || isOnSegment(rectangle.getLeft(), collisionPoint)) {
                currentVelocity.setDx(-currentVelocity.getDx());
            }
            if (isOnSegment(rectangle.getTop(), collisionPoint)
                    || isOnSegment(rectangle.getBottom(), collisionPoint)) {
                currentVelocity.setDy(-currentVelocity.getDy());
            }
            this.notifyHit(hitter);
        }
        return currentVelocity;
    }

    /**
     * Instantiates a new Block.
     * the method instance  a new block
     *
     * @param rectangle the rectangle
     * @param color     the color
     */
    public Block(Rectangle rectangle, Color color) {
        this.rectangle = rectangle;
        this.color = color;
        this.hitListeners = new ArrayList<>();
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
     * Gets color.
     * the method return the color of the block
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

    @Override
    public void timePassed() {

    }


    /**
     * Add to game.
     * the method add the block to the game
     *
     * @param gameLevel the game
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addCollidable(this);
        gameLevel.addSprite(this);
    }

    /**
     * Remove from game.
     * the method remove the requested block from the game
     *
     * @param gameLevel the game
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
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

    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
