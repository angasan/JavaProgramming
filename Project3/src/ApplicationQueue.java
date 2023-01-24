//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Author:   Angela Galindo 
//
///////////////////////////////////////////////////////////////////////////////

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Array-based heap implementation of a priority queue containing Applications.
 * Guarantees the min-heap invariant, so that the Application at the root should
 * have the lowest score, and children always have a higher or equal score as
 * their parent. The root of a non-empty queue is always at index 0 of this
 * array-heap.
 */
public class ApplicationQueue implements PriorityQueueADT<Application>, Iterable<Application> {
	private Application[] queue; // array min-heap of applications representing this priority queue
	private int size; // size of this priority queue

	/**
	 * Creates a new empty ApplicationQueue with the given capacity
	 * 
	 * @param capacity Capacity of this ApplicationQueue
	 * @throws IllegalArgumentException with a descriptive error message if the
	 *                                  capacity is not a positive integer
	 */
	public ApplicationQueue(int capacity) {
		// TO DO verify the capacity
		if (capacity <= 0)
			throw new IllegalArgumentException("Invalid Capacity");

		// TO DO initialize fields appropriately
		this.queue = new Application[capacity];
		this.size = 0;

	}

	/**
	 * Checks whether this ApplicationQueue is empty
	 * 
	 * @return {@code true} if this ApplicationQueue is empty
	 */
	@Override
	public boolean isEmpty() {
		return this.size == 0 && queue[0] == null; // TO DO fix
	}

	/**
	 * Returns the size of this ApplicationQueue
	 * 
	 * @return the size of this ApplicationQueue
	 */
	@Override
	public int size() {
		return this.size; // TO DO fix
	}

	/**
	 * Adds the given Application to this ApplicationQueue and use the percolateUp()
	 * method to maintain min-heap invariant of ApplicationQueue. Application should
	 * be compared using the Application.compareTo() method.
	 * 
	 * 
	 * @param o Application to add to this ApplicationQueue
	 * @throws NullPointerException  if the given Application is null
	 * @throws IllegalStateException with a descriptive error message if this
	 *                               ApplicationQueue is full
	 */
	@Override
	public void enqueue(Application o) {
		// TO DO verify the application
		if (o == null)
			throw new NullPointerException("Can't add a null Application");

		// TO DO verify that the queue is not full
		if (this.size() == queue.length)
			throw new IllegalStateException("ApplicationQueue is full");

		// TO DO if allowed, add the application to the queue and percolate to restore
		// the heap condition

		queue[this.size] = o; // Add the Application to the next available leaf
		// Increment the size by 1 to represent this addition

		// Percolate to preserve the order property
		percolateUp(this.size); // If we are adding the root the loop inside this function won't run

		this.size++;
	}

	/**
	 * Removes and returns the Application at the root of this ApplicationQueue,
	 * i.e. the Application with the lowest score.
	 * 
	 * @return the Application in this ApplicationQueue with the smallest score
	 * @throws NoSuchElementException with a descriptive error message if this
	 *                                ApplicationQueue is empty
	 */
	@Override
	public Application dequeue() {
		// TO DO verify that the queue is not empty
		if (this.isEmpty())
			throw new NoSuchElementException("Can't dequeue in an empty queue");

		// TO DO save the lowest-scoring application
		Application temp = queue[0];

		if (this.size() == 1) {
			this.size--;
			queue[0] = null;
			return temp;
		}

		// TO DO replace the root of the heap and percolate to restore the heap
		// condition
		queue[0] = queue[this.size() - 1]; // Substitute the root with the last available leaf to preserve the shape property
		percolateDown(0); // Percolate starting from the root
		this.size--;
		
		// TO DO return the lowest-scoring application
		return temp;
	}

	/**
	 * An implementation of percolateDown() method. Restores the min-heap invariant
	 * of a given subtree by percolating its root down the tree. If the element at
	 * the given index does not violate the min-heap invariant (it is due before its
	 * children), then this method does not modify the heap. Otherwise, if there is
	 * a heap violation, then swap the element with the correct child and continue
	 * percolating the element down the heap.
	 * 
	 * This method may be implemented recursively OR iteratively.
	 * 
	 * @param i index of the element in the heap to percolate downwards
	 * @throws IndexOutOfBoundsException if index is out of bounds - do not catch
	 *                                   the exception
	 */
	private void percolateDown(int i) {

		if (i >= queue.length || i < 0)
			throw new IndexOutOfBoundsException("The index provided is out of bounds");

		// Get the indexes for the left and right children
		int left = i * 2 + 1, right = i * 2 + 2;

		// We know this exist if they are inside of the list capacity
		boolean leftExists = left < this.size(), rightExists = right < this.size();

		// Base case: The node we are currently at is a leaf (has no children)
		if (!leftExists)
			return;

		// compare with left child and right child (if exists)
		boolean leftSwap = queue[i].compareTo(queue[left]) > 0;
		boolean rightSwap = rightExists && queue[i].compareTo(queue[right]) > 0;

		// if either child is SMALLER, swap with smaller child (if no swap, done)
		if (leftSwap || rightSwap) {
			// If there is no right then swap with left child
			if (!rightExists) {
				swap(i, left);
				return; // Left was a leaf node so we can stop the recursion
			}

			// RECURSIVE CASE
			// swap with smaller child
			int smallerIndex = (queue[left].compareTo(queue[right]) > 0) ? right : left;
			swap(i, smallerIndex);
			percolateDown(smallerIndex);

		} else {
			// No swap needed
			return;
		}
	}

