package eecs285;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Networking.ClientServerSocket;

public class Game {

	private Player localPlayer;
	
	public Game(String ipInfo){
		ClientServerSocket network = null;
		try{
			network = initNetworkSocket(ipInfo);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		JFrame gameWindow = new JFrame();
		gameWindow.setTitle("Internet Fighter");
		gameWindow.setSize(800, 600);
		gameWindow.setResizable(false);
		
		setBackground(gameWindow, 1);

		GamePanel gamePanel = new GamePanel(network);	
		gameWindow.add(gamePanel);
		
		gameWindow.setVisible(true);
		
	}
	
	public ClientServerSocket initNetworkSocket(String ipInfo){

		ClientServerSocket network;
		
		//Server
		if(ipInfo.equals("")){
			
			
			InetAddress ip = null;
			try{
				
				ip = InetAddress.getLocalHost();
				System.out.println("currentIP:"+ip);
				}catch(Exception e){		
				}
		
			network = new ClientServerSocket(/*ip.toString()*/ "localhost", 45000);
			network.startServer();
			
		}else{
		//Client
		
			localPlayer = new Player(Consts.RIGHT_XPOS, Consts.RIGHT_YPOS);
			
			network = new ClientServerSocket(ipInfo, 45000);
			network.startClient();			
		}	
		
		return network;	
	}

	public void setBackground(JFrame gameWindow, int background){
		
	}
	
}
