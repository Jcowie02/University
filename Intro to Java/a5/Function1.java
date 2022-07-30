package a5;

import java.util.List;
import java.util.ArrayList;

/**
 * An interface for real-valued, 1-dimensional functions of the form y = f(x).
 *
 */
public interface Function1 {
    
    /**
     * Evaluate the function at the specified argument returning y = f(x) if the function
     * exists at {@code x}. Returns {@code Double.NaN} if the function does not exist at {@code x}.
     * 
     * @param x the argument to the function
     * @return the function evaluated at x
     */
    public double eval(double x);
    
    
    /**
     * Evaluate the function at each of the arguments contained in the specified
     * list returning a list containing the evaluated function values.
     * {@code Double.NaN} is used for each argument value where the function does not exist.
     * 
     * @param x a list of arguments to the function
     * @return a list of values of the function evaluated at each element of x
     */
    public default List<Double> eval(List<Double> x) {
        List<Double> y = new ArrayList<>();
        for (double xi : x) {
            y.add(this.eval(xi));
        }
        return y;
    }
}