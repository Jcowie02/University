package ca.queensu.cs.cisc124.notes.basics.geometry;

import static org.junit.Assert.*;

import org.junit.Test;

public class Point2Test {

	@Test
	public void test01_noArgCtor() {
		Point2 p = new Point2();
		
		double expX = 0.0;
		double expY = 0.0;
		
		double tol = 1e-9;
		assertEquals(expX, p.x(), tol);
		assertEquals(expY, p.y(), tol);
		
	}
	
	@Test
	public void test02a_multiplyScalar() {
		
		Point2 p = new Point2(1.1, -1.3);
		double s = 3.0;
		double expX = 3.3;
		double expY = -3.9;
		
		Point2 got = p.multiply(s);
		
		double tol = 1e-9;
		assertEquals(expX, got.x(), tol);
		assertEquals(expY, got.y(), tol);
		
		
	}
	
	@Test
	public void test02b_multiplyScalar() {
		
		Point2 p = new Point2(1.1, -1.3);
		double s = 3.0;
		Point2 got = p.multiply(s);

		assertSame(p, got);
	}
	
	@Test
	public void test03a_toString() {
		
		Point2 p = new Point2(1.1, -1.3);
		
		String exp = "(1.1, -1.3)";
		String got = p.toString();
		assertEquals(exp, got);
		
	}
	
	@Test
	public void test03b_toString() {
		
		Point2 p = new Point2(1.1, -1.3);
		double expX = 1.1;
		double expY = -1.3;
		
		String got = p.toString();
		double tol = 1e-9;
		assertEquals(expX, p.x(), tol);
		assertEquals(expY, p.y(), tol);

		
	}
}
