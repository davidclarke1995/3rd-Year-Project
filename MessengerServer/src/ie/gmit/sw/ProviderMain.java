package ie.gmit.sw;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import java.net.*;

public class ProviderMain {
	public static void main(String args[]) throws IOException
	{
		//1. creating a server socket
		int port = 8123;
		
		
		Server server = new Server(port);
		server.start();
		
		
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
			
			
			handleConversation();
			
			
			
		}//end try
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
	}//end send message
	
	//conversation
	void handleConversation(){
		while(!message.equals("FINISHED")){
			
			try{
				

				message = (String)in.readObject();
				// https://stackoverflow.com/questions/11726023/split-string-into-individual-words-java
				String[] words = message.split(" ");
				
				if(words[0].equals("login")){
					System.out.println("attempting login");
					loginUser(words);
					
				}
				else{
					sendMessage("Server says ------- " + message);	
					//System.out.println("first word: " +  words[0]);
				}
				
				//Thread testing
				words = null;

			}//end try
			catch(ClassNotFoundException classnot){
				System.err.println("Data received in unknown format");
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}//end while
		
	}//end handleConversation

	// loginUser function
	public void loginUser(String[] words) {
		if(words.length == 3){
			String userName = words[1];
			String password = words[2];
			
			if(userName.equals("GaryConnelly") && password.equals("gary") || userName.equals("DaveClarke") && password.equals("dave") || userName.equals("EoghanConnor") && password.equals("yogan")){
				sendMessage("You are logged in as: " + userName);
			}
			else{
				sendMessage("Invalid username or password!");
			}	
		}//end if
		
		
	}
	
}


