package ie.gmit.sw;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server extends Thread{
private int port;
//create a list of providers so we can broadcast messages to each provider on the connection
private ArrayList<Provider> providerList = new ArrayList<>();
	public Server(int port) {
		this.port = port;
	}
		
	public List<Provider> getProviderList(){
		return providerList;
	}
	public void run(){
		ServerSocket providerSocket;
		try {
			providerSocket = new ServerSocket(port);
			while(true){
				Socket connection = null;
				connection = providerSocket.accept();
				Provider provider = new Provider(this, connection);
				providerList.add(provider);
				provider.start();
				
			}
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		
	}
		
	

}

