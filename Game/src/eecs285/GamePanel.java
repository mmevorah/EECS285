package eecs285;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import Networking.ClientServerSocket;

public class GamePanel extends JPanel implements ActionListener {
	
	Timer mainTimer;
	Player localPlayer;
	Player internetPlayer;
	ClientServerSocket network;
	HealthBar localHealthBar;
	HealthBar internetHealthBar;
	
	public GamePanel(ClientServerSocket network) { 
		setFocusable(true);
	
		localPlayer = new Player(100, 100);
		addKeyListener(new KeyAdapt(localPlayer));
		internetPlayer = new Player(100,100);
		
		this.network = network;
		
		this.localHealthBar = new HealthBar(Consts.LOCALHEALTH_X, Consts.LOCALHEALTH_Y);
		this.internetHealthBar = new HealthBar(Consts.INTERNETHEALTH_X, Consts.INTERNETHEALTH_Y);
			
		mainTimer = new Timer(10, this);
		mainTimer.start();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		localPlayer.draw(g2d);
		internetPlayer.draw(g2d);
		localHealthBar.draw(g2d);
		internetHealthBar.draw(g2d);
		
	}
	
	
	//Clock Loop
	public void actionPerformed(ActionEvent arg0) {
		localPlayer.update(true);
		
		if((localPlayer.intersects(internetPlayer)) && (localPlayer.attacking)){
			int tmpHealth = internetPlayer.getHealth();
			int newHealth = tmpHealth-localPlayer.getAttackDamage();
			internetPlayer.setHealth(newHealth);
		}
		if((internetPlayer.intersects(internetPlayer)) && (internetPlayer.attacking)){
			int tmpHealth = localPlayer.getHealth();
			int newHealth = tmpHealth-internetPlayer.getAttackDamage();
			localPlayer.setHealth(newHealth);
		}
		
		PlayerInfo b = new PlayerInfo();
		b.x = localPlayer.getx();
		b.y = localPlayer.gety();
		b.dx = localPlayer.getdx();
		b.dy = localPlayer.getdy();
		b.health = localPlayer.getHealth();
		b.left = localPlayer.left;
		b.right = localPlayer.right;
		b.jumping = localPlayer.jumping;
		b.falling = localPlayer.falling;
		b.attacking = localPlayer.attacking;
		
		network.sendPlayer(b);
		
		PlayerInfo rec = network.recvPlayer();
		internetPlayer.setHealth(rec.health);
		internetPlayer.setPosition(rec.x, rec.y);
		internetPlayer.setVector(rec.dx, rec.dy);
		internetPlayer.setLeft(rec.left);
		internetPlayer.setRight(rec.right);
		internetPlayer.setJumping(rec.jumping);
		internetPlayer.setFalling(rec.falling);
		internetPlayer.setAttacking(rec.attacking);
		
		internetPlayer.update(false);
		
		localHealthBar.set_health(localPlayer.getHealth());
		internetHealthBar.set_health(internetPlayer.getHealth());
			
		repaint();
	}
	
}
