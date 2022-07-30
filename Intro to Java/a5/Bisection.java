package a5;
import java.lang.Math;

/**
 * Root finding via the bisection method.
 *
 */
public class Bisection implements RootFinder1 {

	// YOUR CLASS SHOULD PROVIDE ALL OF THE FEATURES
	// DESCRIBED BY ITS DOCUMENTATION
	public static final double TOL = 1.0E-6;
	
	@Override
	public Root root(Function1 f, double a, double b) {
		
		if (a >= b) throw new IllegalArgumentException("a>=b");
		double c = (a+b)/2;
		double ya = f.eval(a); double yb = f.eval(b); double yc = f.eval(c);
		if ((ya > 0 && yb > 0) || (ya < 0 && yb < 0)) throw new IllegalArgumentException("function evaluated at a and b are the same sign");
		
		while (Math.abs(yc) > TOL) {
			if (ya > 0 && yc > 0 || ya < 0 && yc < 0) a = c;
			else b = c;
			c = (a+b)/2;
			yc = f.eval(c);
		}
		Root result = new Root(c);
		if(result.value() < a || result.value() > b) return Root.NONE;
		else return result;
		}

	/**
	 * Small example program that finds a root of the sine and cosine functions.
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) {
		// find the root of sin(x) on the interval [3, 4]
		Function1 sin = new Sine();
		Bisection bisect = new Bisection();
		Root x0 = bisect.root(sin, 3.0, 4.0);
		if (x0 != Root.NONE) {
			System.out.println(x0.value()); // should be approx. pi
		}

		// find the root of cos(x) on the interval [1, 2]
		Function1 cos = new Cosine();
		x0 = bisect.root(cos, 1.0, 2.0);
		if (x0 != Root.NONE) {
			System.out.println(x0.value()); // should be approx. pi / 2
		}
	}

}
