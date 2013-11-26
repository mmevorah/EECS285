package Networking;

import java.net.InetAddress;


public class ServerDemo {

	public static void main(String args[])
	{
		ClientServerSocket theServer;
		String recvdStr;
		
		InetAddress ip = null;
		try{
			ip = InetAddress.getLocalHost();
			System.out.println("currentIP:"+ip);
		}catch(Exception e){
			
		}
		
		theServer = new ClientServerSocket(ip.toString(), 45000);
		theServer.startServer();
		
		recvdStr = theServer.recvString();
		System.out.println("Recevied message from client: " + recvdStr);
		
		theServer.sendString("Back at ya client");
	}



}