	/**
	 * Quick helper method to swap things in an Array
	 * 
	 * @param i one index
	 * @param j the other index
	 */
	private void swap(int i, int j) {
		Application temp = queue[i];
		queue[i] = queue[j];
		queue[j] = temp;
	}

	/**
	 * An implementation of percolateUp() method. Restores the min-heap invariant of
	 * the tree by percolating a leaf up the tree. If the element at the given index
	 * does not violate the min-heap invariant (it occurs after its parent), then
	 * this method does not modify the heap. Otherwise, if there is a heap
	 * violation, swap the element with its parent and continue percolating the
	 * element up the heap.
	 * 
	 * This method may be implemented recursively OR iteratively.
	 * 
	 * Feel free to add private helper methods if you need them.
	 * 
	 * @param i index of the element in the heap to percolate upwards
	 * @throws IndexOutOfBoundsException if index is out of bounds - do not catch
	 *                                   the exception
	 */
	private void percolateUp(int i) {
		// TO DO implement the min-heap percolate up algorithm to modify the heap in
		// place

		int index = i;
		int parent = (index - 1) / 2;

		Application element = queue[index];

		// If the element is at the root (index = 0) we want to stop percolating.
		// We also want to stop percolating if the node added is bigger than its parent
		// because this means we have found a good spot for it

		while (index > 0 && element.compareTo(queue[parent]) < 0) {
			// swap with its parent
			queue[index] = queue[parent];
			queue[parent] = element;

			// update the indexes
			index = parent;
			parent = (index - 1) / 2;

		}
	}

	/**
	 * Returns the Application at the root of this ApplicationQueue, i.e. the
	 * Application with the lowest score.
	 * 
	 * @return the Application in this ApplicationQueue with the smallest score
	 * @throws NoSuchElementException if this ApplicationQueue is empty
	 */
	@Override
	public Application peek() {
		// TO DO verify that the queue is not empty
		if (this.isEmpty())
			throw new NoSuchElementException("Can't peek of of an empty queue");

		// TO DO return the lowest-scoring application
		return queue[0];
	}

	/**
	 * Returns a deep copy of this ApplicationQueue containing all of its elements
	 * in the same order. This method does not return the deepest copy, meaning that
	 * you do not need to duplicate applications. Only the instance of the heap
	 * (including the array and its size) will be duplicated.
	 * 
	 * @return a deep copy of this ApplicationQueue. The returned new application
	 *         queue has the same length and size as this queue.
	 */
	public ApplicationQueue deepCopy() {
		// TO DO implement this method according to its Javadoc comment
		ApplicationQueue copy = new ApplicationQueue(this.size());

		// Loop over the array to copy its contents to the new queue
		for (int i = 0; i < this.size(); i++) {
			copy.enqueue(this.queue[i]);
		}

		return copy;
	}

	/**
	 * Returns a String representing this ApplicationQueue, where each element
	 * (application) of the queue is listed on a separate line, in order from the
	 * lowest score to the highest score.
	 * 
	 * This implementation is provided.
	 * 
	 * @see Application#toString()
	 * @see ApplicationIterator
	 * @return a String representing this ApplicationQueue
	 */
	@Override
	public String toString() {
		StringBuilder val = new StringBuilder();

		for (Application a : this) {
			val.append(a).append("\n");
		}

		return val.toString();
	}

	/**
	 * Returns an Iterator for this ApplicationQueue which proceeds from the
	 * lowest-scored to the highest-scored Application in the queue.
	 * 
	 * This implementation is provided.
	 * 
	 * @see ApplicationIterator
	 * @return an Iterator for this ApplicationQueue
	 */
	@Override
	public Iterator<Application> iterator() {
		return new ApplicationIterator(this);
	}
}
