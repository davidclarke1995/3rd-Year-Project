package ie.gmit.sw;

import java.awt.BorderLayout;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.net.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class NewLogin extends JFrame {

	private JPanel contentPaneMain;
	private JTextField textField;
	private JPasswordField passwordField;
	private JTextArea textArea;
	
	//private JPanel contentPane;
    Socket requestSocket;
	ObjectOutputStream out;
	ObjectInputStream in;
	String message = "x";
	boolean contains = message.contains("x");
	Scanner input;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewLogin frame = new NewLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public NewLogin() {
		
		initComponents();
		//connect to the server at local host
	    acceptConnections();
		//createEvents();
		
	}

	public void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPaneMain = new JPanel();
		contentPaneMain.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPaneMain.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPaneMain);
		
		//functionality 
		
		JLabel lblUsername = new JLabel("UserName:");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		
		passwordField = new JPasswordField();
		passwordField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setText("Enter your details");
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			//@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				///////////////////////
				String userName = textField.getText();
				String password = passwordField.getText();
				
				
				sendMessage("login " + userName + " " + password);
				try {
					message = (String) in.readObject();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if(message.equals("log")) {
					MessageChatYurt myChat;
					myChat = new MessageChatYurt();
					myChat.setVisible(true);
				}
				
				else{
					//txtrHello.setText("Invalid username or password");
					lblNewLabel.setText("Invalid username or password");
				}
				
					
				//hard coded user before connecting it to a database
				/*
				if(userName.equals("GaryConnelly") && password.equals("gary") || userName.equals("DaveClarke") && password.equals("dave")){
					MessageChatYurt myChat;
					myChat = new MessageChatYurt();
					myChat.setVisible(true);
					//myChat.run();
					
				}
				else{
					//txtrHello.setText("Invalid username or password");
					lblNewLabel.setText("Invalid username or password");
				}
				*/
				
			}

			
		});//end action listener
		
		
		
		
		
		
		
		GroupLayout gl_contentPaneMain = new GroupLayout(contentPaneMain);
		gl_contentPaneMain.setHorizontalGroup(
			gl_contentPaneMain.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPaneMain.createSequentialGroup()
					.addGap(108)
					.addGroup(gl_contentPaneMain.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPaneMain.createSequentialGroup()
							.addComponent(lblPassword)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(passwordField))
						.addGroup(gl_contentPaneMain.createSequentialGroup()
							.addComponent(lblUsername)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(173, Short.MAX_VALUE))
				.addGroup(gl_contentPaneMain.createSequentialGroup()
					.addGap(82)
					.addComponent(btnLogin, GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
					.addGap(91))
				.addGroup(gl_contentPaneMain.createSequentialGroup()
					.addGap(184)
					.addComponent(lblNewLabel)
					.addContainerGap(194, Short.MAX_VALUE))
		);
		gl_contentPaneMain.setVerticalGroup(
			gl_contentPaneMain.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPaneMain.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPaneMain.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUsername)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPaneMain.createParallelGroup(Alignment.LEADING)
						.addComponent(lblPassword)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnLogin)
					.addGap(18)
					.addComponent(lblNewLabel)
					.addContainerGap(121, Short.MAX_VALUE))
		);
		contentPaneMain.setLayout(gl_contentPaneMain);
		
		
		
		
		

	}//end init components
	
	
	//accept connections method
	private void acceptConnections() {
		// 1. creating a socket to connect to the server
		input = new Scanner(System.in);
		try {//
			requestSocket = new Socket("35.189.235.6", 80);
			// requestSocket = new Socket("35.205.181.61", 2004);
			//System.out.println("Connected to localhost in port 2004");
			outPutMessage("Connected to server yurt");
			// 2. get Input and Output streams
			out = new ObjectOutputStream(requestSocket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(requestSocket.getInputStream());
			
			// connection successful
			try {
				message = (String) in.readObject();
				//System.out.println("server>" + message);
				outPutMessage("server>" + message);
				message = (String) in.readObject();
				outPutMessage("server>" + message);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			message = "x";
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	

	//implement send message method
	private void sendMessage(String msg) {
		try {
			out.writeObject(msg);
			// flush the output stream
			out.flush();
			System.out.println("client>" + msg);
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
		
	}//end send message
	
///////////////////////////////////////////////////////////////////////////
// This method is for testing purposes only
/////////////////////////////////////////////////
public void outPutMessage(String message) {
System.out.println(message);
}

public void outPutInt(int i) {
System.out.println(i);
}
		
	}//end JFrame


