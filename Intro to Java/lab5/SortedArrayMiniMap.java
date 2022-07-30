import java.util.Arrays;
import java.util.Objects;

/**
 * An array-based {@code MiniMap} where the keys are maintained in sorted
 * order. The key type {@code K} must implement the {@code Comparable}
 * interface.
 *
 */
public class SortedArrayMiniMap<K, V> extends AbstractMiniMap<K , V> {

	/**
	 * Initializes an empty map.
	 */
	public SortedArrayMiniMap() {
		super();
	}
	
	
	/**
	 * Returns the value to which the specified key is mapped.
	 * 
	 * <p>
	 * More formally, if this map contains a mapping from a key {@code k} to a value
	 * {@code v} such that {@code key.equals(k)}, then this method returns
	 * {@code v}; otherwise it returns {@code null}. (There can be at most one such
	 * mapping.)
	 * 
	 * <p>
	 * This map uses binary search to search for the specified key.
	 * 
	 * @param key the key whose associated value is to be returned
	 * @return the value to which the specified key is mapped, or null if this map
	 *         contains no mapping for the key
	 * @throws NullPointerException if the specified key is null
	 */
	@SuppressWarnings("unchecked")
	@Override
	public V get(Object key) {
		Objects.requireNonNull(key);
		int index = Arrays.binarySearch(this.keys, 0, this.size, key);
		if (index < 0) return null;
		return (V) this.values[index];
	}
	
	/**
	 * Associates the specified value with the specified key in this map (optional
	 * operation). If the map previously contained a mapping for the key, the old
	 * value is replaced by the specified value.
	 * 
	 * <p>
	 * A new key and its value will be inserted such that the set of keys of this
	 * map remain in sorted order if the map is not full.
	 *  
	 * @param key   key with which the specified value is to be associated
	 * @param value value to be associated with the specified key
	 * @return the previous value associated with key, or null if there was no
	 *         mapping for key
	 * @throws NullPointerException if the specified key is null
	 * @throws NullPointerException if the specified value is null
	 * @throws MiniMapFullException if this map cannot store the new key-value pair
	 */
	@SuppressWarnings("unchecked")
	@Override
	public V put(K key, V value) {
		Objects.requireNonNull(key);
		Objects.requireNonNull(value);
		
		// test if key is in map
		int index = Arrays.binarySearch(this.keys, 0, this.size, key);
		if (index >= 0) {
			Object oldValue = this.values[index];
			this.values[index] = value;
			return (V) oldValue;
		}
		
		// test if there is capacity to store the key-value pair
		if (this.size == this.keys.length) throw new MiniMapFullException("map full");
		
		//key if not in the map, add key-value pair to the map so that keys are sorted
		//index = (-(insertion point) - 1)
		int ip = -index - 1;
		for (int i = this.size; i > ip; i--) {
			this.keys[i] = this.keys[i - 1];
			this.values[i] = this.values[i -1];
		}
		this.keys[ip] = key;
		this.values[ip] = value;
		this.size++;
		return null;
	}
	
	public boolean containsKey(Object key) {
		Objects.requireNonNull(key);
		int index = Arrays.binarySearch(this.keys, 0, this.size, key);
		if (index >= 0) return true;
		return false;
	}

	
	public static void main(String[] args) {
		MiniMap<String, Integer> months = new SortedArrayMiniMap<>();
		months.put("Jan", 31);
		months.put("Feb", 28);
		months.put("Mar", 31);
		months.put("Apr", 30);
		months.put("May", 31);
		months.put("Jun", 30);
		months.put("Jul", 31);
		months.put("Aug", 31);
		months.put("Sep", 30);
		months.put("Oct", 31);
		months.put("Nov", 30);
		months.put("Dec", 31);
		System.out.println(months);
	}
}
