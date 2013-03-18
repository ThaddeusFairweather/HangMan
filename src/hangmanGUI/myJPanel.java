/**
 * Bill Nicholson
 * nicholdw@ucmail.uc.edu
 * Little Hangman game
 */
package hangmanGUI;

import java.awt.Graphics;

import javax.swing.JPanel;

public class myJPanel extends JPanel {

	private Config config;
    /**
	 * 
	 */
	private static final long serialVersionUID = -1636029535660955315L;

	public myJPanel(Config myConfig) {
		this.config = myConfig;
	}
	@Override public void paintComponent(Graphics g) {
        super.paintComponent(g);    // paints background
        DrawAssembly(g);
        DrawVictim(g);
   }
	private void DrawAssembly(Graphics g) {
		// Base    
		g.drawRect(config.getLeftMargin()
				 , config.getTopMargin()  + config.getPoleHeight() + config.getArmHeight()
				 , config.getBaseWidth()
				 , config.getBaseHeight());

		// Pole
		g.drawRect(config.getLeftMargin() + config.getPoleOffsetFromLeftEdgeOfBase()
				 , config.getTopMargin()  + config.getArmHeight()
				 , config.getPoleWidth()
				 , config.getPoleHeight());
		
		// Arm
		g.drawRect(config.getLeftMargin() + config.getPoleOffsetFromLeftEdgeOfBase()
				 , config.getTopMargin() + config.getArmHeight()
				 , config.getArmWidth()
				 , config.getArmHeight());
		
		// Rope dangling from the arm
		g.drawRect(config.getLeftMargin() + config.getPoleOffsetFromLeftEdgeOfBase() + (int)(config.getArmWidth()*.75) 
				 , config.getTopMargin() + config.getArmHeight()
				 , 1
				 , config.getRopeLength());
		
	}
	private void DrawVictim(Graphics g)
	{	
		config.getBody().Draw(g, 
							  config.getLeftMargin() + config.getPoleOffsetFromLeftEdgeOfBase() + (int)(config.getArmWidth()*.75),
				              config.getTopMargin() + config.getArmHeight()+ config.getRopeLength());
	}
	
}
