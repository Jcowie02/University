package a5;

/**
 * An object that attempts to solve for a root of a function on a given
 * interval. Such an object attempts to find the value of {@code x} such that
 * {@code x} is between some minimum and maximum value and
 * {@code f.eval(x) == 0.0} for some {@code Function1} object {@code f}.
 *
 */
public interface RootFinder1 {

	/**
	 * Searches for a root on the interval {@code a} to {@code b} for the
	 * specified {@code Function1} object {@code f}. Returns {@code Root.NONE} if no
	 * root is found on the specified interval.
	 * 
	 * @param f    a one-dimensional function
	 * @param a the minimum value of the interval to search
	 * @param b the maximum value of the interval to search
	 * @return a {@code Root} object containing the root if a root is found, or
	 *         {@code Root.NONE} if no root is found
	 * @throws IllegalArgumentException if {@code a >= b}
	 */
	public Root root(Function1 f, double a, double b);
}
