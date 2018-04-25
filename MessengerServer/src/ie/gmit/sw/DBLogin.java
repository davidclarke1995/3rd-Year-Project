package ie.gmit.sw;



import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;


import java.sql.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.net.*;


/**
 * The Class DBLogin.
 */
public class DBLogin extends Thread{
 
  /** The console. */
  Scanner console = new Scanner(System.in);
  
  /** The option. */
  int option = 0;
  
  /** The name. */
  String name = "";
  
  /** The user name. */
  String userName = "";
  
  /** The password. */
  String password = "";
  
  /** The login success. */
  boolean loginSuccess = false;
  
  /** The counter. */
  int counter = 0;
  
  /** The connection. */
  Socket connection = null;
  
  /** The out. */
  ObjectOutputStream out;
  
  /** The in. */
  ObjectInputStream in;
  
  /** The message. */
  String message = " ";
  
  /** The server. */
  Server server;
  
  /**
   * Instantiates a new DB login.
   */
  public DBLogin() {}
  
  /**
   * Instantiates a new DB login.
   *
   * @param server the server
   * @param s the s
   */
  DBLogin(Server server, Socket s) {
		connection = s;
		this.server = server;
	  }
  

/* (non-Javadoc)
 * @see java.lang.Thread#run()
 */
public void run() {
  try {

   
	//2. Wait for connection
	  Provider provider = new Provider();
	    provider.outPutMessage("Connection received from " + connection.getInetAddress().getHostName());
		//3. get Input and Output streams
		out = new ObjectOutputStream(connection.getOutputStream());
		out.flush();
		in = new ObjectInputStream(connection.getInputStream());
		sendMessage("Connection successful");
		//4. The two parts communicate via the input and output streams
		sendMessage("Please the phrase you wish to echo or the word FINISHED to exit");
		
		//read username and password and output to console
		message = (String)in.readObject();
		provider.outPutMessage(message);
		//split the input message into the different words
		String[] words = message.split(" ");
		
		//if the first word is login
		if(words[0].equals("login")) {
			//initialize the username and password
			String loginUserName = words[1];
			String loginPassword = words[2];
			
			Class.forName("com.mysql.jdbc.Driver");
			//get a handle on the database
			   Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "");
		   
			   Statement stmt = conn.createStatement();
			   //write the query and decrypt the password
			   ResultSet rs = stmt.executeQuery("SELECT name, userName, AES_DECRYPT(password, 'YURT') FROM userDetails");
			   User user = null;
			   final ArrayList < User > users = new ArrayList < User > ();
			   while (rs.next()) {
			    user = new User();
			    user.setName(rs.getString(1));
			    user.setUserName(rs.getString(2));
			    user.setPassword(rs.getString(3));
			    users.add(user);
			    user = null;
			   }
			   // for each user in the user list
			   for (User u: users) {
					
				    if (u.getUserName().equals(loginUserName) && u.getPassword().equals(loginPassword)) {
				     loginSuccess = true;
				     provider.outPutMessage("logged in");
				     sendMessage("log");
				     Server server = new Server();
				     provider.outPutMessage("Starting thread " + loginUserName);
				     server.startProviderMain(loginUserName);
				     
				     counter ++;
				     
				    } 
				   }//end for users
			   //if the user doesnt exist
			   if(counter == 0) {
				   provider.outPutMessage("nope");
			   		sendMessage("nope");
				   
			   }
			
			   //to test for each user in database
			   for (User u: users) {
			    // System.out.println(u.toString());
			    System.out.println(u.getUserName() + " " + u.getPassword());
			   }
			   
		 }//end login users
		//----------------if somone is trying to sign up
		else if(words[0].equals("signup")) {
			try {

				User user = new User();
				name = words[1];
				user.setName(name);
				userName = words[2];
				user.setUserName(userName);
				
				password = words[3];
				user.setPassword(password);
				
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "");
				PreparedStatement stmt = null;
				
				//encrypt the password as it is being inserted into the database
				String sql = "INSERT INTO userDetails (name, userName, password) VALUES (? , ? ,AES_ENCRYPT(?,'YURT'));";

				stmt = conn.prepareStatement(sql);
				stmt.setString(1, user.getName());
				stmt.setString(2, user.getUserName());
				stmt.setString(3, user.getPassword());

				stmt.execute();
				//if it is successful send the confirmation message
				sendMessage("signedUp");

			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}//end if signup
		else if(words[0].equals("delete")){
			try {

				User user = new User();
				String userName;
				String password;

				userName = words[1];
				password = words[2];
				user.setUserName(userName);
				user.setPassword(password);

				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "");
				PreparedStatement stmt = null;

				//making sure to decrypt the password
				String delete = "DELETE FROM userDetails WHERE userName= ? AND AES_DECRYPT(password, 'YURT') = ?;";

				stmt = conn.prepareStatement(delete);

				stmt.setString(1, userName);
				stmt.setString(2, password);
				
				sendMessage("deleted");

				stmt.execute();

			} catch (Exception e) {

				e.printStackTrace();
				// TODO: handle exception
			}
			
		}//end if deleted
		
		
		} catch (Exception e) {
			   e.printStackTrace();
			  }	
	 }//end login user
  


/**
 * Send message.
 *
 * @param msg the msg
 */
public void sendMessage(String msg) {
	
		try{
			out.writeObject(msg);
			out.flush();
			//System.out.println("server>" + msg);
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
	
}//end sendMessage
}//end class


