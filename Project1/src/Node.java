////////////////////////////// FILE HEADER ////////////////////////////////////
//
// Author:   Angela Galindo Santos
//
///////////////////////////////////////////////////////////////////////////////

/**
 * 
 * Implementation of the nodes that will be used to form the queue to simulate DNA
 *
 * @param <T>
 */
public class Node<T> {
	
	private T data;
	private Node<T> next;

	// Constructors
	/**
	 * Creates a node with data but no reference pointing to any other node
	 * 
	 * @param data
	 */
	public Node(T data) {
		this.data = data;
		this.next = null;
	}

	/**
	 * Creates a node with data and a reference to the next node in line
	 * 
	 * @param data
	 */
	public Node(T data, Node<T> next) {
		this(data);
		this.next = next;
	}

	/**
	 * Gets the data in the node.
	 * 
	 * @return T
	 */
	public T getData() {
		return this.data;
	}

	/**
	 * Gives the reference to the next node
	 * 
	 * @return Node<T>
	 */
	public Node<T> getNext() {
		return this.next;
	}
	
	/**
	 * Sets the pointer to the next node to the parameter passed. 
	 * 
	 * @param next
	 */
	public void setNext​(Node<T> next) {
		this.next = next;
	}
}
