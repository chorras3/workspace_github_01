package mainFileDrop;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import net.iharder.dnd.FileDrop;

public class SimpleExample003 extends JFrame {
	protected static JTextArea textArea;

    public SimpleExample003() {
    	getUi();
    }
    



    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(new Runnable() {
            ////@Override
            public void run() {
                SimpleExample003 ex = new SimpleExample003();
                ex.setVisible(true);
                
                final ArrayList arrActions = new ArrayList();  // first column: action(copy/delete/etc), second column:source file, third column: destination file
                
                
                // Event: something dropped on the JTextArea
                new net.iharder.dnd.FileDrop( System.out, textArea, /*dragBorder,*/ new FileDrop.Listener()
                {   public void filesDropped( java.io.File[] files )
                    {   for( int i = 0; i < files.length; i++ )
                        {   try
                            {   textArea.append( files[i].getCanonicalPath() + "\n" );
                            	//--
                            	String strSourceFile = files[i].getCanonicalPath();
                            	FileAction fileAction = new FileAction();
                            	////fileAction = new FileAction();
                            	fileAction.setAction(FileAction.ACTION_MOVE);
                            	fileAction.setSource(strSourceFile);
                            	String strDestination = getDestination(strSourceFile, FileAction.ACTION_MOVE);
                            	fileAction.setDestination(strDestination);
                            	//--
                            	arrActions.add (fileAction);
                            	boolean execOk = ejecutar(fileAction);
                            	System.out.println("execOk="+execOk);
                            	
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
        butOk.setToolTipText("This button terminates the application");
        // Listens click on butOk
        butOk.addActionListener(new java.awt.event.ActionListener() {
            // Action to do when button butOk is pressed
     	   public void actionPerformed(java.awt.event.ActionEvent event) {
     		   butOkTask();
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
    	
    	////bufTask.append( files[i].getCanonicalPath() + "\n" );
    	
    	//String task = 
    	////System.exit(0);    	
    }
    

    
    protected static String getDestination (String strSourceFile, String strFolderName){
    	String strDestination="";   // Destination file where to move source file to.
    	
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
    	long lngFileLastModifiedDate = filAux.lastModified();
    	
    	System.out.println("lngFileLastModifiedDate="+lngFileLastModifiedDate);
    	
    	String strFilenameWithoutExtension = filAux.getName().split("\\.")[0];
    	
    	////strDestination = strPath+"\\"+lngFileLastModifiedDate+filAux.getName();
    	strDestination = strPath+"\\"+lngFileLastModifiedDate+"_"+strFilenameWithoutExtension;
    			
    	//Put commas enclosing it
    	strDestination = "\""+strDestination+"\"";
    	
    	return strDestination;
    }
    
    
    public static boolean ejecutar (FileAction fileAction){
    	boolean resultOk = true;
    	
    	String strDestinationOk = fileAction.getDestination()+ "\\Nuevo documento de texto.txt";
    	
    	String strCommand = "move " + fileAction.getSource() + " " + strDestinationOk;
    	System.out.println("strCommand="+strCommand);
    	
        try {
            String[] command = new String[3];
            command[0] = "cmd";
            command[1] = "/c";
            command[2] = strCommand;   // Command itself

            
            
            Process p = Runtime.getRuntime().exec(command);
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            String Error;

            while ((Error = stdError.readLine()) != null) {
                System.out.println(Error);
            }
        } catch (Exception e) {
        	 resultOk = false;
            e.printStackTrace();
        }
        
        return resultOk;
    }
    
} // End of class
