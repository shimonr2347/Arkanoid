/**
 * The type Point.
 *
 * @author Shimon Rahamim
 */
public class Point {
    // constructor
    private final double x;
    private final double y;

    private static final double EPSILON = 0.000001; //threshold value

    /**
     * Instantiates a new Point.
     *
     * @param x the x value of the point
     * @param y the y value of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Distance double.
     * the method calculate the distance between two points using the distance
     * formula
     *
     * @param other the other point
     * @return the length of the line
     */
// distance -- return the distance of this point to the other point
    public double distance(Point other) {
        // calculating the length using the distance formula
        return Math.sqrt(((other.x - this.x) * (other.x - this.x))
                + ((other.y - this.y) * (other.y - this.y)));
    }

    /**
     * Equals boolean.
     * the method check if the two points are equal
     *
     * @param other the other point
     * @return the boolean if the points are equal
     */
// equals -- return true is the points are equal, false otherwise
    public boolean equals(Point other) {
        return (Math.abs(other.y - this.y) < EPSILON)
                && (Math.abs(other.x - this.x) < EPSILON);
    }

    /**
     * Gets x.
     * the method return the x value of the point
     *
     * @return the x value of the point
     */
// Return the x and y values of this point
    public double getX() {
        return this.x;
    }

    /**
     * Gets y.
     * the method return the y value of the point
     *
     * @return the y value of the point
     */
    public double getY() {
        return this.y;
    }
}