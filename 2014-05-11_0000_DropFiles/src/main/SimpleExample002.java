package main;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class SimpleExample002 extends JFrame {
	protected JTextArea textArea;

    public SimpleExample002() {
    	getUi();
    }
    



    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(new Runnable() {
            ////@Override
            public void run() {
                SimpleExample002 ex = new SimpleExample002();
                ex.setVisible(true);
            }
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
        ////JTextArea textArea = getTextArea();
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
    	////System.exit(0);    	
    }
    

    
} // End of class
