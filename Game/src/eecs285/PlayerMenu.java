package eecs285;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

public class PlayerMenu extends JFrame {
	
	private JButton b1;
	private JButton b2;
	private PlayerListener playerListener; 
	public ImageIcon playerIcon;
	
	public PlayerMenu(String title) {
		super(title);
		b1 = new JButton(new ImageIcon("map1.jpg"));
		b1.setSize(300, 300);
		b2 = new JButton(new ImageIcon("map2.jpg"));
		b2.setSize(300, 300);
		add(b1);
		add(b2);
		b1.setLocation(100, 100);
		b2.setLocation(450, 100);
		JTextField f = new JTextField("Select player:");
		add(f);
		f.setLocation(100, 50);
		playerListener = new PlayerListener();
		b1.addActionListener(playerListener);
		b2.addActionListener(playerListener);
	}

	public class PlayerListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == b1)
			{
				b1.setEnabled(false);
				playerIcon = new ImageIcon("map1.jpg");
				ImageIcPlayer();
				
				// close window
				dispose();
			}
			else if (e.getSource() == b2)
			{
				b2.setEnabled(false);
				playerIcon = new ImageIcon("map2.jpg");
				ImageIcPlayer();
				
				// close window
				dispose();
			}
			else
			{
				// error case should not happen
			}
		}
	}
	
	public ImageIcon ImageIcPlayer() {
		return playerIcon;
	}
}
