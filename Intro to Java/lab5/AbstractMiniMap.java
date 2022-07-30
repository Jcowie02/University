/**
 * This class provides a skeletal implementation of the {@code MiniMap}
 * interface for array-based maps. The key-value pairs of the map are
 * stored in parallel arrays.
 * 
 * <p>
 * To implement a modifiable map, the programmer needs only to extend
 * this class and provide implementations for the {@code get(K)} and
 * {@code put(K, V)} methods.
 * 
 * <p>
 * Sorted maps might take advantage of binary search in their
 * implementations. 
 *
 */
public abstract class AbstractMiniMap<K, V> implements MiniMap<K, V> {

	/**
	 * The array of keys for this map.
	 */
	protected Object[] keys;
	
	/**
	 * The array of values for this map. 
	 */
	protected Object[] values;
	
	/**
	 * The number of key-value pairs stored in this map.
	 */
	protected int size;


	/**
	 * Initialize an empty map. The key and value arrays are allocated by
	 * this constructor.
	 */
	public AbstractMiniMap() {
		this.keys = new Object[MAX_SIZE];
		this.values = new Object[MAX_SIZE];
		this.size = 0;
	}

	/**
	 * Returns the number of key-value mappings in this map.
	 * 
	 * @return the number of key-value mappings in this map
	 */
	@Override
	public int size() {
		return this.size;
	}

	
	/**
	 * Returns a string representation of this map. The string representation
	 * consists of a list of key-value mappings in the order that the keys
	 * are stored by this map, enclosed in braces ("{}"). Adjacent mappings are
	 * separated by the characters ", " (comma and space). Each key-value mapping is
	 * rendered as the key followed by an equals sign ("=") followed by the
	 * associated value. Keys and values are converted to strings as by
	 * String.valueOf(Object).
	 * 
	 * @return a string representation of this map
	 */
	public String toString() {
		StringBuilder b = new StringBuilder("{");
		if (this.size > 0) {
			b.append(this.keys[0] + "=" + this.values[0]);
		}
		for (int i = 1; i < this.size; i++) {
			b.append(", " + this.keys[i] + "=" + this.values[i]);
		}
		b.append("}");
		return b.toString();
	}

}
