import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Database {
	private static Connection connexion;

	private Database() {
		getConnexion();
	}

	public static Connection getConnexion() {
		try {
		 Class.forName("com.mysql.jdbc.Driver");
	 } catch(ClassNotFoundException e) {
			 System.err.println("Unable to load MySQL Driver " + e.getMessage());
		 e.printStackTrace();
	 }
		if (connexion == null) {
			try {
				System.out.println("Initialising connection to Database");
				Database.connexion = DriverManager.getConnection("jdbc:mysql://remotemysql.com/","unZgwZdVHJ", "9QMg89jteB");
				System.out.println("Connection to Hostel Database complete");
				return connexion;
			} catch (SQLException e) {
				System.err.println("Couldn't connect to DB : " + e.getMessage());
			}
		}
		System.out.println("Getting connexion to database...");
		return connexion;
	}
	public static void main(String[] args) {
		Database.getConnexion();
	}
}
