package eecs285;

import java.awt.Graphics2D;

public abstract class Entity {
	int x, y;
	//Holds each object
	public Entity(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public abstract void update();
	public abstract void draw(Graphics2D g2d);
	
}
