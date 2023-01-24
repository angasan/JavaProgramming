
//////////////// FILE HEADER //////////////////////////
//
// Author:   Angela Galindo
//
///////////////////////////////////////////////////////

import java.util.ArrayList;

/**
 * Utility methods for coordinating passengers and flights
 */
public class BookingSystem {
  
  //the one class variable, a list of all valid flights in this system
  private static ArrayList<Flight> flights = new ArrayList<Flight>();
  
  /**
   * A utility method which creates a Flight object and adds it to the list of available flights.
   * 
   * @param origin where the flight will take off
   * @param destination where the flight will land
   * @param flightNo the flight's identifier
   * @param capacity the number of seats on the flight
   */
  public static void scheduleFlight(String origin, String destination, String flightNo,
      int capacity) {
    Flight f = new Flight(origin, destination, flightNo, capacity);
    flights.add(f);
  }
  /**
   * Only for testing purposes so I make sure the list is reseted every time I want
   */
  public static void restart() {
    flights = new ArrayList<Flight>();
  }
  
  /**
   * A utility method which locates the flight in the system's list of flights based on its
   * identifier (flightNo) and, if found, creates an object to store the traveler's information
   * and a Booking for the reservation.
   * 
   * @param name the name of the traveler
   * @param email the email address of the traveler
   * @param flightNo the flight's identifier
   * @return the confirmation number associated with the booking, if successfully created
   * 
   * @throws IllegalArgumentException if the specified flight is not in the flights ArrayList
   * @throws IllegalStateException if the booking cannot be created (the flight is full)
   */
  public static String bookTicket(String name, String email, String flightNo) {
    // locate the specified flight
    Flight f = null;
    for(int i=0; i<flights.size(); i++) {
      Flight tmp = flights.get(i);
      if (tmp.getFlightNo().equals(flightNo)) {
        f = tmp;
        break;
      }
    }
    
    // if we couldn't find it:
    if(f == null) {
      throw new IllegalArgumentException("Flight "+flightNo+" not found.");
    }
    
    // otherwise create the traveler and attempt to book them on the flight
    try {
      Traveler t = new Traveler(name, email);
      Booking b = new Booking(t, f);
      f.addBooking(b);
      return b.getConfirmationNo();
    } catch (RuntimeException e) {
      throw new IllegalStateException("Could not book "+name+" on flight "+flightNo);
    }
  }
  
  /**
   * A utility method which looks through all of the Bookings in all of the Flights listed in the
   * system for a Booking with the given confirmation number.
   * 
   * @param confirmationNo the confirmation number associated with the Booking we're looking for
   * @return the string representation of the Booking with that confirmation number, if found, or
   *   the string "Not Found" if the Booking was not located
   */
  public static String getBooking(String confirmationNo) {
    // look through the booking lists on each flight
    Flight f = null;
    for (int i = 0; i<flights.size(); i++) {
      f = flights.get(i);
      Booking b = f.getBooking(confirmationNo);
      if(b != null) {
        // recall that null is getBooking's error code
        return b.toString();
      }
    }
    
    // if we made it this far, that booking wasn't on any flight
    return "Not Found";
  }
}
