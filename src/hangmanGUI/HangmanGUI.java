/**
 * Bill Nicholson
 * Little Hangman game
 */
package hangmanGUI;

import hangmanGUI.Config.victimIdxEnum;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class HangmanGUI extends JFrame {

	/**
	 * 
	 */
	private Config myConfig;
	
	private static final long serialVersionUID = -7261224931517376685L;
	private myJPanel contentPane; 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		final HangmanGUI myHangmanGUI = new HangmanGUI();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					myHangmanGUI.Go();	
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public HangmanGUI() {
		myConfig = new Config();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 400, 450, 300);
		contentPane = new myJPanel(myConfig);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}
	private void Init() {
		
	}
	private void InitNewGame() {
		myConfig.setVictimIdx(victimIdxEnum.victimIdxNew);
	}
	public void Go() {
		Init();
		HangmanGUI frame = new HangmanGUI();
		frame.setVisible(true);
	}
}
