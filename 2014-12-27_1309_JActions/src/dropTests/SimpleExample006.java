package main;

import jacBusiness.FileAction;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import core.file.FileTool;

//import core.file.Manager;

import net.iharder.dnd.FileDrop;

public class SimpleExample006 extends JFrame {
	//--Constants START
	final protected static String DATE_TO_USE="created";   // Values: "created"/"modified" E.g. with "modified" we call the folder as the last modification date
	//--Constants END
	
	protected static JTextArea textArea;
    protected static ArrayList arrActions = new ArrayList();  // first column: action(copy/delete/etc), second column:source file, third column: destination file

    public SimpleExample006() {
    	getUi();
    }
    



    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(new Runnable() {
            ////@Override
            public void run() {
                SimpleExample006 ex = new SimpleExample006();
                ex.setVisible(true);
                
                /*
                 * Event: something dropped on the JTextArea
                 */
                new net.iharder.dnd.FileDrop( System.out, textArea, /*dragBorder,*/ new FileDrop.Listener()
                {   public void filesDropped( java.io.File[] files )
                    {   for( int i = 0; i < files.length; i++ )
                        {   try
                            {   
	                          	/*
	                          	 * Date
	                          	 */
                        		String someDate = "05-10-2011 17:10";
	                        	////java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("MM.dd.yyyy HH:mm", java.util.Locale.ENGLISH);
	                          	java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("MM-dd-yyyy HH:mm", new java.util.Locale("es", "es"));  // Spain, Spanish
	                        	try {
		                        	Date date = sdf.parse(someDate);
		                        	System.out.println("date="+date);
		                        	long lngTimestamp = date.getTime();
		                        	System.out.println("lngTimestamp="+lngTimestamp);
	                        	}catch (Exception ex){
	                        		ex.printStackTrace();
	                        		System.exit(0);
	                        	}
	                        	
	                          	/*
	                          	 * Calendar
	                          	 */	                        	
	                            java.util.Calendar cal6 = java.util.Calendar.getInstance();
	                            java.text.SimpleDateFormat sdf6 = new java.text.SimpleDateFormat("dd-MM-yyyy hh:mm");
	                            try {
	                    			cal6.setTime(sdf6.parse("05-10-2011 17:10"));
	                    		} catch (java.text.ParseException e) {
	                    			// TODO Auto-generated catch block
	                    			e.printStackTrace();
	                    		}// all done
	                            System.out.println("cal5="+cal6);                        	

	                        	////millisecondsToCalendar
	                        	
                        	
                        		textArea.append( files[i].getCanonicalPath() + "\n" );
                            	//--
                            	String strSourceFile = files[i].getCanonicalPath();
                            	FileAction fileAction = new FileAction();
                            	////fileAction = new FileAction();
                            	fileAction.setAction(FileAction.ACTION_MOVE);
                            	fileAction.setSource(strSourceFile);
                            	String strDestinationFolder = getDestination(strSourceFile, FileAction.ACTION_MOVE);
                            	fileAction.setDestination(strDestinationFolder);
                            	//--
                            	arrActions.add (fileAction);
                            	////ejecutar(fileAction, files[i].getName());
                            	////executeActions(arrActions);
                            }   // end try
                            catch( java.io.IOException e ) {}
                        }   // end for: through each dropped file
                    }   // end filesDropped
                }); // end FileDrop.Listener                
                
                
                
            } // End of run()
            
            
            
            
        });
        
    } // End of main
    
    
    protected JTextArea getTextArea(){    	
    	JTextArea textArea = new JTextArea();  
    	return textArea;
    }
    
    
    protected void getUi(){
    	// Draw this class itself (a JFrame)
    	setTitle("Simple example");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        //Create scroll with a JTextArea within
        textArea = getTextArea();
        JScrollPane scroll = new JScrollPane(textArea);
        
        //Create "dated folder (secure)" button
        JButton butMoveToDatedFolderSecure = new JButton("Move to dated folder (secure)");
        butMoveToDatedFolderSecure.setToolTipText("This button moves the file to a folder with reversed date format (secure)");
        
        //Create "dated folder (not secure)" button
        JButton butMoveToDatedFolder = new JButton("Move to dated folder (NOT secure)");
        butMoveToDatedFolder.setToolTipText("This button moves the file to a folder with reversed date format (secure)");
        
        // Listens click on butMoveToDatedFolderSecure
        butMoveToDatedFolderSecure.addActionListener(new java.awt.event.ActionListener() {
            // Action to do when button butOk is pressed
     	   public void actionPerformed(java.awt.event.ActionEvent event) {
     		  
//    		   try {
//				clicked_butMoveToDatedFolder(true);
//				textArea.setText("");
//    		   } catch (IOException ioe) {
//    			   // TODO Auto-generated catch block
//    			   ioe.printStackTrace();
//    			   textArea.setText("An I/O Error occurred performing action");
//    		   } catch (Exception e){
//    			   textArea.setText("An unknown Error occurred performing action");
//    		   }
    		   
//    		   try {
				clicked_butMoveToDatedFolder(true);
				textArea.setText("");
//    		   } catch (IOException ioe) {
//    			   // TODO Auto-generated catch block
//    			   ioe.printStackTrace();
//    			   textArea.setText("An I/O Error occurred performing action");
//    		   } catch (Exception e){
//    			   textArea.setText("An unknown Error occurred performing action");
//    		   }

    		   
           }  
        });  // End button listener

        
        // Listens click on butMoveToDatedFolder
        butMoveToDatedFolder.addActionListener(new java.awt.event.ActionListener() {
            // Action to do when button butOk is pressed
     	   public void actionPerformed(java.awt.event.ActionEvent event) {
     		  
//    		   try {
//				clicked_butMoveToDatedFolder(false);
//				textArea.setText("");
//    		   } catch (IOException ioe) {
//    			   // TODO Auto-generated catch block
//    			   ioe.printStackTrace();
//    			   textArea.setText("An I/O Error occurred performing action");
//    		   } catch (Exception e){
//    			   textArea.setText("An unknown Error occurred performing action");
//    		   }
     		  
  				clicked_butMoveToDatedFolder(false);
  				textArea.setText("");

           }  
        });  // End button listener
        
        // Add every component to this class (a JFrame)
        this.getContentPane().add(scroll);
        ////this.getContentPane().add(butMoveToDatedFolderSecure, java.awt.BorderLayout.AFTER_LAST_LINE);
        this.getContentPane().add(butMoveToDatedFolderSecure, java.awt.BorderLayout.LINE_END);
        this.getContentPane().add(butMoveToDatedFolder, java.awt.BorderLayout.AFTER_LAST_LINE);
        
    	
    }
    // true=secure (copy+rename original)
    protected void clicked_butMoveToDatedFolder(boolean blnSecureCopy)  
