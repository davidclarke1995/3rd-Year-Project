package ie.gmit.sw;
import java.io.*;
import java.net.*;
import java.util.Scanner;
public class Requester{
	Socket requestSocket;
	ObjectOutputStream out;
 	ObjectInputStream in;
 	String message = "";
 	Scanner input;
	Requester(){

		
	}
	void run()
	{
		input = new Scanner(System.in);
		try{
			//1. creating a socket to connect to the server

			requestSocket = new Socket("127.0.0.1", 8123);
			System.out.println("Connected to localhost in port 8123");
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
			do{
				try{
					
					message = (String)in.readObject();
		
					outPut(message);
					
					
					message = input.nextLine();
					sendMessage(message);
					//message = (String)in.readObject();

				}
				catch(ClassNotFoundException classNot){
					System.err.println("data received in unknown format");
				}
			}while(!message.equals("FINISHED"));
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
	void sendMessage(String msg)
	{
		try{
			out.writeObject(msg);
			out.flush();
			System.out.println("client>" + msg);
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
	}
	void outPut(String myMessage) {
		System.out.println("Server > " + myMessage);
		
	}
	public static void main(String args[])
	{
		Requester client = new Requester();
		client.run();
	}
}

