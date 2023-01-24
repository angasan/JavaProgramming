//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Author:   Angela Galindo 
//
///////////////////////////////////////////////////////////////////////////////

import java.util.NoSuchElementException;

/**
 * This class implements unit test methods to check the correctness of
 * Application, ApplicationIterator, ApplicationQueue and OpenPosition classes
 * in the assignment.
 *
 */
public class OpenPositionTester {

	/**
	 * This method tests and makes use of the Application constructor, getter
	 * methods, toString() and compareTo() methods.
	 * 
	 * @return true when this test verifies the functionality, and false otherwise
	 */
	public static boolean testApplication() {

		// Check that when created with invalid inputs, the constructor throws the
		// adequate exception
		try {
			Application app = new Application(null, "ma@mail", 1);
			return false;
		} catch (IllegalArgumentException iae) {
		} catch (Exception e) {
			return false;
		}

		try {
			Application app = new Application(" ", "ma@mail", 1);
			return false;
		} catch (IllegalArgumentException iae) {
		} catch (Exception e) {
			return false;
		}

		try {
			Application app = new Application("Hola", null, 1);
			return false;
		} catch (IllegalArgumentException iae) {
		} catch (Exception e) {
			return false;
		}

		try {
			Application app = new Application("Hola", " ", 1);
			return false;
		} catch (IllegalArgumentException iae) {
		} catch (Exception e) {
			return false;
		}

		try {
			Application app = new Application("Hola", "maria", 1);
			return false;
		} catch (IllegalArgumentException iae) {
		} catch (Exception e) {
			return false;
		}

		try {
			Application app = new Application("Hola", "maria@@@@", 1);
			return false;
		} catch (IllegalArgumentException iae) {
		} catch (Exception e) {
			return false;
		}

		try {
			Application app = new Application("Hola", "Hola@mail", -1);
			return false;
		} catch (IllegalArgumentException iae) {
		} catch (Exception e) {
			return false;
		}

		try {
			Application app = new Application("Hola", "Hola@mail", 101);
			return false;
		} catch (IllegalArgumentException iae) {
		} catch (Exception e) {
			return false;
		}

		try {
			Application app = new Application("Hola", "Hola@mail", 1);
		} catch (Exception e) {
			return false;
		}

		try {
			Application app = new Application("Hola", "Hola@mail", 100);
		} catch (Exception e) {
			return false;
		}

		// create an Application with valid input
		Application app;
		try {
			app = new Application("Maria", "mariasg@mail", 70);
		} catch (Exception e) {
			return false;
		}

		// verify getters
		if (!app.getName().equals("Maria"))
			return false;
		if (!app.getEmail().equals("mariasg@mail"))
			return false;
		if (app.getScore() != 70)
			return false;

		// verify compareTo
		Application app2 = new Application("Maria", "mariasg@mail", 70);
		Application app3 = new Application("carla", "carla@mail", 50);

		// Check that the correct exception is thrown when given an invalid input
		try {
			app.compareTo(null);
			return false;
		} catch (NullPointerException npe) {
		}

		try {
			app.compareTo(app2);
		} catch (Exception e) {
			return false;
		}

		if (app.compareTo(app2) != 0)
			return false;
		if (app.compareTo(app3) <= 0)
			return false;
		if (app3.compareTo(app) >= 0)
			return false;

		// verify toString
		String expected = "Maria" + ":" + "mariasg@mail" + ":" + "70";
		String gotten = app.toString().trim();
		if (!gotten.equals(expected))
			return false;

		return true; // TO DO change this
	}

