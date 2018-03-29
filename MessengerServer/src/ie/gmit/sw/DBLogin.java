package ie.gmit.sw;

import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.net.*;


public class DBLogin extends Thread{
	Scanner console = new Scanner(System.in);
	  int option = 0;
	  String name = "";
	  String userName = "";
	  String password = "";
	  boolean loginSuccess = false;
	  Socket connection = null;
	  ObjectOutputStream out;
	  ObjectInputStream in;
	  String message = " ";
	  Server server;
	 // public DbLogin() {}
	  public DBLogin(){
		  
	  }
	  
	 
	  //constructor
	  public DBLogin(Server server, Socket s) {
			connection = s;
			this.server = server;
		  }
	  
	  //implement run method
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
				provider.outPutMessage(words[1] + words[2]);
				
				String loginUserName = words[1];
				String loginPassword = words[2];
		  } catch (Exception e) {
			   e.printStackTrace();
		  }
	  }


	public void sendMessage(String string) {
		// TODO Auto-generated method stub
		
	}

}//end class