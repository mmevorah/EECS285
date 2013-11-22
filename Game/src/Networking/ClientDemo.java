package Networking;

public class ClientDemo
{
public static void main(String [] args)
{
ClientServerSocket theClient;
String recvdStr;
theClient = new ClientServerSocket("67.194.25.21", 45000);
theClient.startClient();
theClient.sendString("Hello to the server!");
recvdStr = theClient.recvString();
System.out.println("Received this message from server: " + recvdStr);
}
}
