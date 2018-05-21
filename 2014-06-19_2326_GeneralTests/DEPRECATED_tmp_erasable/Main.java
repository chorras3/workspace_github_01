//
//
//package tmp_erasable;
//
//import java.lang.reflect.Field;
//
//public class Main {
//	
//	public static void main(String[] args){
//		Main app = new Main();
////		app.test01();
//		app.test02();
//		System.exit(0);
//	}
//	
//	
//	private void test01(){
//		System.out.println("test01");
//		
//		Contact contact = new Contact();
//		contact.setName("Arnold");
//		contact.setLocation("Austria");
//		
//		System.out.println("contact.toString()="+contact.toString());
//		
//		System.out.println("End");
//
//	}
//	
//	
//	
//	private void test02(){
//		System.out.println("test02");
//		
//		Contact contact = new Contact();
//		
//		Field[] fieldsArr = this.getClass().getDeclaredFields();
//		for (int i = 0; i < fieldsArr.length; i++) {
//			Field curField = fieldsArr[0];
//			
//		}
//		
//		fillBean(contact, "");
//		
//		
//		
//
//	}
//
//	
//	
//	
//	
//	
//	//NOTA: ver abajo el método comentado: setFieldValue y la web de referencia, parece simplificar el SET. 
//	
//	
//	
//	
//	
//	// Receives: bean, options as CSV.
//	// References: ** "", https://stackoverflow.com/questions/14374878/using-reflection-to-set-an-object-property
//	public <T> T fillBean(T object, String options){
//		Object fieldValue = null;
//		final boolean TRUE_boolean=true;
//		
//		final String DEFAULT_String_VALUE="1";
//		final Long DEFAULT_Long_VALUE=new Long(1);
//		final long DEFAULT_long_VALUE=1;
//		final Boolean DEFAULT_Boolean_VALUE=true;
//		final boolean DEFAULT_boolean_VALUE=true;
//		
//
//		// (...) TO-DO terminarlo
//		
//		// If options parameter not empty: Replace options (alternative default values):
//		// =====
//		// DEFAULT_String_VALUE = "new value"
//		// (...) TO-DO
//		
//		
//	    Class<?> clazz = object.getClass();
//		Field[] fieldsArr = object.getClass().getDeclaredFields();
//		
//		for (int i = 0; i < fieldsArr.length; i++) {
//			// Read field
//            //=====
//			Field curField = fieldsArr[i];
//			
//			////String curFieldTypeStr = curField.getType().getName();
//			////System.out.println(curField.getName()+";"+curFieldTypeStr);
//			
//			// Read value
//            //=====			
//            if (curField.getType().getName().equals("java.lang.String")){
//				fieldValue=curField.getName()+" "+DEFAULT_String_VALUE;
//			} else if (curField.getType().getName().equals("java.lang.Long")){
//				fieldValue=DEFAULT_Long_VALUE;
//			} else {
//				fieldValue=null;
//			}
//
//// TRY02 Write                      
//			// Write value 
//          //=====
//            // Set: "We want to access private fields":
//            curField.setAccessible(true);
//            try {
//            	curField.set(object, fieldValue);
//			} catch (IllegalArgumentException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IllegalAccessException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//            
//		} // end: "for each field..."
//		
//		
//		System.out.println("object"+object);
//		
//		return object;
//	}
//	
//	
///*	
//	// References: ** "", https://stackoverflow.com/questions/14374878/using-reflection-to-set-an-object-property
//	public static boolean setFieldValue(Object object, String fieldName, Object fieldValue) {
//	    Class<?> clazz = object.getClass();
//	    while (clazz != null) {
//	        try {
//	            Field field = clazz.getDeclaredField(fieldName);
//	            field.setAccessible(true);
//	            field.set(object, fieldValue);
//	            return true;
//	        } catch (NoSuchFieldException e) {
//	            clazz = clazz.getSuperclass();
//	        } catch (Exception e) {
//	            throw new IllegalStateException(e);
//	        }
//	    }
//	    return false;
//	}
//*/	
//	
//}
