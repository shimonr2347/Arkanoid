import java.util.ArrayList;


/**
 * The type Game environment.
 *
 * @author Shimon Rahamim
 */
public class GameEnvironment {
    private final ArrayList<Collidable> collidables;

    /**
     * Instantiates a new Game environment.
     * the method instance new array list of the collidables
     *
     * @param collidables the collidables
     */
    public GameEnvironment(ArrayList<Collidable> collidables) {
        this.collidables = collidables;
    }

    /**
     * Add collidable.
     * the method add new collidable to the list
     *
     * @param c the collidable
     */
// add the given collidable to the environment.
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * Remove collidable.
     * the method remove the requested collidable from the list
     *
     * @param c the c
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }

    /**
     * Get game environment array list.
     * the method return the array list of the collidables
     *
     * @return the array list
     */
    public ArrayList<Collidable> getGameEnvironment() {
        return this.collidables;
    }

    /**
     * Get closest collision.
     * the method return the closest collision with the trajectory
     *
     * @param trajectory the trajectory
     * @return the collision info
     */
// Assume an object moving from line.start() to line.end().
    // If this object will not collide with any of the collidables
    // in this collection, return null. Else, return the information
    // about the closest collision that is going to occur.
    public CollisionInfo getClosestCollision(Line trajectory) {
        CollisionInfo closetCollision = null;
        double closetDistance = Double.POSITIVE_INFINITY;
        for (Collidable c : collidables) {
            Point inter = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
            if (inter != null) {
                double distance = trajectory.start().distance(inter);
                if (distance < closetDistance) {
                    closetDistance = distance;
                    closetCollision = new CollisionInfo(inter, c);
                }
            }
        }
        return closetCollision;
    }

}