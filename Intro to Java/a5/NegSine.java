package a5;

/**
 * The function -sin(x).
 */
public class NegSine implements Function1 {

	/**
	 * Returns the value of -sin(x).
	 * 
	 * @param x the argument to the function
     * @return -sin(x)
	 */
	@Override
	public double eval(double x) {
		return -Math.sin(x);
	}

}
