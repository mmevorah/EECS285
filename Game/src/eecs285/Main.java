package eecs285;

import java.awt.BorderLayout;
import java.net.InetAddress;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Main {

	public static void main(String[] args){
		JFrame ipFrame = new JFrame("Get IP Address");
		JTextField ip_send_field;
		JButton sendButton;
		
		ipFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ipFrame.setSize(400,500);
		ipFrame.setLayout(new BorderLayout());

		InetAddress ip = null;
		try{
			ip = InetAddress.getLocalHost();
			System.out.println("currentIP:"+ip);
		}catch(Exception e){
			
		}
		
		String ipAddress;
		ipAddress = ip.toString();
		
		ip_send_field = new JTextField(20);
		JLabel prompt = new JLabel (" Please copy the IP Address Seen Here : " + ipAddress);
		
	
		
		sendButton = new JButton ("Send");
		
		
		ipFrame.add(prompt, BorderLayout.NORTH);
		ipFrame.add(ip_send_field, BorderLayout.CENTER);
		ipFrame.add(sendButton, BorderLayout.SOUTH);
		
		ipFrame.setVisible(true);
	}
	
}
