import java.util.Iterator;
import java.util.NoSuchElementException;

public class SongPlayer implements Iterable<Song> {
	private int size; // size of the list
	private LinkedNode<Song> head; // head of this doubly linked list
	private LinkedNode<Song> tail; // tail of this doubly linked list
	private boolean playingBackward; // true if this song player is reading the list backward

	// Constructor
	public SongPlayer() {
		this.size = 0;
		this.head = null;
		this.tail = null;
		this.playingBackward = false;
	}

	public void addFirst​(Song oneSong) {
		if (oneSong == null)
			throw new NullPointerException("No song passed");

		// If the list is empty, initialise the head and tail to a new node
		if (size == 0) {
			this.head = new LinkedNode(null, oneSong, null);
			this.tail = head;
			size++;
		} else { // If the list has node(s)
			/**
			 * 1. Create a new node that stores the song. 2. Make this new node point to the
			 * node at head as their next. 3. Make the node at the head point to the new
			 * node as their previous. 4. Change the reference of the head to point to the
			 * new node
			 */
			LinkedNode first = new LinkedNode(null, oneSong, head);
			this.head.setPrev(first);
			this.head = first;

			size++; // Increment the size by one
		}
	}

	public void addLast​(Song oneSong) {
		if (oneSong == null)
			throw new NullPointerException("No song passed"); // Just in case

		// If the list is empty, initialise the head and tail to a new node
		if (size == 0) {
			this.head = new LinkedNode(null, oneSong, null);
			this.tail = head;
			size++;
		} else {
			/**
			 * 1. Create a new node that stores the song. 2. Make this new node point to the
			 * node at tail as their previous. 3. Make the node at the tail point to the new
			 * node as their next. 4. Change the reference of the tail to point to the new
			 * node.
			 */

			LinkedNode last = new LinkedNode(tail, oneSong, null);
			this.tail.setNext(last);
			this.tail = last;

			size++; // Increment size by one
		}
	}

	public void add​(int index, Song s) {

		// Check that the index and song passed are valid parameters
		if (s == null)
			throw new NullPointerException();
		if (index < 0 || index >= this.size)
			throw new IndexOutOfBoundsException("Index out of bounds");

		// If the list is empty initialise the head and tail to a new node
		if (size == 0) {
			this.head = new LinkedNode<Song>(null, s, null);
			this.tail = head;
			this.size++;
		} else {
			/**
			 * 1. Create a new node that stores the song 2. Iterate over the list (starting
			 * at the head) until the index wanted is found. 3. Make the node at position
			 * index point to the new node as previous. 4. Set the new node's next to that
			 * node. 5. Make the node at position index-1 point to the new node as next. 6.
			 * Set the new node's prev to that node.
			 */

			LinkedNode<Song> newN = new LinkedNode<Song>(null, s, null);

			LinkedNode n1 = this.head;
			LinkedNode n2 = this.head.getNext();

			for (int i = 0; i < index - 1; i++) {
				n1 = n1.getNext();
				n2 = n2.getNext();
			}
			newN.setNext(n2);
			newN.setPrev(n1);
			n1.setNext(newN);
			n2.setPrev(newN);

			this.size++; // Increment the size by one
		}
	}

	public Song getFirst() {
		if (size == 0)
			throw new NoSuchElementException("Empty list");
		return this.head.getData();
	}

	public Song getLast() {
		if (size == 0)
			throw new NoSuchElementException("Empty list");
		return this.tail.getData();
	}

	public Song get​(int index) {

		if (index < 0 || index >= this.size)
			throw new IndexOutOfBoundsException("Index out of bounds");

		// Iterate over the list (starting from the head) until finding the node at the
		// desired position
		LinkedNode<Song> n1 = this.head;
		for (int i = 0; i < index; i++) {
			n1 = n1.getNext();
		}

		return n1.getData();
	}

	public Song removeFirst() {
		if (size == 0 || this.head == null)
			throw new NoSuchElementException("List is empty");

		Song s = this.head.getData(); // Store the song from the node we are going to remove

		/**
		 * If there's only one node and we remove it, the list will be empty. If the
		 * list has more than one node then update the head and remove the head's
		 * previous pointer.
		 */
		if (size == 1) {
			this.head = null;
			this.tail = null;
		} else {
			this.head = head.getNext();
			this.head.setPrev(null);
		}

		this.size--; // decrease the size by one
		return s;
	}

	public Song removeLast() {
		if (size == 0)
			throw new NoSuchElementException("List is empty");

		Song s = this.tail.getData(); // Save the song we want to return before losing it

		/**
		 * If there's only one node and we remove it, the list will be empty. If the
		 * list has more than one node then update the tail and remove the tail's next
		 * pointer.
		 */
		if (size == 1) {
			this.head = null;
			this.tail = null;
		} else {
			this.tail = this.tail.getPrev();
			this.tail.setNext(null);
		}

		this.size--; // decrease the size by one
		return s;
	}

	public Song remove(int index) {
		if (index < 0 || index >= this.size)
			throw new IndexOutOfBoundsException("Index out of bounds");

		// Iterate over the list (starting at the head), until the desired node is
		// reached
		LinkedNode<Song> n1 = this.head;
		for (int i = 0; i < index; i++) {
			n1 = n1.getNext();
		}

		// Get the data at that node
		Song s = n1.getData();

		// Update the next and previous nodes of the current node so they will now point
		// at each other. The result should leave the current node with nothing pointing to it.
		n1.getPrev().setNext(n1.getNext());
		n1.getNext().setPrev(n1.getPrev());
		this.size--;

		return s;
	}

	public boolean contains​(Song o) {
		
		LinkedNode<Song> n1 = this.head;
		for (int i = 0; i < this.size; i++) {
			if (n1.getData().equals(o)) // Check the song at the current node
				return true;
			n1 = n1.getNext(); // update current node
		}
		return false;
	}

	public void clear() {
		
		// Same settings as in the constructor
		this.head = null;
		this.tail = null;
		this.size = 0;
		this.playingBackward = false;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return this.size;
	}

	public void switchPlayingDirection() {
		if (this.playingBackward == true) this.playingBackward = false;
		else this.playingBackward = true;
	}

	public String play() {
		Iterator<Song> it = iterator();
		String s = "";
		for (Song i : this) {
			s += i.toString();
			s += "\n";
		}
		return s;
	}

	@Override
	public Iterator<Song> iterator() {
		Iterator<Song> it;
		if (this.playingBackward)
			it = new BackwardSongIterator(tail);
		else
			it = new ForwardSongIterator(head);
		return it;
	}

}
