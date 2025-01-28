import java.util.List;

/**
 * The type Line.
 *
 * @author Shimon Rahamim
 */
public class Line {
    // constructors
    private final Point start;
    private final Point end;
    private final double slope;
    private final double yIntercept;


    private static final double EPSILON = 0.000001; //threshold value

    /**
     * Instantiates a new Line.
     *
     * @param start the start point of the line
     * @param end   the end point of the line
     */
    public Line(Point start, Point end) {
        if (start.getX() < end.getX() - EPSILON) {
            this.start = start;
            this.end = end;
        } else if (start.getX() > end.getX() + EPSILON) {
            this.start = end;
            this.end = start;
        } else if (start.getY() < end.getY() - EPSILON) {
            this.start = start;
            this.end = end;
        } else {
            this.start = end;
            this.end = start;
        }

        if (Math.abs(start.getX() - end.getX()) < EPSILON) {
            // line is parallel to y-axis
            this.slope = Double.POSITIVE_INFINITY;
            this.yIntercept = start.getX();
        } else {
            this.slope = (this.end.getY() - this.start.getY())
                    / (this.end.getX() - this.start.getX());
            this.yIntercept =
                    this.start.getY() - (this.slope * this.start.getX());
        }
    }


    /**
     * Instantiates a new Line.
     *
     * @param x1 the x value of the start point
     * @param y1 the y value of the start point
     * @param x2 the x value of the end point
     * @param y2 the y value of the end point
     */
    public Line(double x1, double y1, double x2, double y2) {
        //setting the values according to the location
        if (x1 < x2 - EPSILON) {
            this.start = new Point(x1, y1);
            this.end = new Point(x2, y2);
        } else if (x1 > x2 + EPSILON) {
            this.start = new Point(x2, y2);
            this.end = new Point(x1, y1);
        } else if (y1 < y2 - EPSILON) {
            this.start = new Point(x1, y1);
            this.end = new Point(x2, y2);
        } else {
            this.start = new Point(x2, y2);
            this.end = new Point(x1, y1);
        }

        if (Math.abs(start.getX() - end.getX()) < EPSILON) {
            // line is parallel to y-axis
            slope = Double.POSITIVE_INFINITY;
            yIntercept = start.getX();
        } else {
            slope = (end.getY() - start.getY()) / (end.getX() - start.getX());
            yIntercept = start.getY() - slope * start.getX();
        }
    }

    /**
     * Length double.
     * the method calculate the length of the line using the distance formula
     *
     * @return the length of the line
     */
// Return the length of the line
    public double length() {
        // calculating the length using the distance formula
        return Math.sqrt(((this.start.getX() - this.end.getX())
                * (this.start.getX() - this.end.getX()))
                + ((this.start.getY() - this.end.getY())
                * (this.start.getY() - this.end.getY())));
    }

    /**
     * Middle point.
     * the method calculate the middle point of the line
     *
     * @return the middle point
     */
// Returns the middle point of the line
    public Point middle() {
        double midY = (this.start.getY() + this.end.getY()) / 2;
        double midX = (this.start.getX() + this.end.getX()) / 2;
        return new Point(midX, midY);
    }

    /**
     * Start point.
     * the method return the start point of the line
     *
     * @return the start point
     */
// Returns the start point of the line
    public Point start() {
        return new Point(this.start.getX(), this.start.getY());
    }

    /**
     * Gets slope.
     *
     * @return the slope
     */
    public Double getSlope() {
        return this.slope;
    }

    /**
     * Gets yintercept.
     *
     * @return the yintercept
     */
    public Double getYintercept() {
        return this.yIntercept;
    }

    /**
     * End point.
     * the method return the end point of the line
     *
     * @return the end point
     */
// Returns the end point of the line
    public Point end() {
        return new Point(this.end.getX(), this.end.getY());

    }


