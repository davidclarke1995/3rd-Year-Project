
package ie.gmit.sw;

import java.io.Console;
import java.sql.*;
import java.util.*;

public class DbAccess {
	// https://www.javatpoint.com/example-to-connect-to-the-mysql-database

	/**
	 * @param args
	 * @throws SQLException
	 */
	@SuppressWarnings({ "unlikely-arg-type", "null" })
	public static void main(String args[]) throws SQLException {

		Scanner console = new Scanner(System.in);
		int option = 0;
		String name = "";
		String userName = "";
		String password = "";

		boolean loginSuccess = false;

		System.out.println(
				"Select 1 to view contents\nSelect 2 to add to DB\nSelect 3 to delete\nSelect 4 to Sign in-1 to exit");
		option = console.nextInt();

		while (option != -1) {
			if (option == 1) {

				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "");
					// System.out.println("1111");

					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery("SELECT * FROM userDetails");
					// System.out.println("1111");
					final ArrayList<User> users = new ArrayList<User>();

					while (rs.next()) {
						User user = new User();
						user.setName(rs.getString(1));
						user.setUserName(rs.getString(2));
						user.setPassword(rs.getString(3));
						users.add(user);

						System.out.println("" + user.getName() + " " + user.getUserName() + " " + user.getPassword());
						// System.out.println("1111");

						// conn.close();
					} // end while
				} catch (Exception e) {
					System.out.println(e);
				} // end catch
			} // end option 1 view
			else if (option == 2) 
			{
				try {

					User user = new User();

					System.out.println("Please Enter a name: ");
					name = console.next();
					user.setName(name);
					System.out.println("Please Enter Username: ");
					userName = console.next();
					user.setUserName(userName);
					System.out.println("Please enter a password: ");
					password = console.next();
					user.setPassword(password);
					System.out.println(name + " " + userName + " " + password);

					Class.forName("com.mysql.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/user", "root", "");
					PreparedStatement stmt = null;

					String sql = "INSERT INTO userDetails VALUES (? , ? , ?);";

					stmt = conn.prepareStatement(sql);
					stmt.setString(1, user.getName());
					stmt.setString(2, user.getUserName());
					stmt.setString(3, user.getPassword());

					stmt.execute();

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			else if (option == 3) 
			{

							try {

								User user = new User();
								String condition;

								System.out.println("Please Enter which userName you wish to delete: ");
								condition = console.next();
								// user.setUserName(condition);

								Class.forName("com.mysql.jdbc.Driver");
								Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/user", "root", "");
								PreparedStatement stmt = null;

								String delete = "DELETE FROM userDetails WHERE userName= ?;";

								stmt = conn.prepareStatement(delete);

								stmt.setString(1, condition);

								System.out.println(delete);
								stmt.execute();

							} catch (Exception e) {

								e.printStackTrace();
								// TODO: handle exception
							}
						}
			else {
				try {

					// User user = new User();

					String loginUserName;
					String loginPassword;

					System.out.println("Please enter username: ");
					loginUserName = console.next();

					System.out.println("Please enter password: ");
					loginPassword = console.next();

					Class.forName("com.mysql.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/user", "root", "");
					// System.out.println("1111");

					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery("SELECT * FROM userDetails");
					User user = null;
					final ArrayList<User> users = new ArrayList<User>();
					while (rs.next()) {
						user = new User();
						user.setName(rs.getString(1));
						user.setUserName(rs.getString(2));
						user.setPassword(rs.getString(3));
						// System.out.println(user.getName() + " " +
						// user.getPassword() + " " + user.getPassword() );
						users.add(user);
						user = null;
					}

					for (User u : users) {

						if (u.getUserName().equals(loginUserName) && u.getPassword().equals(loginPassword)) {
							loginSuccess = true;
							System.out.println("***");

						} else {
							System.out.println("nope");
						}

					}

					for (User u : users) {
						// System.out.println(u.toString());
						System.out.println(u.getUserName() + " " + u.getPassword());
					}

					System.out.println(users.size());

					/*
					 * for(int i =0;i<=users.size();i++) {
					 * System.out.println(users.toString()); }
					 */

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
			
			System.out.println("Select 1 to view contents\nSelect 2 to add to DB\nSelect 3 to delete\nSelect 4 to Sign in-1 to exit");
			option = console.nextInt();
			

		} // end while

		console.close();

	}// end main

}// end class
