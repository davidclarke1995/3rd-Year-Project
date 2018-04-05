package ie.gmit.sw;

import java.awt.BorderLayout;
import java.awt.EventQueue;
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

public class Signup extends JFrame {

	//private JPanel contentPane;
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
	  //  acceptConnections();
		//createEvents();
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	

	//initialise components method
	public void initComponents() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPaneMain = new JPanel();
		contentPaneMain.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPaneMain.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPaneMain);
	}
	
	private void acceptConnections() {
		// 1. creating a socket to connect to the server
				input = new Scanner(System.in);
				try {//
					requestSocket = new Socket("35.190.197.5", 80);
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

	private void outPutMessage(String msg) {
		// TODO Auto-generated method stub
		
	}//end output message
	
	
}


