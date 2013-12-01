package eecs285;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import Networking.ClientServerSocket;

public class GamePanel extends JPanel implements ActionListener {
	
	Timer mainTimer;
	Player localPlayer;
	Player internetPlayer;
	ClientServerSocket network;
	
	public GamePanel(ClientServerSocket network) { 
		setFocusable(true);
	
		localPlayer = new Player(100, 100);
		addKeyListener(new KeyAdapt(localPlayer));
		internetPlayer = new Player(100,100);
		
		this.network = network;
			
		mainTimer = new Timer(10, this);
		mainTimer.start();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		localPlayer.draw(g2d);
		internetPlayer.draw(g2d);
	}
	
	
	//Clock Loop
	public void actionPerformed(ActionEvent arg0) {
		localPlayer.update();
		
		PlayerInfo b = new PlayerInfo();
		b.x = localPlayer.getx();
		b.y = localPlayer.gety();
		b.dx = localPlayer.getdx();
		b.dy = localPlayer.getdy();
		b.health = localPlayer.getHealth();
		
		network.sendPlayer(b);
		
		PlayerInfo rec = network.recvPlayer();
		internetPlayer.setHealth(rec.health);
		internetPlayer.setPosition(rec.x, rec.y);
		internetPlayer.setVector(rec.dx, rec.dy);
			
		repaint();
	}
	
	public void setBackGround(JFrame mainframe, ImageIcon image)
	{
		mainframe.setLayout(new BorderLayout());
		JLabel background=new JLabel(image);
		mainframe.add(background);
	}
	
}
