package a4;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Collections;
/**
 * A class that represents a signed immutable binary number having no specified
 * limits on the number of bits. The binary number is represented using a ones
 * complement representation.
 *
 */
public class BigBinary implements Comparable<BigBinary> {
	
	private List<Bit> bin = new ArrayList<>();

	/**
	 * Initializes this binary number to have the specified number of bits and a
	 * decimal value of (positive) zero.
	 * 
	 * @param nbits the number of bits
	 * @throws IllegalArgumentException if {@code nbits} is less than 1
	 */
	public BigBinary(int nbits) {
		
		if (nbits < 1) throw new IllegalArgumentException();
		for (int i = 0; i < nbits; i++) {
			this.bin.add(Bit.ZERO);
		}
		
	}

	/**
	 * Translates the decimal string representation of a {@code BigBinary} into a
	 * {@code BigBinary}. The string representation consists of a sequence of one or
	 * more binary digits (0s and 1s). The string may not contain any extraneous
	 * characters (whitespace, for example) or characters not equal to {@code '0'}
	 * or {@code '1'}.
	 * 
	 * <p>
	 * If {@code bits} is empty then this binary number is initialized to the 8-bit
	 * binary number equal to zero.
	 * 
	 * @param bits the bits of the binary number
	 * @throws IllegalArgumentException if bits is not a valid representation of a
	 *                                  {@code BigBinary}
	 */
	public BigBinary(String bits) {
		
		
		if (bits.isEmpty()) {
			for (int i = 0; i < bits.length(); i++) {
				this.bin.add(Bit.ZERO);
			}
		}
		else { 
			for (int i = 0; i < bits.length(); i++) {
				if (bits.charAt(i) != '0' && bits.charAt(i) != '1') {
					throw new IllegalArgumentException();
				}
				else if (bits.charAt(i) == '1') {
					this.bin.add(Bit.ONE);
				}
				else this.bin.add(Bit.ZERO);
			}
		}
	}

	/**
	 * Returns the number of bits in this binary number.
	 * 
	 * @return the number of bits in this binary number
	 */
	public int numberOfBits() {
		
		return this.bin.size();
		
	}

	/**
	 * Returns {@code true} if this binary number is equal to positive or negative
	 * zero, {@code false} otherwise. In ones complement representation, a binary
	 * number is equal to zero if all of the bits are equal to zero or all of the
	 * bits are equal to one.
	 * 
	 * @return {@code true} if this binary number is equal to positive or negative
	 *         zero, {@code false} otherwise
	 */
	public boolean isZero() {
		Bit tmp = null;
		for (int i = 0; i < this.bin.size(); i++) {
			if (i > 0) {
				if (tmp != this.bin.get(i)) return false;
			}
			tmp = this.bin.get(i);
		}
		return true;
	}

	/**
	 * Returns {@code true} if this binary number is negative, and {@code false}
	 * otherwise. This method considers {@code -0} to be negative.
	 * 
	 * @return {@code true} if this binary number is negative, and {@code false}
	 *         otherwise
	 */
	public boolean isNegative() {
		
		if (this.bin.get(0) == Bit.ONE) return true;
		else return false;
		
		
	}

	/**
	 * Returns a new binary number equal to the bitwise NOT of this binary number.
	 * The returned number has a decimal value equal to the negative decimal value
	 * of this binary number (it is the ones complement of this number).
	 * 
	 * @return a new binary number equal to the bitwise NOT of this binary number
	 */
	public BigBinary not() {
		
		BigBinary result = new BigBinary(1);
		for (int i = 0; i < this.bin.size(); i++) {
			if (this.bin.get(i) == Bit.ONE) result.bin.add(Bit.ZERO);
			else result.bin.add(Bit.ONE);
		}
		result.bin.remove(0);
		return result;
	}

