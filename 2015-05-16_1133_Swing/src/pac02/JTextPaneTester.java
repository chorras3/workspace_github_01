/*
 * References
 * --http://stackoverflow.com/questions/7468550/getting-jtextpane-to-scroll
 */

package src.pac02;
import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.text.*;

public class JTextPaneTester
{
    JTextPane jtp = new JTextPane();
    StyledDocument doc;
    Style style;
    JScrollPane jsp = new JScrollPane(jtp);

    JTextPaneTester()
    {
        doc = (StyledDocument)jtp.getDocument();
        style = doc.addStyle("fancy", null);
        jsp.setVerticalScrollBarPolicy(
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    }

    void append(String s)
    {
        try
        {
            doc.insertString(doc.getLength(), s, style);
        }
        catch (BadLocationException e) { assert false: "problem"; }
    }

    public static void main(String[] args)
    {
        JTextPaneTester thing = new JTextPaneTester();


        JFrame f = new JFrame();
        JPanel center = new JPanel();
        f.add(center, BorderLayout.CENTER);
        
        f.setVisible(true);
        for (int i = 0; i < 100; i++)
            thing.append("nouns verbs adjectives \n");


        center.add(thing.jsp);
        f.setSize(400, 400);
        f.setVisible(true);
    }
}