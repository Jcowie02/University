package a5;
import java.lang.Math;
import java.util.ArrayList;

/**
 * Root finding via Newton's method.
 *
 */
public class Newton implements RootFinder1 {

	// YOUR CLASS SHOULD PROVIDE ALL OF THE FEATURES
	// DESCRIBED BY ITS DOCUMENTATION
	
	public static final double TOL = 1.0E-6;
	private Function1 derivative;
	
	public Newton(Function1 df) {
		this.derivative = df;
	}

	@Override
	public Root root(Function1 f, double a, double b) {
		
		if (a >= b) throw new IllegalArgumentException("a>=b");
		double x0 = (a+b)/2;
		int i = 0;
		ArrayList<Double> xi = new ArrayList<>();
		xi.add(x0);
		while (Math.abs(f.eval(xi.get(i))) > TOL) {
			double val = xi.get(i) - (f.eval(xi.get(i))/this.derivative.eval(xi.get(i)));
			xi.add(val);
			i++;
		}
		Root result = new Root(xi.get(i));
		if (xi.get(i) < a || xi.get(i) > b) return Root.NONE;
		else return result;
		
	}
	
	/**
	 * Small example program that finds a root of the sine and cosine function.
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) {

		// find the root of sin(x) on the interval [3, 4]
		Function1 sin = new Sine();
		Function1 cos = new Cosine(); // derivative of sin(x)
		Newton n = new Newton(cos);
		Root x0 = n.root(sin, 3.0, 4.0);
		if (x0 != Root.NONE) {
			System.out.println(x0.value()); // should be approx. pi
		}

		// find the root of cos(x) on the interval [1, 2]
		Function1 negsin = new NegSine();
		Newton n2 = new Newton(negsin); // derivative of cos(x)
		x0 = n2.root(cos, 1.0, 2.0);
		if (x0 != Root.NONE) {
			System.out.println(x0.value()); // should be approx. pi / 2
		}
	}

}
