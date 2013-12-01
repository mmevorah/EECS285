package eecs285;

import java.io.Serializable;

public class PlayerInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int internetHealth = 0;
	public int localHealth = 0;
	public int x = 0;
	public int y = 0;
	public double dx = 0;
	public double dy = 0;
	public boolean left;
	public boolean right;
	public boolean up;
	public boolean down;
	public boolean jumping;
	public boolean falling;
	public boolean attacking;
	
}
