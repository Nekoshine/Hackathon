import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class Database {
	private static Connection connexion;

	private Database() {
		connexion = getConnexion();
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
				Database.connexion = DriverManager.getConnection("jdbc:mysql://remotemysql.com/unZgwZdVHJ","unZgwZdVHJ", "9QMg89jteB");
				System.out.println("Connection to Hostel Database complete");
				return connexion;
			} catch (SQLException e) {
				System.err.println("Couldn't connect to DB : " + e.getMessage());
			}
		}
		System.out.println("Getting connexion to database...");
		return connexion;
	}

	public static int getRandom(int categorie){
		Random rand = new Random();
		int id=0;
		try {
			PreparedStatement requete = Database.getConnexion()
					.prepareStatement("SELECT COUNT(*) FROM Question WHERE Categorie=?");
			requete.setInt(1, categorie);
			ResultSet resultat = requete.executeQuery();
			resultat.next();
			int count=resultat.getInt("count(*)");
			id = rand.nextInt(count)+1;
			requete.close();
			resultat.close();
		} catch (SQLException e) {
			System.err.println("Erreur connexion : " + e.getMessage());
		}
		return id;
	}

	public static Question getSituation(int id,int categorie){
		try {
			PreparedStatement requete = Database.getConnexion()
					.prepareStatement("SELECT * FROM Question WHERE IDQuestion=? and Categorie=?");
			requete.setInt(1, id);
			requete.setInt(2, categorie);
			ResultSet resultat = requete.executeQuery();
			resultat.next();
			Question situation = new Question(resultat.getInt("IDQuestion"), resultat.getString("Texte"),
						new Choice(resultat.getInt("NextD"),resultat.getInt("ArgentD"),resultat.getInt("VieD"),resultat.getString("TexteD")),
						new Choice(resultat.getInt("NextG"),resultat.getInt("ArgentG"),resultat.getInt("VieG"),resultat.getString("TexteG")),
								resultat.getString("image"));
			requete.close();
			resultat.close();
			return situation;
		} catch (SQLException e) {
			System.err.println("Erreur connexion : " + e.getMessage());
		}
		return null;
	}

	public static void main(String[] args) {
		Database.getConnexion();
	}
}
