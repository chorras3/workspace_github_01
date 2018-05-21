package pac01;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.ImageIcon;

public class JlabelDemo_B extends JPanel {

	JLabel jlbLabel1, jlbLabel2, jlbLabel3;
	public JlabelDemo_B() {
//		ImageIcon icon = new ImageIcon("java-swing-tutorial.JPG",
//				"My Website");
		ImageIcon icon = new ImageIcon("imageIcon.png",
				"My Website");
		// Creating an Icon
		setLayout(new GridLayout(3, 1));
		// 3 rows, 1 column Panel having Grid Layout
		jlbLabel1 = new JLabel("Image with Text", icon, JLabel.CENTER);
		// We can position of the text, relative to the icon:
		jlbLabel1.setVerticalTextPosition(JLabel.BOTTOM);
		jlbLabel1.setHorizontalTextPosition(JLabel.CENTER);
		jlbLabel1.setBorder(BorderFactory.createLineBorder(Color.green));
		jlbLabel1.setBackground(Color.YELLOW);
		jlbLabel1.setOpaque(true);  // force use background
		
		jlbLabel2 = new JLabel("Text Only Label");
		jlbLabel2.setBorder(BorderFactory.createLineBorder(Color.green));
		jlbLabel2.setBackground(Color.CYAN);
		jlbLabel2.setOpaque(true);  // force see background
		
		jlbLabel3 = new JLabel(icon); // Label of Icon Only
		jlbLabel3.setBorder(BorderFactory.createLineBorder(Color.green));
		jlbLabel3.setBackground(Color.GRAY);
		jlbLabel3.setOpaque(true);  // force see background		
		
		// Add labels to the Panel
		add(jlbLabel1);
		add(jlbLabel2);
		add(jlbLabel3);
	}
	public static void main(String[] args) {
		JFrame frame = new JFrame("jLabel Usage Demo");
		frame.addWindowListener(new WindowAdapter() {

			// Shows code to Add Window Listener
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		frame.setContentPane(new JlabelDemo_B());
		frame.pack();
		frame.setVisible(true);
	}
}