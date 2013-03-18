/*
 * Bill Nicholson
 * nicholdw@ucmail.uc.edu
 */
package hangmanPackage;

import hangmanGUI.Body;
import hangmanGUI.Config;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.*;
import java.util.ArrayList;
import java.util.Random;

/**
 */
public class Hangman {
	static public enum enumGameMode  {idle, inProgress, won, resigned, lost};
	private static ArrayList<String> wordList;
	private Config config;
	private enumGameMode gameMode;
	private Random random;

    /**
     * This is the word that the player is trying to guess.
     */
    private String word;

    /**
     * The number of guesses used by the current player for the current word
     */
    private int numberOfGuesses;

    /**
     * This should match up with the number of body parts displayed on the gallows. 
     */
    private int maxNumberOfGuesses = 8;

    /**
     * The list of letters that the player has already guessed
     */
    private String guessedLetters;

    /**
     * Constructor
     */
    public Hangman(Config config) {
    	gameMode = enumGameMode.idle;
    	this.config = config;
    }

    public void setMaxNumberOfGuesses(int maxNumberOfGuesses){this.maxNumberOfGuesses = maxNumberOfGuesses;}
    public int getMaxNumberOfGuesses(){return maxNumberOfGuesses;}
    
    /**
     * @return the word being guessed
     */
    public String getWord() {
        return word;
    }

    /**
     * Sets the word that will be guessed.
     * @param word The word that will be guessed.
     * @return The word that was passed in as a parameter
     */
    public String setWord(String word) {
    	this.word = word;
        return word;
    }

    /**
     * Return the value of the private variable 'numberOfGuesses'
     * @return 
     */
    public int getNumberOfGuesses() {
        return 0;
    }

    /**
     * @return The list of guessed letters
     */
    public String getGuessedLetters() {
        return guessedLetters;
    }

    /**
     * The player has guessed a letter: this method decides if the letter is in the word or if the player is out of guesses and has lost the game.
     * @throws GameOverException if the player has used up all the guesses and has lost the game.
     * @throws GameWonException if the player has won the game.
     * @param letter The letter that is being guessed
     * @return True if the letter was in the word and has not been already guessed, false otherwise
     */
    public boolean guess(String letter) throws GameWonException, GameOverException {
    	//CharSequence tmp = new String(letter);
    	// Add to the list of guessed letters
    	if (!(guessedLetters.toLowerCase().contains(letter.toLowerCase()))) {
    		guessedLetters += letter.toLowerCase();
    	} 
    	// Did we win?
		if (!getWordProgress().contains("-")) {
			gameMode = enumGameMode.won;
			throw new GameWonException();
		}
		// Did we lose?
		String x = word.toLowerCase(); 
		String y = letter.toLowerCase();
    	if (!x.contains(y)) {
    		numberOfGuesses++;
			try {config.getBody().AddBodyPart();} catch(Exception ex){}
    		if (numberOfGuesses == maxNumberOfGuesses) {
    			gameMode = enumGameMode.lost;
    			throw new GameOverException();
    		}
    	}
        return false;
    }

    /**
     * @return True if the game has been won, false otherwise.
     */
    public boolean checkForWinner() {
        return false;
    }

    /**
     * Start a new game. Reset the list of guessed letters, reset the guess counter, establish a random word to be guessed if the parameter is null.
     * @param word The word to be guessed. If null, this method will generate a word
     */
    public void startNewGame(String word) {
    	if (word == null) word = getRandomWord();
    	setWord(word);
    	numberOfGuesses = 0;
    	guessedLetters = "";
    	gameMode = enumGameMode.inProgress;
    }
    
    private String getRandomWord() {
//    	return "pplugh";
    	if (wordList == null) {
    		loadWordList();
    	}
    	if (random == null) random = new Random(config.getPreferences().getSeed());
    	int idx = random.nextInt(wordList.size());
    	String word = wordList.get(idx);
//    	return "Cat in the hat";
    	return word;
    }
     
    public enumGameMode getGameMode() {return gameMode;}
 
    /**
     * Build a string with the guessed letters and with underscores everywhere else
     * @return
     */
    public String getWordProgress() {
    	StringBuilder wordProgress = new StringBuilder();
    	String word = getWord();
    	char letter, letterLowerCase;
    	// This method could be called when a word has not yet been established, so we check for null to be safe...
    	if (word != null) {
        	String wordLowerCase = getWord().toLowerCase();
	    	for (int i = 0; i < word.length(); i++) {
	    		letter = word.charAt(i);
	    		letterLowerCase = wordLowerCase.charAt(i);
	    		// The contains method requires an object that implements the CharSequence interface...
	    		StringBuilder tmp = new StringBuilder(1);
	    		tmp.append(letterLowerCase);
	    		// If showItAll is true, the player lost or resigned and we are showing the whole word
	    		if (guessedLetters.contains(tmp)) {
	    			wordProgress = wordProgress.append(letter);
	    		} else {
	    			if (letter != ' ') {
	    				wordProgress = wordProgress.append("-");
	    			} else {
	    				wordProgress = wordProgress.append(" ");	    				
	    			}
//	    			wordProgress = wordProgress.append((char)(150));
	    		}
	    	}
    	}  
    	return new String(wordProgress);
    }
    /**
     * Resign the game, if it is in progress.
     */
    public void Resign() {
    	if (gameMode == enumGameMode.inProgress){
    		gameMode = enumGameMode.resigned;
    	}
    }
    private int loadWordList() {
    	int count = 0;
    	String line;
    	try {
    		FileReader reader = new FileReader(config.getPreferences().getWordFileName());
    		BufferedReader br = new BufferedReader(reader);
    		wordList = new ArrayList<String>();
    		while ((line = br.readLine()) != null) {
    			wordList.add(line);
    			count++;
    		}
    	} catch (Exception ex) {}
    	return count;
    }
}

