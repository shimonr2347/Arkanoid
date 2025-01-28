/**
 * The type Velocity.
 *
 * @author Shimon Rahamim
 */
// Shimon Rahamim 315249003
// Velocity specifies the change in position on the `x` and the `y` axes.
public class Velocity {
    // constructor
    private double dx;
    private double dy;

    /**
     * Instantiates a new Velocity.
     *
     * @param dx the dx value of the velocity
     * @param dy the dy value of the velocity
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Apply to point.
     * the method change the center of the ball values by adding the velocity
     *
     * @param p the point
     * @return the point after the change
     */
// Take a point with position (x,y) and return a new point
    // with position (x+dx, y+dy)
    public Point applyToPoint(Point p) {
        return new Point((p.getX() + this.dx), (p.getY() + this.dy));
    }

    /**
     * From angle and speed velocity.
     * the program convert the angle and speed to the dx,dy values
     *
     * @param angle the angle
     * @param speed the speed
     * @return the velocity of the ball
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        //converting the angle from degrees to radians.
        double rad = Math.toRadians(angle);
        double dx = Math.sin(rad) * speed;
        double dy = -Math.cos(rad) * speed;
        return new Velocity(dx, dy);
    }

    /**
     * Sets dx.
     * set the dx value of the velocity
     *
     * @param dx the dx
     */
    public void setDx(double dx) {
        this.dx = dx;
    }

    /**
     * Sets dy.
     * set the dy value of the velocity
     *
     * @param dy the dy
     */
    public void setDy(double dy) {
        this.dy = dy;
    }

    /**
     * Gets dx.
     * return the dx value of the velocity
     *
     * @return the dx
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Gets dy.
     * return the dy value of the velocity
     *
     * @return the dy
     */
    public double getDy() {
        return this.dy;
    }
}
