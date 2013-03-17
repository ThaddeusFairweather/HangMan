/**
 * Bill Nicholson
 * Little Hangman game
 * It's fun, really!
 */
package hangmanGUI;

import java.awt.Color;
import java.awt.Graphics;

public class Body {
	private int headCircumference;
	private int torsoHeight, torsoWidth;
	private int armWidth, armHeight;
	private int legWidth, legLength;
	
	public Body() {
		headCircumference = 40;
		torsoHeight = 40;
		torsoWidth = 20;
		armWidth = 30;
		armHeight = 10;
		legWidth = (torsoWidth/2) - 2;
		legLength = 35;
	}
	
	public int getHeadCircumference(){ return headCircumference;}
	public void SetHeadCircumference(int headCircumference){ this.headCircumference = headCircumference;}
	
	/**
	 * Draw the body
	 * @param g Graphics context
	 * @param victimIdxEnum how much of the body to draw. See Config.victimIdxEnum
	 * @param x the x co-ordinate of the top of the head
	 * @param y the y co-ordinate of the top of the head
	 */
	public void Draw(Graphics g, Config.victimIdxEnum victimIdx, int x, int y){
		Config.victimIdxEnum victimIdxTmp;
		for (int i = 0; i < 2; i++) {
			// The first we will erase everything. 
			// The second pass we will draw everything.
			if (i == 0) {
				victimIdxTmp = Config.victimIdxEnum.victimIdxLeftLeg;
				g.setColor(Color.white); 
			} else {
				victimIdxTmp = victimIdx;
				g.setColor(Color.black);
			}

			// List the body parts 'backward' so each case falls through to the next case.
			switch (victimIdxTmp) {
			
			case victimIdxRightLeg:
				g.draw3DRect(x + 5
						   , y + headCircumference + torsoHeight
						   , legWidth
						   , legLength
						   , true);			

			case victimIdxLeftLeg:
				g.draw3DRect(x - (legWidth + 5) /*- legWidth/2*/
						   , y + headCircumference + torsoHeight
						   , legWidth
						   , legLength
						   , true);
						
			
			case victimIdxLeftArm:
				g.draw3DRect(x - torsoWidth/2 - armWidth 
						   , y + headCircumference + torsoHeight/4 
				           , armWidth
				           , armHeight
				           , true);
			
			case victimIdxRightArm:
				g.draw3DRect(x + torsoWidth/2 
						   , y + headCircumference + torsoHeight/4
				           , armWidth
				           , armHeight
				           , true);
				
			case victimIdxTorso:
				g.draw3DRect(x - torsoWidth/2
						   , y + headCircumference
						   , torsoWidth
						   , torsoHeight
						   , true);
				
			case victimIdxHead:
				// first 2 args are the upper left corner of the arc!
				g.drawArc(x - headCircumference/2
						, y 
						, headCircumference
						, headCircumference, 0, 359);
			
			
			case victimIdxNew:	// No body parts have been hung, yet. 
				
				break;
		
			}
		}	// for (i = 0; i < 2; i++)
	}
	
}
