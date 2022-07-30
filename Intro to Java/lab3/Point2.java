package ca.queensu.cs.cisc124.notes.basics.geometry;

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
     * Description here.
     * 
     * @param newX x coordinate of this point
     * @param y y coordinate of this point
     */
    public Point2(double newX, double y) {
    	this.x = newX;
    	this.y = y;
    }

    /**
     * Initializes the coordinates of this point by copying the coordinates
     * from {@code other}.
     *
     * @param other the point to copy
     */
    public Point2(Point2 other) {
        this.x = other.x;
        this.y = other.y;
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
	 * Add a vector to this point changing the coordinates of this point.
	 * 
	 * <p>
	 * Use this method when you want to write something like
	 * {@code p = p + v} where {@code p} is a point and {@code v}
	 * is a vector.
	 * 
	 * @param v the vector to add
	 * @return a reference to this point
	 */
	public Point2 add(Vector2 v) {
		this.x += v.x();
		this.y += v.y();
		return this;
	}
	
	/**
	 * Subtract a vector from this point changing the coordinates of this point.
	 * 
	 * <p>
	 * Use this method when you want to write something like
	 * {@code p = p - v} where {@code p} is a point and {@code v}
	 * is a vector.
	 * 
	 * @param v the vector to subtract
	 * @return a reference to this point
	 */
	public Point2 subtract(Vector2 v) {
		this.x -= v.x();
		this.y -= v.y();
		return this;
	}
	
	
	/**
	 * Returns the vector pointing from the specified point
	 * {@code q} to this point.
	 * 
	 * <p>
	 * {@code p.from(q)} is equal to the vector {@code p - q}.
	 * 
	 * @param q a point
	 * @return the vector pointing from {@code q} to this point
	 */
	public Vector2 from(Point2 q) {
		Vector2 result = new Vector2();
		result.set(this.x - q.x, this.y - q.y);
		return result;
	}
	
	/**
	 * Returns the vector pointing from this point to the 
	 * specified point {@code q}.
	 * 
	 * <p>
	 * {@code p.to(q)} is equal to the vector {@code q - p}.
	 * 
	 * @param q a point
	 * @return the vector pointing from this point to {@code q}
	 */
	public Vector2 to(Point2 q) {
		Vector2 result = new Vector2();
		result.set(q.x - this.x, q.y - this.y);
		return result;
	}
	
	/**
	 * Multiply this point by a scalar value changing the coordinates of this point.
	 * 
	 * <p>
	 * Use this method when you want to write something like
	 * {@code p = s * p} where {@code p} is a point and {@code s}
	 * is a scalar.
	 * 
	 * @param s the scalar value to multiply this point by
	 * @return a reference to this point
	 */
	public Point2 multiply(double s) {
		this.x *= s;
		this.y *= s;
		return this;
	}
	
	/**
	 * Divide this point by a scalar value changing the coordinates of this point.
	 * 
	 * <p>
	 * Use this method when you want to write something like
	 * {@code p = p / s} where {@code p} is a point and {@code s}
	 * is a scalar.
	 * 
	 * @param s the scalar value to divide this point by
	 * @return a reference to this point
	 * @throws IllegalArgumentException if s == 0.0 is true
	 */
	public Point2 divide(double s) {
		if (s == 0.0) {
			throw new IllegalArgumentException("division by 0.0");
		}
		this.x /= s;
		this.y /= s;
		return this;
	}
	
	/**
	 * Negate the coordinates of this point changing the coordinates of this point.
	 * 
	 * <p>
	 * Use this method when you want to write something like
	 * {@code p = -p} where {@code p} is a point.
	 * 
	 * @return a reference to this point
	 */
	public Point2 negate() {
		this.x = -this.x;
		this.y = -this.y;
		return this;
	}
	
	/**
	 * Returns a new point equal to {@code p + v}.
	 * 
	 * @param p a point
	 * @param v a vector
	 * @return a new point equal to {@code p + v}
	 */
	public static Point2 add(Point2 p, Vector2 v) {
		Point2 result = new Point2();
		result.set(p.x + v.x(), p.y + v.y());
		return result;
	}
	
	/**
	 * Returns a new point equal to {@code p - v}.
	 * 
	 * @param p a point
	 * @param v a vector
	 * @return a new point equal to {@code p - v}
	 */
	public static Point2 subtract(Point2 p, Vector2 v) {
		Point2 result = new Point2();
		result.set(p.x - v.x(), p.y - v.y());
		return result;
	}
	
	/**
	 * Returns a new point equal to {@code s * p}.
	 * 
	 * @param s a scalar
	 * @param p a point 
	 * @return a new point equal to {@code s * p}
	 */
	public static Point2 multiply(double s, Point2 p) {
		Point2 result = new Point2();
		result.set(p.x * s, p.y * s);
		return result;
	}
	
	/**
	 * Returns a new point equal to {@code p / s}.
	 * 
	 * @param s a scalar
	 * @param p a point 
	 * @return a new point equal to {@code p / s}
	 * @throws IllegalArgumentException if s == 0.0 is true
	 */
	public static Point2 divide(Point2 p, double s) {
		Point2 result = new Point2();
		result.divide(s);    // will throw if s == 0.0
		return result;
	}
	
	/**
	 * Returns a new point equal to {@code -p}.
	 * 
	 * @param p a point 
	 * @return a new point equal to {@code -p}
	 */
	public static Point2 negate(Point2 p) {
		Point2 result = new Point2();
		result.set(-p.x, -p.y);
		return result;
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
		return "(" + this.x + ", " + this.y + ")";
	}
	
	public static void main(String[] args) {
		Point2 p = new Point2();
		System.out.println("p : " + p);
		System.out.println("p : " + p.toString());
		
		Point2 q = new Point2(1, 1);
		System.out.println("q : " + q);
		
		//p.set(1, 1).multiply(2).negate();
		//System.out.println(p);
		//System.out.println(p.set(1, 1).multiply(2));
		//System.out.println(multiply(3.0, p));
	}
}
















































