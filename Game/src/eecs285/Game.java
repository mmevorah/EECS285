package eecs285;

import java.net.InetAddress;

import Networking.ClientServerSocket;

public class Game {

	private Player player1;
	private Player player2;
	private GameWindow gameWindow;
	private Driver driver;
	
	public Game(String ipInfo){
		this.player1 = new Player();
		this.player2 = new Player();
		this.gameWindow = new GameWindow();
		
		ClientServerSocket network = initNetworkSocket(ipInfo);
		
		driver = new Driver(player1, player2, gameWindow, network);
		driver.run();
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
		
			network = new ClientServerSocket(ip.toString(), 45000);
			network.startServer();
		}else{
		//Client
			network = new ClientServerSocket(ipInfo, 45000);
			network.startClient();
		}	
		
		return network;	
	}
	
}
