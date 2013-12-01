package eecs285;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JApplet;
import javax.swing.JFrame;


public class Main {

	public static void main(String[] args){
		//IP Frame stuff
		
		JFrame blah = new JFrame();
		IpFrame ipFrame = new IpFrame(blah, "IP Exchange");
		String ip = ipFrame.get_input();
		Game game = new Game(ip);
		
	}
	
}