	/**
	 * Returns the bits of this number as a list. The returned list cannot be used
	 * to modify this binary number.
	 * 
	 * <p>
	 * If you use a list to store the bits of the number then return a new copy of
	 * the list.
	 * 
	 * <p>
	 * If you use an array to store the bits of the number then use
	 * {@code Arrays.toList} to get a list representation of your array, and then
	 * return a new copy of that list.
	 * 
	 * @return a the bits of this number as a list
	 */
	public List<Bit> getBits() {
		
		final List<Bit> lst = new ArrayList<>(); // cannot modify
		for (int i = 0; i < this.bin.size(); i++) {
			lst.add(this.bin.get(i));
		}
		
		List<Bit> result = new ArrayList<>(lst);
		return result;
		
	}

	/**
	 * Compares this binary number to an object for equality. Returns true if and
	 * only if {@code obj} is a {@code BigBinary} number having the same number of
	 * bits and the same decimal value as this binary number.
	 * 
	 * <p>
	 * Note that this method considers positive zero to be equal to negative zero if
	 * both numbers have the same number of bits. For all other binary numbers, two
	 * binary numbers are equal if and only if they have exact same bit pattern.
	 * 
	 * <p>
	 * Two binary numbers having the same decimal value but different number of bits
	 * are considered to be not equal by this method.
	 * 
	 * @param obj the object to compare
	 * @return true if this binary number is equal to the other binary number, false
	 *         otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		
		if (this == obj) return true;
		if (obj == null) return false;
		if (this.getClass() != obj.getClass()) return false; //now know they must be the same type
		
		BigBinary other = (BigBinary) obj;
		
		if (other.bin.size() != this.bin.size()) {
			return false;
		}
		
		int count1 = 0;
		int count2 = 0;
		for (int i = 0; i < this.bin.size(); i++) {
			if (this.bin.get(i) != other.bin.get(i)) count1++; //if every term is opposite, then the terms must be -0 and +0, which are equal
			else if (this.bin.get(i) == other.bin.get(i)) count2++;
			
		}
		if (count1 == this.bin.size() || count2 == this.bin.size()) return true; //-0 and +0
		else return false;
		
	}

	/**
	 * Returns a hash code for this binary number.
	 * 
	 * @return a hash code for this binary number
	 */
	@Override
	public int hashCode() {
		
		//-0 and +0 hash needs to be the same
		if (this.isZero()) return Objects.hash(0);
		else return Objects.hash(this.bin);
	}