    /**
     * Intersection with point.
     * the method calculate the intersection point between the two given
     * lines and if the intersection point is between the range of the two
     *
     * @param other the other line
     * @return the intersection point
     */
    public Point intersectionWith(Line other) {
        double interX;
        double interY;
        if (slope == other.slope) {
            // lines are parallel
            if (slope == Double.POSITIVE_INFINITY
                    && yIntercept == other.yIntercept) {
                // lines are the same
                return start.equals(other.start) || start.equals(other.end)
                        ? start : end.equals(other.start)
                        || end.equals(other.end) ? end : null;
            } else if (slope != Double.POSITIVE_INFINITY) {
                return start.equals(other.start) || start.equals(other.end)
                        ? start : end.equals(other.start)
                        || end.equals(other.end) ? end : null;
            } else {
                return null;
            }
        } else if (slope == Double.POSITIVE_INFINITY) {
            // this line is parallel to the y-axis
            //noinspection SuspiciousNameCombination
            interX = yIntercept;
            interY = other.slope * interX + other.yIntercept;
        } else if (other.slope == Double.POSITIVE_INFINITY) {
            // other line is parallel to the y-axis
            //noinspection SuspiciousNameCombination
            interX = other.yIntercept;
            interY = slope * interX + yIntercept;
        } else {
            // find intersection point using linear equations
            interX = (other.yIntercept - yIntercept) / (slope - other.slope);
            interY = slope * interX + yIntercept;
        }

        // check if one line is a semi-line of the other
        if (start.equals(other.start) || start.equals(other.end)
                || end.equals(other.start) || end.equals(other.end)) {
            return null;
        }

        if (isOnSegment(interX, interY) && other.isOnSegment(interX, interY)) {
            // intersection point is within the range of both lines
            return new Point(interX, interY);
        } else {
            return null;
        }
    }

    /**
     * Is on segment boolean.
     * the method check if the point is on the line segment
     *
     * @param x the x value of the point
     * @param y the y value of the point
     * @return the boolean
     */
    private boolean isOnSegment(double x, double y) {
        // check if the point (x,y) is on the line segment
        double minX = Math.min(start.getX(), end.getX());
        double maxX = Math.max(start.getX(), end.getX());
        double minY = Math.min(start.getY(), end.getY());
        double maxY = Math.max(start.getY(), end.getY());
        return x >= minX - EPSILON && x <= maxX + EPSILON
                && y >= minY - EPSILON && y <= maxY + EPSILON;
    }

    /**
     * Contains point boolean.
     * check if the give point is within the line range
     *
     * @param p the point
     * @return the boolean
     */
    public boolean containsPoint(Point p) {
        double x = p.getX();
        double y = p.getY();
        if (slope == Double.POSITIVE_INFINITY) {
            return x == yIntercept && isOnSegment(x, y);
        } else {
            double yExpected = slope * x + yIntercept;
            return Math.abs(y - yExpected) < EPSILON && isOnSegment(x, y);
        }
    }

    /**
     * Is intersecting boolean.
     * check if the two line are intersect.
     *
     * @param other the other line
     * @return the boolean
     */
    public boolean isIntersecting(Line other) {
        if (slope == other.slope) {
            // lines are parallel
            return containsPoint(other.start) || containsPoint(other.end)
                    || other.containsPoint(start) || other.containsPoint(end);
        } else {
            Point intersection = intersectionWith(other);
            if (intersection != null) {
                return true;
            } else {
                // check if one line is a semi-line of the other
                return other.containsPoint(start) || other.containsPoint(end)
                        || containsPoint(other.start) || containsPoint(other.end);
            }
        }
    }


    /**
     * Equals boolean.
     * the method check if the two lines are equal by comparing both of their
     * points
     *
     * @param other the other line
     * @return the boolean if they are equal.
     */
// equals -- return true is the lines are equal, false otherwise
    public boolean equals(Line other) {
        return this.start.equals(other.start) && this.end.equals(other.end)
                || this.start.equals(other.end) && this.end.equals(other.start);
    }

    /**
     * Closest intersection to start of line point.
     * the method return the closest intersection point in the rectangle
     *
     * @param rect the rectangle
     * @return the point
     */
// If this line does not intersect with the rectangle, return null.
    // Otherwise, return the closest intersection point to the
    // start of the line.
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> list = rect.intersectionPoints(new Line(this.start,
                this.end));
        if (list.isEmpty()) {
            return null;
        } else if (list.size() == 1) {
            return list.get(0);
        } else {
            Point p1 = list.get(0);
            double x1 = p1.getX();
            Point p2 = list.get(1);
            double x2 = p2.getX();
            //check which point is closer to the start of the line
            if (x2 > x1) {
                return p1;
            } else {
                return p2;
            }
        }
    }
}