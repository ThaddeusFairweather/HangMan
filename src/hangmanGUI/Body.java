/**
 * Bill Nicholson
 * nicholdw@ucmail.uc.edu
 * Little Hangman game
 * It's fun, really!
 */
package hangmanGUI;

import java.awt.Color;
import java.awt.Graphics;

public class Body {
	private int headDiameter;
	private int torsoHeight, torsoWidth;
	private int armWidth, armHeight;
	private int legWidth, legLength;
	private Config.victimIdxEnum victimIdx;
	private Config config;
	private int handDiameter;

	public Body(Config config) {
		this.config = config;
		headDiameter = 40;
		torsoHeight = 40;
		torsoWidth = 20;
		armWidth = 30;
		armHeight = 10;
		legWidth = (torsoWidth/2) - 2;
		legLength = 35;
		victimIdx = Config.victimIdxEnum.victimIdxNew;		// No body parts will be drawn
		handDiameter = 15;
	}

	public void reset(){
		victimIdx = Config.victimIdxEnum.victimIdxNew;		// No body parts will be drawn
	}

	public int getHeadCircumference(){ return headDiameter;}
	public void SetHeadCircumference(int headCircumference){ this.headDiameter = headCircumference;}
	
	/**
	 * Increment the bodypart count to display the next body part when it's drawn
	 */
	public void AddBodyPart() {
		int tmp = victimIdx.ordinal() + 1;
		victimIdx = Config.victimIdxEnum.values()[tmp];
	}
	/**
	 * Draw the body
	 * @param g Graphics context
	 * @param victimIdxEnum how much of the body to draw. See Config.victimIdxEnum
	 * @param x the x co-ordinate of the top of the head
	 * @param y the y co-ordinate of the top of the head
	 */
	public void Draw(Graphics g, int x, int y){
		Config.victimIdxEnum victimIdxTmp;
//		for (int i = 0; i < 2; i++) {
			// The first we will erase everything. 
			// The second pass we will draw everything.
//			if (i == 0) {
//				victimIdxTmp = Config.victimIdxEnum.victimIdxRightLeg;
				//g.setColor(Color.white); 
//				g.setColor(config.getBackgroundColor()); 
//			} else {
 				victimIdxTmp = victimIdx;
//				//g.setColor(Color.black);
//				g.setColor(Color.blue);
//			}
			g.setColor(config.getBackgroundColor()); 
			g.fillRect(x-headDiameter/2, y, 
					   armWidth*2 + torsoWidth+2, headDiameter + torsoHeight + legLength+2);
			 
			g.setColor(Color.gray);
			// List the body parts 'backward' so each case falls through to the next case.
			config.getHangmanGUI().showLeftHand(false);
			config.getHangmanGUI().showRightHand(false);

			switch (victimIdxTmp) {
			  

			case victimIdxRightHand:
				//config.getHangmanGUI().showRightHand(true);				
				g.drawArc(x + torsoWidth/2 + armWidth //+ handDiameter/2
						, y + headDiameter + torsoHeight/4 - handDiameter/4
						, handDiameter
						, handDiameter, 0, 359);
			
			case victimIdxLeftHand:
				//config.getHangmanGUI().showLeftHand(true);		
				g.drawArc(x - torsoWidth/2 - armWidth - handDiameter
						, y + headDiameter + torsoHeight/4 - handDiameter/4
						, handDiameter
						, handDiameter, 0, 359);
								
			case victimIdxRightLeg:
				g.draw3DRect(x + 5
						   , y + headDiameter + torsoHeight
						   , legWidth
						   , legLength
						   , true);			

			case victimIdxLeftLeg:
				g.draw3DRect(x - (legWidth + 5) /*- legWidth/2*/
						   , y + headDiameter + torsoHeight
						   , legWidth
						   , legLength
						   , true);
						
			
			case victimIdxRightArm:
				g.draw3DRect(x - torsoWidth/2 - armWidth 
						   , y + headDiameter + torsoHeight/4 
				           , armWidth
				           , armHeight
				           , true);
			
			case victimIdxLeftArm:
				g.draw3DRect(x + torsoWidth/2 
						   , y + headDiameter + torsoHeight/4
				           , armWidth
				           , armHeight
				           , true);
				
			case victimIdxTorso:
				g.draw3DRect(x - torsoWidth/2
						   , y + headDiameter
						   , torsoWidth
						   , torsoHeight
						   , true);
				
			case victimIdxHead:
				// first 2 args are the upper left corner of the arc!
				g.drawArc(x - headDiameter/2
						, y 
						, headDiameter
						, headDiameter, 0, 359);
			
			case victimIdxNew:	// No body parts have been hung, yet. 

				break;

			}
//		}	// for (i = 0; i < 2; i++)
	}
	
}
