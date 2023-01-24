//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Author:   Angela Galindo 
//
///////////////////////////////////////////////////////////////////////////////

/**
 * A application handler of an open position using priority queue. Only saves a new Application
 * when the queue is not full, or when it can replace older, lower-scored ones with its higher
 * scores.
 */
public class OpenPosition {
	private String positionName;
    private ApplicationQueue applications; // the priority queue of all applications
    private int capacity;                  // the number of vacancies
    
    /**
     * Creates a new open position with the given capacity
     * 
     * @param capacity the number of vacancies of this position
     * @throws IllegalArgumentException with a descriptive error message if the capacity is not a
     *                                  positive integer
     */
    public OpenPosition(String positionName, int capacity) {
    	// TO DO verify the value of capacity
    	if (capacity <= 0) throw new IllegalArgumentException();
    	
    	// TO DO initialize the data fields appropriately
    	this.positionName = positionName;
    	this.capacity = capacity;
    	this.applications = new ApplicationQueue(capacity);
    }
    
    public String getPositionName() { return this.positionName; }
    
    /**
     * Tries to add the given Application to the priority queue of this position.  return
     * False when the new Application has a lower score than the lowest-scored Application
     * in the queue.
     * 
     * @return Whether the given Application was added successfully
     */
    public boolean add(Application application) {
    	// TO DO if the queue is full, determine whether this application has a higher score than 
    	// the current lowest-scoring application; if not, do not add it
    	if (this.applications.size() == capacity) {	
    		int comparison = application.compareTo(this.applications.peek());
    		if (comparison > 0) {
    			applications.dequeue();
    			applications.enqueue(application);
    			return true;
    		} else return false;
    	} else {
    		applications.enqueue(application);
    		return true;
    	}
    }
    
    /**
     * Returns the list of Applications in the priority queue.
     * 
     * @return The list of Applications in the priority queue, in increasing order of the
     * scores.
     */
    public String getApplications() {
        return applications.toString();
    }
    
    /**
     * Returns the total score of Applications in the priority queue.
     * 
     * @return The total score of Applications in the priority queue.
     */
    public int getTotalScore() {
        // Just add up the scores iterating over the queue
    	int count = 0;
    	for (Application a : applications) {
    		count += a.getScore();
    	}
        return count;  
    }
    
    
}
