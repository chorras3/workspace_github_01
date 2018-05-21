package src.pac02;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
 
public class ScrollControl {
 
   JTextPane textPane;
   JScrollBar scrollBar;
   DefaultCaret caret;
   BoundedRangeModel model;
 
   public static void main(String[] args) {
      SwingUtilities.invokeLater(new Runnable() {
 
         @Override
         public void run() {
            new ScrollControl().makeUI();
         }
      });
   }
 
   public void makeUI() {
        textPane = new JTextPane();
      caret = (DefaultCaret) textPane.getCaret();
      
      JScrollPane scrollPane = new JScrollPane(textPane);
      scrollBar = scrollPane.getVerticalScrollBar();
      model = scrollBar.getModel();
      
      scrollBar.addAdjustmentListener(new AdjustmentListener() {
 
         public void adjustmentValueChanged(AdjustmentEvent e) {
            if (model.getValue() == model.getMaximum() - model.getExtent()) {
               caret.setDot(textPane.getDocument().getLength());
               caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
            } else {
               caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
            }
         }
      });
      JButton button = new JButton("Click");
      button.addActionListener(new ActionListener() {
 
         public void actionPerformed(ActionEvent e) {
               try {
                    textPane.getDocument().insertString(textPane.getDocument().getLength(), "testing. \n", null);
               } catch (BadLocationException e1) {}
         }
      });
      
      JFrame frame = new JFrame();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(400, 400);
      frame.add(scrollPane, BorderLayout.CENTER);
      frame.add(button, BorderLayout.SOUTH);
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
   }
}