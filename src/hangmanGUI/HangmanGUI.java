/**
 * Bill Nicholson
 * Little Hangman game
 */
package hangmanGUI;

import hangmanGUI.Config.victimIdxEnum;
import hangmanPackage.Hangman;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;

public class HangmanGUI extends JFrame {

	/**
	 * 
	 */
	private Config myConfig;
	
	private static final long serialVersionUID = -7261224931517376685L;
	private myJPanel contentPane; 
	private Hangman hangman;
	private JTextField txtGuess;
	private JButton btnGuess;

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
		setTitle("Your Hangman");
		setResizable(false);
		myConfig = new Config();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 400, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmPreferences = new JMenuItem("Preferences");
		mnFile.add(mntmPreferences);
		
		JMenu mnGame = new JMenu("Game");
		menuBar.add(mnGame);
		
		JMenuItem mntmNew = new JMenuItem("New");
		mntmNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NewGame();
			}
		});
		mnGame.add(mntmNew);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("About");
		mnHelp.add(mntmNewMenuItem_1);
		contentPane = new myJPanel(myConfig);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtGuess = new JTextField();
		txtGuess.setToolTipText("Guess a letter");
		txtGuess.setHorizontalAlignment(SwingConstants.CENTER);
		txtGuess.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		txtGuess.setBounds(385, 11, 49, 20);
		contentPane.add(txtGuess);
		txtGuess.setColumns(10);
		btnGuess = new JButton("Guess");
		btnGuess.setBounds(318, 10, 63, 23);
		contentPane.add(btnGuess);
	}
	private void Init() {
		
	}

	public void Go() {
		Init();	
		hangman = new Hangman();// A new instance of the game engine will default to idle mode.
		HangmanGUI frame = new HangmanGUI();
		PrepTheDisplay();
		frame.setVisible(true);
	}

	private void NewGame(){
		hangman.startNewGame(null);
		myConfig.setVictimIdx(victimIdxEnum.victimIdxNew);
		PrepTheDisplay();
	}
	
	private void PrepTheDisplay(){
		switch (hangman.getGameMode()) {
		case idle:
			DisplayGameControls(false, true);
			break;
		case inProgress:
			DisplayGameControls(true, true);
			break;
		case lost:
			DisplayGameControls(true, false);
			break;
		case resigned:
			DisplayGameControls(true, false);
			break;
		default:
			break;
		
		}
	}
	
	private void DisplayGameControls(boolean visible, boolean enabled) {
		txtGuess.setVisible(visible);
		btnGuess.setVisible(visible);
		
	}
}
