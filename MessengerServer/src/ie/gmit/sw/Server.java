package ie.gmit.sw;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread{
private int port;
	public Server(int port) {
		this.port = port;
	}
		// TODO Auto-generated constructor stub
	public void run(){
		ServerSocket providerSocket;
		try {
			providerSocket = new ServerSocket(port);
			while(true){
				Socket connection = null;
				connection = providerSocket.accept();
				Provider provider = new Provider(connection);
				provider.start();
				
			}
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		
	}
		
	

}

