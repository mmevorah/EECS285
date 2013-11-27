package eecs285.chrisWorkSpace;

import javax.swing.JFrame;

public class Main {
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Mortal Kombat");
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(new GameFrame());
		frame.setVisible(true);
	}
}
