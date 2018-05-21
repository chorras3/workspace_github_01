package pac01;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;


/*
 * References: http://www.javabeginner.com/java-swing-tutorial/
 */

//import statements
//Check if window closes automatically. Otherwise add suitable code
public class HelloWorldFrame extends JFrame {

	public static void main(String args[]) {
		new HelloWorldFrame();
	}
	HelloWorldFrame() {
		
		JLabel jlbHelloWorld = new JLabel("Hello World");
		jlbHelloWorld.setBorder(BorderFactory.createLineBorder(Color.green));
		
		add(jlbHelloWorld);
		this.setSize(100, 100);
		// pack();
		setVisible(true);
	}
}
