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

	public GameWindow() {
		// super("Game");
		setTitle("Game");
		setSize(200, 200);

		setVisible(true);
	}

	public void updateView() {

		// Update Character Positions
		// Update status stuff ie. health

	}

}
