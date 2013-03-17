package hangmanPackage;

import java.lang.*;

/**
 */
public class Hangman {
	static public enum enumGameMode  {idle, inProgress, resigned, lost};
	
	private enumGameMode gameMode;
	
    /**
     * This is the word that the player is trying to guess.
     */
    private String word;

    /**
     * The number of guesses used by the current player for the current word
     */
    private int numberOfGuesses;

    /**
     */
    private int maxNumberOfGuesses = 5;

    /**
     * The list of letters that the player has already guessed
     */
    private String guessedLetters;

    /**
     * Constructor
     */
    public Hangman() {
    	gameMode = enumGameMode.idle;
    	//setGameMode(enumGameMode.idle);
    }
    
    /**
     * @return the word being guessed
     */
    public String getWord() {
        return null;
    }

    /**
     * Sets the word that will be guessed.
     * @param word The word that will be guessed.
     * @return The word that was passed in as a parameter
     */
    public String setWord(String word) {
        return null;
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
        return null;
    }

    /**
     * The player has guessed a letter: this method decides if the letter is in the word or if the player is out of guesses and has lost the game.
     * @throws GameOverException if the player has used up all the guesses and has lost the game.
     * @throws GameWonException if the player has won the game.
     * @param letter The letter that is being guessed
     * @return True if the letter was in the word and has not been already guessed, false otherwise
     */
    public boolean guess(char letter) {
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
    	gameMode = enumGameMode.idle;
    }
    
    private String getRandomWord() {
    	return "plugh";
    }
    
    public enumGameMode getGameMode() {return gameMode;}
    
}

