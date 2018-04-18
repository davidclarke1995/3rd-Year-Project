package ie.gmit.sw;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JButton;

public class Delete extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	
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
					Delete frame = new Delete();
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
	public Delete() {
		
		
		initComponents();
		//connect to the server at local host
	    acceptConnections();
		//createEvents();
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	//initialise components method
		public void initComponents() {
			setBounds(100, 100, 450, 300);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			
			textField = new JTextField();
			textField.setColumns(10);
			
			passwordField = new JPasswordField();
			
			JLabel lblUsername = new JLabel("Username:");
			
			JLabel lblPassword = new JLabel("Password");
			
			JLabel lblEnterYourDetails = new JLabel("Enter your details below:");
			
			
			JButton btnDelete = new JButton("Delete");
			btnDelete.addActionListener(new ActionListener() {
			
					// @SuppressWarnings("deprecation")
					public void actionPerformed(ActionEvent arg0) {
						String userName = textField.getText();
						String password = passwordField.getText();
						
						sendMessage("delete " + userName + " " + password);
						
						try {
							message = (String) in.readObject();
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						if(message.equals("deleted")){
							lblEnterYourDetails.setText("You are now deleted from the system.");
							
						}
						else{
							lblEnterYourDetails.setText("An error occured while trying to delete you.");
						}
						
						
					}
			});// end action listener
			GroupLayout gl_contentPane = new GroupLayout(contentPane);
			gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
						.addContainerGap(116, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addComponent(lblUsername)
							.addComponent(lblPassword))
						.addGap(40)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addComponent(btnDelete)
							.addComponent(lblEnterYourDetails)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(passwordField, Alignment.LEADING)
								.addComponent(textField, Alignment.LEADING)))
						.addGap(136))
			);
			gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblEnterYourDetails)
						.addGap(38)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
							.addComponent(lblUsername)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
							.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblPassword))
						.addGap(18)
						.addComponent(btnDelete)
						.addContainerGap(89, Short.MAX_VALUE))
			);
			contentPane.setLayout(gl_contentPane);
		}
		
		private void acceptConnections() {
			// 1. creating a socket to connect to the server
					input = new Scanner(System.in);
					try {//
						requestSocket = new Socket("35.195.193.152", 80);
						//requestSocket = new Socket("79.140.211.73", 2003);
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
					
			
		}//end accept connections
		
		public void sendMessage(String msg) {
			try {
				out.writeObject(msg);
				// flush the output stream
				out.flush();
				System.out.println("client>" + msg);
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
			
		}
		
		public void outPutMessage(String msg) {
			System.out.println(msg);
			
		}//end output message
		
		
		

}
