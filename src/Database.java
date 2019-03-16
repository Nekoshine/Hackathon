import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Database {
	private static Connection connexion;

	private Database() {
		connexion = getConnection();
	}

	public static Connection getConnection() {
		if (connexion == null) {
			try {
				System.out.println("Initialising connection to Database");
				Database.connexion = DriverManager.getConnection("jdbc:mysql://localhost","unZgwZdVHJ", "9QMg89jteB");
				System.out.println("Connection to Hostel Database complete");
				return connexion;
			} catch (SQLException e) {
				System.err.println("Couldn't connect to DB : " + e.getMessage());
			}
		}
		System.out.println("Getting connexion to database...");
		return connexion;
	}
}
