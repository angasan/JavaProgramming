//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Author:   Angela Galindo 
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Online Sources: Intermediate Java Programming - CS300 Zybooks 
//
///////////////////////////////////////////////////////////////////////////////

/**
 * This class models a application with a name and due date
 */
public class Application implements Comparable<Application> {
	private final String name; // name of this applicant
	private final String email; // email of this applicant
	private final int score; // estimated score of this applicant

	/**
	 * Creates a new Application with the given information
	 *
	 * @param name  name of this applicant
	 * @param email email of this applicant
	 * @param score estimated score of this applicant (must be in the range 0 ..
	 *              100)
	 * @throws IllegalArgumentException if the provided name is null or blank, or if
	 *                                  the email is null or does not have a single
	 *                                  {@literal @}, or if score is not in the 0 ..
	 *                                  100 range.
	 */
	public Application(String name, String email, int score) {
		// throws an IllegalArgumentException if the provided name is null or blank
		if (name == null || name.isBlank()) throw new IllegalArgumentException("Name null or blank");
		// ... or if the provided email is null, or has no or multiple @
		if (email == null || email.isBlank()) throw new IllegalArgumentException("Email null or blank");
		int count = 0;
		for (int i = 0; i < email.length(); i++) {
			if (email.charAt(i) == '@') count += 1;
		}
		if (count != 1)  throw new IllegalArgumentException("Invalid email address");
		
		// ... or if the provided score is not in the 0 .. 100 range
		if (score < 0 || score > 100) throw new IllegalArgumentException("Score not in the desired range");
		
		// initialize values (TO DO change these)
		this.name = name;
		this.email = email;
		this.score = score;
	}

	/**
	 * Returns the name of this Applicant
	 * 
	 * @return the name of this Applicant
	 */
	public String getName() {
		return this.name; // TO DO fix
	}

	/**
	 * Returns the email of this Applicant
	 * 
	 * @return the email of this Applicant
	 */
	public String getEmail() {
		return this.email; // TO DO fix
	}

	/**
	 * Returns the score of this Applicant
	 * 
	 * @return the score of this Applicant
	 */
	public int getScore() {
		return this.score; // TO DO fix
	}

	/**
	 * TO DO: add this method Compares this Applicant to another applicant based on
	 * their score
	 * 
	 * @return a negative integer if this Applicant has an lower score, {@code 0} if
	 *         the two Applicants have the same score, and a positive integer if
	 *         this Applicant has a higher score.
	 * @throws NullPointerException if the other assignment o is null
	 */
	@Override
	public int compareTo(Application other) {
		if (other == null) throw new NullPointerException("Other applicant is null");
		
		int otherScore = other.getScore();
		if (this.getScore() > otherScore) return 1;
		else if (this.getScore() < otherScore) return -1;
		else return 0; // TO DO fix
	}

	/**
	 * Returns a String representing this Application containing its name, email and
	 * score. (This implementation is provided for you.)
	 * 
	 * @return a String representing this Application
	 */
	@Override
	public String toString() {
		return name + ":" + email + ":" + score;
	}
}
