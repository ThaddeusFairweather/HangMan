/**
 * Bill Nicholson
 * nicholdw@ucmail.uc.edu
 * Little Hangman game
 */
package hangmanGUI;

import hangmanPackage.Preferences;

import java.awt.Color;

public class Config {
	private int baseHeight, baseWidth;
	private int poleHeight, poleWidth;
	private int armHeight, armWidth;
	private int leftMargin, topMargin;
	private int poleOffsetFromLeftEdgeOfBase;
	private int ropeLength;
	private Color backgroundColor = new Color(238,238,238);
	private String configFileName;
	private Body body;
	private HangmanGUI hangmanGUI;
	private Preferences preferences;
	
	public static enum victimIdxEnum{victimIdxNew, victimIdxHead, victimIdxTorso
		                            ,victimIdxLeftArm, victimIdxRightArm
		                            ,victimIdxLeftLeg, victimIdxRightLeg,
		                            victimIdxLeftHand, victimIdxRightHand};
	  
	public Config() {
		baseHeight = 20;
		baseWidth = 200;
		poleHeight = 200;
		poleWidth = 10;
		armHeight = 10;
		armWidth = 125;
		leftMargin = 10;
		topMargin = 10;
		poleOffsetFromLeftEdgeOfBase = 5;
		ropeLength = 50;
		body = new Body(this);
		configFileName = "hangman.config";
		preferences = new Preferences();
		preferences.load(configFileName);
	}
	public String getConfigFileName() {return configFileName;}
	public Preferences getPreferences(){ return preferences;}
	public void setPreferences(Preferences preferences) {this.preferences = preferences;}
	
	public void setHangmanGUI(HangmanGUI hangmanGUI) {
		this.hangmanGUI = hangmanGUI;
	}
	public HangmanGUI getHangmanGUI() {return hangmanGUI;}
	
	public Body getBody() {return body;}	// Data hiding violated
	  
	public Color getBackgroundColor(){ return backgroundColor;}
	
	public int getBaseHeight(){ return baseHeight;}
	public void setBaseHeight(int baseHeight) { this.baseHeight = baseHeight;}
	
	public int getBaseWidth(){ return baseWidth;}
	public void setBaseWidth(int baseWidth) { this.baseWidth = baseWidth;}

	public int getPoleHeight(){ return poleHeight;}
	public void setPoleHeight(int poleHeight) { this.poleHeight = poleHeight;}
	
	public int getPoleWidth(){ return poleWidth;}
	public void setPoleWidth(int poleWidth) { this.poleWidth = poleWidth;}

	public int getArmHeight(){ return armHeight;}
	public void setArmHeight(int armHeight) { this.armHeight = armHeight;}
	
	public int getArmWidth(){ return armWidth;}
	public void setArmWidth(int armWidth) { this.armWidth = armWidth;}

	public int getLeftMargin(){ return leftMargin;}
	public void setLeftMargin(int leftMargin) { this.leftMargin = leftMargin;}

	public int getTopMargin(){ return topMargin;}
	public void setTopMargin(int topMargin) { this.topMargin = topMargin;}
	
	public int getPoleOffsetFromLeftEdgeOfBase(){ return poleOffsetFromLeftEdgeOfBase;}
	public void setPoleOffsetFromLeftEdgeOfBase(int poleOffsetFromLeftEdgeOfBase) { this.poleOffsetFromLeftEdgeOfBase = poleOffsetFromLeftEdgeOfBase;}
	
	//public victimIdxEnum getVictimIdx(){ return victimIdx;}	
	//public void setVictimIdx(victimIdxEnum victimIdx){this.victimIdx = victimIdx;}

	public int getRopeLength(){ return ropeLength;}
	public void setRopeLength(int ropeLength) { this.ropeLength = ropeLength;}
}
