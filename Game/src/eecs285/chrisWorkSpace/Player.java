package eecs285.chrisWorkSpace;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Player extends Entity {

	int velX = 0, velY = 0;
	int speed = 2;
	public Player(int x, int y) {
		super(x, y);
	}
	
	public void update() {
		y += velY;
		x += velX;
		
		checkCollisions();
	}
	public void draw(Graphics2D g2d) {
		//Gets player image and coordinates
		g2d.drawImage(getPlayerImg(), x, y, null);
		//g2d.draw(getBounds());
	}
	
	//Returns the updated player image
	public Image getPlayerImg() {
		ImageIcon ic = new ImageIcon(getClass().getResource("/Test.jpeg"));
		return ic.getImage();
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_W) {
			velY = -speed;
		} else if(key == KeyEvent.VK_S) {
			velY = speed;
		} else if(key == KeyEvent.VK_A) {
			velX = -speed;
		} else if(key == KeyEvent.VK_D) {
			velX = speed;
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_W) {
			velY = 0;
		} else if(key == KeyEvent.VK_S) {
			velY = 0;
		} else if(key == KeyEvent.VK_A) {
			velX = 0;
		} else if(key == KeyEvent.VK_D) {
			velX = 0;
		}
	}
	
	public void checkCollisions() {
		ArrayList<Enemy> enemies = GameFrame.getEnemylist();
		for (int i = 0; i < enemies.size(); i++) {
			Enemy tempEnemy = enemies.get(i);
			if (getBounds().intersects(enemies.get(i).getBounds())) {
				GameFrame.removeEnemy(tempEnemy);
			}
		}
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, getPlayerImg().getWidth(null), 
				getPlayerImg().getHeight(null));
		
	}
}
