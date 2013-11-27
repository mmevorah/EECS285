package eecs285.chrisWorkSpace;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class GameFrame extends JPanel implements ActionListener {
	
	Timer mainTimer;
	Player player;
	int enemyCount = 5;
	static ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	Random rand = new Random();
	public GameFrame() { 
		setFocusable(true);
	
		//x and y coor of the initial player
		player = new Player(100, 100);
		
		addKeyListener(new KeyAdapt(player));
		mainTimer = new Timer(10, this);
		mainTimer.start();
		
		for (int i = 0; i < enemyCount; i++) {
			addEnemy(new Enemy(rand.nextInt(800), rand.nextInt(600)));
		}
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		player.draw(g2d);
		for (int i = 0; i < enemies.size(); i++) {
			Enemy tempEnemy = enemies.get(i);
			tempEnemy.draw(g2d);
		}
		//g2d.drawString("Start Fight", 100, 100);
		//ImageIcon ic = new ImageIcon("Player.png");
		//g2d.drawImage(i, 500, 200, null);
	}
	
	public void actionPerformed(ActionEvent arg0) {
		player.update();
		repaint();
		//System.out.println("Timer fires");
	}
	
	public void addEnemy(Enemy e) {
		enemies.add(e);
	}
	
	public static void removeEnemy(Enemy e) {
		enemies.remove(e);
	}
	public static ArrayList<Enemy> getEnemylist() {
		return enemies;
	}
	
}