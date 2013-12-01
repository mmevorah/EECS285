package eecs285;

import java.awt.Graphics2D;
import java.io.Serializable;

public abstract class Entity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int x, y;
	//Holds each object
	public Entity(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public abstract void update();
	public abstract void draw(Graphics2D g2d);
	
}