	/**
	 * This method tests and makes use of the ApplicationIterator class.
	 * 
	 * @return true when this test verifies the functionality, and false otherwise
	 */
	public static boolean testApplicationIterator() {
		// create an ApplicationQueue with capacity at least 3
		// and at least 3 Applications with different scores
		ApplicationQueue aq1 = new ApplicationQueue(3);
		Application a1 = new Application("maria", "ma@mail", 2);
		Application a2 = new Application("angela", "an@mail", 5);
		Application a3 = new Application("carla", "ca@mail", 3);

		// add those Applications to the queue (We are assuming the enqueue/dequeue
		// methods work)
		aq1.enqueue(a3);
		aq1.enqueue(a2);
		aq1.enqueue(a1);

		// verify that iterating through the queue gives you the applications in order
		// of INCREASING score
		int i = 0;
		for (Application a : aq1) {
			// System.out.println(a.toString());
			if (i == 0 && a.compareTo(a1) != 0)
				return false;
			if (i == 1 && a.compareTo(a3) != 0)
				return false;
			if (i == 2 && a.compareTo(a2) != 0)
				return false;
			i++;
		}

		return true; // TO DO change this
	}

	/**
	 * This method tests and makes use of the enqueue() and dequeue() methods in the
	 * ApplicationQueue class.
	 * 
	 * @return true when this test verifies the functionality, and false otherwise
	 */
	public static boolean testEnqueueDequeue() {
		// create an ApplicationQueue with capacity 3
		// and at least 4 Applications with different scores
		ApplicationQueue aq1 = new ApplicationQueue(3);
		Application a1 = new Application("maria", "ma@mail", 2);
		Application a2 = new Application("angela", "an@mail", 5);
		Application a3 = new Application("carla", "ca@mail", 1);

		// enqueue an invalid value (null)
		try {
			aq1.enqueue(null);
			return false;
		} catch (NullPointerException npe) {
		} catch (Exception e) {
			return false;
		}

		// enqueue one valid application
		try {
			aq1.enqueue(a1);
		} catch (Exception e) {
			return false;
		}
		if (aq1.isEmpty())
			return false;
		if (aq1.peek() != a1)
			return false;
		if (aq1.size() != 1)
			return false;

		// enqueue two more valid applications
		aq1.enqueue(a2);
		if (aq1.isEmpty())
			return false;
		if (aq1.peek() != a1)
			return false;
		if (aq1.size() != 2)
			return false;

		aq1.enqueue(a3);
		if (aq1.isEmpty())
			return false;
		if (aq1.peek() != a3)
			return false;
		if (aq1.size() != 3)
			return false;

		// enqueue one more application (exceeds capacity)
		try {
			aq1.enqueue(a3);
			return false;
		} catch (IllegalStateException ise) {
		}

		// dequeue one application (should be lowest score)
		Application a;
		try {
			a = aq1.dequeue();
		} catch (Exception e) {
			return false;
		}
		if (a.compareTo(a3) != 0)
			return false;
		if (aq1.size() != 2)
			return false;
		if (aq1.isEmpty())
			return false;

		// dequeue all applications
		a = aq1.dequeue();
		if (a.compareTo(a1) != 0)
			return false;
		if (aq1.size() != 1)
			return false;
		if (aq1.isEmpty())
			return false;

		a = aq1.dequeue();
		if (a.compareTo(a2) != 0)
			return false;
		if (aq1.size() != 0)
			return false;
		if (!aq1.isEmpty())
			return false;

		// dequeue from an empty queue
		try {
			a = aq1.dequeue();
			return false;
		} catch (NoSuchElementException nse) {
		} catch (Exception e) {
			return false;
		}

		return true; // TODO change this
	}

