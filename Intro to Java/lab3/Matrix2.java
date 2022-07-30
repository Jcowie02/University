package a3;

import ca.queensu.cs.cisc124.notes.basics.geometry.Vector2;

/**
 * A 2x2 matrix. This class provides basic mathematical operations such
 * as matrix-vector and matrix-matrix products, determinant, and inverse.
 *
 */
public class Matrix2 {
	
	private double[][] mx = new double[2][2];
	
	/**
	 * Initializes this matrix to the identity matrix
	 */	
	public Matrix2() {
		
		this.mx[0][0] = 1.0;
		this.mx[0][1] = 0.0;
		this.mx[1][0] = 0.0;
		this.mx[1][1] = 1.0;
		
	}
	/**
	 * Initializes this matrix so that its entries are equal to the specified
	 * values. The matrix entries are defined to be:
	 * 
	 * <pre>
	 * a b
	 * c d
	 * </pre>
	 * 
	 * @param a the value of the upper left element
	 * @param b the value of the upper right element
	 * @param c the value of the lower left element
	 * @param d the value of the lower right element
	 */
	public Matrix2(double a, double b, double c, double d) {
		
		this.mx[0][0] = a;
		this.mx[0][1] = b;
		this.mx[1][0] = c;
		this.mx[1][1] = d;
		
	}
	
	/**
	 * Initializes this matrix so that it is equal to the specified matrix
	 * 
	 * @param other a matrix to copy
	 */
	public Matrix2(Matrix2 other) {
		
		this.mx[0][0] = other.mx[0][0];
		this.mx[1][0] = other.mx[1][0];
		this.mx[0][1] = other.mx[0][1];
		this.mx[1][1] = other.mx[1][1];
	}
	
	/**
	 * Returns the entry of this matrix located at the given one-based row and column 
	 * indexes.
	 * 
	 * @param row the row index of the element
	 * @param col the column index of the element
	 * 
	 * @return the element at the specified row and column indexes
	 * 
	 * @throws IndexOutOfBoundsException if {@code row} is not 1 and not 2
	 * @throws IndexOutOfBoundsException if {@code col} is not 1 and not 2
	 */
	public double get(int row, int col) {
		if (row != 1 && row != 2) {
			throw new IndexOutOfBoundsException();
		}
		if (col != 1 && col != 2) {
			throw new IndexOutOfBoundsException();
		}
		return this.mx[row-1][col-1];
	}
	
	/**
	 * Sets the entry of this matrix located at the given zero-based row and column indexes to the specified value.
	 * 
	 * 
	 * @param row the row index of the element
	 * @param col the column index of the element
	 * @param val the element to store in this matrix
	 * 
	 * @return a reference to this matrix
	 * 
	 * @throws IndexOutOfBoundsException if {@code row} is not 1 and not 2
	 * @throws IndexOutOfBoundsException if {@code col} is not 1 and not 2
	 */
	public Matrix2 set(int row, int col, double val) {
		if (row != 1 && row != 2) {
			throw new IndexOutOfBoundsException();
		}
		if (col != 1 && col != 2) {
			throw new IndexOutOfBoundsException();
		}
		this.mx[row-1][col-1] = val;
		return this;
	}
	
	/**
	 *Postmultiply this matrix with the specified column vector returning a new vector.
	 *
	 *<p>
	 *{@code w = A.mult(v)} is equivalent to the mathematical equation {@code w = Av} where 
	 *{@code v} and {@code w} are 2x1 column vectors.
	 *
	 *@param v a 2x1 vector
	 *@return a 2x1 vector equal to this matrix times v
	 */
	public Vector2 mult(Vector2 v) {

		Vector2 result = new Vector2();
		result.set(this.mx[0][0]*v.x() + this.mx[0][1]*v.y(), this.mx[1][0]*v.x() + this.mx[1][1]*v.y());
		return result;
	}
	
	/**
	 *Postmultiply this matrix with the specified matrix returning a new matrix.
	 *
	 *<p>
	 *{@code C = A.mult(B)} is equivalent to the mathematical equation {@code C = AB}
	 *
	 *
	 *@param m a 2x2 matrix
	 *@return a 2x2 matrix equal to this matrix times m
	 */
	public Matrix2 mult(Matrix2 m) {
	
		Matrix2 result = new Matrix2();
		result.mx[0][0] = (this.mx[0][0]*m.mx[0][0])+(this.mx[0][1]*m.mx[1][0]);
		result.mx[1][0] = (this.mx[1][0]*m.mx[0][0])+(this.mx[1][1]*m.mx[1][0]);
		result.mx[0][1] = (this.mx[0][0]*m.mx[0][1])+(this.mx[0][1]*m.mx[1][1]);
		result.mx[1][1] = (this.mx[1][0]*m.mx[0][1])+(this.mx[1][1]*m.mx[1][1]);
		return result;
	}
	
	/**
	 * Returns the determinant of this matrix
	 * 
	 * @return the determinant of this matrix 
	 */
	public double det() {
		return (mx[0][0] * mx[1][1]) - (mx[0][1] * mx[1][0]);
	}
	
	/**
	 * Returns the inverse of this matrix if the determinant of this matrix is not exactly
	 * zero.
	 * 
	 * @return the inverse of this matrix
	 * 
	 * @throws ArithmeticException if the determinant of this matrix is exactly zero
	 */
	public Matrix2 inv() {
		if (this.det() == 0) {
			throw new ArithmeticException();
		}
		double det = this.det();
		Matrix2 result = new Matrix2();
		result.mx[1][1] = 1/det * mx[0][0];
		result.mx[0][0] = 1/det * mx[1][1];
		result.mx[1][0] = -1/det * mx[1][0];
		result.mx[0][1] = -1/det * mx[0][1];
		return result;
		
	}
	
	/**
	 * Returns a string representation of this matrix. The returned string
	 * has the following form:
	 * 
	 * <pre>
	 * [a, b]
	 * [c, d]
	 * </pre>
	 * 
	 * <p>
	 * where {@code a}, {@code b}, {@code c}, and {@code d} are the {@code double}
	 * values of the entries of this matrix.
	 * 
	 * @return a string representation of this matrix
	 */
	@Override
	public String toString() {

		String result = ("["+this.mx[0][0]+", ")+(this.mx[0][1]+"]\n")+
				("["+this.mx[1][0]+", ")+(this.mx[1][1]+"]");
		return result;
		
	}
} 
