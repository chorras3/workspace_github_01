//package practical.old;
////package com.SGPF.coopera2020.controllers.administracion.roles.funcsubrol;
//
//import java.util.List;
//import java.util.logging.Logger;
//
//import com.SGPF.coopera2020.modelo.roles.FuncionSubRol;
////import com.SGPF.coopera2020.modelo.roles.FuncionSubRol;
//
//
//
///**
//* La clase LibSimulation_20180509_0725. 
//* @author IGAE
//*
//*/
//public class LibSimulation_20180509_0725_BACKHAND_001 {
//	
//	// LOG
//	java.util.logging.Handler fileHandler = null;
//  public final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger(LibSimulation_20180509_0725_BACKHAND_001.class
//          .getClass().getName());
//  
//  
//  /**
//   * Constructor de la clase LibSimulation_20180509_0725. 
//   *	
//  * @author fgaparicio
//   */
//  LibSimulation_20180509_0725_BACKHAND_001(){
//  	setupLogger();
//  }
//  
//		
//	// TO STRING RECURSIVE
//  private static final List LEAVES = java.util.Arrays.asList(
//	        Boolean.class, Character.class, Byte.class, Short.class,
//	        Integer.class, Long.class, Float.class, Double.class, Void.class,
//	        String.class);
//
//  /**
//   * Metodo setupLogger. 
//   *
//   * @author fgaparicio
//   */
//  public void setupLogger(){
//		try {
//			fileHandler = new java.util.logging.FileHandler("./logfile.log");
//		} catch (SecurityException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (java.io.IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//      java.util.logging.SimpleFormatter simple = new java.util.logging.SimpleFormatter();
//      fileHandler.setFormatter(simple);
//      LOGGER.addHandler(fileHandler);//adding Handler for file
//
//  }
//  
//  
//	/**
//	 * Metodo toStringRecursive. 
//	 * @param o Object which values will be be returned as a String
//	 * @return String of the object returned
//	 * @throws Exception
//	 *
//	 * @author fgaparicio
//	 */
//	public static String toStringRecursive(Object o) throws Exception {
//	    if (o == null)
//	        return "null";
//
//	    if (LEAVES.contains(o.getClass()))
//	        return o.toString();
//
//	    StringBuilder sb = new StringBuilder();
//	    sb.append(o.getClass().getSimpleName()).append(": [");
//	    for (java.lang.reflect.Field f : o.getClass().getDeclaredFields()) {
//	        if (java.lang.reflect.Modifier.isStatic(f.getModifiers()))
//	            continue;
//	        f.setAccessible(true);
//	        sb.append(f.getName()).append(": ");
//	        sb.append(toStringRecursive(f.get(o))).append(" ");
//	    }
//	    sb.append("]");
//	    return sb.toString();
//	}
//	
//	
//	
//	/**
//	 * Metodo funcionSubRolToString. 
//	 * @param funcionSubRol
//	 * @return functinSubrol as a String
//	 * @throws Exception
//	 *
//	 * @author fgaparicio
//	 */
//	public static String funcionSubRolToString(FuncionSubRol funcionSubRol) throws Exception {
////		public static String LIBSIM_funcionSubRolToString(E funcionSubRol) throws Exception {
//			final String NEW_LINE ="\n";
//			StringBuffer result= new StringBuffer();
//
//	   		String funcionSubRolAsStr=toStringRecursive(funcionSubRol);  // TODO: No se compone bien, no usar de momento
//			
//			String funcionAsStr = toStringRecursive(funcionSubRol.getFuncion());
//	   		String rolAsStr = toStringRecursive(funcionSubRol.getRol());
//	   		String subRolAsStr = toStringRecursive(funcionSubRol.getSubrol());
//	   		String faseValidacionCodAsStr =  toStringRecursive(funcionSubRol.getId().getFaseValidacion());
//
//			result.append(NEW_LINE+subRolAsStr
//					+NEW_LINE+funcionSubRolAsStr
//					+NEW_LINE+faseValidacionCodAsStr);
//	   		
//			return result.toString();
//	}
//	
//	/**
//	 * Metodo removeNewLines. 
//	 * @param string String to format
//	 * @return String with new lines removed
//	 * @throws Exception
//	 *
//	 * @author fgaparicio
//	 */
//	public static String removeNewLines(String string) throws Exception {		
//		String result = string.replace("\n", " --linebreak-- ");  // TODO: Use an unique line
//		return result;
//	}
//	
//	/**
//	 * Metodo msgConsole.
//	 * Writes message on console. 
//	 * -The second parameter ('options') is not available yet, just set empty "" value always.
//	 *  
//	 * @param message Text to be printed on console
//	 * @param options Not available yet. Put "" empty value always. 
//	 *
//	 * @author fgaparicio
//	 */
//	public static void msgConsole(String message, String options){
//		//TO-DO: implementation of options parameter still not available, simply put "" value
//
//		if (options !=null){
//			
//			if (options.equals("error")){
//				System.err.println(message);	
//			} else {
//				//TODO: AVOID SYSTEM OUTPUT
//				try {
//					System.out.write(  (message+" \n").getBytes()  );  
//				} catch (java.io.IOException e) {
//					e.printStackTrace();
//				}
////				LOGGER.
////				logger
////				LOGGER.
//			}	
//			
//		} 
//	}
//	
//	
//	/**
//	 * Metodo msgLogger.  
//	 * @param Message to log
//	 * @param options Leave empty String: "". No implementation available yet.   
//	 * @param logger
//	 *
//	 * @author fgaparicio
//	 */
//	public static <E> void msgLogger(String message, String options, E logger){
//		//TO-DO: implementation of options parameter still not available, simply put "" value
//
//		if (options !=null){
//			
//			if (options.equals("error")){
//				System.err.println(message);	
//			} else {
////				//TODO: AVOID SYSTEM OUTPUT
////				try {
////					System.out.write(  (message+" \n").getBytes()  );  
////				} catch (java.io.IOException e) {
////					e.printStackTrace();
////				}
//				((Logger) logger).info(message);
//			}	
//			
//		} 
//	}
//
//	
//	
//	
//	
//}
