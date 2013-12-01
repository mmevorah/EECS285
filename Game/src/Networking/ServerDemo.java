package Networking;

import java.net.InetAddress;

import eecs285.Player;

public class ServerDemo {

	public static void main(String args[])
	{
		ClientServerSocket theServer;
		Player p;
		
		InetAddress ip = null;
		try{
			ip = InetAddress.getLocalHost();
			System.out.println("currentIP:"+ip);
		}catch(Exception e){
			
		}
		
		theServer = new ClientServerSocket("localhost", 45001);
		theServer.startServer();
		
		p = new Player(1, 1);
		
		theServer.sendPlayer(p);		
	}

}
