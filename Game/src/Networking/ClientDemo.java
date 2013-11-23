package Networking;

import java.net.InetAddress;

public class ClientDemo
{
	public static void main(String [] args)
	{
		ClientServerSocket theClient;
		String recvdStr;
		
		InetAddress ip = null;
		try{
			ip = InetAddress.getLocalHost();
			System.out.println("currentIP:"+ip);
		}catch(Exception e){
			
		}
		
		theClient = new ClientServerSocket(ip.toString(), 45000);
		theClient.startClient();
		theClient.sendString("Hello to the server!");
		recvdStr = theClient.recvString();
		System.out.println("Received this message from server: " + recvdStr);
	}
}
