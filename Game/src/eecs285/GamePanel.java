package eecs285;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import eecs285.KeyAdapt;
import eecs285.Player;

public class GamePanel extends JPanel implements ActionListener {
	
	Timer mainTimer;
	Player localPlayer;
	Player internetPlayer;
	
	public GamePanel() { 
		setFocusable(true);
	
		localPlayer = new Player(75, 175);	
		addKeyListener(new KeyAdapt(localPlayer));
			
		mainTimer = new Timer(10, this);
		mainTimer.start();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		localPlayer.draw(g2d);
	}
	
	public void actionPerformed(ActionEvent arg0) {
		localPlayer.update();
		repaint();
	}
	
}