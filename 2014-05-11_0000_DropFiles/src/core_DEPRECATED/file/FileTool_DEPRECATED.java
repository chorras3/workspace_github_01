// References: **http://es.wikipedia.org/wiki/Objeto_todopoderoso

package core_DEPRECATED.file;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class FileTool_DEPRECATED {
	
    public static boolean validateExists(String strFile) {
    	java.io.File filAux = new java.io.File(strFile);
    	boolean blnResult = false;
    	if (filAux.exists())
    		blnResult=true;
    	return blnResult;
    }
    
    protected String getFormattedDate(String strDate, String strFormat){
    	return getFormattedDate(strDate, strFormat);
    }
    
    
    
    
    
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

}
