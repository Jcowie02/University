import java.util.Objects;

/**
 * An array-based {@code MiniMap} where the keys are maintained in insertion
 * order. 
 *
 */
public class ArrayMiniMap<K, V> extends AbstractMiniMap<K, V> {

	/**
	 * Initializes an empty map.
	 */
	public ArrayMiniMap() {
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
	 * @param key the key whose associated value is to be returned
	 * @return the value to which the specified key is mapped, or null if this map
	 *         contains no mapping for the key
	 * @throws NullPointerException if the specified key is null
	 */
	@SuppressWarnings("unchecked")
	@Override
	public V get(Object key) {
		Objects.requireNonNull(key);
		V value = null;
		for (int i = 0; i < this.size;) {
			value = (V) this.values[i];	
			break;
		}
		return value;
	}
	
	
	/**
	 * Associates the specified value with the specified key in this map (optional
	 * operation). If the map previously contained a mapping for the key, the old
	 * value is replaced by the specified value.
	 * 
	 * <p>
	 * A new key and its value will be inserted at the end of the map if the map
	 * is not full.
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
	public V put(Object key, Object value) {
		Objects.requireNonNull(key);
		Objects.requireNonNull(value);		
		
		//test if key is in the map
		for (int i = 0; i < this.size; i++) {
			if (this.keys[i].equals(key)) {
				Object oldValue = this.values[i];
				this.values[i] = value;
				return (V) oldValue;
			}
		}
		
		//test if there is capacity to store the key-value pair

		if (this.size == this.keys.length) {
			throw new MiniMapFullException("minimap out of capacity");
			
		}
		
		//key is not in map, add key-value pair to end of map
		this.keys[this.size] = key; 
		this.values[this.size] = value;
		this.size++;
		return null;
	}
	
	public boolean containsKey(Object key) {
		Objects.requireNonNull(key);
		for (int i = 0; i < this.size; i++) {
			if (this.keys[i].equals(key)) return true;
		}
		return false;
	}

	
	public static void main(String[] args) {
		MiniMap<String, Integer> months = new ArrayMiniMap<>();
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
