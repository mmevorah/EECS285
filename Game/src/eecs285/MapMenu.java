package eecs285;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

public class MapMenu extends JFrame {
	
	private JButton b1;
	private JButton b2;
	private MapListener mapListener; 
	public ImageIcon mapIcon;
	public MapMenu(String title) {
		super(title);
		b1 = new JButton(new ImageIcon("map1.jpg"));
		b1.setSize(300, 300);
		b2 = new JButton(new ImageIcon("map2.jpg"));
		b2.setSize(300, 300);
		add(b1);
		add(b2);
		b1.setLocation(100, 100);
		b2.setLocation(450, 100);
		JTextField f = new JTextField("Select map:");
		add(f);
		f.setLocation(100, 50);
		mapListener = new MapListener();
		b1.addActionListener(mapListener);
		b2.addActionListener(mapListener);
	}

	public void close() {
		WindowEvent winClosingEvent = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
	}
	
	public class MapListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == b1)
			{
				setVisible(true);
				b1.setEnabled(false);
				mapIcon = new ImageIcon("map1.jpg");
				ImageIcMap();
				//close
				dispose();
				
				// communicate selection to board
				// must set map Image here
				
				// open Player Menu
				PlayerMenu play = new PlayerMenu("Player Menu");
				play.setSize(800, 600);
				play.setVisible(true);
				play.setResizable(true);
				play.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				
			}
			else if (e.getSource() == b2)
			{
				b2.setEnabled(false);
				mapIcon = new ImageIcon("map2.jpg");
				ImageIcMap();
				
				PlayerMenu play = new PlayerMenu("Player Menu");
				play.setSize(800, 600);
				play.setVisible(true);
				play.setResizable(true);
				play.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				//close();
				dispose();
				

			}
			else
			{
				// error case should not happen
			}
		}
	}
	
	public ImageIcon ImageIcMap() {
		return mapIcon;
	}
	
}