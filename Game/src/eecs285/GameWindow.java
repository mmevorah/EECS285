package eecs285;

import javax.swing.JFrame;

/**
 * Actual game window. Its a view controller that draws everything within the
 * game view through the updateView method.
 * 
 * @author markmevorah
 * 
 */
public class GameWindow extends JFrame {

	//Player localPlayer
	//Player opponentPlayer
	
	public GameWindow() {
		setSize(200, 200);

		
		//Format game window
		///Size is a medium to large window
		///Set title to game title (pending)
		///Set background to an image
		
		setVisible(true);
	}

	public void updateView() {

		//look at player class to get exact names of methods
		
		//localPlayer.getImage();
		//self.displayImageAt( localPlayer.getXCoord() , localPlayer.getYCoord() )
		//same thing above for opponent player
		
		//get health localPlayer.getHealth()
		//update localPlayerHealthBar
		//same thing for opponent player
		
		
	}

}
