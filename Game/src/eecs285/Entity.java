package eecs285;

import java.awt.Rectangle;

public abstract class Entity {
	// tile stuff
	Entity[][] tiles;
	private int[][] map;
	
	//protected TileMap tileMap;
	//protected int tileSize = ;
	protected double xmap;
	protected double ymap;
	
	// position and vector
	protected double x;
	protected double y;
	
	//direction the object is going
	protected double dx;
	protected double dy;
	
	// dimensions - for reading in sprite sheets
	protected int width;
	protected int height;
	
	// real dimensions
	protected int cwidth;
	protected int cheight;
	
	// collision
	protected int currRow;
	protected int currCol;
	protected double xdest;
	protected double ydest;
	protected double xtemp;
	protected double ytemp;
	protected boolean topLeft;
	protected boolean topRight;
	protected boolean bottomLeft;
	protected boolean bottomRight;
	
	// animation
	protected Animation animation;
	protected int currentAction;
	protected int previousAction;
	protected boolean facingRight;
	
	// movement
	protected boolean left;
	protected boolean right;
	protected boolean up;
	protected boolean down;
	protected boolean jumping;
	protected boolean falling;
	
	// movement attributes - physics
	protected double moveSpeed;
	protected double maxSpeed;
	protected double stopSpeed;
	protected double fallSpeed;
	protected double maxFallSpeed;
	protected double jumpStart;
	protected double stopJumpSpeed;

	// constructor - holds each object
	public Entity(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	//Used for fighting
	public boolean intersects(Entity o) {
		Rectangle r1 = getRectangle();
		Rectangle r2 = o.getRectangle();
		return r1.intersects(r2);
	}
	public Rectangle getRectangle() {
		return new Rectangle(
				(int)x - cwidth,
				(int)y - cheight,
				cwidth,
				cheight
		);
	}

	
	private void screenBounds() {
		if(x <= Consts.LEFT_BOUND) {
			x = Consts.LEFT_BOUND;
		}
		if(x >= Consts.RIGHT_BOUND) {
			x = Consts.RIGHT_BOUND;
		}
		if(y <= Consts.TOP_BOUND) {
			y = Consts.TOP_BOUND; 
		}
		if(y >= Consts.BOTTOM_BOUND) {
			y = Consts.BOTTOM_BOUND;
		}
	}
	
	public void checkTileMapCollision() {
		screenBounds();
		currCol = (int)x / Consts.RIGHT_BOUND;
		currRow = (int)y / Consts.BOTTOM_BOUND;
		
		xdest = x + dx;
		ydest = y + dy;
		
		xtemp = x;
		ytemp = y;
		
		if(dy < 0) {
			if((x == 0 && ydest == 0) || (x == 750 && ydest == 0)) {
				dy = 0;
				ytemp = currRow * 555 + cheight / 2;
			}
			else {
				ytemp += dy;
			}
		}
		
		System.out.println("dy:"+dy);
		if(dy > 0) {
			//bottomLeft || bottomRight
			if((x == 0 && ydest == 555) || (x == 750 && ydest == 555)) {
				dy = 0;
				falling = false;
				ytemp = (currRow + 1) * 555 - cheight / 2;
			}
			else {
				if(ydest == Consts.BOTTOM_BOUND){
					System.out.println("Hit Bottom");
					falling = false;
				}
				ytemp += dy;
			}
		}
		
		if(dx < 0) {
			//topLeft || bottomLeft
			if((xdest == 0 && y == 0) || (xdest == 0 && y == 555)) {
				dx = 0;
				xtemp = currCol * 750 + cwidth / 2;
			}
			else {
				xtemp += dx;
			}
		}
		if(dx > 0) {
			//topRight || bottomRight
			if((xdest == 800 && y == 0) || (xdest == 800 && y == 555)) {
				dx = 0;
				xtemp = (currCol + 1) * 750 - cwidth / 2;
			}
			else {
				xtemp += dx;
			}
		}
		
		if(!falling) {
			//System.out.println("Not Falling");
			//!bottomLeft || !bottomRight
			if((x < 0 && ydest + 1 > 555) || !(x > 750 && ydest + 1 > 555)) {
				falling = true;
			}
		}
		
	}
	
	public int getx() { 
		return (int)x; 
	}
	
	public void setx(double x){
		this.x = x; 
	}
	
	public int gety() { 
		return (int)y; 
	}
	
	public void sety(double y){
		this.y = y;
	}
	
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	public int getCWidth() { return cwidth; }
	public int getCHeight() { return cheight; }
	
	public void setPosition(double x, double y) {
		this.x = x;
		this.y = y;
		//System.out.println("x: " + x + " y: " + y);
	}
	public void setVector(double dx, double dy) {
		this.dx = dx;
		this.dy = dy;
	}
	public double getdx(){
		return this.dx;
	}
	public double getdy(){
		return this.dy;
	}
	
	public void setMapPosition() {
		//System.out.println("Update");
		//getx();
		//gety();
		
	}
	
	public void setLeft(boolean b) { left = b; }
	public void setRight(boolean b) { right = b; }
	public void setUp(boolean b) { up = b; }
	public void setDown(boolean b) { down = b; }
	public void setJumping(boolean b) { jumping = b; }
	
	public boolean notOnScreen() {
		System.out.println("GamePanel width: " + GamePanel.WIDTH + "GamePanel height: " + GamePanel.HEIGHT);
		return x + width < 0 ||
			x - width > GamePanel.WIDTH ||
			y + height < 0 ||
			y - height > GamePanel.HEIGHT;
	}
}