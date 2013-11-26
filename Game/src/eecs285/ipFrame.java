package eecs285;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ipFrame extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame ipFrame = new JFrame("Get IP Address");
	private JTextField ip_send_field;
	private JButton sendButton;
	private String ipAddress;

	public ipFrame()
	{
		JFrame ipframe;
		boolean is_server;
		String ip;
		ipFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ipFrame.setSize(400,100);
		ipFrame.setLayout(new BorderLayout());
		boolean is_serv = true;

		InetAddress ip1 = null;
		try{
			ip1 = InetAddress.getLocalHost();
			System.out.println("currentIP:"+ip1);
		}catch(Exception e){

		}


		ipAddress = ip1.toString();

		ip_send_field = new JTextField(20);
		JLabel prompt = new JLabel (" Please copy the IP Address Seen Here : " + ipAddress);

		sendButton = new JButton ("Send");
		sendButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				ipAddress = ip_send_field.getText();
				setVisible(false);
			}
		});


		ipFrame.add(prompt, BorderLayout.NORTH);
		ipFrame.add(ip_send_field, BorderLayout.CENTER);
		ipFrame.add(sendButton, BorderLayout.SOUTH);

		ipFrame.setVisible(true);
	}
	
	public String get_ip()
	{
		return ipAddress;
	}
}


