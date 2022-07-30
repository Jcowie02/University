/**
 * A fixed size map capable of holding up to 32 key-value pairs.
 * 
 * <p>
 * A map is an object that maps keys to values. A map cannot contain duplicate
 * keys; each key can map to at most one value. This interface does not allow
 * keys or values to be {@code null}.
 * 
 * <p>
 * Small, fixed sized maps can be implemented using arrays. Such implementations
 * can have similar (or superior) runtime speeds compared to more complicated
 * implementations based on hash tables or search trees.
 *
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of mapped values
 */
public interface MiniMap<K, V> {

	/**
	 * The maximum number of key-value pairs that this map can store.
	 */
	public static final int MAX_SIZE = 32;

	/**
	 * Returns the value to which the specified key is mapped.
	 * 
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
	public V get(Object key);

	/**
	 * Associates the specified value with the specified key in this map (optional
	 * operation). If the map previously contained a mapping for the key, the old
	 * value is replaced by the specified value.
	 * 
	 * @param key   key with which the specified value is to be associated
	 * @param value value to be associated with the specified key
	 * @return the previous value associated with key, or null if there was no
	 *         mapping for key
	 * @throws NullPointerException if the specified key is null
	 * @throws NullPointerException if the specified value is null
	 * @throws MiniMapFullException if this map cannot store the new key-value pair
	 */
	public V put(K key, V value);

	/**
	 * Returns the number of key-value mappings in this map.
	 * 
	 * @return the number of key-value mappings in this map
	 */
	public int size();
	
	public boolean containsKey(Object key);

}
