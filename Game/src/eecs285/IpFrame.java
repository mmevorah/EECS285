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

@SuppressWarnings("serial")
public class IpFrame extends JDialog {

	private JTextField ip_send_field;
	private JButton sendButton;
	private String ipAddress;
	private String input;

	public IpFrame(JFrame mainFrame, String title)
	{	
		super(mainFrame, title, true);
		setSize(500,100);
		setLayout(new BorderLayout());

		InetAddress ip1 = null;
		try{
			ip1 = InetAddress.getLocalHost();
		}catch(Exception e){

		}

		ipAddress = ip1.toString();
		JLabel prompt = new JLabel (" Please send the IP Address Seen Here : " + ipAddress);
		add(prompt, BorderLayout.NORTH);

		ip_send_field = new JTextField(20);
		add(ip_send_field, BorderLayout.CENTER);

		sendButton = new JButton ("SEND");
		sendButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					ip_send_field.toString();
					input = ip_send_field.getText();
					setVisible(false);
				}
				catch (NumberFormatException nfe)
				{
					ip_send_field.setText("");
					setVisible(true);
				}
			}
		});

		add(sendButton, BorderLayout.SOUTH);
		setVisible(true);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}

	public String get_ip()
	{
		return ipAddress;
	}

	public String get_input()
	{
		return input;
	}

	public String printer(String to_print)
	{
		return to_print;
	}
}


