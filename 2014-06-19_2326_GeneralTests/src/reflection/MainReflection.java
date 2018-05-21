package reflection;

import java.util.Arrays;

/*
 * -References
 *   ** "Sort ArrayList of custom Objects by property", stackoverflow.com/questions/2784514/sort-arraylist-of-custom-objects-by-property
 *   ** "", http://stackoverflow.com/questions/13400075/reflection-generic-get-field-value
 * */

public class MainReflection {
	public static void main(String[] args){
		MainReflection application = new MainReflection();
//		application.runQuick();		

		PersonBean p1 = new PersonBean(1, "name-1", "surname-1", "surname2-1", 1001.0);
		
//		application.inspect(PersonBean.class);
//		application.inspect_values_embedded(PersonBean.class, (Object)p1);
		
		String strPersonBean_ToString = application.objectToString(PersonBean.class, (Object)p1);
		
		System.out.println("strPersonBean_ToString="+strPersonBean_ToString);
		
		System.exit(0);
	}
	
	protected void runQuick(){
		System.out.println("runQuick");
		
	}
	
	

	/* 
	 * References:
	 * ** "How to loop over a Class attributes in Java?", stackoverflow.com/questions/3333974/how-to-loop-over-a-class-attributes-in-java
	 * */
	static <T> void inspect(Class<T> klazz) {
		java.lang.reflect.Field[] fields = klazz.getDeclaredFields();
        System.out.printf("%d fields:%n", fields.length);
        for (java.lang.reflect.Field field : fields) {
            System.out.printf("%s %s %s%n",
            	java.lang.reflect.Modifier.toString(field.getModifiers()),
                field.getType().getSimpleName(),
                field.getName()
            );
        }
    }
	
	
	/* 
	 * Receives a class, and a instance of it, and for each attribute prints:
	 *   -Field name
	 *   -Field value
	 *   -Field modifier (e.g. public? private? protected?)
	 * 
	 * References:
	 * ** "How to loop over a Class attributes in Java?", stackoverflow.com/questions/3333974/how-to-loop-over-a-class-attributes-in-java
	 * ** http://stackoverflow.com/questions/16171637/java-reflection-how-to-get-field-value-from-an-object-not-knowing-its-class
	 * 
	 * */
	public <T> void inspect_values_embedded(Class<T> klazz, Object objInstance) {
//		Class<?> clazz = objInstance.getClass();
		
		java.lang.reflect.Field[] fields = klazz.getDeclaredFields();
		System.out.println("The class with name: "+klazz.getName());
		System.out.println("==========");
        ////System.out.printf("%d fields:%n", fields.length);        
		System.out.println("Has "+fields.length+" fields (also called 'attributes'), the full information is:");
        
        String strArrayNames = java.util.Arrays.toString(fields);
        System.out.println(strArrayNames);
        
        
        for (java.lang.reflect.Field field : fields) {   // For each field...

        	/* ==========
        	 * Print each field, and its value
        	 * ==========
        	 */
        	System.out.println("-----CAMPO ACTUAL: field="+field);  // Info completa, sin trocear

			String strField_modifiers = java.lang.reflect.Modifier.toString(field.getModifiers());
			System.out.println("          **strField_modifiers="+strField_modifiers);
			
			Class<?> strField_type = (field.getType());
			System.out.println("          **strField_type="+strField_type);

    		String strFieldName_current = field.getName();
    		System.out.println(  "          **strFieldName_current="+strFieldName_current  );

			/** Print value
			 */
    		try {
				Object strField_value = field.get(objInstance);
			    
				System.out.println("          **strField_value="+strField_value);
//			} catch (IllegalArgumentException | IllegalAccessException e) {
    		} catch (IllegalArgumentException  ilArEx) {
    			ilArEx.printStackTrace();
    		} catch (IllegalAccessException IlAcEx) {
    			IlAcEx.printStackTrace();
			}    
			
        }  // END OF "For each array element"
        
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
		System.out.println("The class with name: "+klazz.getName());
		System.out.println("==========");
        ////System.out.printf("%d fields:%n", fields.length);        
//		System.out.println("Has "+fields.length+" fields (also called 'attributes'), the full information is:");
        
        String strArrayNames = java.util.Arrays.toString(fields);
//        System.out.println(strArrayNames);
        
        
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
//			} catch (IllegalArgumentException | IllegalAccessException e) {
			} catch (IllegalArgumentException ilArEx) {
				ilArEx.printStackTrace();
			} catch ( IllegalAccessException ilAcEx) {
				ilAcEx.printStackTrace();
			}
    		
    		/* Concatenate toString() info
    		 * HACER: alternativa: formato JSON (manual, no usar bibliotecas externas)
    		 */
    		if (resultBuffer.length()>0){
    			resultBuffer.append(";  ");
    		}
    		resultBuffer.append(strFieldName_current+"="+strField_value);
			
        }  // END OF "For each field"
        
        
        return resultBuffer.toString();
    }	
	
	
} // End of class

	
	