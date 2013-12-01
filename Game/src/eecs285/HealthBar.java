package eecs285;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;

public class HealthBar{
	
	/**
	 * 
	 */
	private int MAX_HEALTH = 100;
	private int health = 100;
	private static final long serialVersionUID = 1L;
	
	private static final double SCALE = 2.5;

	private int x;
	private int y;
	private boolean local;
	
	public HealthBar(int x, int y, boolean local) {
		this.x = x;
		this.y = y;
		this.local = local;
	
	}
	
	public void set_health(int setter)
	{
		health = setter;
	}
	
	public void draw(Graphics2D g2)
	{
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		        RenderingHints.VALUE_ANTIALIAS_ON);

		    g2.setPaint(Color.gray);

		    //the third dimension here is current health
		    //I just set it at 100 to see if it would
		    //work
		    g2.setPaint(Color.black);
		    g2.fill(new Rectangle2D.Double(x- 10, y - 10, MAX_HEALTH * SCALE + 20, 60));
		    
		    g2.setPaint(Color.green);
		    g2.fill(new Rectangle2D.Double(x, y, health * SCALE, 40));
		    
		    g2.setPaint(Color.white);
		    
		    g2.setFont(new Font("default", Font.BOLD, 16));
		    if(local){
		    	g2.drawString("YOU", x + 5, y + 25);
		    }else{
		    	g2.drawString("THEM", x + 5, y + 25);
		    }
		    
		    
	}
	
	
}
