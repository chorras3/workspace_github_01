//package tmp_erasable;
//public class Contact {
//    private String name;
//    private String location;
//    private String address;
//    private String email;
//    private String phone;
//    private String fax;
//
//    public String toString() {
//    	  StringBuilder result = new StringBuilder();
//    	  String newLine = System.getProperty("line.separator");
//
//    	  result.append( this.getClass().getName() );
//    	  result.append( " Object {" );
//    	  result.append(newLine);
//
//    	  //determine fields declared in this class only (no fields of superclass)
////    	  Field[] fields = this.getClass().getDeclaredFields();
//    	  java.lang.reflect.Field[] fields = this.getClass().getDeclaredFields();
//
//    	  //print field names paired with their values
//    	  for ( java.lang.reflect.Field field : fields  ) {
//    	    result.append("  ");
//    	    try {
//    	      result.append( field.getName() );
//    	      result.append(": ");
//    	      //requires access to private field:
//    	      result.append( field.get(this) );
//    	    } catch ( IllegalAccessException ex ) {
//    	      System.out.println(ex);
//    	    }
//    	    result.append(newLine);
//    	  }
//    	  result.append("}");
//
//    	  return result.toString();
//    	}
//
//    
//    // Getters and setters.
//    
//	/**
//	 * @return the name class field to get.
//	 */
//	public String getName() {
//		return name;
//	}
//	/**
//	 * @param name the name class field to set. 
//	 */
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	/**
//	 * @return the location class field to get.
//	 */
//	public String getLocation() {
//		return location;
//	}
//	/**
//	 * @param location the location class field to set. 
//	 */
//	public void setLocation(String location) {
//		this.location = location;
//	}
//
//	/**
//	 * @return the address class field to get.
//	 */
//	
//	public String getAddress() {
//		return address;
//	}
//	/**
//	 * @param address the address class field to set. 
//	 */
//	public void setAddress(String address) {
//		this.address = address;
//	}
//
//	/**
//	 * @return the email class field to get.
//	 */
//	public String getEmail() {
//		return email;
//	}
//	/**
//	 * @param email the email class field to set. 
//	 */
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	/**
//	 * @return the phone class field to get.
//	 */
//	public String getPhone() {
//		return phone;
//	}
//
//	/**
//	 * @param phone the phone class field to set. 
//	 */
//	public void setPhone(String phone) {
//		this.phone = phone;
//	}
//
//	/**
//	 * @return the fax class field to get.
//	 */
//	public String getFax() {
//		return fax;
//	}
//	/**
//	 * @param fax the fax class field to set. 
//	 */
//	public void setFax(String fax) {
//		this.fax = fax;
//	}   
//}