//    		throws IOException
    {  
    	
    	String str;
    	str = textArea.getText();
    	
    	System.out.println("str="+str);
    	
    	StringBuffer bufTask;
    	
    	int i = 0;
    	
    	String strDateReversed = ( (FileAction)arrActions.get(0) ).getDestination();
    	
    	//TODO    	<<<<<   OJO SI EL NOMBRE DE ARCHIVO TIENE BLANCOS, HAY ERROR. 
    	//TODO    	<<<<<  IMPORTANTE: ADEMÁS SE HAN OBSERVADO CASOS EN QUE EL ARCHIVO DESAPARECE (NO SE MUEVE AL DESTINO) ¿TIENE RELACIÓN CON LO ANTERIOR O ADEMÁS HAY OTRAS CAUSAS?. OBSERVAR ÉSTO HACIENDO PRUEBAS CON ARCHIVOS NO-IMPORTANTES O HACER COPIA BACKUP PRIMERO. PODRÍA HACER COPY EN LUGAR DE MOVER, HASTA QUE LA ACCIÓN SEA SEGURA. O HACER BACKUP GLOBAL DEL DISCO
    	
    	for (int posArrActions = 0; posArrActions < arrActions.size(); posArrActions++) {
    		FileAction fileActionCurrent = (FileAction)arrActions.get(posArrActions);
    		
    		

    		
    		////String strNewFolder = strBaseFolder +"\\"+ fileActionCurrent.getDestination();
    		String strFolderDestination = fileActionCurrent.getSource().substring(0, fileActionCurrent.getSource().length()-4);
    		File filFolderDestination = new File(strFolderDestination);
		
    		
    		
    		//Get paths:
    		File strFileSource = new File(fileActionCurrent.getSource());
//    		String strFileSourceParentFolder = strFileSource.getParent();
    		File filFileSource = new File(fileActionCurrent.getSource());
    		String strFileSourceParentFolder = filFileSource.getParent();
    		String strFileSourceNameAndExtension = filFileSource.getName();
    		//String strFileNewPath = filOldPath.getAbsolutePath();
    		//String strFileNewPath = filOldPath.getPath();
    		////String strFileNewFolder = filFileSource.getParent() + filFileSource.getAbsoluteFile();
//    		String strFileNewFilename = filFileSource.getName();   //.split("\.")[0];  //f.getName().split("\.")[0]
//    		strFileNewFilename = strFileNewFilename.split(",")[0];
//    		strFileNewFilename = File.name( filOldPath );   //.split("\.")[0];  //f.getName().split("\.")[0]
    		String originalFileBaseName = getFileBasename(filFileSource);
    		////File fileNewPath = new File(strFileNewFolder + "" + strFileSourceNameAndExtension);
    		String strAbsolutePath = filFolderDestination.getAbsolutePath();
    		String strFileDestination = filFolderDestination+"\\"+strFileSourceNameAndExtension;
    		
    		
    		
    		if (blnSecureCopy){
    			
        		/* Create destination folder
        		 * 
        		 */
//        		String strCommandCreateFolder = "mkdir \"" + fileActionCurrent.getDestination() +"\"";
//        		core.file.FileTool.runCommand(strCommandCreateFolder);
        		////strNewFolder = fileActionCurrent.getDestination();    		
        		boolean blnFolderCreated = filFolderDestination.mkdir();
        		if (!blnFolderCreated){
        			System.out.println("Error creating filFolderDestination:" + filFolderDestination.toString());
        			System.exit(0);
        		}
        		
        		
        		if (blnSecureCopy){  // Copy first, then rename original name to flag as "old"
        			
            		//Idea: copiar archivo a carpeta nueva, luego renombrar el original con PREFIJO: [ERASABLE],,,
            		// References: **"Standard concise way to copy a file in Java?", stackoverflow.com/questions/106770/standard-concise-way-to-copy-a-file-in-java    		
//            		Files.copy(source, out);      // References: **"Standard concise way to copy a file in Java?", stackoverflow.com/questions/106770/standard-concise-way-to-copy-a-file-in-java
            		
            		/* Create destination copy     <<<<<<backup copy of file
            		 * 
            		 */
        			//TODO [Errors]: throws IOException
            		try {
						Files.copy( 
								new File(fileActionCurrent.getSource()).toPath() 
								, new File(strFileDestination).toPath()
								, java.nio.file.StandardCopyOption.REPLACE_EXISTING
								, java.nio.file.StandardCopyOption.COPY_ATTRIBUTES
						        , java.nio.file.LinkOption.NOFOLLOW_LINKS
								);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            		
            		/* RENAME ORIGINAL FILE (TO EASE REMOVE BY HAND) <<<<<Move file to the new folder
            		 */
            		String strFileSourceFolder = filFileSource.getParent();
            		String strSourceFileRenamed = strFileSourceFolder + "\\[OLD]"+strFileSourceNameAndExtension+".old";
            		boolean moveSucess = filFileSource.renameTo( new File(strSourceFileRenamed) );
            		System.out.println("moveSucess(secure)="+moveSucess);        		
            		
        		} else {  // Only move, no security
        			
            		// Move file to destination folder
//            		String strCommandMove = "move \"" + fileActionCurrent.getSource() + "\" " + "\""+ fileActionCurrent.getDestination() + "\"";
//            		core.file.FileTool.runCommand(strCommandMove);
        			
            		String strFileSourceFolder = filFileSource.getParent();
            		String strSourceFileRenamed = strFileSourceFolder + "\\"+strDateReversed+"\\"+strFileSourceNameAndExtension;
            		boolean moveSucess = filFileSource.renameTo( new File(strSourceFileRenamed) );
            		System.out.println("moveSucess(not secure)="+moveSucess); 
        		}
    			
    			
    		} else { // if not blnSecureCopy...
    			
        		/* Create destination folder
        		 * 
        		 */
//        		String strCommandCreateFolder = "mkdir \"" + fileActionCurrent.getDestination() +"\"";
//        		core.file.FileTool.runCommand(strCommandCreateFolder);
        		////strNewFolder = fileActionCurrent.getDestination();    		
//        		filFolderDestination.mkdir();
        		
        		/* MOVE ORIGINAL FILE RENAMING IT 
        		 */
//        		String strFileSourceFolder = filFileSource.getParent();
//        		String strSourceFileRenamed = strFileSourceFolder +"\\"+strDateReversed+"\\"+strFileSourceNameAndExtension;
//        		boolean moveSucess = filFileSource.renameTo( new File(strSourceFileRenamed) );
//        		System.out.println("moveSucess= "+moveSucess);
    			
    			
    			
    			
    			
    			
//    			filFolderDestination.mkdir();
//    			
//        		String strFileSourceFolder = filFileSource.getParent();
//        		String strSourceFileRenamed = strFileSourceFolder +"\\"+strDateReversed+"\\"+strFileSourceNameAndExtension;
//        		File filFileTarget = new File(strSourceFileRenamed);
//        		try {
//					filFileTarget.createNewFile();
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//        		
//        		boolean blnFileSourceExists = filFileSource.exists();
//        		boolean blnTargetFileExists = filFileTarget.exists();
//        		
////        		boolean moveSucess = filFileSource.renameTo( filTargetFile );
////        		System.out.println("moveSucess= "+moveSucess);
//        		
//        		
//        		try {
////					Files.move(filFileSource.toPath(), filFileTarget.toPath(), java.nio.file.StandardCopyOption.ATOMIC_MOVE);
//					Files.move(filFileSource.toPath(), filFileTarget.toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}

    			
    			String strFileSourceFolder = filFileSource.getParent();
    			
    			String strSourceFileRenamed = strFileSourceFolder +"\\"+strDateReversed+"\\"+strFileSourceNameAndExtension;
    			File filFileTarget = new File(strSourceFileRenamed);
    			
        		boolean blnFileSourceExists = filFileSource.exists();
        		boolean blnTargetFileExists = filFileTarget.exists();
    			
        		filFolderDestination.mkdir();
    			try {
					copyFile(filFileSource, filFileTarget) ;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			System.out.println("Copy OK");
    			
    			
    			
    		}
    			
    		



    		
    		

    		
    		

    		
    		
    		
    		
		} 
    	
    	
    	
    }
    
    
    
    /*
     * 
     */

    
    
    /*
     * Receives a File, and returns the base name of it (file name without extension)
     */
    protected String getFileBasename(File aFile){
    	String strFullName = aFile.getName();
    	String strBaseName = strFullName.substring(0, strFullName.lastIndexOf("."));
    	return strBaseName; 
    }
    
    protected static String getDestination (String strSourceFile, String strFolderName){
//    	String strDestinationFolder="";   // Destination file where to move source file to.
//    	String strReverseDate="";
    	
    	java.io.File filAux = new java.io.File(strSourceFile);
    	
    	
    	String strPath= (String)filAux.getParent();   // Obtener ruta completa donde se encuentra el archivo
    	
//    	//Obtener sólo la carpeta padre
//    	String nombreDirectorio[] = filAux.getParent().split("\\\\");
//
//        for(int i=0;i<nombreDirectorio.length;i++){
//            System.out.println(nombreDirectorio[i]);
//        }
//        System.out.println("El nombre de mi carpeta padre es: "+nombreDirectorio[nombreDirectorio.length-1]);

    	// Obtener "fecha modificación" del archivo origen, se obtiene un long que luego habrá que pasar a Calendar:
    	long lngReverseDate=0; 
    	String strDestinationFoldername="";
    	if (DATE_TO_USE.equals("created")){
    		////strDestinationFolder = filAux.getFileCreationDate();
    //		String strDateAux = core.file.FileTool.getFileCreationDate();
//    		long lng = core.file.FileTool.getInverseDate(lngDAteInMilliseconds);  //<<<<<<
 //    		strDestinationFoldername = "";
    		////java.io.File filToValidate = new java.io.File(strSourceFile);
    		String strCreationDate = core.file.FileTool.getFileCreationDate_mkyong(strSourceFile);
    		strDestinationFoldername = core.file.FileTool.getInverseDate(strCreationDate, "dd/MM/yyyy HH:mm");
    		System.out.println("strDestinationFoldername="+strDestinationFoldername);
    	} else if (DATE_TO_USE.equals("modified")){
    		lngReverseDate = filAux.lastModified();
    		
        	////System.out.println("lngFileLastModifiedDate="+lngReverseDate);
        	GregorianCalendar cal = core.file.FileTool.millisecondsToCalendar(lngReverseDate);
        	String strInverseDate = core.file.FileTool.getInverseDate(filAux.lastModified());
        	String strFilenameWithoutExtension = filAux.getName().split("\\.")[0];
        	////strDestination = strPath+"\\"+lngFileLastModifiedDate+filAux.getName();
        	strDestinationFoldername = strPath+"\\"+strInverseDate+"_"+strFilenameWithoutExtension; ////+"\\"+filAux.getName();    		
    	}
    		 			
    	System.out.println("strDestinationFoldername="+strDestinationFoldername);

    	return strDestinationFoldername;
    }
    
    
//    public static void ejecutar (FileAction fileAction, String strFilenameAndExtension){
//    	////String strDestinationOk = fileAction.getDestination()+ "\\Nuevo documento de texto.txt";
//    	
//
//    	
//        
//    	// Create the folder
//    	String strCommandCreateFolder = "mkdir " + fileAction.getDestination();
//    	try {
//            String[] command = new String[3];
//            command[0] = "cmd";
//            command[1] = "/c";
//            command[2] = strCommandCreateFolder;   // Command itself
//
//            
//            
//            Process p = Runtime.getRuntime().exec(command);
//            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
//            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
//            String Error;
//
//            while ((Error = stdError.readLine()) != null) {
//                System.out.println(Error);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }    	
//    	
//    	
//    	
//    	////String strCommandMofeFile = "move \"" + fileAction.getSource() + "\" " + fileAction.getDestination()+"\\" + fileAction.getSource()+"\"";
//    	String strCommandMofeFile = "move \"" + fileAction.getSource() + "\" " + fileAction.getDestination()+"\\" + strFilenameAndExtension+"\"";
//    	System.out.println("strCommand="+strCommandMofeFile);
//    	
//    	// Move file to the folder
//    	try {
//            String[] command = new String[3];
//            command[0] = "cmd";
//            command[1] = "/c";
//            command[2] = strCommandMofeFile;   // Command itself
//
//            
//            
//            Process p = Runtime.getRuntime().exec(command);
//            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
//            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
//            String Error;
//
//            while ((Error = stdError.readLine()) != null) {
//                System.out.println(Error);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }     
//    	
//    }
    
    
    
//    protected static void executeActions(ArrayList arrActions){
//    	System.out.println("executeActions");
//    	
//    	for (Iterator iterItems = arrActions.iterator(); iterItems.hasNext();) {
//			String strElement = (String) iterItems.next();
//			
//			java.io.File filAux = new java.io.File(strElement);
//			
//			if (core.file.FileTool.validateExists(strElement)){
//				////String strPath = core.file.FileTool_DEPRECATED.getPath(strElement);
//				////core.file.FileTool;
//				
//				
//				// Get inverse date 
//				long lngFileLastModifiedDateInMilliseconds = filAux.lastModified();
//				////String strInverseDate = getInverseDate(filAux.lastModified());
//				
//				////String strInverseDate = IOManager.FileTool.convertMilliseconds2InverseDate(lngDAteInMilliseconds)
//				
//				String strInverseDate = core.file.FileTool.convertMilliseconds2InverseDate(lngFileLastModifiedDateInMilliseconds);
//					
//			}
//						
//		}
//    }
    
    

    	
    /*
     * -References:
     *    **"Move / Copy File Operations in Java", stackoverflow.com/questions/300559/move-copy-file-operations-in-java   (answered May 26 '09 at 7:28 Rigo Vides)
     */
    public static void copyFile(File sourceFile, File destFile) throws IOException {
        if(!destFile.exists()) {
            destFile.createNewFile();
        }

        java.nio.channels.FileChannel source = null;
        java.nio.channels.FileChannel destination = null;
        try {
            source = new FileInputStream(sourceFile).getChannel();
            destination = new FileOutputStream(destFile).getChannel();

            // previous code: destination.transferFrom(source, 0, source.size());
            // to avoid infinite loops, should be:
            long count = 0;
            long size = source.size();              
            while((count += destination.transferFrom(source, count, size-count))<size);
        }
        finally {
            if(source != null) {
                source.close();
            }
            if(destination != null) {
                destination.close();
            }
        }
    }
    

    
} // End of class
