/**
 * Thrown to indicate that a minimap is full. 
 *
 */
public class MiniMapFullException extends RuntimeException {
	/**
	 * ID for Serializable
	 */
	private static final long serialVersionUID = 5836144766631097511L;

	/**
	 * Constructs an exception with {@code null} as its detail message.
	 */
	public MiniMapFullException() {
		super();
	}
	
	/**
	 * Constructs an exception with the specified detail message.
	 */
	public MiniMapFullException(String message) {
		super(message);
	}
}
