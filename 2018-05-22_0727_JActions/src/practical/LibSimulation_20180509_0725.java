//package practical;
package com.SGPF.coopera2020.controllers.administracion.roles.funcsubrol;

import java.util.List;
import java.util.logging.Logger;

import com.SGPF.coopera2020.modelo.roles.FuncionSubRol;
//import com.SGPF.coopera2020.modelo.roles.FuncionSubRol;



/**
 * La clase LibSimulation_20180509_0725. 
 * @author IGAE
 *
 */
public class LibSimulation_20180509_0725 {
	
	// LOG
	java.util.logging.Handler fileHandler = null;
    public final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger(LibSimulation_20180509_0725.class
            .getClass().getName());
    
    
    /**
     * Constructor de la clase LibSimulation_20180509_0725. 
     *	
    * @author fgaparicio
     */
    LibSimulation_20180509_0725(){
    	setupLogger();
    }
    
		
	// TO STRING RECURSIVE
    private static final List LEAVES = java.util.Arrays.asList(
	        Boolean.class, Character.class, Byte.class, Short.class,
	        Integer.class, Long.class, Float.class, Double.class, Void.class,
	        String.class);

    /**
     * Metodo setupLogger. 
     *
     * @author fgaparicio
     */
    public void setupLogger(){
		try {
			fileHandler = new java.util.logging.FileHandler("./logfile.log");
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (java.io.IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        java.util.logging.SimpleFormatter simple = new java.util.logging.SimpleFormatter();
        fileHandler.setFormatter(simple);
        LOGGER.addHandler(fileHandler);//adding Handler for file

    }
    
    
	/**
	 * Metodo toStringRecursive.
	 * -References: "answered Oct 11 '10 at 10:36 aioobe", https://stackoverflow.com/questions/3905382/recursivley-print-an-objects-details 
	 * @param o Object which values will be be returned as a String
	 * @return String of the object returned
	 * @throws Exception
	 *
	 * @author fgaparicio
	 */
	public static String toStringRecursive_ORIG(Object o) throws Exception {
	    if (o == null)
	        return "null";

	    if (LEAVES.contains(o.getClass()))
	        return o.toString();

	    StringBuilder sb = new StringBuilder();
	    sb.append(o.getClass().getSimpleName()).append(": [");
	    for (java.lang.reflect.Field f : o.getClass().getDeclaredFields()) {
	        if (java.lang.reflect.Modifier.isStatic(f.getModifiers()))
	            continue;
	        f.setAccessible(true);
	        sb.append(f.getName()).append(": ");
	        sb.append(toStringRecursive_ORIG(f.get(o))).append(" ");
	    }
	    sb.append("]");
	    return sb.toString();
	}
	
	
	
	// ATENCIÓN: Actualmente es igual a toStringRecursive_ORIG, hay que modificarla o buscar otro algoritmo? zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz   
	/**
	 * Metodo toStringRecursive. 
	 * @param o Object which values will be be returned as a String
	 * @return String of the object returned
	 * @throws Exception
	 *
	 * @author fgaparicio
	 */
	public static String toStringRecursive_v20180510_0939(Object o) throws Exception {
	    if (o == null)
	        return "null";

	    if (LEAVES.contains(o.getClass()))
	        return o.toString();

	    StringBuilder sb = new StringBuilder();
	    sb.append(o.getClass().getSimpleName()).append(": [");
	    for (java.lang.reflect.Field f : o.getClass().getDeclaredFields()) {
	        if (java.lang.reflect.Modifier.isStatic(f.getModifiers()))
	            continue;
	        f.setAccessible(true);
	        sb.append(f.getName()).append(": ");  // Identifier shown preceded by a @ symbol
	        sb.append(toStringRecursive_v20180510_0939(f.get(o))).append(" ");
	    }
	    sb.append("]");
	    return sb.toString();
	}
		
	
	
	
	
	
	
	/**
	 * Metodo funcionSubRolToString. 
	 * @param funcionSubRol
	 * @return functinSubrol as a String
	 * @throws Exception
	 *
	 * @author fgaparicio
	 */
	public static String funcionSubRolToString(FuncionSubRol_ORIG funcionSubRol) throws Exception {
//		public static String LIBSIM_funcionSubRolToString(E funcionSubRol) throws Exception {
			final String NEW_LINE ="\n";
			StringBuffer result= new StringBuffer();

	   		String funcionSubRolAsStr=toStringRecursive_ORIG(funcionSubRol);  // TODO: No se compone bien, no usar de momento
			
			String funcionAsStr = toStringRecursive_ORIG(funcionSubRol.getFuncion());
	   		String rolAsStr = toStringRecursive_ORIG(funcionSubRol.getRol());
	   		String subRolAsStr = toStringRecursive_ORIG(funcionSubRol.getSubrol());
	   		String faseValidacionCodAsStr =  toStringRecursive_ORIG(funcionSubRol.getId().getFaseValidacion());

			result.append(NEW_LINE+subRolAsStr
					+NEW_LINE+funcionSubRolAsStr
					+NEW_LINE+faseValidacionCodAsStr);
	   		
			return result.toString();
	}
	
	/**Formats an object represented as a String (that String must be previously obtained using objectToString method). 
	 * 
	 * @param objectAsString the String describing the object (fields, values). Required: Each field marked with @, and separated by comma and space: ", " 
	 * @return
	 */
	public String prettyObjectAsString(String objectAsString){
		StringBuffer result = null;
		result = new StringBuffer(  objectAsString.replace("@", "\n@")  );
		return result.toString();
	}
	
	/**
	 * Metodo removeNewLines. 
	 * @param string String to format
	 * @return String with new lines removed
	 * @throws Exception
	 *
	 * @author fgaparicio
	 */
	public static String removeNewLines(String string) throws Exception {		
		String result = string.replace("\n", " --linebreak-- ");  // TODO: Use an unique line
		return result;
	}
	
	/**
	 * Metodo msgConsole.
	 * Writes message on console. 
	 * -The second parameter ('options') is not available yet, just set empty "" value always.
	 *  
	 * @param message Text to be printed on console
	 * @param options Not available yet. Put "" empty value always. 
	 *
	 * @author fgaparicio
	 */
	public static void msgConsole(String message, String options){
		//TO-DO: implementation of options parameter still not available, simply put "" value

		if (options !=null){
			
			if (options.equals("error")){
				System.err.println(message);	
			} else {
				//TODO: AVOID SYSTEM OUTPUT
				try {
					System.out.write(  (message+" \n").getBytes()  );  
				} catch (java.io.IOException e) {
					e.printStackTrace();
				}
//				LOGGER.
//				logger
//				LOGGER.
			}	
			
		} 
	}
	
	
	/**
	 * Metodo msgLogger.  
	 * @param Message to log
	 * @param options Leave empty String: "". No implementation available yet.   
	 * @param logger
	 *
	 * @author fgaparicio
	 */
	public static <E> void msgLogger(String message, String options, E logger){
		//TO-DO: implementation of options parameter still not available, simply put "" value

		if (options !=null){
			
			if (options.equals("error")){
				System.err.println(message);	
			} else {
//				//TODO: AVOID SYSTEM OUTPUT
//				try {
//					System.out.write(  (message+" \n").getBytes()  );  
//				} catch (java.io.IOException e) {
//					e.printStackTrace();
//				}
				((Logger) logger).info(message);
			}	
			
		} 
	}

	
	
	
	
}
