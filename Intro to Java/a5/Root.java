package a5;

/**
 * An object that stores information about the existence of a root of a
 * function, and the value of the root if it exists. If a root does not exist
 * then the value returned by the {@code value()} method is {@code Double.NaN}.
 * 
 * <p>
 * For convenience, the constant {@code Root.NONE} can be used by root finding
 * objects to indicate that a root does not exist.
 */
public class Root {

	/**
	 * Constant object indicating the lack of root.
	 */
	public static final Root NONE = new Root();

	private double value;
	private boolean hasRoot;

	/**
	 * Initializes an object equal to {@code Root.NONE}.
	 */
	public Root() {
		this.value = Double.NaN;
		this.hasRoot = false;
	}

	/**
	 * Initializes an object indicating the existence of a root having the specified
	 * value.
	 * 
	 * @param value the value of the root
	 */
	public Root(double value) {
		this.value = value;
		this.hasRoot = true;
	}

	/**
	 * Returns {@code true} if a root exists, {@code false} otherwise.
	 * 
	 * @return true if a root exits, false otherwise
	 */
	public boolean hasRoot() {
		return this.hasRoot;
	}

	/**
	 * Returns the value of the root if it exists, or {@code Double.NaN} if a root
	 * does not exist.
	 * 
	 * @return the value of the root if it exists, or {@code Double.NaN} if a root
	 *         does not exist
	 */
	public double value() {
		return this.value;
	}
}
