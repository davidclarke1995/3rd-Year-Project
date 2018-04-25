package ie.gmit.sw;

import java.awt.BorderLayout;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.*;
import java.util.Scanner;

import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.JTextArea;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

/**
 * The Class MessageChatYurt.
 */
public class MessageChatYurt extends JFrame {
	
	

	/** The content pane. */
	private JPanel contentPane;
	
	/** The text field. */
	//private JPanel contentPane;
    private JTextField textField;
    
    /** The text area. */
    private JTextArea textArea;
    
    /** The user name. */
    private String userName;
    
    /** The request socket. */
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
	
	/** The login. */
	NewLogin login = new NewLogin();
	
	/** The scroll pane. */
	private JScrollPane scrollPane;
	
	/** The scroll pane 1. */
	private JScrollPane scrollPane_1;
	
	/** The label. */
	private JLabel label;
	
	/** The lbl new label. */
	private JLabel lblNewLabel;
	
	

	/**
	 * Gets the user name.
	 *
	 * @return the user name
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Sets the user name.
	 *
	 * @param userName the new user name
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Launch the application.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//MessageChatYurt frame = new MessageChatYurt();
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 *
	 * @param userName the user name
	 */
	public MessageChatYurt(String userName) {
		
		this.userName = userName;
		
		login.outPutMessage("USER " + userName);
		//sendMessage("USER " + userName);
		
		
	    try {
	    	
	    	//connect to the server at local host
		    acceptConnections();
			
			//initialize components
			initComponents();
			showMessage("'#HELP' for command list!");
			//read messages in from the server in a loop
			whileChatting();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * Inits the components.
	 */
	public void initComponents() {
		 setTitle("ChatYurt");
		 addWindowListener(new java.awt.event.WindowAdapter() {
			    @Override
			    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
			    	//send logout message to the server
			    	sendMessage("FINISHED");
			    }
			});
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		textField = new JTextField();
	    textField.setColumns(10);
        textArea = new JTextArea();
        textArea.setLineWrap(true);
        JButton btnSend = new JButton("Send");
        
        //action listener
        btnSend.addActionListener(new ActionListener() {
        	//append the text from the text box to the text area
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    if (!(textField.getText().equals(""))) {
                        String message = textField.getText();
                        if(message.equals("#HELP")){
                        	textArea.append("\n Commands: \n\n #SHOW- Displays everyone currently online. "
                        			+ "\n\n DM(direct message) NameOfFriend + message - sends a direct message to NameOfFriend"
                        			+ "\n\n *no prefix + message - if a message has no prefix it will by default send a broadcast message to everyone online on the system."
                        			+ "\n\n FINISHED - logs you out of the system");
                        	
                        	textField.setText("");
                        }
                        else{
                        	 textArea.append("\n\t\t\t--> " + "You: " + "\n");
                             // textArea.append(message + "\n");
                              //method for appending the text
                              showMessage(message + "\n");
                              textField.setText("");
                              //send that message to the server for processing
                              sendMessage(message);
                        }//end else
                       
                       
                    }
                }

				

			
            } //end actionlistener method
        ); //end action listener
        
        scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        
        gl_contentPane.setHorizontalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
            .addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
                .addContainerGap(62, Short.MAX_VALUE)
                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                    .addComponent(scrollPane, Alignment.TRAILING)
                    .addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
                        .addComponent(textField, GroupLayout.PREFERRED_SIZE, 289, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(btnSend)))
               
                .addContainerGap())
        );
        gl_contentPane.setVerticalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
            .addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
                .addGap(18)
                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                    .addComponent(btnSend)
                    .addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(19))
        );
        contentPane.setLayout(gl_contentPane);
    
		
	}//end init components
	
	/**
	 * Accept connections.
	 */
	//accept connection with the server
	 public void acceptConnections() {
	    	
	    	// 1. creating a socket to connect to the server
	    	input = new Scanner(System.in);
			try {
				requestSocket = new Socket("35.195.193.152", 2004);
				login.outPutMessage("Connected to localhost in port 2004");
				// 2. get Input and Output streams
				out = new ObjectOutputStream(requestSocket.getOutputStream());
				out.flush();
				in = new ObjectInputStream(requestSocket.getInputStream());
				
				// connection successful
				try {
					message = (String) in.readObject();
					//System.out.println("server>" + message);
					
					message = (String) in.readObject();
					
					sendMessage("USER " + getUserName());
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
	 * Show message.
	 *
	 * @param text the text
	 */
	//show message method - threaded
	public void showMessage(String text) {
		SwingUtilities.invokeLater(
				new Runnable() {
					public void run() {
						textArea.append(text);
					}
				}
				);
		
	}
	
	/**
	 * Send message.
	 *
	 * @param msg the msg
	 */
	//send message method
	public void sendMessage(String msg) {
		try {
			login.outPutMessage(msg);
			out.writeObject(msg);
			// flush the output stream
			out.flush();
			System.out.println("client>" + msg);
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
		
	}//end send message
	
	
	/**
	 * While chatting.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	//while chatting
	private void whileChatting() throws IOException {
    	SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>(){

			@Override
			protected Void doInBackground() throws Exception {
				int i = 0;
				do {
					try {	
							message = (String) in.readObject();
									
								showMessage("\n" + message);
						
					} catch (ClassNotFoundException e) {
						
						showMessage("\n message recieved in unknown format");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
						showMessage("\n////////////// message recieved in unknown format");
					}
					
				}while(!message.equals("FINISHED"));
				return null;
			}
    		
    	};
    	
    	worker.execute();
    		
		
    			
		
	}

}//end class
