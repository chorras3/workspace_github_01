package common;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;
import java.util.StringTokenizer;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
//&&&&&&&&&&&&&&&&&&&&&
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

////import tools.validator.EmailValidator;



/** Class to manage input/output, conversions, and other stuff.  
 * @author Francisco Gómez Aparicio 99GU4668
 * @since 17-abr-2009, 12:48:59
 * Last updated: 19-oct-2009 17:36
 */
public class IOManager {
	
	// This ArrayList must be declared out of the recursive methods (because of recursive calling nature)
	public static ArrayList lisGenericRecursive = new ArrayList();
	
	private String editorPath = null;
	
	static final String SEMICOLON = ";";
	static final String NEW_LINE = "\n";
	static final String N_ERRORES_MAS = "(##### errores más)";
	
	
	
	
	public String getFormattedDate(String strDate, String strFormat)
	throws IOException
	{
		String strDateFormatted = "";
				
		if (!strFormat.equals("dd-mm-yyyy_hh;mi")){
			// <<<<<<<<<<<<<<<<<<<<g
		} else {
			throw new IOException("User IOException: Format do not recognized");
		}
		
//		String strInverseDate = getPassedDateInverseFormat(); 
//		return strInverseDate;
		
		return strDateFormatted;
	}
	


		

		
		
		
		
		
		// TODO Fran: INICIO métodos que he heredado de la GISS y que conviene quitar: <<<<<<<<<<<<<<<<<<<<<<<<<<<
		public static String truncate(String str, int endIndex)
		{
			return ((str.length() > endIndex) ? str.substring(0, endIndex): str);
		}
		
		// TODO Fran: FIN métodos que he heredado y que conviene quitar: <<<<<<<<<<<<<<<<<<<<<<<<<<<
		
		
		
		

		
		
	    protected static GregorianCalendar millisecondsToCalendar(long lngDateInMilliseconds){
	    	Date d = new Date(lngDateInMilliseconds);

	    	GregorianCalendar c = new GregorianCalendar();
	    	c.setTime(d);

	    	return c;
	    }
		
	    public static String convertMilliseconds2InverseDate (long lngDAteInMilliseconds){

	    	GregorianCalendar gregorianCalendar = millisecondsToCalendar(lngDAteInMilliseconds);
	    	   	
	    	int day = gregorianCalendar.get(Calendar.DATE);
	    	int month = 1+ gregorianCalendar.get(Calendar.MONTH);       //Add 1 because months are managed as array (0 to 11)
	    	int annio = gregorianCalendar.get(Calendar.YEAR);
	    	int hour = gregorianCalendar.get(Calendar.HOUR_OF_DAY);
	    	int minute = gregorianCalendar.get(Calendar.MINUTE);
	    	int second = gregorianCalendar.get(Calendar.SECOND);
	  
	    	String strInverseDate = new Integer(annio).toString();
	    	
	    	
	        java.text.DecimalFormat decimalFormat = new java.text.DecimalFormat("00");
	    	
	    	strInverseDate = strInverseDate+"-"+decimalFormat.format(month);
	    	strInverseDate = strInverseDate+"-"+decimalFormat.format(day);
	    	strInverseDate = strInverseDate+"_"+decimalFormat.format(hour);
	    	strInverseDate = strInverseDate+""+decimalFormat.format(minute);
	    	
	    	return strInverseDate;
	    }

		
	    
		
		public static class FileTool {
	        public static boolean validateFile(String strFile) {
	        	java.io.File filAux = new java.io.File(strFile);
	        	boolean blnResult = false;
	        	if (filAux.exists())
	        		blnResult=true;
	        	return blnResult;
	        }
	    }
	    
	    
	}  // End of class 
	








//	/**
//	 *  -This class repeats sequential replacements on text contents. Receives an 
//	 *  Arraylist as argument containing all replacements (match and 
//	 *  replacement). So, it is an ArrayList of ArrayList (bidimensional: each 
//	 *  element has a two Strings ArrayList), the Search string and the 
//	 *  replacement string.  
//	 * @param pstrTextOriginal
//	 * @param lisReplacementPair
//	 * @return
//	 */
//	public String applyReplaceList(String pstrTextOriginal, ArrayList lisReplacementPair){
//		String strResult = pstrTextOriginal;
//		
//		Iterator itReplacementPair = lisReplacementPair.iterator();
//		while (itReplacementPair .hasNext()) {
//			ArrayList arlisFilaCurrent = (ArrayList) itReplacementPair.next();
//			String strSearchCurrent = (String) arlisFilaCurrent.get(0);
//			String strTextReplaciationCurrent = (String) arlisFilaCurrent.get(1);
//			
//			System.out.println("strSearchCurrent="+strSearchCurrent);
//			
//			strResult = pstrTextOriginal.replace(strSearchCurrent, strTextReplaciationCurrent);
//		}
//		
//		return strResult;
//	}


	
	
	
	
	
	
	
//}  //End of IOManager class



	
//	public static String conexionPreparedToString(ConexionPrepared conn, String strSql){
//		String strSqlValuated = strSql;
//		String strValue = "";
//		int posValue = 0;
//
//		while (posValue <= conn.getTotalValues()-1){
//			// Get value
//			if (conn.getType(posValue) == 1) {  // If String, put quotes around 
//				strValue = "'" + conn.getValue(posValue) + "'";
//			} else {  // Unknown type, use the same format than String by default 
//				strValue = "'" + conn.getValue(posValue) + "'";
//				System.out.println("strValue=" + strValue);
//			}
//			// Obtener poner valor en la sql
//			strSqlValuated = strSqlValuated.replaceFirst("\\?", strValue);
//			posValue++;
//		}
//		return strSqlValuated;
//		
////		Object o = conn.getClass().getComponentType();
////		String elString = conn.toString();
////		int j = conn.total;
////		Field largoFields = conn.getClass().getFields()[0];
////		System.out.println("elString=" + elString);
//		////ConexionPrepared c = conn;		
//		
//	}
	
	
//	/** ATENCIÓN, ACTUALMENTE NO FUNCIONA. 
//	 * Recibe un texto con etiquetas HTML y las elimina y retorna un texto plano. 
//	 * -References: 
//	 *   http://www.masterdlabs.es/2009/expresiones-regulares-en-java/
//	 *   http://es.wikipedia.org/wiki/Expresi%C3%B3n_regular
//	 *   http://en.wikipedia.org/wiki/Regular_expression
//	 * 
//	 * @param html
//	 * @return
//	 */
//	// <<<<<<<<<<< NO FUNCIONA
//	private static String htmlToPlainText(String html) {
//		html = html.replaceAll(";", "\n"); //salto de línea
//		html = html.replaceAll("", "\n"); //salto de línea
//		Pattern pattern = Pattern.compile("&lt;(.*?)&gt;");
//		Matcher matcher = pattern.matcher(html);
//		String plainText = matcher.replaceAll("");
//		return plainText;
//	}
