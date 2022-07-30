package a5;

/**
 * The function cos(x).
 *
 */
public class Cosine implements Function1 {

	/**
	 * Returns the value of cos(x).
	 * 
	 * @param x the argument to the function
     * @return cos(x)
	 */
	@Override
	public double eval(double x) {
		return Math.cos(x);
	}

}
