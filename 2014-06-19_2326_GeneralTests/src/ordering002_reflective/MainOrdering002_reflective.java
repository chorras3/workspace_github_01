package ordering002_reflective;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ordering.PersonBean;


/**
 * @author fgomezconnectis
 * @since:JDK1.6 
 *
 */
public class MainOrdering002_reflective {
	public static void main(String[] args){
		MainOrdering002_reflective application = new MainOrdering002_reflective();
				
//		application.runQuick();
		application.tryTest02();
		
		System.exit(0);
	}
	
	
	protected void runQuick(){
		System.out.println("runQuick");
		
		List aList = new ArrayList();
		aList.add("red");
		aList.add("yellow");
		aList.add("blue");
		
		if (  aList.contains("yellow")  ){
			System.out.println("The list contains that colour!");
		} else {
			System.out.println("Sorry, that colour was not included in the list");
		}
		
	}
	
	
	protected void tryTest02(){
		System.out.println("tryTest02()");
		
		List<PersonBean> personasLista = new ArrayList<PersonBean>();
		
		PersonBean per2 = new PersonBean(2, "name-2", "surname-2", "surname2-2", 1002.0);
		System.out.println("per2="+per2);
		personasLista.add(per2);
		PersonBean per1 = new PersonBean(1, "name-1", "surname-1", "surname2-1", 1001.0);
		personasLista.add(per1);
		
		/* Print unordered list
		 */
		System.out.println("Unordered list:");
		printPersonList(personasLista);
		////System.out.println("personasLista="+personasLista);  //TO-DO: imprimir haciendo un FOR, así comprobamos bien orden (hacerlo llamando a un método)
		
		String FIELD_NAME ="surname";
		
		////ListComparator listReflectiveComparator = new ReflectiveComparator(). new ListComparator(FIELD_NAME);
		ReflectiveComparator.ListComparator reflectiveComparatorForLists = new ReflectiveComparator().new ListComparator(FIELD_NAME);

		Collections.sort(personasLista, reflectiveComparatorForLists);
		
		/* Print ordered final list
		 */
		System.out.println("Order ascending:");
		printPersonList(personasLista);
		////System.out.println("personasLista="+personasLista);
		
		////System.out.println("Fin ordering");		//TODO: imprimir haciendo un FOR, así comprobamos bien orden (hacerlo llamando a un método)

		// Descending order (Reversed)
		Collections.sort(personasLista, reflectiveComparatorForLists);
		Collections.sort(  personasLista, Collections.reverseOrder(reflectiveComparatorForLists)  );
		
		System.out.println("Reversed -> Order decending:");
		printPersonList(personasLista);
		
	}
	
	
	/* Show List of PersonBean
	 * */
	private void printPersonList(List<PersonBean> personasLista) {
		final String MODO_IMPRESION="loop";   //
		final String ERROR_MODO_DESCONOCIDO = "MODO DE IMPRESIÓN DESCONOCIDO. NO POSIBLE IMPRESIÓN";
		
		if (MODO_IMPRESION.equals("print")){
			System.out.println(personasLista);
		} else if (MODO_IMPRESION.equals("loop")){
			//
			for(PersonBean personBeanAux : personasLista){
				System.out.println(  "actual personBeanAux="+personBeanAux  );
			}
			//
		} else {
			System.out.println(ERROR_MODO_DESCONOCIDO);
		}
		
		////System.out.println("personasLista="+personasLista);  //TO-DO: imprimir haciendo un FOR, así comprobamos bien orden (hacerlo llamando a un método)
		
	}



	protected void tryTest01(){
		/**
		 * -References: **"Sort ArrayList of custom Objects by property", "answered Apr 25 '12 at 19:28 Kevin", stackoverflow.com/questions/2784514/sort-arraylist-of-custom-objects-by-property/34574138
		 */
		
//		JsonNode j; //DBCollection  gson; 

		List anArrayListOfSomeObjectPerhapsUsersOrSomething = new ArrayList();
		PersonBean per1 = new PersonBean(1, "name-1", "surname-1", "surname2-1", 1001.0);
		
//		application.inspect(per1);
//		int i = inspect(per1);
//		inspectPerson(per1);
		////inspect(PersonBean.class);
//		inspectObject(PersonBean.class, per1);
		////inspectObject_values(PersonBean.class, per1);
		////inspectObject_values(PersonBean.class, (Object)per1);
		//inspect_values_embedded(PersonBean.class, (Object)per1);
		
		
		anArrayListOfSomeObjectPerhapsUsersOrSomething.add(per1);
		PersonBean per2 = new PersonBean(2, "name-2", "surname-2", "surname2-2", 1002.0);
		anArrayListOfSomeObjectPerhapsUsersOrSomething.add(per2);		
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
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//				System.out.println("          **strField_value="+strField_value);
    		
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

	
	