//main
package ie.gmit.sw;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.net.*;




public class ProviderMain {
	public static void main(String args[]) throws IOException
	{
		//1. creating a server socket
		int port = 8123;
		int port1 = 2004;
		
		//Server server = new Server(port);
		Server server = new Server(port1);
		server.start();
		
		
	}

}// end main


class Provider extends Thread{
	ServerSocket providerSocket;
	Socket connection = null;
	ObjectOutputStream out;
	ObjectInputStream in;
	Server server;
	String message = "";
	String userName;
	boolean loggedIn = false;
	private HashSet<String> groupSet = new HashSet<>();
	Provider(){}
	
	Provider(Server server, Socket s) {
		connection = s;
		this.server = server;
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
				
				if(words[0].equals("login") && words.length == 3){
					System.out.println("attempting login");
					loginUser(words);
					
				}
				else{
					if(loggedIn){
						if(words[0].equals("DM")) {
							List<Provider> userList = server.getProviderList();
							for(Provider provider : userList){
								if(provider.getUsername().equals(words[1])){
									if(!(message.equals(""))){
										provider.sendMessage("Private message ------- " + getUsername() + " " + message);
									}//end if
									
								}//end if
							
							}//end for
						}//end if
						else{
							//-------------------attempting a broadcast message--------------------------
							List<Provider> userList = server.getProviderList();
							for(Provider provider : userList){
								if(provider.getUsername() != this.getUsername()){
									if(!(message.equals(""))){
										provider.sendMessage("Broadcast message ------- " + getUsername() + " " +  message);
									}//end if
									
								}//end if
									
								
								
							}//end for
							
						}//end else
						
						
					}//end if logged in
					else
					{
						sendMessage("please log in to continue");
					}
					
					
					sendMessage(null);
					//sendMessage("Server says ------- " + message);	
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
	//function to return the user
	public String getUsername(){
		return userName;
	}
	// loginUser function
	public void loginUser(String[] words) {
		if(words.length == 3){
			String userName = words[1];
			String password = words[2];
			User gary = new User("GaryConnelly", "gary");
			User dave = new User("DaveClarke", "dave");
			User yogan = new User("EoghanConner", "yogan");
			ArrayList<User> users = new ArrayList<User>();
			users.add(gary);
			users.add(dave);
			users.add(yogan);
			boolean isUser = false;
			for(User user: users){
				//if(userName.equals("GaryConnelly") && password.equals("gary") || userName.equals("DaveClarke") && password.equals("dave") || userName.equals("EoghanConnor") && password.equals("yogan"))
				if(userName.equals(user.getUserName()) && password.equals(user.getPassword())){
					this.userName = userName;
					
					List<Provider> providerList = server.getProviderList();
					//Provider provider;
					for(Provider provider : providerList){
						provider.sendMessage(userName + " Is online");
					}
					//Provider provider;
					
					for(Provider provider : providerList){
						provider.sendMessage(null);
					}
					isUser = true;
					loggedIn = true;
					//sendMessage("You are logged in as: " + userName);
				}//end if for user validation
				/*
				else{
					sendMessage("Invalid username or password!");
				}
				*/	
				
			}
			if(isUser == false){
				sendMessage("Invalid username or password!");
			}
			
			
		}//end if
		
		
	}
	
}

