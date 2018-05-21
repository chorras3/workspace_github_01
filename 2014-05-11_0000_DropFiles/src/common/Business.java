package common;

public class Business {

	 public static final String NIF_STRING_ASOCIATION = "TRWAGMYFPDXBNJZSQVHLCKE";
	 
	  /**
	   * Devuelve un NIF completo a partir de un DNI. Es decir, añade la letra del NIF
	   * @param dni dni al que se quiere añadir la letra del NIF
	   * @return NIF completo.
	   * 
	   * @Author: wikibooks.org, variación del método: 
	   *   -references: "Algoritmo para obtener la letra del NIF", http://es.wikibooks.org/wiki/Algoritmo_para_obtener_la_letra_del_NIF
	   */
	  public static String getDNIletter(int dni) {
		////public static final String NIF_STRING_ASOCIATION = "TRWAGMYFPDXBNJZSQVHLCKE";
	    ////return String.valueOf(dni) + NIF_STRING_ASOCIATION.charAt(dni % 23);
		  return  ""+ NIF_STRING_ASOCIATION.charAt(dni % 23);
	  }
	
	
}
