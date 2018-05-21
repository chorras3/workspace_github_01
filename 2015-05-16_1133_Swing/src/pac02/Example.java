package src.pac02;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Position.Bias;
import javax.swing.text.View;


/*
 * References: **"JTextPane - How can I create a scrolling log", stackoverflow.com/questions/6421007/jtextpane-how-can-i-create-a-scrolling-log
 */

public class Example {

    private static int MAX = 7;

    static public void main( String[] s ) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {

        UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );

        JFrame frame = new JFrame();
        frame.setBounds( 50, 50, 200, 300 );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        final JTextPane pane = new JTextPane();

        pane.setText( "1\n2\n3\n4" );

        JPanel pnl = new JPanel(new BorderLayout());
        pnl.add( pane, BorderLayout.CENTER );

        pane.getDocument().addDocumentListener( new DocumentListener() {
            public void removeUpdate( DocumentEvent e ) {
            }
            public void insertUpdate( DocumentEvent e ) {
                SwingUtilities.invokeLater( new Runnable() {
                    public void run() {
                        try {
                            View baseView = pane.getUI().getRootView( pane );
                            View root = baseView.getView(0);
                            for( int i = 0; i < root.getViewCount()-MAX; i++ ) {
                                int line = root.getViewIndex( i, Bias.Forward );
                                View lineview = root.getView(line);
                                pane.getDocument().remove( lineview.getStartOffset(), lineview.getEndOffset() );
                            }
                        } catch( BadLocationException e1 ) {
                            e1.printStackTrace();
                        }
                    }
                } );
            }
            public void changedUpdate( DocumentEvent e ) {
            }
        });

        pnl.add(new JButton(new AbstractAction("Delete") {
            public void actionPerformed( ActionEvent e ) {
               try {
                   View baseView = pane.getUI().getRootView( pane );
                   View root = baseView.getView(0);
                   int line = root.getViewIndex( 0, Bias.Forward );
                   View lineview = root.getView(line);
                   pane.getDocument().remove( lineview.getStartOffset(), lineview.getEndOffset() );
               } catch( BadLocationException e1 ) {
                   e1.printStackTrace();
               }
            }
        }), BorderLayout.SOUTH);

        pnl.add(new JButton(new AbstractAction("Add") {
            @Override
            public void actionPerformed( ActionEvent e ) {
                try {
                    pane.getDocument().insertString(pane.getDocument().getEndPosition().getOffset(), new SimpleDateFormat("ss").format( new Date() )+": This is a new line\n", null);
                } catch( BadLocationException e1 ) {
                    e1.printStackTrace();
                } 
            }
        }), BorderLayout.NORTH);
        frame.setContentPane( pnl );
        frame.setVisible( true );
    }
}