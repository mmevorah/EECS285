package eecs285;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Player extends Entity {
	
	public int playerType;
	
	
	
	// player stuff
	private int health;
	private boolean dead;
	
	// scratch
	public boolean attacking;
	private int attackDamage;
	private int attackRange;
	
	public boolean attack;

	// animations
	private ArrayList<BufferedImage[]> sprites;

	// animation actions
	private static final int IDLE = 0;
	private static final int WALKING = 1;
	private static final int FALLING = 2;
	private static final int ATTACKING = 3;
	private static final int JUMPING = 4;
	
	public Player(int x, int y, int ptype) {
		
		super(x, y);
		
		this.playerType = ptype;
		
		if(ptype == 1){
			width = 70;
			height = 120;
			cwidth = 70;
			cheight = 140;	
		}else{
			width = 100;
			height = 150;
			cwidth = 100;
			cheight = 150;
		}
		
		
		moveSpeed = 0.5;
		maxSpeed = 2;
		stopSpeed = 0.4;
		fallSpeed = 0.15;
		maxFallSpeed = 4.0;
		jumpStart = -9;
		stopJumpSpeed = 0.3;
		
		facingRight = true;
		
		health = 100;
		
		attackDamage = 5;
		attackRange = 40;
		
		attack = false;
		
		// load sprites
		try {
			if(playerType == 1){
				sprites = new ArrayList<BufferedImage[]>();

				BufferedImage spritesheeti = ImageIO.read(getClass().getResourceAsStream("/eecs285/graphics/idle-01.png"));
				BufferedImage[] bi = new BufferedImage[2];
				bi[0] = spritesheeti.getSubimage(0, 0, 70, 119);
				bi[1] = spritesheeti.getSubimage(70, 0, 70, 119);
				sprites.add(bi);

				BufferedImage spritesheetw = ImageIO.read(getClass().getResourceAsStream("/eecs285/graphics/walking-01.png"));
				BufferedImage[] bw = new BufferedImage[1];
				bw[0] = spritesheetw.getSubimage(0, 0, 100, 119);
				sprites.add(bw);
				
				BufferedImage[] bf = new BufferedImage[1];
				sprites.add(bf);
				
				BufferedImage spritesheeta = ImageIO.read(getClass().getResourceAsStream("/eecs285/graphics/attacking-01.png"));
				BufferedImage[] ba = new BufferedImage[3];
				ba[0] = spritesheeta.getSubimage(0, 0, 70, 119);
				ba[1] = spritesheeta.getSubimage(70, 0, 110, 119);
				ba[2] = spritesheeta.getSubimage(180, 0, 70, 119);
				sprites.add(ba);
				
				BufferedImage spritesheetj = ImageIO.read(getClass().getResourceAsStream("/eecs285/graphics/jumping-01.png"));
				BufferedImage[] bjump = new BufferedImage[3];
				bjump[0] = spritesheetj.getSubimage(0, 0, 70, 119);
				bjump[1] = spritesheetj.getSubimage(70, 0, 70, 119);
				bjump[2] = spritesheetj.getSubimage(140, 0, 70, 119);
				sprites.add(bjump);

			}else{
				 int[] numFrames = {
						6, 6, 6, 6, 3
					};
				sprites = new ArrayList<BufferedImage[]>();
				BufferedImage spritesheet = ImageIO.read(
						getClass().getResourceAsStream(
							"/eecs285/graphics/sprites.png"
						
						)
					);
					
					sprites = new ArrayList<BufferedImage[]>();
					for(int i = 0; i < 5; i++) {
						
						BufferedImage[] bi =
							new BufferedImage[numFrames[i]];
						
						for(int j = 0; j < numFrames[i]; j++) {
							
							if(i != 3) {
								bi[j] = spritesheet.getSubimage(
										j * 100,
										i * 150,
										100,
										150
								);
							}
							/*else if(i == 1) {
								bi[j] = spritesheet.getSubimage(
										j * width,
										i * height,
										width,
										height
								);
							}
							else if(i == 2) {
								bi[j] = spritesheet.getSubimage(
										j * width,
										i * height,
										width,
										height
								);
							}*/
							else {
								bi[j] = spritesheet.getSubimage(
										j * 150,
										i * 150,
										100,
										150
								);
							}

						}
						
						sprites.add(bi);
						
					}

			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		animation = new Animation();
		currentAction = IDLE;
		animation.setFrames(sprites.get(IDLE));
		animation.setDelay(400);
		
	}
	
	
	public int getHealth() { 
		return health; 
	}
	
	public void setHealth(int h){
		health = h;
	}
	
	
	public void setAttacking(boolean s) {
		attacking = s;
	}

	private void getNextPosition() {
		
		// movement
		if(left) {
			dx -= moveSpeed;
			if(dx < -maxSpeed) {
				dx = -maxSpeed;
			}
		}
		else if(right) {
			dx += moveSpeed;
			if(dx > maxSpeed) {
				dx = maxSpeed;
			}
		}
		else {
			if(dx > 0) {
				dx -= stopSpeed;
				if(dx < 0) {
					dx = 0;
				}
			}
			else if(dx < 0) {
				dx += stopSpeed;
				if(dx > 0) {
					dx = 0;
				}
			}
		}
		
		// cannot move while attacking, except in air
		if(currentAction == ATTACKING) {
			dx = 0;
		}
		
		// jumping
		if(jumping && !falling) {
			dy = jumpStart;
			falling = true;
		}	
		// falling
		if(falling) {
			
			if(dy > 0){
				dy += fallSpeed * 0.1;
			}else{
				dy += fallSpeed;
			}
			
			if(dy > 0) 	jumping = false;

			
			if(dy < 0 && !jumping){
				dy += stopJumpSpeed;
			}
			
			if(dy > maxFallSpeed){
				dy = maxFallSpeed;
			}
			
		}
		
	}
	
	public void update(boolean isLocal) {
		
		// update position
		if(isLocal){
			getNextPosition();	
			checkCollision();		
			setPosition(xtemp, ytemp);
		}
		
		// set animation
		if(attacking) {
			if(currentAction != ATTACKING) {
				currentAction = ATTACKING;
				animation.setFrames(sprites.get(ATTACKING));
				animation.setDelay(50);
				if(this.playerType == 1){
					width = 70;
				}else{
					width = 100;
				}
			}
		}else if(dy < 0) {
			if(currentAction != JUMPING) {
				currentAction = JUMPING;
				animation.setFrames(sprites.get(JUMPING));
				animation.setDelay(-1);
				if(this.playerType == 1){
					width = 70;
				}else{
					width = 100;
				}			}
		}
		else if(left || right) {
			if(currentAction != WALKING) {
				currentAction = WALKING;
				animation.setFrames(sprites.get(WALKING));
				animation.setDelay(100);
				if(this.playerType == 1){
					width = 70;
				}else{
					width = 100;
				}			}
		}
		else {
			if(currentAction != IDLE) {
				currentAction = IDLE;
				animation.setFrames(sprites.get(IDLE));
				animation.setDelay(400);
				if(this.playerType == 1){
					width = 70;
				}else{
					width = 100;
				}			}
		}
		
		animation.update();
		
		// set direction
		if(currentAction != ATTACKING) {
			if(right) facingRight = true;
			if(left) facingRight = false;
		}
		
	}
	
	public void draw(Graphics2D g) {
		
		setMapPosition();	
		if(facingRight) {
			g.drawImage(
				animation.getImage(),
				(int)(x + xmap - width / 2),
				(int)(y + ymap - height / 2),
				null
			);
		}
		else {
			g.drawImage(
				animation.getImage(),
				(int)(x + xmap - width / 2 + width),
				(int)(y + ymap - height / 2),
				-width,
				height,
				null
			);
			
		}
		
	}
	
	public int getAttackDamage(){
		return attackDamage;
	}
	
	public boolean attacked = false;
	public boolean getAt(){
		return attacked;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_UP) {
			setJumping(true);
		} else if(key == KeyEvent.VK_LEFT) {
			setLeft(true);
		} else if(key == KeyEvent.VK_RIGHT) {
			setRight(true);
		} else if(key == KeyEvent.VK_SHIFT){
			setAttacking(true);
			attack = true;
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_UP) {
			setJumping(false);
		} else if(key == KeyEvent.VK_LEFT) {
			setLeft(false);
		} else if(key == KeyEvent.VK_RIGHT) {
			setRight(false);
		} else if(key == KeyEvent.VK_SHIFT){
			setAttacking(false);
			attack = false;
		}
	}
}
