package a1;

/**
 * A class representing zero-based two-dimensional indexes. The {@code public}
 * fields {@code row} and {@code col} represent the row and column indexes,
 * respectively.
 */
public class Index2 {
	/**
	 * The row index.
	 */
	public int row;

	/**
	 * The column index.
	 */
	public int col;

	/**
	 * Initializes this index to row zero and column zero.
	 */
	public Index2() {
		this.row = 0;
		this.col = 0;
	}

	/**
	 * Initializes this index to the specified values.
	 * 
	 * @param row the column index
	 * @param col the row index
	 */
	public Index2(int row, int col) {
		this.row = row;
		this.col = col;
	}

	/**
	 * Returns a hash code for this index.
	 * 
	 * @return a hash code for this index
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + col;
		result = prime * result + row;
		return result;
	}

	/**
	 * Compares this index with another index for equality. The result is
	 * {@code true} if and only if {@code obj} is a reference to an {@code Index2}
	 * object having the same row and column indexes as this object.
	 * 
	 * @return true if this index is equal to the specified object, false otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Index2)) {
			return false;
		}
		Index2 other = (Index2) obj;
		if (col != other.col) {
			return false;
		}
		if (row != other.row) {
			return false;
		}
		return true;
	}

	/**
	 * Returns a string representation of this index. The returned string has the
	 * form {@code [row, col]}.
	 * 
	 * @return a string representation of this index
	 */
	public String toString() {
		return String.format("[%d, %d]", this.row, this.col);
	}

}