	/**
	 * This method tests and makes use of the common methods (isEmpty(), size(),
	 * peek()) in the ApplicationQueue class.
	 * 
	 * @return true when this test verifies the functionality, and false otherwise
	 */
	public static boolean testCommonMethods() {
		// create an ApplicationQueue with 0 capacity (should fail)
		ApplicationQueue a;
		try {
			a = new ApplicationQueue(0);
		} catch (IllegalArgumentException iae) {

		}

		try {
			a = new ApplicationQueue(-2);
		} catch (IllegalArgumentException iae) {

		}

		// create an ApplicationQueue with capacity 3
		// and at least 3 Applications with different scores
		try {
			a = new ApplicationQueue(3);
		} catch (Exception e) {
			return false;
		}

		Application a1 = new Application("maria", "ma@mail", 8);
		Application a2 = new Application("angela", "an@mail", 5);
		Application a3 = new Application("carla", "ca@mail", 2);

		// verify the methods' behaviors on an empty queue
		if (!a.isEmpty())
			return false;
		if (a.size() != 0)
			return false;
		try {
			a.peek();
			return false;
		} catch (NoSuchElementException nse) {
		}

		// add one Application and verify the methods' behaviors
		a.enqueue(a1);
		if (a.isEmpty())
			return false;
		if (a.size() != 1)
			return false;
		try {
			Application ap = a.peek();
			if (ap != a1)
				return false;
		} catch (Exception nse) {
			return false;
		}

		// add the rest of the Applications and verify the methods' behaviors
		a.enqueue(a2);
		if (a.isEmpty())
			return false;
		if (a.size() != 2)
			return false;
		if (a.peek() != a2)
			return false;

		a.enqueue(a3);
		if (a.isEmpty())
			return false;
		if (a.size() != 3)
			return false;
		if (a.peek() != a3)
			return false;

		Application at = a.dequeue();
		if (at != a3)
			return false;
		if (a.size() != 2)
			return false;

		at = a.dequeue();
		if (at != a2)
			return false;
		if (a.size() != 1)
			return false;

		at = a.dequeue();
		if (at != a1)
			return false;
		if (a.size() != 0)
			return false;

		return true; // TODO change this
	}

	/**
	 * This method tests and makes use of OpenPosition class.
	 * 
	 * @return true when this test verifies the functionality, and false otherwise
	 */
	public static boolean testOpenPosition() {
		// create an OpenPosition with 0 capacity (should fail)
		OpenPosition o;
		try {
			o = new OpenPosition("Machine learning engineer", 0);
			return false;
		} catch (IllegalArgumentException iae) {
		} catch (Exception e) {
			return false;
		}

		// create an OpenPosition with capacity 3
		// and at least 5 Applications with different scores
		try {
			o = new OpenPosition("Machine learning engineer", 3);
		} catch (Exception e) {
			return false;
		}
		Application a1 = new Application("maria", "ma@mail", 8);
		Application a2 = new Application("angela", "an@mail", 5);
		Application a3 = new Application("carla", "ca@mail", 2);
		Application a4 = new Application("maria", "ma@mail", 3);
		Application a5 = new Application("angela", "an@mail", 9);

		// verify that the 3 MIDDLE-scoring Applications can be added
		// don't use the highest and lowest scoring applications YET
		try {
			boolean b = o.add(a1);
			if (!b)
				return false;
			b = o.add(a2);
			if (!b)
				return false;
			b = o.add(a4);
			if (!b)
				return false;
		} catch (Exception e) {
			return false;
		}

		// verify that getApplications returns the correct value for your input
		String expected = "maria:ma@mail:3\n"
				+ "angela:an@mail:5\n"
				+ "maria:ma@mail:8\n";
		
		if (!o.getApplications().trim().equals(expected.trim())) return false;
		
		// verify that the result of getTotalScore is the sum of all 3 Application
		// scores
		if (o.getTotalScore() != 16) return false;
		
		// verify that the lowest-scoring application is NOT added to the OpenPosition
		boolean b = o.add(a3);
		if (b) return false;
 
		// verify that the highest-scoring application IS added to the OpenPosition
		b = o.add(a5);
		if (!b) return false;
 
		// verify that getApplications has changed correctly
		expected = "angela:an@mail:5\n"
				+ "maria:ma@mail:8\n"
				+ "angela:an@mail:9\n";
		
		if (!o.getApplications().trim().equals(expected.trim())) return false;
		
		// verify that the result of getTotalScore has changed correctly
		if (o.getTotalScore() != 22) return false;
		return true; // TO DO change this
	}

	/**
	 * This method calls all the test methods defined and implemented in your
	 * OpenPositionTester class.
	 * 
	 * @return true if all the test methods defined in this class pass, and false
	 *         otherwise.
	 */
	public static boolean runAllTests() {
		return testApplication() && testApplicationIterator() && testEnqueueDequeue() && testCommonMethods()
				&& testOpenPosition();
	}

	/**
	 * Driver method defined in this OpenPositionTester class
	 * 
	 * @param args input arguments if any.
	 */
	public static void main(String[] args) {
		System.out.println(runAllTests());
	}
}
