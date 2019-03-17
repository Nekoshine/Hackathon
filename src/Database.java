import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
	public static Player insertPlayer(Player player) {
			try {
				PreparedStatement requete = Database.getConnexion()
						.prepareStatement("INSERT INTO `Joueur`( `Pseudo`, `Age`, `Sexe`, `ArgentDep`, `VieDep`) VALUES (?,?,?,?,?)");
				requete.setString(1, player.getPseudo());	
				requete.setInt(2, player.getAge());
				requete.setString(3, player.getSexe());
				requete.setInt(4, player.getMoneyStrt());
				requete.setInt(5, player.getHealthStrt());
				requete.executeUpdate();
				requete.close();
				PreparedStatement requete2 = Database.getConnexion()
						.prepareStatement("Select max(IDJoueur) from Joueur ");
				ResultSet resultat = requete2.executeQuery();
				resultat.next();
				player.setId(resultat.getInt("max(IDJoueur)"));
				requete2.close();
				resultat.close();
				return player;
			} catch (SQLException e) {
				System.err.println("Erreur connexion : " + e.getMessage());
			}
			return player;
		}
		
	public static boolean insertAnswer(Answer answer) {
		System.out.println(answer.getMoney());
		System.out.println(answer.getHealth());
		System.out.println(answer.getQuestionNumber());
		System.out.println(answer.getPlayerNumber());
		
		try {
			PreparedStatement requete = Database.getConnexion()
					.prepareStatement("INSERT INTO `Reponse`( NumeroJoueur, NumeroQuestion, `VieActuelle`, `ArgentActuel`) VALUES (?,?,?,?)");
			requete.setInt(1, answer.getPlayerNumber());
			requete.setInt(2, answer.getQuestionNumber());
			requete.setInt(3, answer.getHealth());
			requete.setInt(4, answer.getMoney());
			requete.executeUpdate();
			requete.close();	
			return true;
		} catch (SQLException e) {
			System.err.println("Erreur connexion : " + e.getMessage());
		}
		return false;
	}

	public static boolean insertQuestion(Question question) {
			try {
				PreparedStatement requete = Database.getConnexion()
						.prepareStatement("INSERT INTO `Question`(`Texte`, `ArgentG`, `VieG`, `Image`, `TexteG`, `TexteD`, `Categorie`, `ArgentD`, `VieD`, `NextD`, `NextG`) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
				requete.setString(1, question.getQuestion());	
				requete.setInt(2, question.getLeftChoice().getMoneyCost());
				requete.setInt(3, question.getLeftChoice().getHealthCost());
				requete.setString(4, question.getImage());
				requete.setString(5, question.getLeftChoice().getText());
				requete.setString(6, question.getRightChoice().getText());
				requete.setInt(7, question.getCategorie());
				requete.setInt(8, question.getRightChoice().getMoneyCost());
				requete.setInt(9, question.getRightChoice().getHealthCost());
				requete.setInt(10, question.getRightChoice().getEnding());
				requete.setInt(11, question.getLeftChoice().getEnding());
				requete.executeUpdate();
				requete.close();
				return true;
			} catch (SQLException e) {
				System.err.println("Erreur connexion : " + e.getMessage());
			}
			return false;
		}
	public static boolean insertStory(Story story){
		try {
			PreparedStatement requete= Database.getConnexion()
					.prepareStatement("INSERT INTO `Histoire`(`Name`, `List`) VALUES (?,?)");
			requete.setString(1,story.getName());
			requete.setString(2, story.getListeSituation());
			requete.executeUpdate();
			requete.close();
			return true;
		}catch (SQLException e) {
			System.err.println("Erreur connextion : " + e.getMessage());
		}
		return false;
	}
	
	public static Story getStory(int id) {
		try {
			PreparedStatement requete= Database.getConnexion()
					.prepareStatement("Select * from Histoire where Id=?");
			requete.setInt(1,id);
			ResultSet resultat = requete.executeQuery();
			resultat.next();
			Story story = new Story(resultat.getString("Name"),resultat.getString("List"));
			requete.close();
			resultat.close();
			return story;
		}catch (SQLException e) {
			System.err.println("Erreur connextion : " + e.getMessage());
		}
		return null;
	}

	public static ArrayList<String> getStoriesName() {
		try {
			PreparedStatement requete= Database.getConnexion()
					.prepareStatement("Select Name from Histoire");
			ResultSet resultat = requete.executeQuery();
			ArrayList<String> stories = new ArrayList<>();
			while (resultat.next()) {
				stories.add(resultat.getString("Name"));
			}
			requete.close();
			resultat.close();
			return stories;
		}catch (SQLException e) {
			System.err.println("Erreur connextion : " + e.getMessage());
		}
		return null;
	}

	public static ArrayList<QuestionTmp> getQuestionsName() {
		try {
			PreparedStatement requete= Database.getConnexion()
					.prepareStatement("Select * from Question");
			ResultSet resultat = requete.executeQuery();
			ArrayList<QuestionTmp> stories = new ArrayList<>();
			while (resultat.next()) {
				if (resultat.getInt("Categorie")<=10) {
					stories.add(new QuestionTmp(resultat.getString("Texte"),resultat.getInt("IDQuestion")));
				}
			}
			requete.close();
			resultat.close();
			return stories;
		}catch (SQLException e) {
			System.err.println("Erreur connextion : " + e.getMessage());
		}
		return null;
	}
		
}
