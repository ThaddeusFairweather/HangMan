/**
 * Bill Nicholson
 * nicholdw@ucmail.uc.edu
 * Little Hangman game
 */
package hangmanGUI;

import hangmanGUI.Config.victimIdxEnum;
import hangmanPackage.GameOverException;
import hangmanPackage.GameWonException;
import hangmanPackage.Hangman;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.imageio.ImageIO;
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
import java.awt.image.BufferedImage;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.Canvas;
import java.io.File;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.awt.event.WindowEvent;

public class HangmanGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7261224931517376685L;
	private myJPanel contentPane; 
	private Hangman hangman;
	private JTextField txtGuess;
	private JButton btnGuess;
	private JLabel lblWordProgress;
	private JLabel lblGuessedLetters;
	private ImageCanvas cvsWinner, cvsLoser, cvsRightHand, cvsLeftHand;	
	private BufferedImage youWinImage;
	private Config config;
	private JMenuItem mntmResign;
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
		config = new Config();
		config.setHangmanGUI(this);

		setTitle("Your Hangman");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 400, 450, 300);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmPreferences = new JMenuItem("Preferences");
		mntmPreferences.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PreferencesGUI.launch(config);
			}
		});
		mnFile.add(mntmPreferences);
		
		//JMenuItem mntmExit = new JMenuItem("Exit");
		//mntmExit.addActionListener(new ActionListener() {
		//	public void actionPerformed(ActionEvent arg0) {
                //WindowEvent wev = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
                //Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
		//		this.dispose();
		//	}
		//});
		//mnFile.add(mntmExit);

		JMenu mnGame = new JMenu("Game");
		menuBar.add(mnGame);

		JMenuItem mntmNew = new JMenuItem("New");
		mntmNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		mntmNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NewGame();
			}
		});
		mnGame.add(mntmNew);
		
		mntmResign = new JMenuItem("Resign");
		mntmResign.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				hangman.Resign();
				PrepTheDisplay();
			}
		});
		mntmResign.setEnabled(false);
		mnGame.add(mntmResign);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("About");
		mnHelp.add(mntmNewMenuItem_1);
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				About.launch();
			}
		});
		contentPane = new myJPanel(config);
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
		btnGuess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GuessALetter();
			} 
		});
		btnGuess.setBounds(306, 10, 75, 23);
		contentPane.add(btnGuess);
		
		lblWordProgress = new JLabel("your guess here");
		lblWordProgress.setHorizontalAlignment(SwingConstants.CENTER);
		lblWordProgress.setFont(new Font("Courier New", Font.BOLD, 18));
		lblWordProgress.setBounds(189, 180, 245, 36);
		contentPane.add(lblWordProgress);
		
		cvsWinner = new ImageCanvas("Images/youWin.jpg");
		cvsWinner.setBounds(281, 39, 100, 100);
		contentPane.add(cvsWinner);
		
		cvsLoser = new ImageCanvas("Images/youLost.jpg");
		cvsLoser.setBounds(281, 39, 100, 100);
		contentPane.add(cvsLoser);
		 
		lblGuessedLetters = new JLabel("");
		lblGuessedLetters.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblGuessedLetters.setVerticalAlignment(SwingConstants.TOP);
		lblGuessedLetters.setBounds(21, 223, 100, 14);
		contentPane.add(lblGuessedLetters);
		
		cvsRightHand = new ImageCanvas("Images/rightHand.jpg");
		cvsRightHand.setBounds(192, 116, 25, 18);
		contentPane.add(cvsRightHand);
		cvsRightHand.setVisible(false);
		
		cvsLeftHand = new ImageCanvas("Images/leftHand.jpg");
		cvsLeftHand.setBounds(45, 116, 25, 18);
		contentPane.add(cvsLeftHand);
		cvsLeftHand.setVisible(false);

		hangman = new Hangman(config);// A new instance of the game engine will default to idle mode.
		PrepTheDisplay();
	}
	private void Init() {
		
	}

	public void Go() {
		/* Be careful what code you put in here... the instance of the GUI has not been instantiated yet */
		/* Put the code in the HangmanGUI constructor or reference the frame object, below. */
		Init();	
		//HangmanGUI frame = new HangmanGUI();
		this.setVisible(true);
	}

	private void NewGame(){
		//hangman = new Hangman();// A new instance of the game engine will default to idle mode.
		hangman.startNewGame(null);
		PrepTheDisplay();
		config.getBody().reset();
		txtGuess.requestFocus();
		mntmResign.setEnabled(true);
	}

	private void PrepTheDisplay(){
		switch (hangman.getGameMode()) {
		case idle:
			DisplayGameControls(false, true);
			cvsWinner.setVisible(false);
			cvsLoser.setVisible(false);
			DisplayGuessedLetters(false);
			mntmResign.setEnabled(false);
			break;
		case won:
			DisplayGameControls(false, false);
			cvsWinner.setVisible(true);
			cvsLoser.setVisible(false);
			lblWordProgress.setVisible(true);
			mntmResign.setEnabled(false);
			lblWordProgress.setText(hangman.getWordProgress());
			break;
		case inProgress:
			DisplayGameControls(true, true);
			cvsWinner.setVisible(false);
			cvsLoser.setVisible(false);
			DisplayGuessedLetters(true);
			mntmResign.setEnabled(true);
			lblWordProgress.setText(hangman.getWordProgress());
			break;
		case lost:
			DisplayGameControls(false, false);
			cvsWinner.setVisible(false);
			cvsLoser.setVisible(true);
			lblWordProgress.setVisible(true);
			DisplayGuessedLetters(true);
			mntmResign.setEnabled(false);
			DisplayWordProgress(config.getPreferences().getShowWordAfterLoss());	
			break;
		case resigned:
			DisplayGameControls(false, false);
			cvsWinner.setVisible(false);
			cvsLoser.setVisible(true);
			lblWordProgress.setVisible(true);
			DisplayGuessedLetters(true);
			mntmResign.setEnabled(false);
			DisplayWordProgress(config.getPreferences().getShowWordAfterLoss());	
			break;
		default:
			break;
		}
	}

	private void DisplayGameControls(boolean visible, boolean enabled) {
		txtGuess.setVisible(visible);
		btnGuess.setVisible(visible);
		lblWordProgress.setVisible(visible);
		contentPane.repaint();
	}
	/**
	 * Draw the word with underscores for unguessed letters
	 */
	private void DisplayWordProgress(boolean showItAll){
		String wordProgress;
		if (showItAll) {
			wordProgress = hangman.getWord();
		} else
			wordProgress = hangman.getWordProgress();
		lblWordProgress.setText(wordProgress);		
		contentPane.repaint();
		DisplayGuessedLetters(true);
	}  

	private void GuessALetter() {
		if (txtGuess.getText().trim().length() == 1) {
			try {
				StringBuilder letter = new StringBuilder(1);	// The one is the length of the object
				letter.append(txtGuess.getText().trim().charAt(0));
				hangman.guess(new String(letter));
				DisplayWordProgress(false);
			} catch (GameOverException e) {
				// We lost
				PrepTheDisplay();
			} catch (GameWonException e) {
				// We won 
				PrepTheDisplay();
			} 
			txtGuess.setText("");
			txtGuess.requestFocus();
		}
	}
	private void DisplayGuessedLetters(boolean status) {
		lblGuessedLetters.setText(hangman.getGuessedLetters());
		lblGuessedLetters.setVisible(status);
	}
	public void showLeftHand(boolean status) {
		cvsLeftHand.setVisible(status);
	}
	public void showRightHand(boolean status) {
		cvsRightHand.setVisible(status);
	}
}
