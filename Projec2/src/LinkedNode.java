
public class LinkedNode<T> {

	// Data fields
	private T data; // data field of this linked node
	private LinkedNode<T> prev; // reference to the previous linked node in a list of nodes
	private LinkedNode<T> next; // reference to the next linked node in a list of nodes

	// Constructors
	public LinkedNode(LinkedNode<T> prev, T data, LinkedNode<T> next) {
		if (data == null) throw new IllegalArgumentException("Data is null");
		this.data = data;
		this.prev = prev;
		this.next = next;
	}

	// ACCESSORS
	public LinkedNode<T> getNext() {
		return this.next;
	}

	public LinkedNode<T> getPrev() {
		return this.prev;
	}

	public T getData() {
		return this.data;
	}

	// MUTATORS
	public void setNext(LinkedNode<T> next) {
		this.next = next;
	}

	public void setPrev(LinkedNode<T> prev) {
		this.prev = prev;
	}
}
