package a4;

/**
 * Binary zero and one. Provides methods to flip (negate or complement) a bit and
 * get the decimal value of a bit.
 * 
 * <p>
 * Bits compare as though they are unsigned values (binary zero is less than
 * binary one).
 * 
 * <p>
 * The string representation of a bit is {@code "0"} or {@code "1"}.
 * 
 */
public enum Bit {
	
	/**
	 * Binary zero.
	 */
	ZERO {
		public Bit not() {
			return ONE;
		}
		
		public int value() {
			return 0;
		}
		
		/**
		 * Returns the string {@code "0"}.
		 * 
		 * @return the string {@code "0"}
		 */
		public String toString() {
			return "0";
		}
	},
	
	/**
	 * Binary one.
	 */
	ONE {
		public Bit not() {
			return ZERO;
		}
		
		public int value() {
			return 1;
		}
		
		/**
		 * Returns the string {@code "1"}.
		 * 
		 * @return the string {@code "1"}
		 */
		public String toString() {
			return "1";
		}
	};

	/**
	 * Returns the bit equal to the flipped value of this bit.
	 * 
	 * @return the bit equal to the flipped value of this bit
	 */
	abstract Bit not();
	
	/**
	 * Returns the decimal (base-10) value of this bit.
	 * 
	 * @return the decimal value of this bit
	 */
	abstract int value();
	
	
	/**
	 * Small test program.
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) {
		System.out.println("Bit.ZERO has the value : " + Bit.ZERO.value()); 
		System.out.println("Bit.ONE has the value  : " + Bit.ONE.value());
		System.out.println("Bit.ZERO as a string   : " + Bit.ZERO.toString()); 
		System.out.println("Bit.ONE as a string    : " + Bit.ONE.toString());
		System.out.println("NOT 0 = " + Bit.ZERO.not());
		System.out.println("NOT 1 = " + Bit.ONE.not());
		System.out.println();
	}
}
