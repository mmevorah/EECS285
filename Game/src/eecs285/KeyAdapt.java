package eecs285;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import eecs285.Player;

public class KeyAdapt extends KeyAdapter {
	
	Player p;
	
	public KeyAdapt(Player player) {
		p = player;
	}
	
	public void keyPressed(KeyEvent e) {
		p.keyPressed(e);
	}
	
	public void keyReleased(KeyEvent e) {
		p.keyReleased(e);
	}
}