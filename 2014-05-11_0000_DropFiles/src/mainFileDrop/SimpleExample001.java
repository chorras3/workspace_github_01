package mainFileDrop;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class SimpleExample001 extends JFrame {

    public SimpleExample001() {
    	getUi();
    }
    



    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(new Runnable() {
            ////@Override
            public void run() {
                SimpleExample001 ex = new SimpleExample001();
                ex.setVisible(true);
            }
        });
        
    } // End of main
    
    
    
    protected void getUi(){
        setTitle("Simple example");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        
        JTextArea textArea = getTextArea();
        ////this.getContentPane().add(textArea);
        JScrollPane scroll = new JScrollPane(textArea);
        
        JButton butOk = new JButton("Ok");
        butOk.setToolTipText("A Button component");  //This text will be shown when mouse pointer is over the button

        
        this.getContentPane().add(scroll);
        this.getContentPane().add(butOk, java.awt.BorderLayout.AFTER_LAST_LINE);
        
        // Listens click on butOk
        butOk.addActionListener(new java.awt.event.ActionListener() {
            // Action to do when button butOk is pressed
     	   public void actionPerformed(java.awt.event.ActionEvent event) {
                
         	   System.exit(0);
           }
        });

    }

    
    
    protected JTextArea getTextArea(){    	
    	JTextArea textArea = new JTextArea();  
    	return textArea;
    }
}
