package eecs285;

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
