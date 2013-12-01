package eecs285;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Player extends Entity {
	// player stuff
	private int health;
	private boolean dead;
	
	// scratch
	public boolean attacking;
	private int attackDamage;
	private int attackRange;

	// animations
	private ArrayList<BufferedImage[]> sprites;
	private final int[] numFrames = {
		2, 8, 1, 2, 4, 2, 5
	};
	
	// animation actions
	private static final int IDLE = 0;
	private static final int WALKING = 1;
	private static final int JUMPING = 2;
	private static final int FALLING = 3;
	private static final int ATTACKING = 6;
	
	public Player(int x, int y) {
		
		super(x, y);
		
		width = 30;
		height = 30;
		cwidth = 80;
		cheight = 200;
		
		moveSpeed = 0.3;
		maxSpeed = 1.6;
		stopSpeed = 0.4;
		fallSpeed = 0.15;
		maxFallSpeed = 4.0;
		jumpStart = -4.8;
		stopJumpSpeed = 0.3;
		
		facingRight = true;
		
		health = 100;
		
		attackDamage = 5;
		attackRange = 40;
		
		// load sprites
		try {
			
			BufferedImage spritesheet = ImageIO.read(
				getClass().getResourceAsStream(
					"/eecs285/graphics/playersprites.gif"
				
				)
			);
			
			sprites = new ArrayList<BufferedImage[]>();
			for(int i = 0; i < 7; i++) {
				
				BufferedImage[] bi =
					new BufferedImage[numFrames[i]];
				
				for(int j = 0; j < numFrames[i]; j++) {
					
					if(i != 6) {
						bi[j] = spritesheet.getSubimage(
								j * width,
								i * height,
								width,
								height
						);
					}
					else {//ATTACKING
						bi[j] = spritesheet.getSubimage(
								j * width * 2,
								i * height,
								width,
								height
						);
					}
					
				}
				
				sprites.add(bi);
				
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
				width = 60;
			}
		}
		if(dy > 0) {	
			if(currentAction != FALLING) {
				currentAction = FALLING;
				animation.setFrames(sprites.get(FALLING));
				animation.setDelay(100);
				width = 30;
			}
		}
		else if(dy < 0) {
			if(currentAction != JUMPING) {
				currentAction = JUMPING;
				animation.setFrames(sprites.get(JUMPING));
				animation.setDelay(-1);
				width = 30;
			}
		}
		else if(left || right) {
			if(currentAction != WALKING) {
				currentAction = WALKING;
				animation.setFrames(sprites.get(WALKING));
				animation.setDelay(40);
				width = 30;
			}
		}
		else {
			if(currentAction != IDLE) {
				currentAction = IDLE;
				animation.setFrames(sprites.get(IDLE));
				animation.setDelay(400);
				width = 30;
			}
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
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_W) {
			setJumping(true);
		} else if(key == KeyEvent.VK_A) {
			setLeft(true);
		} else if(key == KeyEvent.VK_D) {
			setRight(true);
		} else if(key == KeyEvent.VK_SHIFT){
			setAttacking(true);
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_W) {
			setJumping(false);
		} else if(key == KeyEvent.VK_A) {
			setLeft(false);
		} else if(key == KeyEvent.VK_D) {
			setRight(false);
		} else if(key == KeyEvent.VK_SHIFT){
			setAttacking(false);
		}
	}
}
