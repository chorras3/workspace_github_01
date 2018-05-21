package tmp_borrable;   // For Home and Alvento workspace
//package src.tmp_borrable;	//For Atocha workspace



public class Contact {
    private Long id;
	private String name;
    private String address;
    private String email;

 
    // Getters and setters.
    
	/**
	 * @return the name class field to get.
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name class field to set. 
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the address class field to get.
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address class field to set. 
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the email class field to get.
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email class field to set. 
	 */
	public void setEmail(String email) {
		this.email = email;
	}
   
}