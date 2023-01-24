//////////////////////////////// FILE HEADER ///////////////////////////////////
//
// Author:   Angela Galindo Santos
//
///////////////////////////////////////////////////////////////////////////////

/**
 * This class models a course with its corresponding name and number of
 * students.
 */
public class Course {

	/**
	 * DATA FIELDS
	 */
	private String name;
	private int numStudents; // number of students enrolled

	/**
	 * CONSTRUCTORS
	 */

	/**
	 * Creates a new Course object with an specific name and number of students
	 *
	 * 
	 * @param name        - name of the course
	 * @param numStudents - number of students enrolled in the course
	 */
	public Course(String name, int numStudents) {
		if (numStudents < 0)
			throw new IllegalArgumentException("Number of students not allowed");
		this.name = name;
		this.numStudents = numStudents;
	}

	/**
	 * ACCESSOR METHODS
	 */

	/**
	 * Returns the name of this course object
	 * 
	 * @return name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Returns the number of students enrolled in this specific course
	 * 
	 * @return numStudents
	 */
	public int getNumStudents() {
		return this.numStudents;
	}
}
