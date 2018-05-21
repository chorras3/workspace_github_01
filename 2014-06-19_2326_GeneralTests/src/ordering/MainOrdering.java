package ordering;

//import java.lang.reflect.Field;
//import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import main.Main;

//public class MainOrdering {
//
//}

/*
 * -References
 *   ** "Sort ArrayList of custom Objects by property", stackoverflow.com/questions/2784514/sort-arraylist-of-custom-objects-by-property
 * */

public class MainOrdering {
	public static void main(String[] args){
		MainOrdering application = new MainOrdering();
		
//		runQuick();
		application.runQuick();		
		
		System.exit(0);
	}
	
	protected void runQuick(){
		System.out.println("runQuick");
		
	}
	
	
	protected void tryTest02(){
		
	}
	
	protected void tryTest01(){
		/**
		 * -References: **"Sort ArrayList of custom Objects by property", "answered Apr 25 '12 at 19:28 Kevin", stackoverflow.com/questions/2784514/sort-arraylist-of-custom-objects-by-property/34574138
		 */
		
//		JsonNode j; //DBCollection  gson; 

		List anArrayListOfSomeObjectPerhapsUsersOrSomething = new ArrayList();
		PersonBean p1 = new PersonBean(1, "name-1", "surname-1", "surname2-1", 1001.0);
		
//		application.inspect(p1);
//		int i = inspect(p1);
//		inspectPerson(p1);
		////inspect(PersonBean.class);
//		inspectObject(PersonBean.class, p1);
		////inspectObject_values(PersonBean.class, p1);
		////inspectObject_values(PersonBean.class, (Object)p1);
		//inspect_values_embedded(PersonBean.class, (Object)p1);
		
		
		anArrayListOfSomeObjectPerhapsUsersOrSomething.add(p1);
		PersonBean p2 = new PersonBean(2, "name-2", "surname-2", "surname2-2", 1002.0);
		anArrayListOfSomeObjectPerhapsUsersOrSomething.add(p2);		
		Collections.sort(anArrayListOfSomeObjectPerhapsUsersOrSomething, new ReflectiveComparator(). new ListComparator("name"));		
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
			} catch (IllegalArgumentException ilArEx) {
				ilArEx.printStackTrace();
			} catch (IllegalAccessException ilAcEx) {
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

	
	