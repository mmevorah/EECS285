package eecs285;

public class ServerDemo {

	public static void main(String args[])
	{
	ClientServerSocket theServer;
	String recvdStr;
	theServer = new ClientServerSocket("127.0.0.1", 45000);
	theServer.startServer();
	recvdStr = theServer.recvString();
	System.out.println("Recevied message from client: " + recvdStr);
	theServer.sendString("Back at ya client");
	}
	
	
	
}