	/**
	 * Compares this binary number to another binary number for order. The result is
	 * {@code -1} if this number is less than the other number, {@code 0} is this
	 * number is equal to the other number, and {@code 1} if this number is greater
	 * than the other number.
	 * 
	 * <p>
	 * Unlike {@code equals}, this method will correctly compare binary numbers
	 * having different numbers of bits. Also, this method considers {@code -0} to
	 * be less than {@code 0}.
	 * 
	 * <p>
	 * Conceptually, the decimal value of the numbers are used in the comparison,
	 * but it is not practical to compute the decimal value of an arbitrary length
	 * binary number. Instead a bit-wise comparison is performed.
	 * 
	 * 
	 * @param other the binary number to compare
	 * @return -1, 0 or 1 as this number is numerically less than, equal to, or
	 *         greater than the other number
	 */
	public int compareTo(BigBinary other) {
		
		List<Bit> working = new ArrayList<>();
		working = other.getBits();
		int size = 0;		

		// following condition ensures that zeros are added to the front of binary numbers to make them the same size, and
		// sets the size to whichever list is bigger, as this is how long the main for loop below will run for
		if (working.size() > this.bin.size()) {
			for (int i = 0; i < working.size() - this.bin.size() + 1; i++) {
				this.bin.add(0, Bit.ZERO);
			}
			size = working.size();
		}
		else if (this.bin.size() > working.size()) {
			for (int i = 0; i < this.bin.size() - working.size() + 1; i++) {
				working.add(0, Bit.ZERO);
			}
			size = this.bin.size();
		}
		else {
			size = this.bin.size();
		}

		//checks if the binary numbers are either all 0's or all 1's
		int counter = 0;
		for (int i = 0; i < size; i++) {
			if (this.bin.get(i) == working.get(i)) counter++;
		}
		if (counter == size) return 0;

		//checks if the binary numbers are -0 and +0 and returns the appropriate number
		BigBinary result = new BigBinary(1);
		int count = 0;
		boolean state;
		if (bin.get(0) == Bit.ONE) state = true;
		else state = false;
		for (int i = 0; i < size; i++) {
			if ((this.bin.get(i) == Bit.ONE && (working.get(i)) == Bit.ZERO) 
					|| (this.bin.get(i) == Bit.ZERO && (working.get(i)) == Bit.ONE)) count++;
		}
		if (count == size && state == true) return -1; //means bin contains all 1's and working contains all 0's
		else if (count == size && state == false) return 1; //opposite of condition above
		
		
		// binary subtraction using 1's complement; if there is a carry left over, then bin is bigger than working, if not, then the result is negative, 
		// so bin is smaller than working
		working = other.not().getBits();
		int carry = 0;
		for (int i = size - 1; i >= 0; i--) {
			if (i == size - 2) result.bin.remove(0);
			if (this.bin.get(i) == Bit.ONE && working.get(i) == Bit.ONE) {
				carry = 1;
				result.bin.add(Bit.ZERO);
			}
			else if (this.bin.get(i) == Bit.ZERO && working.get(i) == Bit.ZERO) {
				if (carry == 1) { 
					carry = 0;
					result.bin.add(Bit.ONE);
				}
				else result.bin.add(Bit.ZERO);
			}
			else {
				if (carry == 1 && i != 0 ) {
					result.bin.add(Bit.ZERO);
				}
				else if (carry == 1 && i > 0) result.bin.add(Bit.ZERO);
				else result.bin.add(Bit.ONE);
			}
		}

		// checks to see if the result is +0 or -0
		Collections.reverse(result.bin);
		int count1 = 0;
		int count2 = 0;
		for (int i = 0; i < result.bin.size(); i++) {
			if (result.bin.get(i) == Bit.ONE) count1++;
			if (result.bin.get(i) == Bit.ZERO) count2++;
		}
		if (count1 == result.bin.size() || count2 == result.bin.size()) return 0;
		
		if (carry != 1) return -1; //if carry does not equal 1, then the result must be negative
		else return 1; //if carry is equal to 1, then result is positive
	}

	/**
	 * Returns a string representation of this binary number. The returned string is
	 * made up of the bit values (0 or 1) of the number from the left-most bit to
	 * the right-most bit.
	 * 
	 * @return a string representation of this binary number
	 */
	@Override
	public String toString() {
		StringBuffer b = new StringBuffer();
		for (Bit bit : this.getBits()) {
			b.append(bit.toString());
		}
		return b.toString();
	}

	/**
	 * Returns the decimal (base 10) value of this binary number as an {@code int}
	 * value throwing an exception if the result overflows an {@code int}.
	 * 
	 * @return the decimal (base 10) value of this binary number
	 * @throws ArithmeticException if the result overflows an {@code int}
	 */
	public int toDecimal() {
		long val = 0;
		List<Bit> bits = this.getBits();
		for (int i = 0; i < bits.size(); i++) {
			int y = bits.size() - 1 - i;
			int bitVal = bits.get(i).value();
			if (this.isNegative()) {
				bitVal = -bits.get(i).not().value();
			}
			val += bitVal * Math.pow(2, y);
			if (val > Integer.MAX_VALUE || val < Integer.MIN_VALUE) {
				throw new ArithmeticException();
			}
		}
		return (int) val;
	}

	/**
	 * Prints the output of of calling {@code toDecimal}, {@code isZero},
	 * {@code isNegative}, and {@code not} on a binary number.
	 * 
	 * @param b      a binary number
	 * @param header a message to print at the start of the test
	 */
	static void test(BigBinary b, String header) {
		String s = b.toString();
		String str;
		try {
			str = "" + b.toDecimal();
		} catch (ArithmeticException ex) {
			str = "overflows int";
		}
		System.out.println(header);
		System.out.println(s + ".toDecimal()  : " + str);
		System.out.println(s + ".isZero()     : " + b.isZero());
		System.out.println(s + ".isNegative() : " + b.isNegative());
		System.out.println(s + ".not()        : " + b.not());
		System.out.println();
	}

