package reflection.old;


import reflection.PersonBean;

//public class MainOrdering {
//
//}

/*
 * -References
 *   ** "Sort ArrayList of custom Objects by property", stackoverflow.com/questions/2784514/sort-arraylist-of-custom-objects-by-property
 * */

public class MainReflection_20160831_1326 {
	public static void main(String[] args){
		MainReflection_20160831_1326 application = new MainReflection_20160831_1326();
//		JsonNode j; //DBCollection  gson; 
//		runQuick();
		application.runQuick();		
		////List anArrayListOfSomeObjectPerhapsUsersOrSomething = new ArrayList();
		PersonBean p1 = new PersonBean(1, "name-1", "surname-1", "surname2-1", 1001.0);
		
//		application.inspect(p1);
//		int i = inspect(p1);
//		inspectPerson(p1);
		inspect(PersonBean.class);
//		inspectObject(PersonBean.class, p1);
		////inspectObject_values(PersonBean.class, p1);
		////inspectObject_values(PersonBean.class, (Object)p1);
		inspect_values_embedded(PersonBean.class, (Object)p1);
		
		
		////anArrayListOfSomeObjectPerhapsUsersOrSomething.add(p1);
		////PersonBean p2 = new PersonBean(2, "name-2", "surname-2", "surname2-2", 1002.0);
		////anArrayListOfSomeObjectPerhapsUsersOrSomething.add(p2);		
		////Collections.sort(anArrayListOfSomeObjectPerhapsUsersOrSomething, new ReflectiveComparator(). new ListComparator("name"));
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
	
//	static <T> void inspectObject(Class<PersonBean> klazz, Object obj) {
//		java.lang.reflect.Field[] fields = klazz.getDeclaredFields();
//        System.out.printf("%d fields:%n", fields.length);
//        for (java.lang.reflect.Field field : fields) {
//            System.out.printf("%s %s %s%n",
//            	java.lang.reflect.Modifier.toString(field.getModifiers()),
//                field.getType().getSimpleName(),
//                field.getName()
//            );
//        }
//    }
	
	
	/**
	 * -References:
	 * - http://stackoverflow.com/questions/13400075/reflection-generic-get-field-value 
	 */
//	public static Object runGetter(java.lang.reflect.Field field, BaseValidationObject o)
//	{
//	    // MZ: Find the correct method
//	    for (Method method : o.getMethods())
//	    {
//	        if ((method.getName().startsWith("get")) && (method.getName().length() == (field.getName().length() + 3)))
//	        {
//	            if (method.getName().toLowerCase().endsWith(field.getName().toLowerCase()))
//	            {
//	                // MZ: Method found, run it
//	                try
//	                {
//	                    return method.invoke(o);
//	                }
//	                catch (IllegalAccessException e)
//	                {
//	                    Logger.fatal("Could not determine method: " + method.getName());
//	                }
//	                catch (InvocationTargetException e)
//	                {
//	                    Logger.fatal("Could not determine method: " + method.getName());
//	                }
//
//	            }
//	        }
//	    }
//
//
//	    return null;
//	}
	

	
//	static <T> void inspectObject_values(Class<PersonBean> klazz, Object obj) {
//	java.lang.reflect.Field[] fields = klazz.getDeclaredFields();
//    System.out.printf("%d fields:%n", fields.length);
//    for (java.lang.reflect.Field field : fields) {
//        System.out.printf("%s %s %s%n",
//        	java.lang.reflect.Modifier.toString(field.getModifiers()),
//            field.getType().getSimpleName(),
//            field.getName()
//        );
//    }
		
	
//	/* 
//	 * References:
//	 * ** "How to loop over a Class attributes in Java?", stackoverflow.com/questions/3333974/how-to-loop-over-a-class-attributes-in-java
//	 * ** http://stackoverflow.com/questions/16171637/java-reflection-how-to-get-field-value-from-an-object-not-knowing-its-class
//	 * 
//	 * */
//	static <T> void inspect_values_OLD(Class<T> klazz, Object obj) {
//		java.lang.reflect.Field[] fields = klazz.getDeclaredFields();
//        System.out.printf("%d fields:%n", fields.length);
//        for (java.lang.reflect.Field field : fields) {
////        	java.lang.reflect.Field currentField = klazz.getField("fieldName"); //Note, this can throw an exception if the field doesn't exist.;
////        	System.out.println(currentField);
//        	//
//            System.out.printf("%s %s %s%n",
//            	java.lang.reflect.Modifier.toString(field.getModifiers()),
//                field.getType().getSimpleName(),
//                field.getName()
//            );
//            //
//            
//        }
//    }	
	

	/* 
	 * References:
	 * ** "How to loop over a Class attributes in Java?", stackoverflow.com/questions/3333974/how-to-loop-over-a-class-attributes-in-java
	 * ** http://stackoverflow.com/questions/16171637/java-reflection-how-to-get-field-value-from-an-object-not-knowing-its-class
	 * 
	 * */
	static <T> void inspect_values_embedded(Class<T> klazz, Object objInstance) {
		
		Class<?> clazz = objInstance.getClass();

		/*
		 * Leer nombre campo y su valor
		 */
		try {
			java.lang.reflect.Field fieldCurrent = clazz.getField("id");  //Note, this can throw an exception if the field doesn't exist.
			String strId = fieldCurrent.getName();
			System.out.println("strId="+strId);
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			System.out.println("Error catched, stackTrace:");
			e.printStackTrace();
		} 		
		
		java.lang.reflect.Field[] fields = klazz.getDeclaredFields();
        System.out.printf("%d fields:%n", fields.length);
        for (java.lang.reflect.Field field : fields) {

//        	try {
//				java.lang.reflect.Field currentField = klazz.getField("fieldName");  //Note, this can throw an exception if the field doesn't exist.;
//			} catch (NoSuchFieldException | SecurityException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
        	
//        	System.out.println(currentField);
        	//
//            System.out.printf("%s %s %s%n",
//            	java.lang.reflect.Modifier.toString(field.getModifiers()),
//                field.getType().getSimpleName(),
//                field.getName()
//            );
            //
        	/* Print each attribute, and its value
        	 * 
        	 */
    		/*
    		 * Leer nombre campo y su valor
    		 */
    		System.out.println("CAMPO ACTUAL: field="+field);
    		String strFieldName_current = field.getName();
    		System.out.println(  "  **strFieldName_current="+strFieldName_current  );
//    		System.out.println(  "  **field.getName()="+field.  );
			
//    		java.lang.reflect.Field fieldCurrent = clazz.getField("id");  //Note, this can throw an exception if the field doesn't exist.
    		////java.lang.reflect.Field fieldCurrent = clazz.getField(strFieldName_current);  //Note, this can throw an exception if the field doesn't exist.
    		
			String strField_modifiers = java.lang.reflect.Modifier.toString(field.getModifiers());
			System.out.println("  **strField_modifiers="+strField_modifiers);
    		
        	try {
    			java.lang.reflect.Field fieldCurrent = clazz.getField("id");  //Note, this can throw an exception if the field doesn't exist.
    			String strField_id = fieldCurrent.getName();
    			System.out.println("  **strField_id="+strField_id);
    			
    			try {
					////Object fieldValue = (Object)field.get(strId);
    				////Object fieldValue = (Object)field.get("id");
    			    ////Object fieldValue = field.get(clazz);
    				////Object fieldValue = field.get(fieldCurrent);
    				Object strField_value = field.get(objInstance);
    			    
					System.out.println("  **strField_value="+strField_value);
				} catch (IllegalArgumentException ilArEx) {
					ilArEx.printStackTrace();
				} catch (IllegalAccessException ilAcEx) {
					ilAcEx.printStackTrace();					
				}
    			
    			
    		} catch (NoSuchFieldException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (SecurityException e) {
    			// TODO Auto-generated catch block
    			System.out.println("Error catched, stackTrace:");
    			e.printStackTrace();
    		} 		
        	
        	
        	
            
        }
    }
	
	
	
	
	
} // End of class

	
	