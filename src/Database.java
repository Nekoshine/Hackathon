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
	public static boolean insertPlayer(Player player) {
			try {
				PreparedStatement requete = Database.getConnexion()
						.prepareStatement("INSERT INTO `Joueur`(`IDJoueur`, `Pseudo`, `Age`, `Sexe`, `ArgentDep`, `VieDep`) VALUES (?,?,?,?,?,?)");
				requete.setString(1, player.getPseudo());
				requete.setInt(2, player.getAge());
				requete.setString(3, player.getSexe());
				requete.setInt(4, player.getArgentDep());
				requete.setInt(5, player.getVieDep());
				ResultSet resultat = requete.executeQuery();
				requete.close();
				resultat.close();
				return true;
			} catch (SQLException e) {
				System.err.println("Erreur connexion : " + e.getMessage());
			}
			return false;
		}
		
	public static boolean insertAnswer(Answer answer) {
		try {
			PreparedStatement requete = Database.getConnexion()
					.prepareStatement("INSERT INTO Reponse VALUES (?,?,?,?,?,?)");
			requete.setInt(1, answer.getAnswerNumber());
			requete.setInt(2, answer.getPlayerNumber());
			requete.setInt(3, answer.getQuestionNumber());
			requete.setInt(4, answer.getHealth());
			requete.setInt(5, answer.getMoney());
			requete.setInt(6, answer.getStatus());
			ResultSet resultat = requete.executeQuery();
			requete.close();
			resultat.close();
			return true;
		} catch (SQLException e) {
			System.err.println("Erreur connexion : " + e.getMessage());
		}
		return false;
	}
}
