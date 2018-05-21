package common;

public class Logger {
	// Defines when trace output
	int level = 2;
	
	public final String OUTPUT_TYPE = "console";
	public String FILENAME = "C:/temp/out.log";
	
	public String getFilename() {
		return FILENAME;
	}


	public void setFilename(String fILENAME) {
		FILENAME = fILENAME;
	}




	public final String OUTPUT_CONSOLE = "console";
	public final String OUTPUT_FILE = "file";
	
	public final int LEVEL_0_NO_TRACE = 0;
	public final int LEVEL_1_DEBUG = 1;
	public final int LEVEL_2_WARNING = 2;
	public final int LEVEL_3_ERROR = 3;
	public final int LEVEL_4_FATAL_ERROR = 4;
	
	
	
	public void Logger(){
	}
	
	
	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	
	
	public boolean debug(String strMessage){
		boolean blnResult = false;
		
		try {
			if (level>=LEVEL_1_DEBUG){
				printTrace(strMessage);
				blnResult = true;
			}			
		} catch (Exception exception){
			blnResult = false;
		} finally{
			blnResult = false;
		}

		return blnResult;
	}
	
	
	public boolean warning(String strMessage){
		boolean blnResult = false;
		
		try {
			if (level>=LEVEL_2_WARNING){
				printTrace(strMessage);
				blnResult = true;
			}			
		} catch (Exception exception){
			blnResult = false;
		} finally{
			blnResult = false;
		}

		return blnResult;
	}
	
	public boolean error(String strMessage){
		boolean blnResult = false;
		
		try {
			if (level>=LEVEL_3_ERROR){
				printTrace(strMessage);
				blnResult = true;
			}			
		} catch (Exception exception){
			blnResult = false;
		} finally{
			blnResult = false;
		}

		return blnResult;
	}
	
	public boolean fatalError(String strMessage){
		boolean blnResult = false;
		
		try {
			if (level>=LEVEL_4_FATAL_ERROR){
				printTrace(strMessage);
				blnResult = true;
			}			
		} catch (Exception exception){
			blnResult = false;
		} finally{
			blnResult = false;
		}

		return blnResult;
	}



	
	protected void printTrace(String strMessage){
		IOManager ioManager = new IOManager();
		
		if ( OUTPUT_TYPE.equals(OUTPUT_CONSOLE) ){
			System.out.println(strMessage);			
		}
		if ( OUTPUT_TYPE.equals(OUTPUT_FILE) ){
			
			try {
				ioManager.fileWriteTextAppending(FILENAME, strMessage);	
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	
	
	protected void printTrace(java.util.ArrayList arlisSeveralMessages){
		IOManager ioManager = new IOManager();
		
		if ( OUTPUT_TYPE.equals(OUTPUT_CONSOLE) ){
			for (java.util.Iterator iter = arlisSeveralMessages.iterator(); iter.hasNext();) {
				String strElementCurrent = (String) iter.next();
				System.out.println(strElementCurrent);
			}
						
		}
		if ( OUTPUT_TYPE.equals(OUTPUT_FILE) ){
			try{
				ioManager.fileWriteAppend_ArrayList_ToFile( FILENAME, arlisSeveralMessages );
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
