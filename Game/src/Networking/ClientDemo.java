package Networking;

import java.net.InetAddress;

import eecs285.Player;

public class ClientDemo
{
	public static void main(String [] args)
	{
		ClientServerSocket theClient;
		
		InetAddress ip = null;
		try{
			ip = InetAddress.getLocalHost();
			System.out.println("currentIP:"+ip);
		}catch(Exception e){
			
		}
		
		theClient = new ClientServerSocket("localhost", 45001);
		theClient.startClient();
		
		Player recvPlayer = theClient.recvPlayer();
		System.out.println("Received this message from server: " + recvPlayer.y);
	}
}
