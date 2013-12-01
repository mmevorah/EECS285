package Networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import eecs285.PlayerInfo;

public class ClientServerSocket {

	private String ipAddr;
	private int portNum;
	private Socket socket;
	private ObjectOutputStream outData;
	private ObjectInputStream inData;


	public ClientServerSocket(String inIPAddr, int inPortNum)
	{
		ipAddr = inIPAddr;
		portNum = inPortNum;
		inData = null;
		outData = null;
		socket = null;
	}

	public void startServer(
			)
	{
		ServerSocket serverSock;
		try
		{
			serverSock = new ServerSocket(portNum);
			
			System.out.println("Waiting for client to connect...");
			socket = serverSock.accept();
			
			outData = new ObjectOutputStream(socket.getOutputStream());
			inData = new ObjectInputStream(socket.getInputStream());
			
			System.out.println("Client connection accepted");
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
			System.out.println("ERROR: Caught exception starting server");
			System.exit(7);
		}
	}


	public void startClient()
	{
		try
		{
			socket = new Socket(ipAddr, portNum);
			
			outData = new ObjectOutputStream(socket.getOutputStream());
			inData = new ObjectInputStream(socket.getInputStream());
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
			System.out.println("ERROR: Unable to connect - " +
					"is the server running?");
			System.exit(10);
		}
	}


	public boolean sendPlayer(PlayerInfo pToSend)
	{
		boolean success = false;
		try
		{			
			outData.writeObject(pToSend);
			success = true;
		}
		catch (IOException e)
		{
			e.printStackTrace();
			System.out.println("Caught IOException Writing To Socket Stream!");
			System.exit(-1);
		}
		return (success);
	}

	
	public PlayerInfo recvPlayer(
			) 
	{
		PlayerInfo p = null;
		try
		{
			p = (PlayerInfo) inData.readObject();
		}
		catch (Exception ioe)
		{
			ioe.printStackTrace();
			System.out.println("ERROR: receiving string from socket");
			System.exit(8);
		}
		return (p);
	}


}
