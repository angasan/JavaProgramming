////////////////////////////// FILE HEADER ////////////////////////////////////
//
// Author:   Angela Galindo Santos
//
///////////////////////////////////////////////////////////////////////////////

import java.util.NoSuchElementException;

/**
 * 
 * Implementation of a queue using a singly linked list
 *
 * @param <T>
 */
public class LinkedQueue<T> implements QueueADT<T> {

	protected Node<T> back;
	protected Node<T> front;
	private int n;

	/**
	 * Adds one element to the back of the queue
	 */
	@Override
	public void enqueue(T element) {
		if (n == 0) {
			this.front = new Node<T>(element);
			this.back = front;
			n++;
		} else {
			this.back.setNext​(new Node<T>(element));
			this.back = back.getNext();
			n++;
		}

	}

	/**
	 * Removes one node from the front of the queue and returns the data in that node
	 * 
	 * @return T the data stored in the node
	 */
	@Override
	public T dequeue() {
		if (this.isEmpty() || front == null) throw new NoSuchElementException();
		T data = front.getData();
		this.front =  front.getNext();
		n--;
		return data;
	}

	/**
	 * 
	 * Gets the data from the node at the front of the queue without altering 
	 * the queue
	 * 
	 * @return T the data in the node
	 */
	@Override
	public T peek() {
		if (this.isEmpty()) throw new NoSuchElementException();
		return front.getData();
	}

	/**
	 * Checks whether the queue is empty or not. Returns true if the queue is empty
	 */
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return n == 0 && front == back;
	}

	/**
	 * 
	 * Checks the size of the queue 
	 * 
	 * @return the number of elements stored in the queue
	 * 
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this.n;
	}

	/**
	 * 
	 * Creates a String representing all the data stored in the queue
	 * 
	 * @returns the String implementation of the queue
	 * 
	 */
	@Override
	public String toString() {
		String s = "";

		int m = this.size();
		Node<T> curr = front;
		while (m > 0) {
			T data = curr.getData();
			s += data.toString() + " ";
			curr = curr.getNext();
			m--;
		}

		return s;
	}

}
