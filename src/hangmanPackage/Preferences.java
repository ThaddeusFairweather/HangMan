package hangmanPackage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Preferences {

	private String wordFileName;
	private int seed = 42;
	
	public Preferences() {
		wordFileName = "hangman.wordlist";
	}

	public String getWordFileName(){return wordFileName;}
	public String setWordFileName(String wordFileName){this.wordFileName = wordFileName; return wordFileName;}

	public int getSeed(){return seed;}
	public int setSeed(int seed){this.seed = seed; return seed;}

	public boolean Load(String fileName){
		boolean result = true;
		
		return result;
	}
	
	public boolean Save(String fileName) {
		boolean result = true;
		try {
			FileWriter writer = new FileWriter(fileName);
			BufferedWriter br = new BufferedWriter(writer);
		    br.write(wordFileName);
		    br.close();
		} catch (IOException x) {
		    System.err.format("savePreferences(): IOException: %s%n", x);
		}		
		
		return result;
	}
	
}
