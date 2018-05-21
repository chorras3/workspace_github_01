package tmp_borrable;   // For Home and Alvento workspace
//package src.tmp_borrable;	//For Atocha workspace

import java.io.IOException;
import java.lang.reflect.Field;

import tools.validator.Validator;
import core.common.IOManager;
import core.common.IoTool;
import core.common.Seed;

public class Main_filling_001 {
	
	public static void main(String[] args){
		Main_filling_001 app = new Main_filling_001();
//		app.test0();
//		app.test0a();
		app.test01a_defaultFilling();
		app.test02a_forceFilling();
		System.exit(0);
	}
	
	
	/**
	 * Testear validaciones, etc. de JCoder. 
	 */
	protected void test0(){
			
		System.out.println("run01");
		IoTool ioTool = new IoTool();
		IOManager ioManager = new IOManager();
		
		
//		tools.validator.Validator validator = new tools.validator.Validator();
		
		boolean resulCorreo = Validator.Email.validate("uncorreoOk@correo.es");
		System.out.println(resulCorreo);
		
		
		System.out.println("Resultado validarResultado NIF="+Validator.NifPersonaFisica.validate("50100535A")); 
		
		long lngDAteInMilliseconds = 1000;
//		int o = ioManager.convertMilliseconds2InverseDate(lngDAteInMilliseconds);  // Must be static, not an instance
		String strDateFromMilliseconds = IOManager.convertMilliseconds2InverseDate(lngDAteInMilliseconds);
		System.out.println("strDateFromMilliseconds="+strDateFromMilliseconds);
				
				
//				Contact contact = new Contact();
//				// Log
//				String strOut = SIMULATOR_LIBRARY_objectToString(contact);
//				System.out.println("class contact EMPTY: contact="+strOut);
//				
//				
//				// Use options: force value to fill some fields: address, prueba: 
////				objectFill(contact, "<FIELD_VALUE>email:correo@correo.es,address:calle Pez 2/, prueba con /, comas</FIELD_VALUE>"); //objectFill(contact, "<FIELD_VALUE>id:"+1+"email:correo@correo.es,address:calle Pez 2/, prueba con /, comas</FIELD_VALUE>");
//				Long idCounter = new Long(1);
//				// We want id=2
//				idCounter++;
//				////objectFill(contact, "<FIELD_VALUE>id:"+counter+",email:email2@correo.es,address:street Fish 2/ address using a /, comma value</FIELD_VALUE>");
////				objectFill(contact, "<FIELD_VALUE>id:"+counter+",email:email2@coldmail.com,address:street Fish 2/ address using a /, comma value in this address info</FIELD_VALUE>");
//				ioManager.objectFill(contact, "<FIELD_VALUE>id:"+idCounter+",email:email2@coldmail.com,address:street Fish 2/ address using a /, comma value in this address info</FIELD_VALUE>");
//				
//				String strOut_end = SIMULATOR_LIBRARY_objectToString(contact);
//				System.out.println("class contact filled: contact="+strOut_end);
//				/**/
			
				
		// Aquí ya probar validar tipos primitivos y también alguna Clase (eso ya lo hace bien Java). 
		
		
	}	
	
	
	private void test0a(){
		System.out.println("test01");
		
		Contact contact = new Contact();
		contact.setName("Arnold");
		contact.setAddress("Austrian street, 11");
		
		System.out.println("contact.toString()="+contact.toString());
		
		System.out.println("End");

	}
	
	
	private void test01a_defaultFilling(){
		System.out.println("test01a_defaultFilling() pending To do. Not available yet");  //TODO. Do not force values (default automatic values will be used)
		
		
		
		Contact contact = new Contact();
		// Log
		String strOut = SIMULATOR_LIBRARY_objectToString(contact);
		System.out.println("class contact EMPTY: contact="+strOut);
		
		
		// Option 1 (deprecated)
		// Rellenar con valores automáticos por defecto (no customizamos para forzar valores), para ello el segundo argumento es un String vacío, "":
		IOManager ioManager = new IOManager();
		
		
		//TODO: semilla
//		
//		// Option 2 (recommended): partiendo de seed, "semilla"
//		Seed.IOManager.beanFill(contact, "");		// TODO: usar NO-estático, instancia ioManager en minúscula
//		Seed seed = new Seed();
//		seed.ioManager.objectFill(contact, "");
		
		// Log: objeto resultante
		String strOut_end_IOMANAGER = SIMULATOR_LIBRARY_objectToString(contact);
		System.out.println("class contact filled: contact="+strOut_end_IOMANAGER);
		
	}
	
	
	
