
//////////////// FILE HEADER //////////////////////////
//
// Author:   Angela Galindo
//
///////////////////////////////////////////////////////

import java.util.ArrayList;

public class BookingSystemTester {

  public static boolean testTraveler() {
    // Create a Traveler object and verify that no exceptions are thrown
    try {
      Traveler t1 = new Traveler("Maria", "ma");

      // Verify that the name/email returned by the accessor methods are correct
      String nombre = t1.getName();
      String email = t1.getEmail();

      if (!nombre.equals("Maria")) {
        System.out.println("El nombre está mal");
        return false;
      }
      if (!email.equals("ma")) {
        System.out.println("El email está mal");
        return false;
      }


      // Use the mutator methods to change the name/email and verify that the accessors
      // return the correct NEW name/email
      t1.setName("Carla");
      t1.setEmail("lo");

      if (!t1.getName().equals("Carla")) {
        System.out.println("El nombre no se ha actualizado");
        return false;
      }
      if (!t1.getEmail().equals("lo")) {
        System.out.println("El email no se ha actualizado");
        return false;
      }

      // Create a second Traveler object and verify that its name/email are different from the
      // first Traveler's
      Traveler t2 = new Traveler("Jose", "jo");
      if (t2.getName().equals(t1.getName()))
        return false;
      if (t2.getEmail().equals(t1.getEmail()))
        return false;

    } catch (Exception e) {
      System.out.println(e.getStackTrace());
      return false;
    }

    return true;
  }

  /**
   * Tests the basic functionality of the Flight class. This implementation is provided for you.
   * 
   * @return true if and only if all tests pass
   */
  public static boolean testFlight() {
    // Create a Flight object and verify that no exceptions are thrown
    Flight f1, f2;
    try {
      f1 = new Flight("MSN", "PDX", "A234", 50);
      f2 = new Flight("DIA", "DFW", "SW32", 2);
    } catch (Exception e) {
      // this should not happen!
      System.out.println(e.getStackTrace());
      return false;
    }

    // Verify that the accessors give the correct values for each Flight
    String f1Expected = "Flight Number: A234\nOrigin: MSN\nDestination: PDX\n"
        + "Capacity: 50\nAvailable Seats: 50";
    String f2Expected =
        "Flight Number: SW32\nOrigin: DIA\nDestination: DFW\n" + "Capacity: 2\nAvailable Seats: 2";
    try {
      // basic accessors
      if (!f1.getFlightNo().equals("A234") || !f2.getFlightNo().equals("SW32")) {
        System.out.println("SW32");
        return false;
      }
      if (f1.getCapacity() != 50 || f2.getCapacity() != 2) {
        System.out.println("getCapacity");
        return false;
      }

      // toString
      if (!f1.toString().trim().equals(f1Expected)) {
        System.out
            .println("toString error, expected:\n" + f1Expected + "\nbut got:\n" + f1.toString());
        return false;
      }
      if (!f2.toString().trim().equals(f2Expected)) {
        System.out
            .println("toString error, expected:\n" + f2Expected + "\nbut got:\n" + f2.toString());
        return false;
      }

      // equals
      if (f1.equals(f2) || f2.equals(f1) || !f1.equals(f1) || !f2.equals(f2))
        return false;
    } catch (Exception e) {
      // this should not happen!
      return false;
    }

    // Verify that the seats work as expected - note this should return a different value each time
    try {
      if (!f2.getNextAvailableSeat().equals("A1") || !f2.getNextAvailableSeat().equals("B1"))
        return false;
    } catch (Exception e) {
      // this should not happen!
      return false;
    }

    // Verify that the seats run out correctly
    try {
      String oops = f2.getNextAvailableSeat();
      System.out.println("Tried to get a third available seat from a flight with capacity 2, but "
          + "instead of throwing an exception, your method returned " + oops);
      return false;
    } catch (IllegalStateException e) {
      // this is good! do nothing.
    } catch (Exception e) {
      // this should not happen!
      return false;
    }

    return true;

  }

