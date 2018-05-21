package core_DEPRECATED.file;

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





/** Class to manage input/output, conversions, and other stuff.  
 * @author Francisco Gómez Aparicio 99GU4668
 * @since 17-abr-2009, 12:48:59
 * Last updated: 19-oct-2009 17:36
 */
public class Manager {
	
	// This ArrayList must be declared out of the recursive methods (because of recursive calling nature)
	public static ArrayList lisGenericRecursive = new ArrayList();
	
	private String editorPath = null;
	
	static final String SEMICOLON = ";";
	static final String NEW_LINE = "\n";
	static final String WIN_DIRECTORY_SEPARATOR = "\\";
	static final String N_ERRORES_MAS = "(##### errores más)";
	

	
	
	
	public static class FileTool {
        protected static boolean validateFile(String strFile) {
        	java.io.File filAux = new java.io.File(strFile);
        	boolean blnResult = false;
        	if (filAux.exists())
        		blnResult=true;
        	return blnResult;
        }
        
        protected String getFormattedDate(String strDate, String strFormat){
        	return getFormattedDate(strDate, strFormat);
        }
        
        protected static String convertMilliseconds2InverseDate (long lngDAteInMilliseconds){
        	return convertMilliseconds2InverseDate (lngDAteInMilliseconds);
        }
    }
	
	
	
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
	
	
    protected static GregorianCalendar millisecondsToCalendar(long lngDateInMilliseconds){
    	Date d = new Date(lngDateInMilliseconds);

    	GregorianCalendar c = new GregorianCalendar();
    	c.setTime(d);

    	return c;
    }
	
    protected static String convertMilliseconds2InverseDate (long lngDAteInMilliseconds){

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

		
	    
		

	    
	    
	}  // End of class 