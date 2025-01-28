import java.util.ArrayList;
import java.util.List;

/**
 * The type Rectangle.
 *
 * @author Shimon Rahamim
 */
public class Rectangle {
    private Point start;
    private final double width;
    private final double height;
    private final Line top;
    private final Line bottom;
    private final Line right;
    private final Line left;


    /**
     * Instantiates a new Rectangle.
     * the method instance new rectangle
     *
     * @param upperLeft the upper left
     * @param width     the width
     * @param height    the height
     */
// Create a new rectangle with location and width/height.
    public Rectangle(Point upperLeft, double width, double height) {
        this.start = upperLeft;
        this.width = width;
        this.height = height;
        Point upperRight = new Point(upperLeft.getX()
                + this.getWidth(), upperLeft.getY());
        Point lowerLeft = new Point(upperLeft.getX(), upperLeft.getY()
                + this.getHeight());
        Point lowerRight = new Point(upperLeft.getX()
                + this.getWidth(), upperLeft.getY() + this.getHeight());
        this.top = new Line(upperLeft, upperRight);
        this.bottom = new Line(lowerLeft, lowerRight);
        this.left = new Line(lowerLeft, upperLeft);
        this.right = new Line(lowerRight, upperRight);
    }

    /**
     * Intersection points java . util . list.
     * the method return the list of intersecting points with the rectangle
     *
     * @param line the line
     * @return the java . util . list
     */
// Return a (possibly empty) List of intersection points
    // with the specified line.
    public java.util.List<Point> intersectionPoints(Line line) {
        Line[] lines = new Line[4];
        lines[0] = this.top;
        lines[1] = this.bottom;
        lines[2] = this.left;
        lines[3] = this.right;
        List<Point> list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Point p = line.intersectionWith(lines[i]);
            if (p != null) {
                list.add(p);
            }
        }
        return list;
    }

    /**
     * Gets width.
     * the method return the width of the rectangle
     *
     * @return the width
     */
// Return the width and height of the rectangle
    public double getWidth() {
        return this.width;
    }

    /**
     * Gets height.
     * the method return the height of the rectangle
     *
     * @return the height
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Gets upper left.
     * *the method return the upper-left point of the rectangle
     *
     * @return the upper left
     */
// Returns the upper-left point of the rectangle.
    public Point getUpperLeft() {

        return this.start;
    }

    /**
     * Sets start.
     * the method set the start point of the rectangle
     *
     * @param point the point
     */
    public void setStart(Point point) {
        this.start = point;
    }

    /**
     * Gets right.
     * the method return the right edge of the rectangle
     *
     * @return the right
     */
    public Line getRight() {
        return this.right;
    }

    /**
     * Gets left.
     * the method return the left edge of the rectangle
     *
     * @return the left
     */
    public Line getLeft() {
        return this.left;
    }

    /**
     * Gets top.
     * the method return the top edge of the rectangle
     *
     * @return the top
     */
    public Line getTop() {
        return this.top;
    }

    /**
     * Gets bottom.
     * the method return the bottom edge of the rectangle
     *
     * @return the bottom
     */
    public Line getBottom() {
        return this.bottom;
    }

    /**
     * Is inside boolean.
     * the method check if the point is inside the rectangle borders
     *
     * @param point the point
     * @return the boolean
     */
    public Boolean isInside(Point point) {
        double x = point.getX();
        double y = point.getY();
        return x > this.getLeft().start().getX()
                && x < this.getRight().start().getX()
                && y > this.getLeft().start().getY()
                && y > this.getLeft().end().getY();
    }
}
