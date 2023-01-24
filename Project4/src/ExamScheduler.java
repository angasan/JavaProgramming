//////////////////////////////// FILE HEADER ///////////////////////////////////
//
// Author:   Angela Galindo Santos
//
///////////////////////////////////////////////////////////////////////////////

import java.util.ArrayList;

/**
 * Models an Exam Scheduler with all the recursive methods to create a schedule.
 */
public class ExamScheduler {

	/**
	 * Using the findScheduleHelper method, this method creates a schedule and finds
	 * a possible combination to assign every course to a room. If the combination
	 * was not possible to find, it throws an IllegalStateException.
	 * 
	 * @param rooms   - array of rooms we want to create an schedule with
	 * @param courses - array of courses we want to find a room for
	 * @return Schedule - returns the schedule with a possible schedule.
	 */
	public static Schedule findSchedule(Room[] rooms, Course[] courses) {

		return findScheduleHelper(new Schedule(rooms, courses), 0);
	}

	/**
	 * Recursive method that actually finds a valid combination to assign every
	 * course to a room.
	 * 
	 * @param schedule - Schedule object created in the findSchedule method.
	 * @param idx      - index of the course we want to start to find a combination
	 *                 with
	 * @return Schedule - returns the Schedule object with the first possible
	 *         combination found
	 */
	private static Schedule findScheduleHelper(Schedule schedule, int idx) {

		/**
		 * BASE CASE: You have looped through all the courses in the course array. Check
		 * if the schedule is complete and, therefore, valid or not.
		 */
		if (idx == schedule.getNumCourses()) {
			if (schedule.isComplete())
				return schedule;
			else
				throw new IllegalStateException("Invalid Schedule");
		}

		/**
		 * RECURSIVE CASE: You still need to go through the courses list.
		 */

		/**
		 * Has the course at index idx been assigned? If so move to the next one. If
		 * not, try to assign it to a room.
		 */
		if (schedule.isAssigned(idx)) {
			return findScheduleHelper(schedule, ++idx);
		} else {
			/**
			 * To assign a room, loop over the rooms array and see if the assignment is
			 * possible. If the assignment is possible, recursively add the next
			 * assignments.
			 */
			for (int i = 0; i < schedule.getNumRooms(); i++) {
				/**
				 * If the room we are at is valid, the try block should run entirely. If not,
				 * after trying to assign the course to the current room, the catch block should
				 * catch the exception thrown.
				 */
				try {
					Schedule schedule2 = schedule.assignCourse(idx, i);
					/**
					 * If this assignment makes the next ones impossible, then the room shouldn't be
					 * assigned, but rather try more options.
					 */
					try {
						Schedule schedule3 = findScheduleHelper(schedule2, (idx + 1));
						return findScheduleHelper(schedule3, (idx + 1));
					} catch (IllegalStateException ise) {
						continue;
					}
				} catch (IllegalArgumentException iae) {
					continue;
				}
			}
		}
		return findScheduleHelper(schedule, (idx + 1));
	}

	/**
	 * With the help of the findAllSchedulesHelper method, this method gets an array
	 * with all the possible schedules that can be created for a set of rooms and
	 * courses
	 * 
	 * @param rooms   - array of rooms to create the schedules with
	 * @param courses - courses that need a room
	 * @return ArrayList<Schedule> - the array list containing all possible
	 *         schedules.
	 */
	public static ArrayList<Schedule> findAllSchedules(Room[] rooms, Course[] courses) {
		return findAllSchedulesHelper(new Schedule(rooms, courses), 0);
	}

	/**
	 * Recursive method that actually finds all the possible schedules and adds them
	 * to the list of schedules that is then returned in the findAllSchedules
	 * method. If no schedules can be found, the method returns an empty list.
	 * 
	 * @param schedule - Schedule object created in the findAllSchedules method
	 * @param idx      - index of the course we are going to start working with
	 * @return ArrayList<Schedule> - the array list containing all possible
	 *         schedules.
	 */
	private static ArrayList<Schedule> findAllSchedulesHelper(Schedule schedule, int idx) {
		ArrayList<Schedule> lista = new ArrayList<Schedule>(); // need to initialise the list to return

		/**
		 * BASE CASE: We have gone through all the courses
		 */
		if (idx == schedule.getNumCourses()) {
			if (schedule.isComplete()) {
				lista.add(schedule); // Only add the schedule if it is complete
			}
			return lista; // return the list wether something has been added or not
		}

		/**
		 * RECURSIVE CASES: We still need to check more courses
		 */
		if (schedule.isAssigned(idx)) {

			/**
			 * If the course at the index we are looking at has been assigned then we move
			 * to the next ones recursively. Once we have the list of schedules for the next
			 * ones we add those to the current list we are working with and return it.
			 */
			lista.addAll(findAllSchedulesHelper(schedule, (idx + 1)));
			return lista; // need to do this in two steps because addAll returns a boolean

		} else {

			/**
			 * If the course we at the index we are working on has not been assigned then we
			 * need to find a room to assign it with.
			 */
			Schedule schedule2;
			for (int i = 0; i < schedule.getNumRooms(); i++) { // To find the room first we have to iterate over the
																// rooms array
				try {

					/**
					 * For each room we try to assign it to the course we are working on. If the
					 * assignment is not possible, the first line of code within this block will
					 * throw an exception (as stated in the assignCourse method) and we can move to
					 * the next room
					 */
					schedule2 = schedule.assignCourse(idx, i);

					/**
					 * If the room could be assigned to the course, then we need to try to find an
					 * assignment for the next courses recursively. Once we get this list of
					 * possible schedules we can add it to the list we are currently working with.
					 */
					lista.addAll(findAllSchedulesHelper(schedule2, (idx + 1)));

					// We don't return the list here because we want to find all the possible
					// combinations
				} catch (IllegalArgumentException iae) {
					continue;
				}
			}
			
			/**
			 * Once we have looped over all the rooms and looked for all possible schedules,
			 * we return the resulting list. This could be empty if no schedules have been found.
			 */
			return lista;
		}

	}

}