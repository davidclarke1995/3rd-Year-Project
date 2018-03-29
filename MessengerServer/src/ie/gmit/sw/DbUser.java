package ie.gmit.sw;
//package ie.gmit.sw;

public class DbUser {
	private String name;
	
public DbUser() {
		
	}
	
	public DbUser(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	private String userName;
	private String password;
	
	


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	

}
