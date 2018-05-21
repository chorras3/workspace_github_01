// THIS CLASS CONTENT IS MOVED TO THE SPECIALIZED CLASS common.IOManager.java


///**
// * @author fgomez
// *
// *
// */
//package common;
//
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.ArrayList;
//
///**
// * @author fgomez
// *
// */
//public class Common {
//	private String editorPath = null;
//
//	public Common() {
//		////System.out.println("Constructor de common.Common ");
//	}
//
//	public void writeFile(String nameAndExtension, String textContent) {
//
//		// Create file
//		File file = new File(nameAndExtension);
//		try {
//			file.createNewFile();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		// Create file manager
//		BufferedWriter bufferedWriter = null;
//		try {
//			bufferedWriter = new BufferedWriter(new FileWriter(file));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		// Write the code in a file
//		try {
//			bufferedWriter.write(textContent);
//			// //bufferedWriter.write("ddd");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		// Empty the buffer to free resources
//		try {
//			bufferedWriter.flush();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		// Close buffer
//		try {
//			bufferedWriter.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} // This also ensures buffer data is
//		// phisically written into file
//
//	}
//
//
//
//	public void openFileInEditor(String fileName) {
//		// Get runtime instance
//		Runtime runtime = Runtime.getRuntime();
//
//		String editor = "";
//
//		File f = new File(fileName);
//		if (!(f.exists())) {
//			// System.out
//			// .println("NO EXISTE C:\\Archivos de
//			// programa\\ConTEXT\\ConTEXT.exe");
//			editor = "notepad.exe ";
//
//		} else {
//			// System.out
//			// .println("SI EXISTE C:\\Archivos de
//			// programa\\ConTEXT\\ConTEXT.exe");
//			//Editor thales
//			editor = this.getEditorPath();
//			////F:\Archivos de programa\ConTEXT\ConTEXT.exe
//		}
//
//		// Exec my program
//		Process process = null;
//		try {
//			// process = runtime
//			// .exec("C:\\Archivos de programa\\ConTEXT\\ConTEXT.exe "
//			// + fileName);
//			process = runtime.exec(editor + " " + fileName);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		// Wait till its finished
//		try {
//			process.waitFor();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}
//
//
//	  /**
//	  * Fetch the entire contents of a text file, and return it in a String.
//	  * This style of implementation does not throw Exceptions to the caller.
//	  *
//	  * @param aFile is a file which already exists and can be read.
//	  */
//	  public String getFileContent(String fileNameAsString) {
//		 File file = new File(fileNameAsString);
//	    //...checks on aFile are elided
//	    StringBuffer contents = new StringBuffer();
//
//	    try {
//	      //use buffering, reading one line at a time
//	      //FileReader always assumes default encoding is OK!
//	      BufferedReader input =  new BufferedReader(new FileReader(fileNameAsString));
//	      try {
//	        String line = null; //not declared within while loop
//	        /*
//	        * readLine is a bit quirky :
//	        * it returns the content of a line MINUS the newline.
//	        * it returns null only for the END of the stream.
//	        * it returns an empty String if two newlines appear in a row.
//	        */
//	        while (( line = input.readLine()) != null){
//	          contents.append(line);
//	          contents.append(System.getProperty("line.separator"));
//	        }
//	      }
//	      finally {
//	        input.close();
//	      }
//	    }
//	    catch (IOException ex){
//	      ex.printStackTrace();
//	    }
//
//	    return contents.toString();
//	  }
//
//	/**
//	 * Fetch the entire contents of a text file, and return it in a ArrayList,
//	 * splitted line by line. This style of implementation does not throw Exceptions to
//	 * the caller.
//	 *
//	 * @param aFile
//	 *            is a file which already exists and can be read.
//	 */
//	public ArrayList getFileContentAsArrayOfLines(
//			String fileNameAsString) {
//		// ...checks on aFile are elided
//		StringBuffer contents = new StringBuffer();
//		ArrayList lineArray = new ArrayList();
//
//		try {
//			// use buffering, reading one line at a time
//			// FileReader always assumes default encoding is OK!
//			BufferedReader input = new BufferedReader(new FileReader(
//					fileNameAsString));
//			try {
//				String line = null; // not declared within while loop
//				/*
//				 * readLine is a bit quirky : it returns the content of a line
//				 * MINUS the newline. it returns null only for the END of the
//				 * stream. it returns an empty String if two newlines appear in
//				 * a row.
//				 */
//				while ((line = input.readLine()) != null) {
//					lineArray.add(line);
//
//				}
//			} finally {
//				input.close();
//			}
//		} catch (IOException ex) {
//			ex.printStackTrace();
//		}
//
//		// //return contents.toString();
//		return lineArray;
//	}
//
//
//	/**
//	 * Fetch the entire contents of a text file, and return it as String source
//	 * code form:
//	 * strTemp = strTemp + "blah blah code".
//	 * strTemp = strTemp + "more blah blah code".
//	 * style of implementation does not throw Exceptions to the caller.
//	 *
//	 * @param aFile
//	 *            is a file which already exists and can be read.
//	 */
//	public String getFileContentAsConcatenationSourceCode(
//			String fileNameAsString) {
//		// ...checks on aFile are elided
//		StringBuffer contents = new StringBuffer();
//		ArrayList lineArray = new ArrayList();
//		String strSourceCode = new String("");
//
//		try {
//			// use buffering, reading one line at a time
//			// FileReader always assumes default encoding is OK!
//			BufferedReader input = new BufferedReader(new FileReader(
//					fileNameAsString));
//			try {
//				String line = null; // not declared within while loop
//				/*
//				 * readLine is a bit quirky : it returns the content of a line
//				 * MINUS the newline. it returns null only for the END of the
//				 * stream. it returns an empty String if two newlines appear in
//				 * a row.
//				 */
//				while ((line = input.readLine()) != null) {
//					//// ////<<<<<<<line = line.replace("\"", "X");
//					
////					if (String.valueOf(line.charAt(0))=="\"") {
////						System.out.println("hay una comilla doble");	
////					}
//					
//					lineArray.add(line);
//				}
//				//Generate source code
//				strSourceCode = "";
//				for (int currentRow = 0; currentRow < lineArray.size(); currentRow++) {
//					////if ( lineArray.get(currentRow).trim().length() == 0 ){
//					if ( lineArray.get(currentRow).toString().trim().length() == 0 ){
//						strSourceCode = strSourceCode + "\nstrTemp = strTemp + \"\\n\"" + lineArray.get(currentRow) + ";";
//					} else {
//						strSourceCode = strSourceCode + "\nstrTemp = strTemp + \"" + lineArray.get(currentRow) + "\";";
//					}
//				}
//
//			} finally {
//				input.close();
//			}
//		} catch (IOException ex) {
//			ex.printStackTrace();
//		}
//
//		// //return contents.toString();
//		return strSourceCode;
//	}
//
//
//// public String transformTextToSystemOut(String text) {
//// String strTemp = text;
//// String code = new String();
//// String output = new String();
////
////		  int startCharacterPosition = 0;
////		  int endCharacterPosition = 10;
////
////		  code = strTemp.substring(startCharacterPosition, endCharacterPosition);
////
////		  output = "System.output(\"something\")";
////		  output = output.replace("something", code);
////		  return output;
////	  }
//
//
//	  /**
//	   * @return the editorPath
//	   */
//	  public String getEditorPath() {
//		  return editorPath;
//	  }
//
//	/**
//	 * @param editorPath
//	 *            the editorPath to set
//	 */
//	public void setEditorPath(String editorPath) {
//		this.editorPath = editorPath;
//	}
//
//
//	/**
//	 * Validates if the parameter strDateAsString (a String representing a date) is   
//	 * a valid date and meets the format received in parameter strFormat 
//	 * (E.g. "dd-MM-yyyy").
//	 * -References: author: PNT (Proyectos de Nuevas Tecnologías) -
//	 * "Fechas En Java", www.dosideas.com/wiki/Fechas_En_Java#Validar_una_fecha
//	 * method original name:
//	 * "isFechaValida(String fechax, String strFormato)"
//	 * 
//	 * @return true if strDateAsString meets the format, false otherwise.
//	 */
//	private static boolean isValid_dateAsString(String strDateAsString, String strFormat) {
//		try {
//
//			// If format parameter is null, set a format default value:
//			// dd-MM-yyyy
//			if (strFormat == null)
//				strFormat = "dd-MM-yyyy";
//
//			java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat(
//					strFormat, java.util.Locale.getDefault());
//			simpleDateFormat.setLenient(false);
//			simpleDateFormat.parse(strDateAsString);
//		} catch (java.text.ParseException e) {
//			return false;
//		}
//		return true;
//	}
//	
//
//	
//}  // End of class
