package eecs285.chrisWorkSpace;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Player extends Entity {

	int velX = 0, velY = 0;
	
	public Player(int x, int y) {
		super(x, y);
	}
	
	public void update() {
		y += velY;
		x += velX;
	}
	public void draw(Graphics2D g2d) {
		//Gets player image and coordinates
		g2d.drawImage(getPlayerImg(), x, y, null);
	}
	
	//Returns the updated player image
	public Image getPlayerImg() {
		ImageIcon ic = new ImageIcon(getClass().getResource("/Test.jpeg"));
		return ic.getImage();
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_UP) {
			velY = -2;
		} else if(key == KeyEvent.VK_DOWN) {
			velY = 2;
		} else if(key == KeyEvent.VK_LEFT) {
			velX = -2;
		} else if(key == KeyEvent.VK_RIGHT) {
			velX = 2;
		}
	}
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_UP) {
			velY = 0;
		} else if(key == KeyEvent.VK_DOWN) {
			velY = 0;
		} else if(key == KeyEvent.VK_LEFT) {
			velX = 0;
		} else if(key == KeyEvent.VK_RIGHT) {
			velX = 0;
		}
	}
}