  public static boolean testBooking() {
    if (!testFlight()) {
      System.out.println("testFlight");
      return false; // this test relies on Flight working properly!
    }
    if (!testTraveler()) {
      System.out.println("testTraveler");
      return false; // this test relies on Traveler working properly!
    }

    // Setup: create a Traveler and a Flight with at least one available seat.
    Flight f1 = new Flight("MSN", "PDX", "A234", 1); // Available seats = 1
    Traveler t1 = new Traveler("Hobbes LeGault", "hobbes@mail.com");

    // Create a booking with the Traveler and the Flight and verify that no exception is thrown
    Booking.resetConfirmationNo();
    Booking b1;

    try {
      b1 = new Booking(t1, f1);
      // f1.getBooking(b1.getConfirmationNo()); La size de bookings en f1 == 0 ahora mismo
    } catch (Exception e) {
      // this should not happen!
      System.out.println(e.getStackTrace());
      return false;
    }
    // Check the results of getSeatNo, getConfirmationNo, and toString
    String b1Expected = "Name: Hobbes LeGault\nConfirmation Number: HOB1234\nFlight Number: A234\n"
        + "Seat Number: A1\n";

    try {
      // getSeatNo
      if (!b1.getSeatNo().equals("A1")) {
        System.out.println("getSeatNo()"); // los asientos empiezan fila 1 col A
        //System.out.println(b1.getSeatNo());
        return false;
      }

      // getConfirmationNo
      if (!b1.getConfirmationNo().equals("HOB1234")) {
        System.out.println("getConfirmationNo"); // los num de conf empiezan con el 1234
        System.out.println(b1.getConfirmationNo());
        return false;
      }

      // toString
      if (!b1.toString().equals(b1Expected)) { // with trims doesn't work but idk why
        System.out
            .println("toString error, expected:\n" + b1Expected + "\nbut got:\n" + b1.toString());
        return false;
      }
    } catch (Exception e) {
      // this should not happen!
      System.out.println(e.getStackTrace());
      return false;
    }
    
    // Add this Booking to the Flight and verify that the Flight's capacity is correct
    // (capacity = available seats + number of bookings)
    try {
      f1.addBooking(b1);
      //f1.getBooking(b1.getConfirmationNo());
      if (f1.getCapacity() != 1) {
        System.out.println(f1.getCapacity());
        return false;
      }
    } catch (Exception e) {
      // this should not happen!
      System.out.println("Primera excepción de addBoking");
      return false;
    }

    // Try adding it again and verify that the correct exception is thrown
    try {
      f1.addBooking(b1);
      System.out.println("Booking aunque no hay asientos");
      return false;
    } catch (IllegalArgumentException e) {
      // this should not happen!
    }

    return true;
  }

  public static boolean testBookingSystem() {
    // verify that all three BookingSystem methods work properly with your instantiable classes!
    // you should test both valid and invalid states.
    
    BookingSystem.restart();
    Booking.resetConfirmationNo();
    // Check scheduleFlight() works?
    try {
      BookingSystem.scheduleFlight("ADL", "PDX", "B234", 10);
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    
    // Check bookTicket() throws an exception
    String confNumber;
    String confNumber2;
    try {
      confNumber = BookingSystem.bookTicket("Hobbes LeGault", "hobbes@mail.com", "B234");
      confNumber2 = BookingSystem.bookTicket("Angela", "@mail.com", "B234");
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    
    // Check getBooking() works?
    String vuelo;
    try {
      vuelo = BookingSystem.getBooking(confNumber);
      String vuelo2 = BookingSystem.getBooking(confNumber2);
      System.out.println(vuelo);
      System.out.println(vuelo2);
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }


    return true;
  }

  public static boolean runAllTests() {
    return testTraveler() && testFlight() && testBooking() && testBookingSystem();
  }

  public static void main(String[] args) {
    boolean allTestsPass = runAllTests();
    System.out.println("All tests: " + allTestsPass);
    if (!allTestsPass) {
      System.out.println("  Traveler: " + testTraveler());
      System.out.println("  Flight: " + testFlight());
      System.out.println("  Booking: " + testBooking());
      System.out.println("  System: " + testBookingSystem());
    }
  }

}
