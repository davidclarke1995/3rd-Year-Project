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
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

// TODO: Auto-generated Javadoc
/**
 * The Class Signup.
 */
public class Signup extends JFrame {

	/** The content pane main. */
	//private JPanel contentPane;
	private JPanel contentPaneMain;
	
	/** The text field. */
	private JTextField textField;
	
	/** The password field. */
	private JPasswordField passwordField;
	
	/** The text area. */
	private JTextArea textArea;
	
	/** The request socket. */
	//private JPanel contentPane;
    Socket requestSocket;
	
	/** The out. */
	ObjectOutputStream out;
	
	/** The in. */
	ObjectInputStream in;
	
	/** The message. */
	String message = "x";
	
	/** The contains. */
	boolean contains = message.contains("x");
	
	/** The input. */
	Scanner input;
	
	/** The text field 1. */
	private JTextField textField_1;
	
	/** The text field 2. */
	private JTextField textField_2;
	
	/** The password field 1. */
	private JPasswordField passwordField_1;

	/**
	 * Launch the application.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Signup frame = new Signup();
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
	public Signup() {
		initComponents();
		//connect to the server at local host
	    acceptConnections();
		//createEvents();
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	

	/**
	 * Inits the components.
	 */
	//initialise components method
	public void initComponents() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPaneMain = new JPanel();
		contentPaneMain.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPaneMain);
		
		JLabel lblSignUpTop = new JLabel("Sign up top use the system");
		
		JLabel lblName = new JLabel("Name:");
		
		JLabel lblPassword = new JLabel("Password:");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username:");
		
		passwordField_1 = new JPasswordField();
		
		JButton btnSignMeUp = new JButton("Sign me up!");
		btnSignMeUp.addActionListener(new ActionListener() {
			// @SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				
				//actionlistener for the sign up button
				
				String name = textField_1.getText();
				String userName = textField_2.getText();
				String password = passwordField_1.getText();

				sendMessage("signup " + name + " " + userName + " " + password);
				
				try {
					message = (String) in.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				
				if(message.equals("signedUp")){
					lblSignUpTop.setText("You are now signed up. Please log in to use our chat system.");
					
				}
				else{
					lblSignUpTop.setText("An error occured while trying to sign you up. Please try again later.");
				}
				
				
			}

			
		});// end action listener
		GroupLayout gl_contentPaneMain = new GroupLayout(contentPaneMain);
		gl_contentPaneMain.setHorizontalGroup(
			gl_contentPaneMain.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPaneMain.createSequentialGroup()
					.addContainerGap(38, Short.MAX_VALUE)
					.addGroup(gl_contentPaneMain.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_contentPaneMain.createSequentialGroup()
							.addGap(90)
							.addGroup(gl_contentPaneMain.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_contentPaneMain.createSequentialGroup()
									.addGroup(gl_contentPaneMain.createParallelGroup(Alignment.LEADING)
										.addComponent(lblName, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblUsername))
									.addGap(18)
									.addGroup(gl_contentPaneMain.createParallelGroup(Alignment.LEADING, false)
										.addComponent(textField_2)
										.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)))
								.addGroup(gl_contentPaneMain.createSequentialGroup()
									.addComponent(lblPassword)
									.addGap(18)
									.addGroup(gl_contentPaneMain.createParallelGroup(Alignment.LEADING)
										.addComponent(btnSignMeUp)
										.addComponent(passwordField_1)))))
						.addComponent(lblSignUpTop))
					.addGap(115))
		);
		gl_contentPaneMain.setVerticalGroup(
			gl_contentPaneMain.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPaneMain.createSequentialGroup()
					.addGap(21)
					.addComponent(lblSignUpTop)
					.addGap(35)
					.addGroup(gl_contentPaneMain.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPaneMain.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUsername))
					.addGap(11)
					.addGroup(gl_contentPaneMain.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPassword)
						.addComponent(passwordField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnSignMeUp)
					.addContainerGap(58, Short.MAX_VALUE))
		);
		contentPaneMain.setLayout(gl_contentPaneMain);
	}
	
	/**
	 * Accept connections.
	 */
	private void acceptConnections() {
		// 1. creating a socket to connect to the server
				input = new Scanner(System.in);
				try {//
					requestSocket = new Socket("35.195.193.152", 80);
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
	
	/**
	 * Send message.
	 *
	 * @param msg the msg
	 */
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

	/**
	 * Out put message.
	 *
	 * @param msg the msg
	 */
	public void outPutMessage(String msg) {
		System.out.println(msg);
		
	}//end output message
}


