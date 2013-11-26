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

	private JTextField ip_send_field;
	private JButton sendButton;
	private String ipAddress;

	public ipFrame()
	{		
		setSize(400,100);
		setLayout(new BorderLayout());
		
		InetAddress ip1 = null;
		try{
			ip1 = InetAddress.getLocalHost();
			System.out.println("currentIP:"+ip1);
		}catch(Exception e){

		}

		ipAddress = ip1.toString();
		JLabel prompt = new JLabel (" Please copy the IP Address Seen Here : " + ipAddress);
		add(prompt, BorderLayout.NORTH);
	
		ip_send_field = new JTextField(20);
		add(ip_send_field, BorderLayout.CENTER);

		sendButton = new JButton ("Send");
		sendButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				ipAddress = ip_send_field.getText();
				setVisible(false);
			}
		});
		add(sendButton, BorderLayout.SOUTH);

        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		setVisible(true);
	}
	
	public String get_ip()
	{
		return ipAddress;
	}
}


