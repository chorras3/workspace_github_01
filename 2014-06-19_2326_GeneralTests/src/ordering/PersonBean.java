package ordering;

public class PersonBean {
	public long id;
	public String name;
	public String surname;
	public String surname2;
	public double salary;
	
	PersonBean(){
		id=0;
		name="";
		surname="";
		surname2="";
		salary=0;
	}
	
	
	public PersonBean(long id, String name, String surname, String surname2,
			double salary) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.surname2 = surname2;
		this.salary = salary;
	}



	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getSurname2() {
		return surname2;
	}
	public void setSurname2(String surname2) {
		this.surname2 = surname2;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}


	
	public String toString(){
		String result = objectToString(PersonBean.class, this);
		return result;
	}
	
	/*
	 * Receives a class and a instance of it, and generates a toString text automatically (including values)
	 * -References:
	 *   ** "How to loop over a Class attributes in Java?", stackoverflow.com/questions/3333974/how-to-loop-over-a-class-attributes-in-java
	 *   ** http://stackoverflow.com/questions/16171637/java-reflection-how-to-get-field-value-from-an-object-not-knowing-its-class
	 * 
	 * */
	public <T> String objectToString(Class<T> klazz, Object objInstance) {
		StringBuffer resultBuffer = new StringBuffer();  
		
		java.lang.reflect.Field[] fields = klazz.getDeclaredFields();
		
//		System.out.println("The class with name: "+klazz.getName());
//		System.out.println("==========");
        ////System.out.printf("%d fields:%n", fields.length);        
//		System.out.println("Has "+fields.length+" fields (also called 'attributes'), the full information is:");
        
        String strArrayNames = java.util.Arrays.toString(fields);
//        System.out.println(strArrayNames);
        
        final String CLASSNAME_OPEN = "{{";
        final String CLASSNAME_CLOSE = "}}";
        final String CLASSVALUES_OPEN = "<<";
        final String CLASSVALUES_CLOSE = ">>";
        
        final String INITIAL_TEXT = CLASSNAME_OPEN+"class '"+klazz.getName()+"'"+CLASSNAME_CLOSE+CLASSVALUES_OPEN;
        
        resultBuffer.append(INITIAL_TEXT);
        
        
        for (java.lang.reflect.Field field : fields) {   // For each field...

        	/* ==========
        	 * Print each field, and its value
        	 * ==========
        	 */
//        	System.out.println("-----CAMPO ACTUAL: field="+field);  // Info completa, sin trocear

			String strField_modifiers = java.lang.reflect.Modifier.toString(field.getModifiers());
//			System.out.println("          **strField_modifiers="+strField_modifiers);
			
			Class<?> strField_type = (field.getType());
//			System.out.println("          **strField_type="+strField_type);

    		String strFieldName_current = field.getName();
//    		System.out.println(  "          **strFieldName_current="+strFieldName_current  );

			/** Print value
			 */
    		Object strField_value=null;
    		
    		try {
				strField_value= field.get(objInstance);
//				System.out.println("          **strField_value="+strField_value);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    		
    		/* Concatenate toString() info
    		 * HACER: alternativa: formato JSON (manual, no usar bibliotecas externas)
    		 */
    		if (resultBuffer.length()>INITIAL_TEXT.length()){
    			resultBuffer.append(";  ");
    		}
    		resultBuffer.append(strFieldName_current+"="+strField_value);
			
        }  // END OF "For each field"
        resultBuffer.append(CLASSVALUES_CLOSE);
        
        
        
        return resultBuffer.toString();
    }


}
