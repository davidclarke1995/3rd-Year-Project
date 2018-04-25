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


/**
 * The Class ProviderMain.
 */
public class ProviderMain {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void main(String args[]) throws IOException
	{
	
		
		//1. creating a server socket
				int port = 80;
				//int port = 2003;
				int port1 = 2004;
				int loginPort = 3333;
				
				//Server server = new Server(port);
				Server Dbserver = new Server(port);
				Dbserver.start();
				//Server server = new Server(port1);
				//server.start();
	
		
	}

}// end main method.


class Provider extends Thread{
	ServerSocket providerSocket;
	Socket connection = null;
	ObjectOutputStream out;
	ObjectInputStream in;
	Server server;
	String message = "";
	String userName;
	boolean loggedIn = true;
	//private HashSet<String> groupSet = new HashSet<>();
	ArrayList<String> groupList = new ArrayList<String>();
	 List<ArrayList<String>> groups = new ArrayList<ArrayList<String>>();
	Provider(){}
	
	// provider constructor
	Provider(Server server, Socket s, String userName) {
		connection = s;
		this.server = server;
		this.userName = userName;
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
			//handle the exception
			catch(IOException ioException){
				ioException.printStackTrace();
			}
		}
	}
	//method to send message
	void sendMessage(String msg)
	{
		try{
			out.writeObject(msg);
			//flush outputstream
			out.flush();
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
	}//end send message
	
	//conversation
	void handleConversation(){
		while(!message.equals("FINISHED")){
			
			try{
				
				//read from inputstream
				message = (String)in.readObject();
				// https://stackoverflow.com/questions/11726023/split-string-into-individual-words-java
				String[] words = message.split(" ");
				
				//to append the username to the text
				if(words[0].equals("USER")) {
					//access the second word in the array
					this.userName = words[1];
					
					//-------------------online status--------------------------
					List<Provider> userList = server.getProviderList();
					//to send it to everyone that is online except himself/herself
					for(Provider provider : userList){
						if(provider.getUsername() != this.getUsername()){
							if(!(message.equals(""))){
								provider.sendMessage(getUsername() + " " +  "is online!");
							}//end if
							
						}//end if	
					}//end for
				}//end online status
				
				
				else{
					if(loggedIn){
						//------------------Direct Messaging--------------------------
						if(words[0].equals("DM")) {
							//get a list of the server instances
							List<Provider> userList = server.getProviderList();
							//for each provider in the list, where the user name = the target of the direct message(words[1], send a message to that connection)
							for(Provider provider : userList){
								if(provider.getUsername().equals(words[1])){
									//removing the prefix from the private message
									String newWords = ""; 
									int i = 0;
									for(String word: words) {
										i++;
										if(word.equals("DM") || i ==2) {
											//do nothing	
										}
										else {
											newWords += " " + word;
										}
									}
										
										provider.sendMessage("Private message - " + getUsername() + ": " + newWords);
									
								}//end if
							
							}//end for
						}//end if DM
						
						//to creat a group
						else if(words[0].equals("CREATEGROUP")) {
							createGroup(words);
						}
						
						//to return the list of groups
						else if(words[0].equals("RETURNGROUPS")) {
							System.out.println("Groups: " + getGroups());
						}
						
						else if(words[0].equals("#SHOW")) {
							System.out.println("inside #SHOW");
							//------------------load online users-------------
							List<Provider> userList = server.getProviderList();
							String onlineUsers = "Online - ";
							for(Provider provider : userList){
								//for each user that is online, append that user to the string along with that users username	
								
								onlineUsers += provider.getUsername() + ", " ;
								
							}//end for
							
							for(Provider provider: userList) {
								//this message only goes to the same client that sent it
								if(provider.getUsername() == this.getUsername()){
									
									provider.sendMessage(onlineUsers);
									
									
								}//end if
								
							}//end for
							
						}//end #SHOW
						
						
						
						else{
							//-------------------broadcast message--------------------------
							List<Provider> userList = server.getProviderList();
							//for every connection to the server, send a message if they are logged in(if(loggedIn))
							for(Provider provider : userList){
								//send it to every instance, except the current instance(Because that would be just sending a message to yourself).
								if(provider.getUsername() != this.getUsername()){
									if(!(message.equals(""))){
										provider.sendMessage("Broadcast message - " + getUsername() + ": " +  message);
									}//end if
									
								}//end if
							}//end for
							
						}//end else
						
						
					}//end if logged in
					else
					{
						sendMessage("please log in to continue");
					}
				}
				
				//re-initialize words to null in preparation for the next inputstream
				words = null;

			}//end try
			catch(ClassNotFoundException classnot){
				System.err.println("Data received in unknown format");
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}//end while
		List<Provider> userList = server.getProviderList();
		userList.remove(this);
		
	}//end handleConversation
	
	/**
	 * Creates the group.
	 *
	 * @param words the words
	 */
	//create group method
	private void createGroup(String[] words) {
		String groupName = words[1];
		
		groupList.add(groupName);
		//groups.add(groupName);
		System.out.println("Successfully added group: " + groupName);
	}
	
	//return the groups
	public ArrayList<String> getGroups() {
		return groupList;
	}
	
	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	//function to return the user
	public String getUsername(){
		return userName;
	}
	
	//for testing only
		public void outPutMessage(String message) {
			System.out.println(message);
			
		}
	
}

