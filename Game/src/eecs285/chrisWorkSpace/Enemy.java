package eecs285.chrisWorkSpace;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Enemy extends Entity {
	
	public Enemy(int x, int y) {
		super(x, y);
		
	}
	
	public void update() {
		
	}
	
	public void draw(Graphics2D g2d) {
		g2d.drawImage(getEnemyImg(), x, y, null);
		//g2d.draw(getBounds());
	}
	
	public Image getEnemyImg() {
		ImageIcon ic = new ImageIcon(getClass().getResource("/Test.jpeg"));
		return ic.getImage();
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, getEnemyImg().getWidth(null), 
				getEnemyImg().getHeight(null));
		
	}
}
