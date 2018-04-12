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

//import com.mysql.fabric.Server;

//import ie.gmit.sw.Server;

public class DBLogin extends Thread{
 


  Scanner console = new Scanner(System.in);
  int option = 0;
  String name = "";
  String userName = "";
  String password = "";
  boolean loginSuccess = false;
  //boolean invalid = false;
  int counter = 0;
  Socket connection = null;
  ObjectOutputStream out;
  ObjectInputStream in;
  String message = " ";
  Server server;
  public DBLogin() {}
  
  DBLogin(Server server, Socket s) {
		connection = s;
		this.server = server;
	  }
  

public void run() {
  try {

   
	//2. Wait for connection
		//System.out.println("Waiting for connection");
	  Provider provider = new Provider();
		
		//System.out.println("Connection received from " + connection.getInetAddress().getHostName());
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
		String[] words = message.split(" ");
		
		
		if(words[0].equals("login")) {
			provider.outPutMessage(words[1] + words[2]);

			String loginUserName = words[1];
			String loginPassword = words[2];
			
			Class.forName("com.mysql.jdbc.Driver");
			   Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "");
		   // System.out.println("1111");

			   Statement stmt = conn.createStatement();
			   ResultSet rs = stmt.executeQuery("SELECT * FROM userDetails");
			   User user = null;
			   final ArrayList < User > users = new ArrayList < User > ();
			   while (rs.next()) {
			    user = new User();
			    user.setName(rs.getString(1));
			    user.setUserName(rs.getString(2));
			    user.setPassword(rs.getString(3));
			    // System.out.println(user.getName() + " " + user.getPassword() + " " +
			    // user.getPassword() );
			    users.add(user);
			    user = null;
			   }
			   
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
				    
				    
				    /*
				    else {
				    	
				    	provider.outPutMessage("nope");
				    	sendMessage("nope");
				    }
				    */
				
				   }//end for users
			   
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

				//System.out.println("Please Enter a name: ");
				name = words[1];
				user.setName(name);
				//System.out.println("Please Enter Username: ");
				userName = words[2];
				user.setUserName(userName);
				//System.out.println("Please enter a password: ");
				password = words[3];
				user.setPassword(password);
				System.out.println(name + " " + userName + " " + password);

				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "");
				PreparedStatement stmt = null;

				String sql = "INSERT INTO userDetails VALUES (? , ? , ?);";

				stmt = conn.prepareStatement(sql);
				stmt.setString(1, user.getName());
				stmt.setString(2, user.getUserName());
				stmt.setString(3, user.getPassword());

				stmt.execute();
				
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

				//System.out.println("Please Enter which userName you wish to delete: ");
				userName = words[1];
				password = words[2];
				user.setUserName(userName);
				user.setPassword(password);
				// user.setUserName(condition);

				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "");
				PreparedStatement stmt = null;

				String delete = "DELETE FROM userDetails WHERE userName= ? AND password = ?;";

				stmt = conn.prepareStatement(delete);

				stmt.setString(1, userName);
				stmt.setString(2, password);
				
				sendMessage("deleted");

				//System.out.println(delete);
				stmt.execute();

			} catch (Exception e) {

				e.printStackTrace();
				// TODO: handle exception
			}
			
		}//end if deleted
		
		
		} catch (Exception e) {
			   e.printStackTrace();
			  }
 
			
		//end if login
			
		
		
	/*
	   System.out.println("Please enter username: ");
	   loginUserName = console.next();
	
	   System.out.println("Please enter password: ");
	   loginPassword = console.next();
	*/
	   
	
	  
	   
	   
	
	   //provider.outPutMessage(users.size());
	
	   /*
	    * for(int i =0;i<=users.size();i++) { System.out.println(users.toString()); }
	    */
	
	 
	  
  
	  
	  //end if
	 }//end login user
  


public void sendMessage(String msg) {
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


}//end sendMessage
}


