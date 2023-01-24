
//////////////// FILE HEADER //////////////////////////
//
// Author:   Angela Galindo
//
///////////////////////////////////////////////////////////////////////////////

public class Traveler {
  
  private String name;
  private String email;
  
  /**
   * Constructors
   */
  public Traveler (String name, String email) {
    this.name = name;
    this.email = email;
  }
  
  // Accessors
  /**
   * 
   * @return
   */
  public String getName() {
    return this.name;
  }
  
  /**
   * 
   * @return
   */
  public String getEmail() {
    return this.email;
  }
  
  // Mutators
  /**
   * 
   * @param name
   */
  public void setName(String name) {
    this.name = name;
  }
  
  /**
   * 
   * @param email
   */
  public void setEmail(String email) {
    this.email = email;
  }
  
}
