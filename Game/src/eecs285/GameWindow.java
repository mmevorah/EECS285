package eecs285;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Actual game window. Its a view controller that draws everything within the
 * game view through the updateView method.
 * 
 * @author markmevorah
 * 
 */
public class GameWindow extends JFrame {

	Player localPlayer;
	Player opponentPlayer;
	
	ImageIcon backgroundImage;
	
	public GameWindow() {
		
		JLabel backgroundLabel;
		try {
			File backgroundFile = new File("src/eecs285/graphics/background.jpg");
			backgroundImage = new ImageIcon(ImageIO.read(backgroundFile));
			backgroundLabel = new JLabel(backgroundImage);
			setContentPane(backgroundLabel);
		} catch (IOException e) {
			e.printStackTrace();
		}
			
		setTitle("Internet Fighter");
		setSize(1000, 800);
		setResizable(false);
		
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
