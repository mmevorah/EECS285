package eecs285;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
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
	
	BufferedImage bimg;
	
	public GamePanel(ClientServerSocket network, int pType) { 
		setFocusable(true);
	
		localPlayer = new Player(400, 100, pType);
		
		addKeyListener(new KeyAdapt(localPlayer));
		
		int blah = 1;
		if(pType == 1){
			blah = 2;
		}
		internetPlayer = new Player(400,100, blah);		
		
		this.network = network;
		
		this.localHealthBar = new HealthBar(Consts.LOCALHEALTH_X, Consts.LOCALHEALTH_Y, true);
		this.internetHealthBar = new HealthBar(Consts.INTERNETHEALTH_X, Consts.INTERNETHEALTH_Y, false);
				
		try {
			this.bimg = ImageIO.read(new File(Consts.MAP1_FILEPATH));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		mainTimer = new Timer(10, this);
		mainTimer.start();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(bimg, null, 0, -20);
		localPlayer.draw(g2d);
		internetPlayer.draw(g2d);
		localHealthBar.draw(g2d);
		internetHealthBar.draw(g2d);
	}
	
	
	//Clock Loop
	public void actionPerformed(ActionEvent arg0) {
		localPlayer.update(true);
				
	
		if((localPlayer.intersects(internetPlayer)) && (localPlayer.attack)){			
			
			int tmpHealth = internetPlayer.getHealth();
			int newHealth = tmpHealth-localPlayer.getAttackDamage();
			
			System.out.println("oldh:"+tmpHealth+" newh:"+newHealth);
			internetPlayer.setHealth(newHealth);
			
			localPlayer.attack = false;
		}
		
		if(internetPlayer.getHealth() == 0){
			JOptionPane.showMessageDialog(null, "You Win!", "Game Over",
                    JOptionPane.INFORMATION_MESSAGE);
            System.exit(4);
		}
		if(localPlayer.getHealth() == 0){
			JOptionPane.showMessageDialog(null,"You Lose!", "Game Over",
                    JOptionPane.INFORMATION_MESSAGE);
            System.exit(4);
		}
		
		PlayerInfo b = new PlayerInfo();
		b.x = localPlayer.getx();
		b.y = localPlayer.gety();
		b.dx = localPlayer.getdx();
		b.dy = localPlayer.getdy();
		b.left = localPlayer.left;
		b.right = localPlayer.right;
		b.jumping = localPlayer.jumping;
		b.falling = localPlayer.falling;
		b.attacking = localPlayer.attacking;
		
		b.internetHealth = localPlayer.getHealth();
		b.localHealth = internetPlayer.getHealth();
		
		network.sendPlayer(b);
		
		PlayerInfo rec = network.recvPlayer();
		
		localPlayer.setHealth(rec.localHealth);
		internetPlayer.setHealth(rec.internetHealth);
		
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
