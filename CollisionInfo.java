/**
 * The type Collision info.
 *
 * @author Shimon Rahamim
 */
public class CollisionInfo {
    private final Point collision;
    private final Collidable collidable;

    /**
     * Instantiates a new Collision info.
     *
     * @param collision  the collision
     * @param collidable the collidable
     */
    public CollisionInfo(Point collision, Collidable collidable) {
        this.collision = collision;
        this.collidable = collidable;
    }

    /**
     * Collision point point.
     * return the collision point
     *
     * @return the point
     */
// the point at which the collision occurs.
    public Point collisionPoint() {
        return this.collision;
    }

    /**
     * Collision object collidable.
     * return the collision object
     *
     * @return the collidable
     */
// the collidable object involved in the collision.
    public Collidable collisionObject() {
        return this.collidable;
    }
}