package eecs285.chrisWorkSpace;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class GameFrame extends JPanel implements ActionListener {
	
	Timer mainTimer;
	Player player;
	
	public GameFrame() { 
		setFocusable(true);
		player = new Player(100, 100);
		addKeyListener(new KeyAdapt(player));
		mainTimer = new Timer(10, this);
		mainTimer.start();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		player.draw(g2d);
		//g2d.drawString("Start Fight", 100, 100);
		//ImageIcon ic = new ImageIcon("Desktop/MortalKombat/Player/player.png");
		//Image i = ic.getImage();
		//g2d.drawImage(i, 500, 200, null);
	}
	
	public void actionPerformed(ActionEvent arg0) {
		player.update();
		repaint();
	}
}