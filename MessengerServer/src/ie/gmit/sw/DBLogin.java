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
	  
	 
	  
	  public DBLogin(Server server, Socket s) {
			connection = s;
			this.server = server;
		  }

}//end class
