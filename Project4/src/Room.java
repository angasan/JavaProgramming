//////////////////////////////// FILE HEADER ///////////////////////////////////
//
// Author:   Angela Galindo Santos
//
///////////////////////////////////////////////////////////////////////////////

/**
 * This class models a room with an specific location and capacity.
 */
public class Room {

	/**
	 * DATA FIELDS
	 */
	private String location;
	private int capacity;

	/**
	 * CONSTRUCTORS
	 */

	/**
	 * Creates a room object with an specific location and capacity (number of
	 * people that fit in the class)
	 * 
	 * @param location
	 * @param capacity
	 */
	public Room(String location, int capacity) {
		if (capacity < 0)
			throw new IllegalArgumentException("This capacity is not allowed");
		this.location = location;
		this.capacity = capacity;
	}

	/**
	 * ACCESSOR METHODS
	 */

	/**
	 * Gets the location assigned to this specific room object
	 * 
	 * @return location
	 */
	public String getLocation() {
		return this.location;
	}

	/**
	 * Gets the capacity that this specific room object has
	 * 
	 * @return capacity
	 */
	public int getCapacity() {
		return this.capacity;
	}

	/**
	 * Creates a new room object with the same location that this specific room
	 * object but with a reduced capacity. The capacity is reduced by the amount
	 * passed as parameter. The method throws an exception if the amount to be
	 * reduced is greater than the actual capacity of the class, and therefore,
	 * impossible.
	 * 
	 * @param difference
	 * @return Room - returns the new object created with the reduced capacity
	 */
	public Room reduceCapacity(int difference) {
		if (difference > this.capacity)
			throw new IllegalArgumentException("Difference not valid");
		Room r = new Room(getLocation(), (getCapacity() - difference));
		return r;
	}
}
