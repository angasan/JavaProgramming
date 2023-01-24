
//////////////// FILE HEADER //////////////////////////
//
// Author:   Angela Galindo
//
///////////////////////////////////////////////////////

import java.util.ArrayList;

/**
 * Represents a flight that can be booked in our system. Maintains a list of all Bookings made for
 * the given flight.
 */
public class Flight {

  // data fields associated with a single Flight object
  private String origin;
  private String destination;
  private String flightNo;
  private ArrayList<Booking> bookings;
  private ArrayList<String> seats; // filled using the generateSeats() method

  /**
   * Constructs a new Flight object with a given origin, destination, and flight number, and uses a
   * helper method to generate the seats available to be booked.
   * 
   * @param origin      where the flight will take off
   * @param destination where the flight will land
   * @param flightNo    the flight's identifier
   * @param capacity    the number of seats on the flight
   */
  public Flight(String origin, String destination, String flightNo, int capacity) {
    // TODO: initialize all fields associated with this Flight
    this.origin = origin;
    this.destination = destination;
    this.flightNo = flightNo;
    seats = generateSeats(capacity); // this method creates seats according to the given capacity
    bookings = new ArrayList<Booking>(); // esto se puede así??
  }

  /**
   * Accessor method to retrieve this flight's number
   * 
   * @return the flight's identifier
   */
  public String getFlightNo() {
    return this.flightNo; // TODO: complete this method according to the documentation
  }

  /**
   * Calculates the total capacity of this flight based on the number of bookings + the number of
   * available seats.
   * 
   * @return the total capacity of this flight
   */
  public int getCapacity() {
    return seats.size() + bookings.size(); // TODO: complete this method according to the
                                           // documentation
  }

  /**
   * Removes and returns the next available seat from this flight's seats list.
   * 
   * @return the generated seat identifier of the next available seat, e.g. "A12"
   * @throws IllegalStateException if no more seats are available
   */
  public String getNextAvailableSeat() throws IllegalStateException {
    String seatNo;

    // Check that there are still seats available
    if (seats.size() > 0) {
      seatNo = seats.get(0); // get the first seat number available
      seats.remove(0); // remove the seat number (that we just used) from the list
      return seatNo;
    } else {
      throw new IllegalStateException();
    }

    // TODO: complete this method according to the documentation
  }

  /**
   * Looks through the list of bookings for this flight to determine whether any of them contain the
   * provided confirmation number.
   * 
   * @param confirmationNo the confirmation number to search for
   * @return the Booking corresponding to the confirmation number if found; null otherwise
   */
  public Booking getBooking(String confirmationNo) {

    //System.out.println(bookings.size());
    // Search the bookings list to see if any of the bookings' confirmationNo matches
    for (int i = 0; i < bookings.size(); i++) {
      if (bookings.get(i).getConfirmationNo().equals(confirmationNo))
        return bookings.get(i);
    }

    return null; // TODO: complete this method according to the documentation
  }

  /**
   * Records that a Booking has been made on this flight. Does NOT call getNextAvailableSeat(). Must
   * verify that the Booking in question has not already been added to the list (hint: use
   * getBooking() to check).
   * 
   * @param b the booking to add, which has already been properly constructed.
   * @throws IllegalArgumentException if the Booking has already been added to this flight
   */
  public void addBooking(Booking b) throws IllegalArgumentException {

    // Check if there is are available seats in the flight
    if (seats.size() <= 0) { // If there are not available seats
      throw new IllegalArgumentException(); //Throw exception
    } else {
      if (getBooking(b.getConfirmationNo()) == null) {
        bookings.add(b); // add the booking only when not found
      }
    }

    return;

    // TODO: complete this method according to the documentation
  }

  /**
   * Creates and returns a String representation of this flight for printing, for example:
   * 
   * Flight Number: A234 Origin: MSN Destination: PDX Capacity: 50 Available Seats: 43
   * 
   * @return a String representation of this flight
   */
  @Override
  public String toString() {

    String flightString = "";

    // Get the Flight Number
    flightString += "Flight Number: " + flightNo + "\n";

    // Get the Origin:
    flightString += "Origin: " + origin + "\n";

    // Get the Destination
    flightString += "Destination: " + destination + "\n";

    // Get the Capacity
    flightString += "Capacity: " + String.valueOf(seats.size() + bookings.size()) + "\n";

    // Get the Available Seats
    flightString += "Available Seats: " + String.valueOf(seats.size()) + "\n";

    /*
     * Remember that as we take the number seat for a booking we remove that item from the seats
     * list and therefore the size of the list is smaller than the capacity
     */

    return flightString; // TODO: complete this method according to the documentation
  }

  /**
   * Determines whether a provided object is equal to this flight. This implementation is provided
   * for you.
   * 
   * @param o the object to compare with
   * @return true if the object is a Flight with the same flightNo as this one, false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (o instanceof Flight) {
      return ((Flight) o).getFlightNo().equals(this.flightNo);
    }
    return false;
  }

  /**
   * A class method to generate the seat identifiers for the flight. All seats begin with one of {A,
   * B, C, D} followed by the row number, beginning at 1 and going until all of the capacity has
   * been accounted for. This implementation is provided for you.
   * 
   * @param capacity the number of seat numbers to generate
   * @return an ArrayList of the generated seat numbers
   */
  protected static ArrayList<String> generateSeats(int capacity) {
    ArrayList<String> s = new ArrayList<String>(capacity);
    String seatLetters = "ABCD";
    int seatCounter = 0;
    int rowCounter = 1;

    while (s.size() < capacity) {
      s.add("" + seatLetters.charAt(seatCounter++) + rowCounter);
      seatCounter %= seatLetters.length();
      if (seatCounter == 0)
        rowCounter++;
    }

    return s;
  }
}


