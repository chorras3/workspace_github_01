package mainFileDrop;

import java.io.BufferedReader;
import java.io.InputStreamReader;
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

public class SimpleExample005 extends JFrame {
	//--Constants START
	final protected static String DATE_TO_USE="created";   // Values: "created"/"modified" E.g. with "modified" we call the folder as the last modification date
	//--Constants END
	
	protected static JTextArea textArea;
    protected static ArrayList arrActions = new ArrayList();  // first column: action(copy/delete/etc), second column:source file, third column: destination file

    public SimpleExample005() {
    	getUi();
    }
    



    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(new Runnable() {
            ////@Override
            public void run() {
                SimpleExample005 ex = new SimpleExample005();
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
        
        //Create Ok button
        JButton butOk = new JButton("Ok");
        butOk.setToolTipText("This button moves the file to a folder with date in inverse order");
        // Listens click on butOk
        butOk.addActionListener(new java.awt.event.ActionListener() {
            // Action to do when button butOk is pressed
     	   public void actionPerformed(java.awt.event.ActionEvent event) {
     		   butOkTask();
     		  textArea.setText("");
           }
        });
        
        // Add every component to this class (a JFrame)
        this.getContentPane().add(scroll);
        this.getContentPane().add(butOk, java.awt.BorderLayout.AFTER_LAST_LINE);
    	
    }
    
    protected void butOkTask(){
    	
    	String str;
    	str = textArea.getText();
    	
    	System.out.println("str="+str);
    	
    	StringBuffer bufTask;
    	
    	int i = 0;
    	
    	String strDestination0 = ( (FileAction)arrActions.get(0) ).getDestination();
    	System.out.println("strDestination0="+strDestination0);
    	
//    	<<<<< OJO SI EL NOMBRE DE ARCHIVO TIENE BLANCOS, HAY ERROR. 
//    	<<<<< IMPORTANTE: ADEMÁS SE HAN OBSERVADO CASOS EN QUE EL ARCHIVO DESAPARECE (NO SE MUEVE AL DESTINO). OBSERVAR ÉSTO HACIENDO PRUEBAS CON ARCHIVOS NO-IMPORTANTES O HACER COPIA BACKUP PRIMERO. PODRÍA HACER COPY EN LUGAR DE MOVER, HASTA QUE LA ACCIÓN SEA SEGURA. O HACER BACKUP GLOBAL DEL DISCO
    	
    	for (int posArrActions = 0; posArrActions < arrActions.size(); posArrActions++) {
    		FileAction fileActionCurrent = (FileAction)arrActions.get(posArrActions);
    		
    		// Create destination folder
    		String strCommandCreateFolder = "mkdir \"" + fileActionCurrent.getDestination() +"\"";
    		core.file.FileTool.runCommand(strCommandCreateFolder);
    		
    		
    		
    		// Create backup copy of file
    		
    		// Move file to destination folder
    		String strCommandMove = "move \"" + fileActionCurrent.getSource() + "\" " + "\""+ fileActionCurrent.getDestination() + "\"";
    		core.file.FileTool.runCommand(strCommandMove);
		} 
    	
    	
    	////bufTask.append( files[i].getCanonicalPath() + "\n" );
    	
    	//String task = 
    	////System.exit(0);    	
    }
    

    
    protected static String getDestination (String strSourceFile, String strFolderName){
    	String strDestinationFolder="";   // Destination file where to move source file to.
    	String strReverseDate="";
    	
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
    		String strCreationDate = core.file.FileTool.getFileCreationDate(strSourceFile);
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
    
    
    
    protected static void executeActions(ArrayList arrActions){
    	System.out.println("executeActions");
    	
    	for (Iterator iterItems = arrActions.iterator(); iterItems.hasNext();) {
			String strElement = (String) iterItems.next();
			
			java.io.File filAux = new java.io.File(strElement);
			
			if (core.file.FileTool.validateExists(strElement)){
				////String strPath = core.file.FileTool_DEPRECATED.getPath(strElement);
				////core.file.FileTool;
				
				
				// Get inverse date 
				long lngFileLastModifiedDateInMilliseconds = filAux.lastModified();
				////String strInverseDate = getInverseDate(filAux.lastModified());
				
				////String strInverseDate = IOManager.FileTool.convertMilliseconds2InverseDate(lngDAteInMilliseconds)
				
				String strInverseDate = core.file.FileTool.convertMilliseconds2InverseDate(lngFileLastModifiedDateInMilliseconds);
					
			}
						
		}
    }
    
    

    

    
} // End of class
