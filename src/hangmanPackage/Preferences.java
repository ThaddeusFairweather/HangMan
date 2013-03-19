package hangmanPackage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Preferences {

	private String wordFileName;
	private int seed = 42;
	private boolean showWordAfterLoss;
	private boolean showWordAfterResign;

	public Preferences() {
		// Defaults, just in case there's no config file.
		setDefaults();
	}

	public void setDefaults() {
		wordFileName = "hangmanWordlist.txt";
		showWordAfterLoss = true;
		showWordAfterResign = true;
	}
	public boolean getShowWordAfterLoss() { return showWordAfterLoss;}
	public boolean setShowWordAfterLoss(boolean showWordAfterLoss) {
		this.showWordAfterLoss = showWordAfterLoss;  
		return showWordAfterLoss;
	}

	public boolean getShowWordAfterResign() { return showWordAfterResign;}
	public boolean setShowWordAfterResign(boolean showWordAfterResign) {
		this.showWordAfterResign = showWordAfterResign;  
		return showWordAfterResign;
	}
	public String getWordFileName(){return wordFileName;}
	public String setWordFileName(String wordFileName){this.wordFileName = wordFileName; return wordFileName;}

	public int getSeed(){return seed;}
	public int setSeed(int seed){this.seed = seed; return seed;}

	public boolean load(String fileName){
		boolean result = true;
		try {
			FileReader reader = new FileReader(fileName);
			BufferedReader br = new BufferedReader(reader);
		    setWordFileName(br.readLine());
		    setShowWordAfterLoss(br.readLine().equals("true"));
		    setShowWordAfterResign(br.readLine().equals("true"));
		    br.close();
		} catch (IOException x) {
		    System.err.format("Preferences.load(): IOException: %s%n", x);
		    save(fileName);
		} catch (Exception ex){
			setDefaults();
		    save(fileName);
		}
		
		return result;
	}

	public boolean save(String fileName) {
		boolean result = true;
		try {
			FileWriter writer = new FileWriter(fileName);
			BufferedWriter br = new BufferedWriter(writer);
		    br.write(wordFileName); br.newLine();
		    br.write(showWordAfterLoss?"true":"false");br.newLine();
		    br.write(showWordAfterResign?"true":"false");br.newLine();
		    br.close();
		} catch (IOException x) {
		    System.err.format("Preferences.save(): IOException: %s%n", x);
		}		
		return result;
	}
	
}
