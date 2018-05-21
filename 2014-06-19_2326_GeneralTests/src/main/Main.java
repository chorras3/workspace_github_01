package main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class Main {
	public static void main(String[] args){
		Main application = new Main();
		application.runQuick();
		application.runLocale();
		application.runArrayAndVectorExample();
		application.run_util_Arrays();
		application.runTestFormatExample();
		application.runListExample();
		application.runDateExample1();
		application.runDateExample2();
		System.exit(0);
	}
	
	
	protected void runQuick(){
		System.out.println("runQuick()");
		// Do short test here
		
		////Date auxDate = new Date(null);
		
		

		
		
		String strFecha = "";
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date fecha = simpleDateFormat.parse(strFecha);
        } catch (Exception e) {
            System.out.println("Error formatting date!");;
        }

		
				
		/*
         * add multiple values to List
         */
		java.util.List<String> list = new java.util.ArrayList<String>() {
			 {
			    add("value1");
			    add("value2");
			 }
		};
		
			
		System.out.println( "list.get(0)="+ list.get(0) );
		//java.util.List.
		
//		System.out.println("java.util.Arrays.toString(stringArray)="+java.util.Arrays.toString(list));
		
//		String joined = TextUtils.join(", ", list);
		
//		String listString = String..join(", ", list);
		
		
		
        /*
         * If, short hand syntax:
         */
		int value1 = 1;
        int value2 = 2;
        int result;
        boolean someCondition = true;
        result = someCondition ? value1 : value2;

        System.out.println(result);
        
        
        
				
	}
		
		
	
	protected void runLocale(){		
		System.out.println("runLocale()");
		
		/*
		 * References: **"Mis apuntes de programación", "Bienvenido a mi humilde rincón!", "aine31", https://misapuntesdeprogramacion.wordpress.com/2013/02/04/locale/ 
		 */
		

		java.util.Locale locDK = new java.util.Locale("da", "DK");  // Denmark
		
		/*DATO LOCAL. */
		
		// En España a Dinamarca se le llama Dinamarca. 
        System.out.println(locDK.getDisplayCountry());
        // Dinamarca
 
        /*DATO LOCAL. */
        
        //En Dinamarca al país se le llama Danmark.
        System.out.println(locDK.getDisplayCountry(locDK));
        // Danmark		
	}
	
	protected void runDateExample1(){
		
	        // Make a new Date object. It will be initialized to the current time.
	        Date now = new Date();

	        // See what toString() returns
	        System.out.println(" 1. " + now.toString());

	        // Next, try the default DateFormat
	        System.out.println(" 2. " + java.text.DateFormat.getInstance().format(now));

	        // And the default time and date-time DateFormats
	        System.out.println(" 3. " + java.text.DateFormat.getTimeInstance().format(now));
	        System.out.println(" 4. " + 
	        		java.text.DateFormat.getDateTimeInstance().format(now));

	        // Next, try the short, medium and long variants of the 
	        // default time format 
	        System.out.println(" 5. " + 
	            java.text.DateFormat.getTimeInstance(java.text.DateFormat.SHORT).format(now));
	        System.out.println(" 6. " + 
	            java.text.DateFormat.getTimeInstance(java.text.DateFormat.MEDIUM).format(now));
	        System.out.println(" 7. " + 
	            java.text.DateFormat.getTimeInstance(java.text.DateFormat.LONG).format(now));

	        // For the default date-time format, the length of both the
	        // date and time elements can be specified. Here are some examples:
	        System.out.println(" 8. " + java.text.DateFormat.getDateTimeInstance(
	            java.text.DateFormat.SHORT, java.text.DateFormat.SHORT).format(now));
	        System.out.println(" 9. " + java.text.DateFormat.getDateTimeInstance(
	            java.text.DateFormat.MEDIUM, java.text.DateFormat.SHORT).format(now));
	        System.out.println("10. " + java.text.DateFormat.getDateTimeInstance(
	            java.text.DateFormat.LONG, java.text.DateFormat.LONG).format(now));
	}
	
	
	protected void runDateExample2() {
		System.out.println("runDateExample()");
		
		int intNumber = 2;
		
		String strResult = String.format("%02d", intNumber);
		System.out.println("strResult="+strResult);
		
		//ok pero es salida IO 
		String heading1b = "1";
		String heading2b = "2";
		System.out.printf( "%2s %n", heading1b); // First column is left-justified (15 characters), the second is 15 characters right-justified
		
		String heading1 = "Exam_Name";
		String heading2 = "Exam_Grade";
		System.out.printf( "%-15s %15s %n", heading1, heading2); // First column is left-justified (15 characters), the second is 15 characters right-justified
		System.out.printf( "---------x----------x----------x----------x"); // First column is left-justified (15 characters), the second is 15 characters right-justified


		
		
		int intNumberB = 2;
		
		String strResultB = String.format("%02d", intNumberB);
		System.out.println("strResult="+strResult);

		// create a calendar
		java.util.Calendar cal = java.util.Calendar.getInstance(new java.util.Locale("es", "es"));

		// print current time
		System.out.println(" Current time is : " + cal.getTime());

		// add a delay of 2 seconds
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// create a new calendar
		java.util.Calendar cal2 = java.util.Calendar.getInstance();

		// print the next time
		java.util.Date d = cal2.getTime();
		System.out.println(" Next time is : " + d);
		
		
		
//		/*
//		 * Original
//		 */
//      	String someDate = "05-10-2011 17:10";
//    	////java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("MM.dd.yyyy HH:mm", java.util.Locale.ENGLISH);
//      	java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("MM-dd-yyyy HH:mm", new java.util.Locale("es", "es"));  // Spain, Spanish
//    	try {
//    	Date date = sdf.parse(someDate);
//    	System.out.println("date="+date);
//    	long lngTimestamp = date.getTime();
//    	System.out.println("lngTimestamp="+lngTimestamp);
//    	}catch (Exception ex){
//    		ex.printStackTrace();
//    		System.exit(0);
//    	}
    	
    	
		/*
		 * Conversiones viceversa
		 */
		long lngTimestamp=0;
      	String someDate = "05-10-2011 17:10";
    	////java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("MM.dd.yyyy HH:mm", java.util.Locale.ENGLISH);
      	java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("MM-dd-yyyy HH:mm", new java.util.Locale("es", "es"));  // Spain, Spanish
    	try {
    	Date date = sdf.parse(someDate);
    	System.out.println("date="+date);
    	lngTimestamp = date.getTime();
    	System.out.println("lngTimestamp="+lngTimestamp);
    	}catch (Exception ex){
    		ex.printStackTrace();
    		System.exit(0);
    	}
    	
    	Date newDate = new Date(lngTimestamp);
    	System.out.println("newDate="+newDate);
    	
    	
    	/* sysdate */
        // create a calendar   java.util.
    	java.util.Calendar cal3 = java.util.Calendar.getInstance();
   	
        // print current time
        System.out.println(" Current time is : " + cal3.getTime());


        /* String to Calendar */
        
        
        // create a calendar   java.util.
    	java.util.Calendar cal4 = java.util.Calendar.getInstance();
    	////SimpleDateFormat sdf4 = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
    	SimpleDateFormat sdf4 = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
    	try {
			cal4.setTime(sdf4.parse("Mon Mar 14 16:02:37 GMT 2011"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println("[ERROR] e.getMessage()"+e.getMessage());
		}
        // print current time
        System.out.println(" Current time is : " + cal4.getTime());
        
        
        java.util.Calendar cal5 = java.util.Calendar.getInstance();
        SimpleDateFormat sdf5 = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
        try {
			cal5.setTime(sdf5.parse("Mon Mar 14 16:02:37 GMT 2011"));
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println("[ERROR] e2.getMessage()"+e2.getMessage());
		}// all done
        
        
        
        java.util.Calendar cal6 = java.util.Calendar.getInstance();
        SimpleDateFormat sdf6 = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        try {
			cal6.setTime(sdf6.parse("19-06-2014 23:30"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// all done
        System.out.println("cal6="+cal6);
        System.out.println("cal6.getTime()="+cal6.getTime());



	}	
	
	
	protected void runTestFormatExample() {
		System.out.println("runTestFormatExample()");

		/*
		 * References "Java String format Example", "Posted by: Nikos Maravitsas  in String March 13th, 2014", https://examples.javacodegeeks.com/core-java/lang/string/java-string-format-example/
		 */
//		String str = "";
//		String str = String.format("%2s", "123456");  	//2 characters max.
//		String str2 = String.format("%2s", 123456);  	//2 characters max.
	
		String str3 = String.format("%02d", new Integer("1") );  	//left padding to fill up to 2 characters
		
//		System.out.println(str +","+ str2 + "," +str3);
		System.out.println(str3);
	}
	
	
	
	
	protected void runListExample(){
		System.out.println("runArrayExample()");
		
		java.util.List countryArrayList = new java.util.ArrayList();
		countryArrayList.add("France");
		countryArrayList.add("Spain");
		
		System.out.println(countryArrayList);
		System.out.println(countryArrayList.toString().replace("[", "").replace("]", ""));
		
	}
	
	
	protected void runArrayAndVectorExample(){
		System.out.println("runArrayExample()");
		
		// Create array which holds up to 3 elements (indexes 0-2). 
		String stringArray[] = new String[3];
		stringArray[0] = "a";
		System.out.println( stringArray.toString() );
		/*print vector values using utility.  
		 * Note that a simple stringArray.toString() would print memory  
		 * reference ( something similar to "Ljava.lang.String;@1f2fbff" ), not values:
		 */
		System.out.println( "java.util.Arrays.toString(stringArray)="+java.util.Arrays.toString(stringArray) );  // Show with: "[" "]"
		System.out.println( "java.util.Arrays.toString(stringArray)="+java.util.Arrays.toString(stringArray).replace("[","").replace("]","") );  // Hide: "[" "]"
		


		
		
		// NOTE: this is not an Array, but a Vector! (a vector, unlike as arrays, has variable size) 
		//Create Vector on the fly (which holds up to 3 elements (indexes 0-2)):
		String[] exampleVector = {"0","0","0"};
		System.out.println("exampleVector.toString()="+exampleVector.toString());
		System.out.println(  "java.util.Arrays.toString(exampleVector) = "+java.util.Arrays.toString(exampleVector).replace("[","").replace("]","")  );
		
		
		
//		String exampleArray[] = {"a","b",","c"};
//		String exampleArray[] = new String[3];

		
		
		
//		String[] exampleArray = new int[3];
//		exampleArray = {"a","b",","c"};
	
//		                exampleArray =
	}
	

	
	
	
	protected void run_util_Arrays(){
		System.out.println("run()");

		int[] array = new int[] {
			    0,
			    1,
			    2,
			    3};
			int[] otherArray = new int[array.length];
			int[] clonedArray = array.clone();
		
		
//		Object[] array = new Object[] {
//			    new Object(),
//			    new Object(),
//			    new Object(),
//			    new Object()};
//			Object[] otherArray = new Object[array.length];
//			Object[] clonedArray = array.clone();
		
		String arrConValores[] = new String[2];
		arrConValores[0] = "a";
		arrConValores[1] = "b";
		System.out.println(arrConValores.toString() +  java.util.Arrays.toString(arrConValores) + java.util.Arrays.deepToString(arrConValores)); 
		String strValores =  java.util.Arrays.toString(arrConValores).replace("[", "(").replace("]", ")");
		System.out.println("strValores="+strValores);

		
		String[] arrInit = new String[10];
//		arrInit.;
		//arrInit = arrInit & arrConValores;
		
		
		
		
		//Arrays.toString(arr) or Arrays.deepToString(arr)
	}
	
	
}
