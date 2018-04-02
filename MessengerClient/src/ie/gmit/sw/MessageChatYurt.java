package ie.gmit.sw;

import java.awt.BorderLayout;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
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

public class MessageChatYurt extends JFrame {
	
	

	private JPanel contentPane;
	
	//private JPanel contentPane;
    private JTextField textField;
    private JTextArea textArea;
    private String userName;
    Socket requestSocket;
	ObjectOutputStream out;
	ObjectInputStream in;
	String message = "x";
	boolean contains = message.contains("x");
	Scanner input;
	NewLogin login = new NewLogin();
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	
	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Launch the application.
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
	 */
	public MessageChatYurt(String userName) {
		
		this.userName = userName;
		
		login.outPutMessage("USER " + userName);
		//sendMessage("USER " + userName);
		
		
	    try {
	    	
	    	//sendMessage("USER " + userName);
	    	
	    	
	    	//connect to the server at local host
		    acceptConnections();
			
			//initialize components
			initComponents();
			
			//read messages in from the server in a loop
			whileChatting();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void initComponents() {
		 setTitle("ChatYurt");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
                        textArea.append("\t\t\t--> " + "You: " + "\n");
                       // textArea.append(message + "\n");
                        //method for appending the text
                        showMessage(message + "\n");
                        textField.setText("");
                        //send that message to the server for processing
                        sendMessage(message);
                       
                    }
                }

				

			
            } //end actionlistener method
        ); //end action listener
        
        scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        //contentPane.add(scrollPane);
        
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        
       // scrollPane_1 = new JScrollPane();
       // contentPane.add(scrollPane_1, BorderLayout.NORTH);
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
	
	//accept connection with the server
	 public void acceptConnections() {
	    	
	    	// 1. creating a socket to connect to the server
	    	input = new Scanner(System.in);
			try {
				requestSocket = new Socket("35.189.235.6", 2004);
				// requestSocket = new Socket("35.205.181.61", 2004);
				//System.out.println("Connected to localhost in port 2004");
				login.outPutMessage("Connected to localhost in port 2004");
				// 2. get Input and Output streams
				out = new ObjectOutputStream(requestSocket.getOutputStream());
				out.flush();
				in = new ObjectInputStream(requestSocket.getInputStream());
				
				// connection successful
				try {
					message = (String) in.readObject();
					//System.out.println("server>" + message);
					login.outPutMessage("server>" + message);
					message = (String) in.readObject();
					login.outPutMessage("server>" + message);
					login.outPutMessage("////inside do while " + getUserName());
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
	
	
	//while chatting
	private void whileChatting() throws IOException {
    	SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>(){

			@Override
			protected Void doInBackground() throws Exception {
				int i = 0;
				do {
					//testing
					 login.outPutMessage("inside do while ");
					try {
						//int data = in.available();
						//if(data > 0){
							//login.outPutMessage((String) data);
						//while((message = (String)in.readObject()) != null){
						login.outPutMessage(message);
							message = (String) in.readObject();
							login.outPutMessage("message recieved " + message);
							
							//-----------------------changed 02/04/2018-----comment out if not null
							//if(!message.equals(null)) {
								login.outPutMessage("inside if");
								showMessage("\n" + message);
							//}
							//else {
							//	login.outPutMessage("END DO");
							//}
								//-----------------------(end)changed 02/04/2018-----comment out if not null
							
						//}
						
							login.outPutMessage("OUTSIDE if");
							
						//}
						
					} catch (ClassNotFoundException e) {
						
						showMessage("\n message recieved in unknown format");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
						showMessage("\n////////////// message recieved in unknown format");
					}
					
				}while(!message.equals("FINISHED"));
				login.outPutMessage("outside loop");
				return null;
			}
    		
    	};
    	
    	worker.execute();
    		
		
    			
		
	}

}
