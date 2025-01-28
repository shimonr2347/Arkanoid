/**
 * The interface Collidable.
 *
 * @author Shimon Rahamim
 */
public interface Collidable {
    /**
     * Gets collision rectangle.
     * return the recangle of the collision object
     *
     * @return the collision rectangle
     */
// Return the "collision shape" of the object.
    Rectangle getCollisionRectangle();

    /**
     * Hit velocity.
     * update the new ball velocity by the location of the hit point
     *
     * @param hitter          the ball
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity
     * @return the velocity
     */
// Notify the object that we collided with it at collisionPoint with
    // a given velocity.
    // The return is the new velocity expected after the hit (based on
    // the force the object inflicted on us).
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);


}