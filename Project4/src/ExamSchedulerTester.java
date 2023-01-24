//////////////////////////////// FILE HEADER ///////////////////////////////////
//
// Author:   Angela Galindo Santos
//
///////////////////////////////////////////////////////////////////////////////

import java.util.ArrayList;

/**
 * This class tests all the methods implemented for the Room, Course and
 * Schedule objects, as well as the ExamScheduler recursive methods.
 */
public class ExamSchedulerTester {

	/**
	 * Checks that the methods at the Course class work.
	 * 
	 * @return true if everything worked as expected or false if it doesn't
	 */
	public static boolean testCourse() {
		// TODO Verify that the constructor correctly initialises name and numStudents
		try {
			Course course1 = new Course("CS300", 90);
			if (!course1.getName().equals("CS300"))
				return false;
			if (course1.getNumStudents() != 90)
				return false;
		} catch (IllegalArgumentException iae) {
			return false; // Checks that no exception is thrown when it shouldn't
		} catch (Exception e) {
			return false;
		}

		// TODO Verify that the constructor throws the correct exception when required
		try {
			Course course2 = new Course("CS300", -90);
			return false;
		} catch (IllegalArgumentException iae) {

		} catch (Exception e) {
			return false;
		}

		return true;
	}

	/**
	 * Test that the methods at the Room class work
	 * 
	 * @return
	 */
	public static boolean testRoom() {
		// TODO test that the constructor initialises the data fields

		// TODO test that the correct exception is thrown by the constructor
		try {
			Room room2 = new Room("AG 150", -100);
			return false;
		} catch (IllegalArgumentException iae) {

		} catch (Exception e) {
			return false;
		}

		// Check that no exception is thrown when it shouldn't
		try {
			Room room1 = new Room("AG 150", 100);
			if (room1.getCapacity() != 100)
				return false;
			if (!room1.getLocation().equals("AG 150"))
				return false;

			/**
			 * Check that reduceCapacity reduces the capacity of the class correctly by
			 * creating a new room object and doesn't modify the original room. Also checks
			 * that no exception is thrown when not expected
			 */
			try {
				Room room3 = room1.reduceCapacity(10);
				if (room3.getCapacity() != 90)
					return false;
				if (room1.getCapacity() != 100)
					return false;
			} catch (IllegalArgumentException iae) {
				return false;
			} catch (Exception e) {
				return false;
			}

			/**
			 * Checks if the correct exception is thrown when expected
			 */
			try {
				Room room3 = room1.reduceCapacity(100);
				return false;
			} catch (IllegalArgumentException iae) {

			} catch (Exception e) {
				return false;
			}

		} catch (IllegalArgumentException iae) {
			return false;
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	/**
	 * Tests all Schedule accessor methods
	 * 
	 * @return
	 */
	public static boolean testScheduleAccessors() {
		// Assuming that Room and Course have been tested and work

		// Create a Room array:
		Room room1 = new Room("AG 150", 100);
		Room room2 = new Room("BG 100", 150);
		Room room3 = new Room("CG 50", 200);
		Room[] rooms = new Room[] { room1, room2, room3 };

		Course course1 = new Course("CS300", 90);
		Course course2 = new Course("CS220", 100);
		Course course3 = new Course("CS368", 50);
		Course[] courses = new Course[] { course1, course2, course3 };

		// Create a schedule object to test its accessors
		Schedule schedule = new Schedule(rooms, courses);

		// Check that the data fields for room and courses is assigned correctly
		if (schedule.getNumRooms() != 3)
			return false;
		if (!schedule.getRoom(0).equals(room1))
			return false;
		if (schedule.getNumCourses() != 3)
			return false;
		if (!schedule.getCourse(0).equals(course1))
			return false;

		// Check that none of the courses have been assigned a room and that the
		// schedule is not complete
		for (int i = 0; i < courses.length; i++) {
			if (schedule.isAssigned(i))
				return false;
		}
		if (schedule.isComplete())
			return false;

		/**
		 * Checks that the getAssignment method throws an exception when expected
		 */
		try {
			Room newRoom = schedule.getAssignment(0);
			return false;
		} catch (IllegalArgumentException iae) {

		} catch (Exception e) {
			return false;
		}
		
		String str = "CS300: Unassigned, CS220: Unassigned, CS368: Unassigned";
		if (!schedule.toString().equals(str)) return false;

		return true;
	}

	/**
	 * Test that the assignCourse works correctly
	 * 
	 * @return
	 */
	public static boolean testAssignCourse() {

		// Create an array of rooms and an array of courses
		Room room1 = new Room("AG 150", 100), room2 = new Room("BG 100", 150), room3 = new Room("CG 50", 200);
		Course course1 = new Course("CS300", 90), course2 = new Course("CS220", 100), course3 = new Course("CS368", 50);

		Room[] rooms = new Room[] { room1, room2, room3 };
		Course[] courses = new Course[] { course1, course2, course3 };

		// Create a schedule object to test assignCourse
		Schedule schedule = new Schedule(rooms, courses);

		// Check the method doesn't take invalid indexes for room
		try {
			Schedule schedule2 = schedule.assignCourse(0, 15);
			System.out.println("129");
			return false;
		} catch (IndexOutOfBoundsException ioob) {

		} catch (Exception e) {
			System.out.println("134");
			return false;
		}

		try {
			Schedule schedule2 = schedule.assignCourse(0, -15);
			System.out.println("140");
			return false;
		} catch (IndexOutOfBoundsException ioob) {

		} catch (Exception e) {
			System.out.println("145");
			return false;
		}

		// Check that the method doesn't take invalid indexes for course
		try {
			Schedule schedule2 = schedule.assignCourse(20, 1);
			System.out.println("152");
			return false;
		} catch (IndexOutOfBoundsException ioob) {

		} catch (Exception e) {
			System.out.println("157");
			return false;
		}

		try {
			Schedule schedule2 = schedule.assignCourse(-20, 1);
			System.out.println("163");
			return false;
		} catch (IndexOutOfBoundsException ioob) {

		} catch (Exception e) {
			System.out.println("168");
			return false;
		}

		// Check that the method doesn't throw any exception when passed valid indexes
		Schedule schedule2;
		try {
			schedule2 = schedule.assignCourse(0, 1);
		} catch (IndexOutOfBoundsException ioob) {
			System.out.println("177");
			return false;
		} catch (IllegalArgumentException iae) {
			System.out.println("180");
			System.out.println(iae.getMessage());
			return false; // This should only be thrown when it has already been assigned
		} catch (Exception e) {
			System.out.println("184");
			return false;
		}

		try {
			if (schedule2.getAssignment(0).getLocation() != "BG 100")
				return false;
			if (schedule2.getAssignment(0).getCapacity() != room2.getCapacity() - course1.getNumStudents())
				return false;
		} catch (IllegalArgumentException iae) {
			System.out.println("192");
			return false;
		} catch (Exception e) {
			System.out.println("195");
			return false;
		}

		try {
			Schedule schedule3 = schedule2.assignCourse(0, 1);
			System.out.println("201");
			return false;
		} catch (IllegalArgumentException iae) {
			// This should only be thrown when the course has already been assigned
		} catch (Exception e) {
			System.out.println("206");
			return false;
		}

		return true;
	}

	/**
	 * Checks if the findSchedule method works
	 * 
	 * @return
	 */
	public static boolean testFindSchedule() {

		// First we need to create two arrays of rooms and courses
		Room room0 = new Room("A", 75), room1 = new Room("B", 100), room2 = new Room("C", 15);
		Course course0 = new Course("CS300", 15), course1 = new Course("CS220", 80), course2 = new Course("CS368", 70);
		Room[] rooms = new Room[] { room0, room1, room2 };
		Course[] courses = new Course[] { course0, course1, course2 };

		/**
		 * Now we can pass the method a valid input to create an schedule and check that
		 * it doesn't throw an exception
		 */
		Schedule schedule;
		try {
			schedule = ExamScheduler.findSchedule(rooms, courses);
		} catch (IllegalStateException ise) {
			System.out.println("1");
			return false;
		} catch (Exception e) {
			System.out.println("2");
			return false;
		}

		// Check that for a valid input, the returned schedule is complete
		if (!schedule.isComplete()) {
			System.out.println("3");
			return false;
		}

		// The first course could be assigned to any room
		if (!(schedule.getAssignment(0).getLocation().equals("A") || schedule.getAssignment(0).getLocation().equals("B")
				|| schedule.getAssignment(0).getLocation().equals("C"))) {
			System.out.println(schedule.getAssignment(0).getLocation());
			return false;
		} 

		// The second course only fits the second room
		if (!schedule.getAssignment(1).getLocation().equals("B")) {
			System.out.println("5");
			return false;
		}
		

		// The last course fits either on the first or second rooms.
		if (!(schedule.getAssignment(0).getLocation().equals("A")
				|| schedule.getAssignment(0).getLocation().equals("B"))){
			System.out.println("6");
			return false;
		}

		/**
		 * Now we are going to check that for an invalid input the method throws an
		 * exception (the correct one):
		 * 
		 */

		Room room4 = new Room("A", 0), room5 = new Room("B", 0), room6 = new Room("C", 0);
		Room[] rooms2 = new Room[] { room4, room5, room6 };
		Course[] courses2 = new Course[] { course0, course1, course2 };
		
		try {
			schedule = ExamScheduler.findSchedule(rooms2, courses2);
			System.out.println("7");
			return false; // shouldn't reach this point
		} catch (IllegalStateException ise) {
			
		} catch (Exception e) {
			System.out.println("8");
			return false;
		}
		
		return true;
	}
	
	/**
	 * Checks whether the method testFindAllSchedule works or not
	 * @return
	 */
	public static boolean testFindAllSchedule() {
		
		/**
		 * Check that for an invalid input, the resulting array is empty
		 */
		
		// First we need to create arrays for rooms and courses
		Room room0 = new Room("A", 0), room1 = new Room("B", 0), room2 = new Room("C", 0);
		Course course0 = new Course("CS300", 15), course1 = new Course("CS220", 80), course2 = new Course("CS368", 70);
		Room[] rooms = new Room[] { room0, room1, room2 };
		Course[] courses = new Course[] { course0, course1, course2 };
		
		ArrayList<Schedule> schedules = ExamScheduler.findAllSchedules(rooms, courses);
		
		if (schedules.size() != 0) return false; // The rooms didn't have enough capcity so no schedules can be made
		
		/**
		 * Check that the input works for a valid input
		 */
		Room room3 = new Room("A", 100), room4 = new Room("B", 100);
		Course course3 = new Course("CS300", 5), course4 = new Course("CS220", 5);
		Room[] rooms2 = new Room[] { room3, room4 };
		Course[] courses2 = new Course[] { course3, course4 };
		
		schedules = ExamScheduler.findAllSchedules(rooms2, courses2);
		if (schedules.size() != 4) return false; 
		// CS300 fits in A and so does CS220
		// CS300 fits in A and CS220 fits in B
		// CS300 fits in B and CS220 fits in A
		// CS300 fits in B and so does CS220

		return true;
	}

	public static void main(String[] args) {
		System.out.println("TEST RESULTS");
		System.out.println("Test course: " + testCourse());
		System.out.println("Test room: " + testCourse());
		System.out.println("Test schedule: " + (testScheduleAccessors() && testAssignCourse()));
		System.out.println("Test findSchedule: " + testFindSchedule());
		System.out.println("Test findAllSchedules: " + testFindAllSchedule());
	}

}
