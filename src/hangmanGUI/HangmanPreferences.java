package hangmanGUI;


//This program shows two ways to make a modeless dialog.
//The first dialog box shown is make by combining components 
//with the JDialog class. (pressing OK brings up the second
//dialog box).
//The second example combines the functionality offered by the
//JOptionPane class with the JDialog class.

//Imports are listed in full to show what's being used
//could just import javax.swing.* and java.awt.* etc..
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.Icon;
import java.awt.FlowLayout;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.BorderLayout;


public class HangmanPreferences extends JFrame
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 8635870926380995860L;
	private JDialog dialog;
    private JDialog optionPaneDialog;
    private JTextArea tracker;
    
    //Using a standard Java icon
    private Icon warningIcon = UIManager.getIcon("OptionPane.warningIcon");
    JButton btnOK;
    JButton btnCancel;
    private JButton btnNewButton;
    
    //Application start point   
    public static void launch() {
     
     //Use the event dispatch thread for Swing components
     EventQueue.invokeLater(new Runnable()
     {
         public void run()
         {
             //create GUI frame
             new HangmanPreferences().setVisible(true);          
         }
     });
              
    }
    
    public HangmanPreferences()
    {
    	setTitle("Hangman Preferences");       
        //The dialog box is made up of the JDialog containing
        //two JLabels (one for the icon, one for message) and
        //two JButtons to make an OK/CANCEL selection
        dialog = new JDialog(this,"Preferences");    
        dialog.setSize(400,400);
        dialog.setLocationRelativeTo(this);
        getContentPane().setLayout(null);
        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(0, 0, 0, 0);
        getContentPane().add(btnCancel);
        
        //Press Cancel and the dialog closes. Game Over.
        btnCancel.addActionListener(new ActionListener()
        {
        public void actionPerformed(ActionEvent event)
        {
            dialog.dispose();
        }
        });
        btnOK = new JButton("OK");
        btnOK.setBounds(0, 0, 0, 0);
        getContentPane().add(btnOK);
        
        //Press OK and the dialog closes and the
        //JOptionPane dialog is shown.
        btnOK.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                dialog.dispose();
            }
        });
        
        btnNewButton = new JButton("New button");
        btnNewButton.setBounds(0, 250, 442, 23);
        getContentPane().add(btnNewButton);
        dialog.getContentPane().setLayout(null);
        dialog.setVisible(true);  
    }
}