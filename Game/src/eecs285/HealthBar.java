package eecs285;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;

import javax.swing.JApplet;

public class HealthBar extends JApplet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void initialize() 
	{
		setBackground(Color.white);
		setForeground(Color.white);
	}
	
	public void paint(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		        RenderingHints.VALUE_ANTIALIAS_ON);

		    g2.setPaint(Color.gray);
		    int x = 1;
		    int y = 5;

		    g2.setPaint(Color.green);
		    //the third dimension here is current health
		    //I just set it at 100 to see if it would
		    //work
		    g2.fill(new Rectangle2D.Double(x, y, 100, 100));
		    g2.setPaint(Color.black);
		
	}
}
