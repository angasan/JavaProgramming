//////////////////////////////// FILE HEADER ///////////////////////////////////
//
// Author:   Angela Galindo Santos
//
///////////////////////////////////////////////////////////////////////////////

import java.util.Arrays;

/**
 * Schedule models an schedule for a given array of rooms and another array of
 * courses. To keep track of assignments it also implements an attribute called
 * assignments. This last attribute is an array containing the indexes of the
 * rooms assigned for each course.
 */
public class Schedule {

	private Room[] rooms;
	private Course[] courses;
	private int[] assignments; // Array where the integer at index N is the index of the room that course [N]
								// has been assigned to

	/**
	 * CONSTRUCTORS
	 */

	/**
	 * Creates a brand new schedule for some rooms and courses. This constructor
	 * initialises the assignments array to -1 (every value in the array is -1)
	 * which means that none of the courses have been assigned and, as a result,
	 * that the schedule is empty.
	 * 
	 * @param rooms   - array of rooms that can be used as options to later create
	 *                the schedule
	 * @param courses - array of courses that need a room to be assigned
	 */
	public Schedule(Room[] rooms, Course[] courses) {
		this.rooms = rooms;
		this.courses = courses;
		this.assignments = new int[courses.length];
		Arrays.fill(this.assignments, -1);
	}

	/**
	 * Creates a schedule that already has some courses assigned to it. In this case
	 * the assignments array is different than -1 for some indexes and the schedule
	 * should be later assigned around that.
	 * 
	 * @param rooms       - array of rooms that can be used as options to later
	 *                    create the schedule
	 * @param courses     - array of courses that need a room to be assigned
	 * @param assignments - array of integers that for some positions (N) contain
	 *                    the index of the room assigned to the course that has the
	 *                    same position (N)
	 */
	private Schedule(Room[] rooms, Course[] courses, int[] assignments) {
		this(rooms, courses);
		this.assignments = assignments;
	}

	/**
	 * ACCESSOR METHODS
	 */

	/**
	 * Returns the number of rooms that this schedule was passed and will eventually
	 * need to find a room for
	 * 
	 * @return numRooms
	 */
	public int getNumRooms() {
		return this.rooms.length;
	}

	/**
	 * This method takes an specific parameter (index) and returns the room
	 * associated to that index from the rooms array.
	 * 
	 * @param i index of the room stored in the rooms array
	 * @return Room - returns the specific room object associated to that index
	 */
	public Room getRoom(int i) {
		if (i < 0 || i >= this.rooms.length)
			throw new IndexOutOfBoundsException("The index provided is invalid");
		return this.rooms[i];
	}

	/**
	 * Returns the number of objects in the courses array.
	 * 
	 * @return numCourses
	 */
	public int getNumCourses() {
		return this.courses.length;
	}

	/**
	 * This method gets the course stored in the courses array at position i
	 * 
	 * @param i index of the desired course
	 * @return - Course object at position i in the courses array
	 */
	public Course getCourse(int i) {
		if (i < 0 || i >= this.courses.length)
			throw new IndexOutOfBoundsException("The index provided is invalid");
		return this.courses[i];
	}

	/**
	 * Checks if the course at an specific position has been assigned
	 * 
	 * @param i index of the course we want to check
	 * @return - true if it has been assigned or false if it hasn't
	 */
	public boolean isAssigned(int i) {
		// Assuming the courses and assignments arrays have the same length
		if (i < 0 || i >= this.courses.length)
			throw new IndexOutOfBoundsException("The index provided is invalid");

		return this.assignments[i] != -1;
	}

	/**
	 * Returns the room object assigned to an specific course at position i
	 * 
	 * @param i - index of the course assignment we want to check
	 * @return Room - room assigned to that specific course
	 */
	public Room getAssignment(int i) {
		if (!isAssigned(i))
			throw new IllegalArgumentException("The room hasn't been assigned yet");
		int idx = this.assignments[i];
		return this.rooms[idx];
	}

	/**
	 * Checks if the schedule is complete. To do so it has to check if all courses
	 * have been assigned a room.
	 * 
	 * @return true if the schedule is complete or false if it's not
	 */
	public boolean isComplete() {
		for (int i = 0; i < assignments.length; i++) {
			if (!isAssigned(i))
				return false; // If there is one course not assigned this means it's not complete
		}
		return true;
	}

	/**
	 * MUTATOR METHODS
	 */

	/**
	 * Assigns an specific room (the one at index i) to an specific course (the one
	 * at index idx). If the room doesn't have enough capacity, the assignment can't
	 * be done, and therefore we throw an exception
	 * 
	 * @param idxCourse
	 * @param idxRoom
	 * @return Schedule - new schedule object with the new (updated) assignments and
	 *         rooms arrays and the previous course array. The room that was
	 *         assigned is updated.
	 */
	public Schedule assignCourse(int idxCourse, int idxRoom) {

		// Check if the index provided are valid or not
		if (idxCourse < 0 || idxCourse >= this.courses.length)
			throw new IndexOutOfBoundsException("Invalid index for course");
		if (idxRoom < 0 || idxRoom >= this.rooms.length)
			throw new IndexOutOfBoundsException("Invalid index for room");

		// Get the current Room and Course for making coding easier
		Room currRoom = this.rooms[idxRoom];
		Course currCourse = this.courses[idxCourse];

		// Check if the course has already been assigned a room and if the room that we
		// want to assign
		// has enough capacity to hold the students of this course
		if (this.assignments[idxCourse] != -1 || currRoom.getCapacity() < currCourse.getNumStudents())
			throw new IllegalArgumentException("This course has already been assigned");

		// Create a copy of the fields of the current schedule
		Room[] newRoom = new Room[this.rooms.length];
		int[] newAs = new int[this.assignments.length];
		for (int i = 0; i < this.rooms.length; i++) {
			newRoom[i] = this.getRoom(i);
			newAs[i] = this.assignments[i];
		}

		try {
			newRoom[idxRoom] = newRoom[idxRoom].reduceCapacity(this.getCourse(idxCourse).getNumStudents());
		} catch (Exception e) {

		}
		newAs[idxCourse] = idxRoom;

		// return a new schedule with the copied and modified data
		return new Schedule(newRoom, this.courses, newAs);
	}

	/**
	 * OVER RIDDEN METHODS
	 */

	/**
	 * Overrides the toString method, to convert an schedule to an actual string.
	 */
	@Override
	public String toString() {

		String str = "";
		for (int i = 0; i < this.courses.length; i++) {
			if (i > 0)
				str += ", ";
			str += this.courses[i].getName() + ": ";
			if (this.isAssigned(i)) {
				str += this.rooms[i].getLocation();
			} else {
				str += "Unassigned";
			}
		}
		return str;
	}
}
