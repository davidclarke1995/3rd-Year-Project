package ie.gmit.sw;
import java.io.*;
import java.net.*;
import java.util.Scanner;
// TODO: Auto-generated Javadoc

/**
 * The Class Requester.
 */
public class Requester{
	
	/** The request socket. */
	Socket requestSocket;
	
	/** The out. */
	ObjectOutputStream out;
 	
	 /** The in. */
	 ObjectInputStream in;
 	
	 /** The message. */
	 String message = "x";
 	
	 /** The input. */
	 Scanner input;
	
	/**
	 * Instantiates a new requester.
	 */
	Requester(){
		
//test

		
	}
	
	/**
	 * Run.
	 */
	void run()
	{
		input = new Scanner(System.in);
		try{
			//1. creating a socket to connect to the server
			//requestSocket = new Socket("127.0.0.1", 8123);
			requestSocket = new Socket("35.205.181.61", 2004);
			System.out.println("Connected to localhost in port 2004");
			//2. get Input and Output streams
			out = new ObjectOutputStream(requestSocket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(requestSocket.getInputStream());
			//3: Communicating with the server
			try {
				//connection successful
				message = (String)in.readObject();
				System.out.println("server>" + message);
			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			}
			
			try {
				//please enter a message
				message = (String)in.readObject();
				System.out.println(message);
				message = input.nextLine();
				sendMessage(message);
				//message = (String)in.readObject();
			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			}
			

			/////------------NORMAL CONVERSATION--------------
			while(!message.equals(null)){
				do{
					try{
						//while the input stream is not null
						while((message = (String)in.readObject()) != null){
							
							outPut(message);
							
						}
					
						//if something is typed in by the user, send that as a message back to the server
						if((message = input.nextLine()) != null){
							sendMessage(message);
						}		

					}//test commit 2
					catch(ClassNotFoundException classNot){
						System.err.println("data received in unknown format");
					}
				}while(!message.equals("FINISHED"));//when the client types "FINISHED", exit the conversation loop to close the connection
				
			}
			
		}
		catch(UnknownHostException unknownHost){
			System.err.println("You are trying to connect to an unknown host!");
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
		finally{
			//4: Closing connection
			try{
				in.close();
				out.close();
				requestSocket.close();
			}
			catch(IOException ioException){
				ioException.printStackTrace();
			}
		}
	}
	
	/**
	 * Send message.
	 *
	 * @param msg the msg
	 */
	//access the output stream to send a message to the server for processing
	void sendMessage(String msg)
	{
		try{
			out.writeObject(msg);
			//flush the output stream
			out.flush();
			System.out.println("client>" + msg);
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
	}
	
	/**
	 * Out put.
	 *
	 * @param myMessage the my message
	 */
	////output the message with the "Server >" identifier to notify the client that it has come from the server
	void outPut(String myMessage) {
		System.out.println("Server > " + myMessage);
		
	}
	
	/**
	 * Load online users.
	 *
	 * @param user the user
	 * @throws ClassNotFoundException the class not found exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	//function to load users
	void loadOnlineUsers(String user) throws ClassNotFoundException, IOException{
		String[] users = null;
		String message2;
		int i = 0;
		while(!(user.contains("Is online"))){
			users[i] = user;
			i++;
			
			user = (String)in.readObject();
			//System.out.println(users[i]);
			outPut(users[i]);
		}
	
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String args[])
	{
		Requester client = new Requester();
		client.run();
	}
}


