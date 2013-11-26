package eecs285.chrisWorkSpace;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyAdapt extends KeyAdapter {
	
	Player p;
	
	public KeyAdapt(Player player) {
		p = player;
	}
	
	public void keyPressed(KeyEvent e) {
		p.keyPressed(e);
	}
	public void KeyReleased(KeyEvent e) {
		p.KeyReleased(e);
	}
}
