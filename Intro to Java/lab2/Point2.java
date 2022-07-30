package lab2;

/**
 * A Cartesian point in 2-dimensions having real coordinates.
 *
 */
public class Point2 {
	
	/**
	 * The coordinates of this point.
	 */
	private double x;
	private double y;

	/**
	 * Initializes the elements of this point to {@code (0.0, 0.0)}.
	 */
	public Point2() {
		this.x = 0.0;
		this.y = 0.0;
	}
		
	/**
     * Initializes the coordinates of this point to {@code (x, y)} where
     * {@code x} and {@code y} are specified by the caller.
     *
     * @param x the x value of this point
     * @param y the y value of this point
     */
    public Point2(double x, double y) {
            this.x = x;
            this.y = y;
    }

    /**
     * Initializes the coordinates of this point by copying the coordinates
     * from {@code other}.
     *
     * @param other the point to copy
     */
    public Point2(Point2 other) {
            this(other.x, other.y);
    }

	/**
	 * Returns the x coordinate.
	 * 
	 * @return the x coordinate
	 */
	public double x() {
		return this.x;
	}
	
	/**
	 * Returns the y coordinate.
	 * 
	 * @return the y coordinate
	 */
	public double y() {
		return this.y;
	}
	
	/**
	 * Sets the x coordinate to the specified value.
	 * 
	 * @param newX the new x coordinate
	 * @return a reference to this point
	 */
	public Point2 x(double newX) {
		this.x = newX;
		return this;
	}
	
	/**
	 * Sets the y coordinate to the specified value.
	 * 
	 * @param newY the new y coordinate
	 * @return a reference to this point
	 */
	public Point2 y(double newY) {
		this.y = newY;
		return this;
	}
	
	/**
	 * Sets the x and y coordinate to the specified values.
	 * 
	 * @param newX the new x coordinate
	 * @param newY the new y coordinate
	 * @return a reference to this point
	 */
	public Point2 set(double newX, double newY) {
		this.x = newX;
		this.y = newY;
		return this;
	}
	
	/**
	 * Compares this point to the specified object. The result is {@code true} if
	 * and only if the argument is not {@code null} and is a {@code Point2} object
	 * that has the same x and y coordinates as this object.
	 * 
	 * @param obj the object to compare this point against
	 * @return true if the given object represents a Point2 with the same x and y
	 *         coordinates as this point, false otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Point2)) {
			return false;
		}
		Point2 other = (Point2) obj;
		if (Double.compare(this.x, other.x) == 0 && Double.compare(this.y, other.y) == 0) {
			return true;
		}
		return false;
	}

	/**
	 * Returns a hash code for this point computed using the coordinates of this
	 * point.
	 * 
	 * @return a hash code for this point
	 */
	@Override
	public int hashCode() {
		int result = Double.hashCode(this.x);
		int c = Double.hashCode(this.y);
		result = 31 * result + c;
		return result;
	}
	
	
	/**
	 * Returns a string representation of this point. The string
	 * representation are the coordinates of the point separated by
	 * a comma and space all inside a pair of parentheses.
	 * 
	 * @return a string representation of this point
	 */
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder("(");
		s.append(this.x).
			append(", ").
			append(this.y).
			append(")");
		return s.toString();
	}
	
}
