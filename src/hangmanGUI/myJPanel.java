/**
 * Bill Nicholson
 * Little Hangman game
 */
package hangmanGUI;

import java.awt.Graphics;

import javax.swing.JPanel;

public class myJPanel extends JPanel {

	private Config myConfig;
    /**
	 * 
	 */
	private static final long serialVersionUID = -1636029535660955315L;

	public myJPanel(Config myConfig) {
		this.myConfig = myConfig;
	}
	@Override public void paintComponent(Graphics g) {
        super.paintComponent(g);    // paints background
        DrawAssembly(g);
        DrawVictim(g);
   }
	private void DrawAssembly(Graphics g) {
		// Base
		g.drawRect(myConfig.getLeftMargin()
				 , myConfig.getTopMargin()  + myConfig.getPoleHeight() + myConfig.getArmHeight()
				 , myConfig.getBaseWidth()
				 , myConfig.getBaseHeight());

		// Pole
		g.drawRect(myConfig.getLeftMargin() + myConfig.getPoleOffsetFromLeftEdgeOfBase()
				 , myConfig.getTopMargin()  + myConfig.getArmHeight()
				 , myConfig.getPoleWidth()
				 , myConfig.getPoleHeight());
		
		// Arm
		g.drawRect(myConfig.getLeftMargin() + myConfig.getPoleOffsetFromLeftEdgeOfBase()
				 , myConfig.getTopMargin() + myConfig.getArmHeight()
				 , myConfig.getArmWidth()
				 , myConfig.getArmHeight());
		
		// Rope dangling from the arm
		g.drawRect(myConfig.getLeftMargin() + myConfig.getPoleOffsetFromLeftEdgeOfBase() + (int)(myConfig.getArmWidth()*.75) 
				 , myConfig.getTopMargin() + myConfig.getArmHeight()
				 , 1
				 , myConfig.getRopeLength());
		
	}
	private void DrawVictim(Graphics g)
	{
		// Test
		myConfig.setVictimIdx(Config.victimIdxEnum.victimIdxRightLeg);
		
		myConfig.getBody().Draw(g, 
								myConfig.getVictimIdx(),
				                myConfig.getLeftMargin() + myConfig.getPoleOffsetFromLeftEdgeOfBase() + (int)(myConfig.getArmWidth()*.75),
				                myConfig.getTopMargin() + myConfig.getArmHeight()+ myConfig.getRopeLength());
	}
	
}
