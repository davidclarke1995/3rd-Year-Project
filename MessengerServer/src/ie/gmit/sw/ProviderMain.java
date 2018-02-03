package ie.gmit.sw;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import java.net.*;

public class ProviderMain {
	public static void main(String args[]) throws IOException
	{
		//1. creating a server socket
		ServerSocket providerSocket = new ServerSocket(8123);
		
		while(true){
			Socket connection = providerSocket.accept();
			Provider server = new Provider(connection);
			server.start();
		}
	}

}// end main


class Provider extends Thread{
	ServerSocket providerSocket;
	Socket connection = null;
	ObjectOutputStream out;
	ObjectInputStream in;
	String message = "";
	Provider(){}
	
	Provider(Socket s) {
		connection = s;
	  }
	public void run()
	{
		try{
			
			//2. Wait for connection
			System.out.println("Waiting for connection");
			
			System.out.println("Connection received from " + connection.getInetAddress().getHostName());
			//3. get Input and Output streams
			out = new ObjectOutputStream(connection.getOutputStream());
			out.flush();
			in = new ObjectInputStream(connection.getInputStream());
			sendMessage("Connection successful");
			//4. The two parts communicate via the input and output streams
			sendMessage("Please the phrase you wish to echo or the word FINISHED to exit");
			//--------------------NORMAL CONVERSATION-----------------------
			while(!message.equals("FINISHED")){
				
				try{
					

					message = (String)in.readObject();
					//Thread testing
					
					sendMessage("Server says ------- " + message);
						
					
				

				}//end try
				catch(ClassNotFoundException classnot){
					System.err.println("Data received in unknown format");
				}
			}//end while
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
		finally{
			//4: Closing connection
			try{
				in.close();
				out.close();
				connection.close();
				System.out.println("server> Finished");
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
			//System.out.println("server>" + msg);
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
	}
	
}