	private void test02a_forceFilling(){
		System.out.println("test02a_forceFilling()"); 
		
		Contact contact = new Contact();
		// Log
		String strOut = SIMULATOR_LIBRARY_objectToString(contact);
		System.out.println("class contact EMPTY: contact="+strOut);
		
		// Use options: force value to fill some fields: address, prueba: 
		Long idCounter = new Long(1);
		// We wish to customize this field value: id=2
		idCounter++;
		
		
//		//LLAMADA normal función dentro de ésta clase
//		objectFill(contact, "<FIELD_VALUE>id:"+idCounter+",email:email2@coldmail.com,address:street Fish 2/ address using a /, comma value in this address info</FIELD_VALUE>");
//		String strOut_end = SIMULATOR_objectToString(contact);
//		System.out.println("class contact filled: contact="+strOut_end);
//		/**/
				

//		//LLAMADA a [Proyecto JCODER] IOManager.java
//		IOManager ioManager = new IOManager();
////		ioManager.objectFill_test(contact, "<FIELD_VALUE>id:"+idCounter+",email:email2@coldmail.com,address:street Fish 2/ address using a /, comma value in this address info</FIELD_VALUE>");
//		ioManager.objectFill(contact, "<FIELD_VALUE>id:"+idCounter+",email:email2@coldmail.com,address:street Fish 2/ address using a /, comma value in this address info</FIELD_VALUE>");
//		String strOut_end_IOMANAGER = SIMULATOR_LIBRARY_objectToString(contact);
//		System.out.println("class contact filled: contact="+strOut_end_IOMANAGER);
		
		// Llamada a seed: 
		core.common.Seed seed = new Seed();
		idCounter++;
		seed.getIoManager().objectFill(contact, "<FIELD_VALUE>id:"+idCounter+",email:email3@coldmail.com,address:street Fish 3/ address using a /, comma value in this address info</FIELD_VALUE>");
		String strOut_end_IOMANAGER2 = SIMULATOR_LIBRARY_objectToString(contact);
		System.out.println("class contact filled: contact="+strOut_end_IOMANAGER2);



		
		
/*		
	    // Crear 2 elementos con direccion Madrid		
		System.out.println("FILLING AT LOOP ============");
		java.util.List direccionesMadList = new java.util.ArrayList();
		for (int i = 1; i <= 2; i++) {
			objectFill(contact, "<FIELD_VALUE>id:"+i+"</FIELD_VALUE>");
			// Log
			String strOut = objectToString(contact);
			System.out.println("class contact filled: contact="+strOut);
		}
		
		
*/		


		
		
		// TODO: 
		//*Use of loops and .add to configure lists
		//*Consider adding list on every element of the main list, since this will be the real scenario. E.g. aCustomerBean.add(aCustomerAdressList). 
		//*Remember to give example of adding a list to another to extend it: aCustomerAdressList1.add(aCustomerAdressList2)
		


	}

	
	
	
	
	
	//NOTA: ver abajo el método comentado: setFieldValue y la web de referencia, parece simplificar el SET. 
	
	
	

	

//	/**
//	 * Metodo objectFill. Receibes a bean and fills its values automatically, or with customization. 
//	 * </br>References: ** "Using reflection to set an object property", https://stackoverflow.com/questions/14374878/using-reflection-to-set-an-object-property  
//	 * @param object the intantiated class to fill with data (any Java class with attributes having getter/setter)
//	 * @param options Optional options (Can be empty, ""). 
//	 * </br>-Example 1: to force value 1 to a Field called 'id' (this is, ".setId(1)"), use option: "&lt;FIELD_VALUE&gt;id:1&lt;/FIELD_VALUE&gt;"
//	 * </br>-Example 2: Dynamic way when int counter = counter++; The option would be-> int counter=1; counter++; "&lt;FIELD_VALUE&gt;id:"+counter+"&lt;/FIELD_VALUE&gt;"
//	 * </br>-Example 3: Using escape for values containing commas: "&lt;FIELD_VALUE&gt;email:themail@mailing.com,address:street Big 2/, this is a test using commas /, comas&lt;/FIELD_VALUE&gt;" 
//	 * @return the object returned, willed with values already.
//	 *  
//	 *
//	 * @Author Connectis
//	 */
//	public <T> T objectFill(T object, String options){
////		core.common.ObjectManager objectManager = new core.common.ObjectManager();
////		T o = objectManager.objectFill(object, "");
////		return o;
//
///**/			
////		Object valueAsString = null;
//		String valueAsString = null;
//		final boolean TRUE_boolean=true;
//		final String TAG_FIELD_VALUE_INIT ="<FIELD_VALUE>";
//		final String TAG_FIELD_VALUE_END ="</FIELD_VALUE>";
//		// Parameter 'object' could include this simbol, which means that 
//		// within a value there is literally a real comma (not a code separator): \,
//		final String COMMA_ESCAPPED ="/,";
//		final String COMMA_CODIFIED ="$$COMMA$$";
//		final String COMMA_REAL=",";
//		
//		final String DEFAULT_String_VALUE="1";
//		final Long DEFAULT_Long_VALUE=new Long(1);
//		final long DEFAULT_long_VALUE=1;
//		final Boolean DEFAULT_Boolean_VALUE=true;
//		final boolean DEFAULT_boolean_VALUE=true;
//		
//		
////// Test map. References: ** "Split string into key-value pairs", https://stackoverflow.com/questions/31153753/split-string-into-key-value-pairs		
////		java.util.Map<String, String> map = new java.util.HashMap<String, String>();
////	    String test = "pet:cat::car:honda::location:Japan::food:sushi";
////
////	    // split on ':' and on '::'
////	    String[] partsTest = test.split("::?");
////
////	    for (int i = 0; i < partsTest.length; i += 2) {
////	        map.put(partsTest[i], partsTest[i + 1]);
////	    }
////
////	    for (String s : map.keySet()) {
////	        System.out.println(s + " is " + map.get(s));
////	    }
//    
//	    
//	
//		// Option parameter received: save it into valuesToForceMap.
//		// ====================
//		// Obtains valuesToForceMap. Contains the values that must be forced later. 
//		// -If there exist options (param "options") get the fieldValueMap (map having pairs: field and default value for it, as desired)
//		// -If options parameter not empty: Replace options (force alternative default values):		
//		// -References: ** "Split string into key-value pairs", https://stackoverflow.com/questions/31153753/split-string-into-key-value-pairs
//		// 		
//		String valuesToForce=null;
//		java.util.Map<String, String> valuesToForceMap = new java.util.HashMap<String, String>();
//		String fieldValueAsString =null;
////		java.util.Map valuesToForceMap=null;
//		if (options!=""){
//			// Escape a textual comma (this is, the characters: /, ):
//			valuesToForce = options;
//			if (valuesToForce.toString().indexOf("/,")!=-1){
//				valuesToForce=valuesToForce.replace(COMMA_ESCAPPED, COMMA_CODIFIED);  //FAILS
//			}
//			// Remove newLine characters
//			valuesToForce = valuesToForce.trim().replaceAll("\n ", "");			
//			if (options.toString().indexOf(TAG_FIELD_VALUE_INIT) != -1){
//				fieldValueAsString = valuesToForce.substring(TAG_FIELD_VALUE_INIT.length(), valuesToForce.length()-TAG_FIELD_VALUE_END.length());
//				String[] fieldValueArray = fieldValueAsString.split(TAG_FIELD_VALUE_INIT+"?");
//				// Split if exist any delimiter: symbols ',' or ':'
//				String[] parts = fieldValueAsString.split("(,)|(:)");  // Split if exist delimiter ',' or ':'
//				// Every correlated pair of parts will go to a map
//				for (int i = 0; i < parts.length; i += 2) {   
//			    	String elementKey=parts[i];
//			    	String elementValue=parts[i + 1];
//			    	// UNDO codification of an escaped comma symbol (The text: "/," was replaced by text $$COMMA$$ or similar, so we revert it back):
//					if (elementKey.indexOf(COMMA_CODIFIED)!=-1){
//						elementKey=elementKey.replace(COMMA_CODIFIED, COMMA_REAL);
//					}
//					if (elementValue.indexOf(COMMA_CODIFIED)!=-1){
//						elementValue=elementValue.replace(COMMA_CODIFIED, COMMA_REAL);
//					}
//			    	valuesToForceMap.put(elementKey, elementValue);
//			    }
//			    // Log
//			    System.out.println("option:valuesToForce. There are the following values to force:");
//			    for (Object fieldAux : valuesToForceMap.keySet()) {    // Print map
//			        System.out.println("Field "+ fieldAux + "=" + valuesToForceMap.get(fieldAux));
//			    }
//
//			}
//		}
//
//		// Object received: Set values
//		// ====================
//		// Set fields to its force value, if proceed (if options says it so)
//	    Class<?> clazz = object.getClass();
//		Field[] classFieldsArray = object.getClass().getDeclaredFields();
//		
//		for (int i = 0; i < classFieldsArray.length; i++) {
//			// Read field
//            //=====
//			Field fieldAux = classFieldsArray[i];
//			String fieldNameAux = fieldAux.getName();
//            // When setting values into the object, we declare: "We want to access private fields":
//            fieldAux.setAccessible(true);
//			
//			// Set value
//			// References: ** "", https://stackoverflow.com/questions/14374878/using-reflection-to-set-an-object-property
//            //=====			
//			if (fieldAux.getType().getName().equals("java.lang.String")){
//				// READ
//				if (valuesToForceMap.get(fieldNameAux)==null ){
//					valueAsString=fieldNameAux+" "+DEFAULT_String_VALUE;
////				// But if options parameter specifies an override value for this field...
////				if ( fieldValueArr!=null && fieldValueArr.toString().indexOf(fieldNameStr)>0 ){
////					fieldValue=fieldNameStr+" "+DEFAULT_String_VALUE;
////				}
//				// If options parameter specifies to forcean override value for this field...
//				} else {
//					// new Default value: 
//					valueAsString=valuesToForceMap.get(fieldNameAux);  
//				}
//				// WRITE 
//				// (remember: when writting, we need: 'fieldAux.setAccessible(true)' as done above)
//				//=====
//	            try {
//	            	fieldAux.set(object, valueAsString);
//				} catch (IllegalArgumentException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (IllegalAccessException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
//			} else if (fieldAux.getType().getName().equals("java.lang.Long")){
////				fieldValue=DEFAULT_Long_VALUE;
//				if (valuesToForceMap.get(fieldNameAux)==null ){
//					valueAsString=new Long(DEFAULT_Long_VALUE).toString();
////				// But if options parameter specifies an override value for this field...
////				if ( fieldValueArr!=null && fieldValueArr.toString().indexOf(fieldNameStr)>0 ){
////					fieldValue=fieldNameStr+" "+DEFAULT_String_VALUE;
////				}
//				// If options parameter specifies to forcean override value for this field...
//				} else {
//					// new Default value: 
//					valueAsString=valuesToForceMap.get(fieldNameAux);  
//				}
//				// WRITE
//				// (remember: when writting, we need: 'fieldAux.setAccessible(true)' as done above)
//				//=====
//	            try {
////		            Long l = new Long(valueAsString);
////	            	Long l = Long.parseLong((String) valueAsString);
//	            	Long l = Long.parseLong(valueAsString.toString());
//	            	fieldAux.set(object, l);
//				} catch (IllegalArgumentException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (IllegalAccessException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//								
//			} else {
//				valueAsString=null;
//			}
//
//// TRY02 Write                      
////			// Write value 
////          //=====
////            // Set: "We want to access private fields":
////            fieldAux.setAccessible(true);
////            try {
//////            	Long l = new Long(valueAsString);
////            	Long l = Long.valueOf((String) valueAsString);
////            	fieldAux.set(object, l);
////			} catch (IllegalArgumentException e) {
////				// TODO Auto-generated catch block
////				e.printStackTrace();
////			} catch (IllegalAccessException e) {
////				// TODO Auto-generated catch block
////				e.printStackTrace();
////			}
//            
//		} // end: "for each field..."
//		
//		
//		System.out.println("object"+object);
//		
//		return object;
//	
//	}
	
	
	
	
	
/*	
	// References: ** "", https://stackoverflow.com/questions/14374878/using-reflection-to-set-an-object-property
	public static boolean setFieldValue(Object object, String fieldName, Object fieldValue) {
	    Class<?> clazz = object.getClass();
	    while (clazz != null) {
	        try {
	            Field field = clazz.getDeclaredField(fieldName);
	            field.setAccessible(true);
	            field.set(object, fieldValue);
	            return true;
	        } catch (NoSuchFieldException e) {
	            clazz = clazz.getSuperclass();
	        } catch (Exception e) {
	            throw new IllegalStateException(e);
	        }
	    }
	    return false;
	}
*/	

	
	/**
	 * Metodo objectToString. 
	 * Recibe un objeto cualquiera y retorna String con valores de todos 
	 * sus atributos. 
	 * 
	 * @param theObject la clase recibida
	 * @return se retorna el tipo String con valores de atributos. 
	 *
	 * @Author Connectis
	 */
	public String SIMULATOR_LIBRARY_objectToString(Object theObject) {
		
//		core.common.ObjectManager objectManager = new core.common.ObjectManager();
//		String o = objectManager.objectToString(theObject);
//		return o;
		
		
		
		StringBuilder result = new StringBuilder();
		String newLine = System.getProperty("line.separator");

		result.append(theObject.getClass().getName());
		result.append(" Object {");
		result.append(newLine);

		// determine fields declared in this class only (no fields of
		// superclass)
		// Field[] fields = theObject.getClass().getDeclaredFields();
		java.lang.reflect.Field[] fields = theObject.getClass()
				.getDeclaredFields();

		// print field names paired with their values
		for (java.lang.reflect.Field fieldAux : fields) {
			result.append("  ");
			fieldAux.setAccessible(true);
			try {
				result.append(fieldAux.getName());
				result.append(": ");
				// requires access to private field:
				result.append(fieldAux.get(theObject));
			} catch (IllegalAccessException ex) {
				System.out.println(ex);
			}
			result.append(newLine);
		}
		result.append("}");

		return result.toString();

	}
  
  
  
	
}
