/*
 * References:
 * --"Chapter 18. More Swing Components", chimera.labs.oreilly.com/books/1234000001805/ch18.html
 */

package src.pac02;
//file: TextEntryBox.java
    import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

    public class TextEntryBox_b {

      public static void main(String[] args) {
        JFrame frame = new JFrame("Text Entry Box");

        ////final JTextArea area = new JTextArea();
        final JTextPane area = new JTextPane();
        area.setFont(new Font("Serif", Font.BOLD, 18));
        area.setText("Howdy!\n");
        final JTextField field = new JTextField();

        frame.add(new JScrollPane(area), BorderLayout.CENTER);
        frame.add(field, BorderLayout.SOUTH);
        field.requestFocus();

        field.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent ae) {
            
        	  ////area.append(field.getText() + '\n');
            Document doc = area.getDocument();
            try {
				doc.insertString(doc.getLength(), "\n"+field.getText(), null);
			} catch (BadLocationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            field.setText("");
          }
        });

        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.setSize(200, 300);
        frame.setVisible(true);
      }
    }