	/**
	 * Prints the output of of calling {@code equals} and {@code compareTo} on a
	 * pair of binary numbers.
	 * 
	 * @param x      a binary number
	 * @param y      a binary number
	 * @param header a message to print at the start of the test
	 */
	static void testEqualsAndCompareTo(BigBinary x, BigBinary y, String header) {
		String sx = x.toString();
		String sy = y.toString();
		System.out.println(header);
		System.out.println(sx + ".equals(" + sy + ")    : " + x.equals(y));
		System.out.println(sx + ".compareTo(" + sy + ") : " + x.compareTo(y));
		System.out.println(sy + ".compareTo(" + sx + ") : " + y.compareTo(x));
		System.out.println();
	}

	/**
	 * A simple test method.
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) {
		// 8-bit binary number equal to +0
		BigBinary b = new BigBinary(8);
		test(b, "8-bit binary number equal to +0");

		// 8-bit binary number equal to -0
		b = new BigBinary("11111111");
		test(b, "8-bit binary number equal to -0");

		// 8-bit binary number equal to 127
		b = new BigBinary("01111111");
		test(b, "8-bit binary number equal to 127");

		// 8-bit binary number equal to -127
		b = new BigBinary("10000000");
		test(b, "8-bit binary number equal to -127");

		// 32-bit binary number equal to Integer.MAX_VALUE
		b = new BigBinary("01111111111111111111111111111111");
		test(b, "32-bit binary number equal to Integer.MAX_VALUE");

		// 33-bit binary number equal to Integer.MAX_VALUE + 1
		b = new BigBinary("010000000000000000000000000000000");
		test(b, "33-bit binary number equal to Integer.MAX_VALUE + 1");

		// 33-bit binary number equal to Integer.MIN_VALUE
		b = new BigBinary("101111111111111111111111111111111");
		test(b, "33-bit binary number equal to Integer.MIN_VALUE");

		// 33-bit binary number equal to Integer.MIN_VALUE - 1
		b = new BigBinary("101111111111111111111111111111110");
		test(b, "33-bit binary number equal to Integer.MIN_VALUE - 1");

		// compare 4-bit +0 to 4-bit +0
		BigBinary x = new BigBinary(4);
		BigBinary y = new BigBinary(4);
		testEqualsAndCompareTo(x, y, "compare 4-bit +0 to 4-bit +0");

		// compare 4-bit +0 to 4-bit -0
		x = new BigBinary(4);
		y = new BigBinary("1111");
		testEqualsAndCompareTo(x, y, "compare 4-bit +0 to 4-bit -0");

		// compare 4-bit +5 to 4-bit +1
		x = new BigBinary("0101");
		y = new BigBinary("0001");
		testEqualsAndCompareTo(x, y, "compare 4-bit +5 to 4-bit +1");

		// compare 4-bit -5 to 4-bit -1
		x = new BigBinary("1010");
		y = new BigBinary("1110");
		testEqualsAndCompareTo(x, y, "compare 4-bit -5 to 4-bit -1");

		// compare 4-bit +0 to 2-bit +0
		x = new BigBinary(4);
		y = new BigBinary("00");
		testEqualsAndCompareTo(x, y, "compare 4-bit +0 to 2-bit +0");

		// compare 4-bit +5 to 6-bit +5
		x = new BigBinary("0101");
		y = new BigBinary("000101");
		testEqualsAndCompareTo(x, y, "compare 4-bit +5 to 6-bit +5");

		// compare large number to a larger number
		x = new BigBinary("01000011000000101000101010100001111101");
		y = new BigBinary("010000110000001010001010101000011111011");
		testEqualsAndCompareTo(x, y, "compare large number to a larger number");

		// compare Integer.MIN_VALUE to (Integer.MIN_VALUE - 1)
		x = new BigBinary("101111111111111111111111111111111");
		y = new BigBinary("101111111111111111111111111111110");
		testEqualsAndCompareTo(x, y, "compare Integer.MIN_VALUE to (Integer.MIN_VALUE - 1)");
	}
}
