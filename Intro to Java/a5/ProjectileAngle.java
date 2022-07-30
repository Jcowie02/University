package a5;
import java.lang.Math;

/**
 * The ideal projectile angle function.
 *
 */
public class ProjectileAngle implements Function1 {
	
	// YOUR CLASS SHOULD PROVIDE ALL OF THE FEATURES
	// DESCRIBED BY ITS DOCUMENTATION
	
	public static final double G = 9.80665;
	private double distance;
	private double speed;

	public ProjectileAngle(double distance, double speed) {
		
		this.distance = distance;
		this.speed = speed;
		if (distance < 0) throw new IllegalArgumentException("distance < 0");
		if (speed < 0) throw new IllegalArgumentException("speed < 0");
	}
	
	public double getDistance() {
		return this.distance;
	}
	
	public double getSpeed() {
		return this.speed;
	}
	
	public double setDistance(double distance) {
		double tmp = this.distance;
		this.distance = distance;
		return tmp;
	}
	
	public double setSpeed(double speed) {
		double tmp = this.speed;
		this.speed = speed;
		return tmp;
	}
	
	@Override
	public double eval(double x) {

		double fx = (this.distance*Math.tan(x)) - 0.5*G*(Math.pow(this.distance/(this.speed*Math.cos(x)), 2));
		if (fx < 0 || fx > this.distance) return Double.NaN; // function is not within the bounds of the projectile
		else return (this.distance*Math.tan(x)) - 0.5*G*(Math.pow(this.distance/(this.speed*Math.cos(x)), 2));
	}
	
	/**
	 * Small example program that finds the two possible aiming angles
	 * for a projectile launched at 32.0 m/s at a target located 100 m
	 * horizontally from the launch point.
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) {
		// target located 100 m horizontally from the launch point
		// launched at 50 m/s
		ProjectileAngle f = new ProjectileAngle(100.0, 50.0);
		
		// root finding using the bisection method
		Bisection n = new Bisection();
		
		// first angle is between 0 and 45 degrees 
		Root x0 = n.root(f, 0.0, Math.PI / 4.0);
		if (x0 != Root.NONE) {
			System.out.println(Math.toDegrees(x0.value()));
		}
		
		// second angle is between 45 and 90 degrees
		x0 = n.root(f, Math.PI / 4.0, Math.PI / 2.0);
		if (x0 != Root.NONE) {
			System.out.println(Math.toDegrees(x0.value()));
		}
	}

	

}
