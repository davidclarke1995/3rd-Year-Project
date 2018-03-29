package ie.gmit.sw;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

//make the server threaded to start a new instance for each connection
public class Server extends Thread{
private int port;
//create a list of providers so we can broadcast messages to each provider on the connection
private ArrayList<Provider> providerList = new ArrayList<>();
	public Server(int port) {
		this.port = port;
	}
	
	public Server() {
		
	}

	//get a list of providers currently connected to the server for messaging purposes
	public List<Provider> getProviderList(){
		return providerList;
	}
	
	//start the thread
	public void run(){
		ServerSocket providerSocket;
		try {
			providerSocket = new ServerSocket(port);
			//infinite loop while this server is running
			while(true){
				Socket connection = null;
				//Provider provider1 = new Provider();
				
				 connection = providerSocket.accept();
				//provider1.outPutMessage("port 8123");
				if(port == 80) {
					
					
					DBLogin login = new DBLogin(this, connection);
					login.start();
					
				}
				
			}
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		
	}
	
	public void startProviderMain(String userName) {
		Provider provider1 = new Provider();
		provider1.outPutMessage("inside method + " + userName);
		ServerSocket providerSocket;
		try {
			providerSocket = new ServerSocket(2004);
			while(true){
				
				Socket connection = null;
				//Provider provider1 = new Provider();
				
				 connection = providerSocket.accept();
				//provider1.outPutMessage("port 8123");
				//if(port == 2004) {
					
					
					Provider provider = new Provider(this, connection, userName);
					providerList.add(provider);
					provider.start();
					
				//}
				
				
					
					
				
				
				
				
				
			}
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		
		
		
	}	
		
	

}


