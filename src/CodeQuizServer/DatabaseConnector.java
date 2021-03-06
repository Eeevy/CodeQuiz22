package CodeQuizServer;

import java.io.Serializable;

import codequiz.ResultUI;

import java.sql.*;
import java.util.LinkedList;

import javax.swing.JPanel;

import codequiz.BossQuestion;
import codequiz.Question;
import codequiz.QuizController;
import codequiz.SortingQuestion;

/**
 * En klass som hämtar och placerar data i databasen.
 * @author Johan
 *
 */
public class DatabaseConnector implements Serializable {
	
	private LinkedList<Question> allQuestions = new LinkedList<Question>();
	private LinkedList<SortingQuestion> allSortingQuestions = new LinkedList<SortingQuestion>();
	private LinkedList<BossQuestion> allBossQuestions = new LinkedList<BossQuestion>();

	
	private static Connection conn;
	private static Statement stat;
	private static ResultSet rs;
	private static final String DATABASE_URL = "jdbc:mysql://94.254.94.236:51515/codequiz";
	private static final String USERNAME = "Bob";
	private static final String PASSWORD = "bob";
	private QuizController controller;

	public void setController(QuizController c) {
		controller = c;
	}

	/**
	 * Startar en uppkoppling mot databasen.
	 * 
	 * @param args
	 * @return
	 */
	public Connection connectToDB() {
		System.out.println("Database: DBSTart()");
		try {
			conn = DriverManager
					.getConnection(DATABASE_URL, USERNAME, PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * Metod som hämtar slumpade frågor från databasen.
	 */
	public void getQuestionDB() {
		System.out.println("Database: getQuestionDB()");
		try {
			conn = connectToDB();
			stat = conn.createStatement();
			rs = stat.executeQuery("select * from question where Level=1 order by rand() limit 3");
			while (rs.next()) {
				Question question1 = new Question(rs.getInt("QuestionID"),
						rs.getString("Question"), rs.getString("Answer1"),
						rs.getString("Answer2"), rs.getString("Answer3"),
						rs.getString("Answer4"), rs.getString("CorrectAnswer"),
						rs.getInt("Level"));
				System.out.println(question1.getCorrectanswer());
				allQuestions.add(question1);
				System.out.println("Fråga tillagd");
				System.out.println("allQuestions: " + allQuestions.size());
			}
			rs = stat.executeQuery("select * from question where Level=2 order by rand() limit 3");
			while (rs.next()) {
				Question question1 = new Question(rs.getInt("QuestionID"),
						rs.getString("Question"), rs.getString("Answer1"),
						rs.getString("Answer2"), rs.getString("Answer3"),
						rs.getString("Answer4"), rs.getString("CorrectAnswer"),
						rs.getInt("Level"));
				System.out.println(question1.getCorrectanswer());
				allQuestions.add(question1);
				System.out.println("Fråga tillagd");
				System.out.println("allQuestions: " + allQuestions.size());
			}
			rs = stat.executeQuery("select * from question where Level=3 order by rand() limit 3");
			while (rs.next()) {
				Question question1 = new Question(rs.getInt("QuestionID"),
						rs.getString("Question"), rs.getString("Answer1"),
						rs.getString("Answer2"), rs.getString("Answer3"),
						rs.getString("Answer4"), rs.getString("CorrectAnswer"),
						rs.getInt("Level"));
				System.out.println(question1.getCorrectanswer());
				allQuestions.add(question1);
				System.out.println("Fråga tillagd");
				System.out.println("allQuestions: " + allQuestions.size());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * En metod som hämtar bossfrågor från databasen.
	 */
	public void getBossQuestionDB() {
		System.out.println("Database: getBossQuestionDB()");
		try {
			conn = connectToDB();
			stat = conn.createStatement();
			rs = stat.executeQuery("select * from bossquestion order by rand() limit 5");
			while (rs.next()) {
				
				BossQuestion question = new BossQuestion(rs.getString("CodeQuestion"), 
						rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
				System.out.println(question.getAnswer1());
				allBossQuestions.add(question);
				System.out.println("Fråga tillagd");
				System.out.println("allBossQuestions: " + allBossQuestions.size());
			}
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metod som hämtar slumpade sorteringsfrågor från databasen.
	 */
	public void getSortingQuestionDB() {
		System.out.println("Database: getSortingQuestionDB()");
		try {
			conn = connectToDB();
			stat = conn.createStatement();
			rs = stat.executeQuery("select * from sortingquestion order by rand() limit 1");
			while (rs.next()) {
				SortingQuestion sortingQuestion = new SortingQuestion(
						rs.getInt("SortingQuestionID"),
						rs.getString("Question"), rs.getString("Answer1"),
						rs.getString("Answer2"), rs.getString("Answer3"),
						rs.getString("Answer4"),
						rs.getString("RavenclawAnswer"),
						rs.getString("GryffindorAnswer"),
						rs.getString("SlytherinAnswer"),
						rs.getString("HufflepuffAnswer"));
				System.out.println(sortingQuestion.getQuestion());
				allSortingQuestions.add(sortingQuestion);
				System.out.println("Fråga tillagd");
				System.out.println("allSortingQuestions: "
						+ allSortingQuestions.size());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Metod som kontrollerar om användare finns eller ej.
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean checkUser(String username, String password) {
		System.out.println("Database: checkUser()");
		boolean res = false;
		try {
			conn = connectToDB();
			Statement stat = conn.createStatement();
			rs = stat.executeQuery("select * from player");
			while (rs.next()) {
				if (((username.equals(rs.getString("Username"))))) {
					System.out.println("Lösen är:" + rs.getString("Password"));
					res = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;

	}

	/**
	 * För att kolla existerande kunders namn stämmer med lösen
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean checkUserlogin(String username, String password) {
		System.out.println("Database: checkUser()");
		boolean res = false;
		try {
			conn = connectToDB();
			Statement stat = conn.createStatement();
			rs = stat.executeQuery("select * from player");
			while (rs.next()) {
				if (((username.equals(rs.getString("Username"))))
						&& password.equals(rs.getString("Password"))) {
					System.out.println("Lösen är:" + rs.getString("Password"));
					res = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;

	}

	/**
	 * Lagrar ny användare
	 * 
	 * @param username
	 * @param password
	 */
	public void setUserDB(String username, String password) {
		System.out.println("Database: setUserDB()");
		try {
			conn = connectToDB();
			Statement stat = conn.createStatement();
			String sql = "insert into player " + "(Username, Password)"
					+ "values(" + "'" + username + "', '" + password + "'"
					+ ")";
			stat.executeUpdate(sql);
			System.out.println("Ok! User added!");

		} catch (Exception e) {
			System.out.println("Användarnamnet upptaget, försök igen");
		}
	}
/**
 * Metod som hämtar elevhemspoäng.
 * @param rUI
 */
	public void getPointsDB(JPanel rUI) {
		ResultUI r = (ResultUI) rUI;

		System.out.println("Database: getPointsDB()");
		try {
			conn = connectToDB();
			stat = conn.createStatement();
			String sql = "select * from house";
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				System.out.println("Poäng: " + rs.getString("HouseName")
						+ rs.getInt("Points"));
				int housePoints = rs.getInt("Points");
				String houseType = rs.getString("HouseName");
				if (houseType.equals("Hufflepuff")) {
					r.setHufflepuff(housePoints);
					System.out.println("Det är hufflepuff!");
				}
				if (houseType.equalsIgnoreCase("Slytherin")) {
					r.setSlytherin(housePoints);
					System.out.println("Det är Slytherin!");

				}
				if (houseType.equals("Ravenclaw")) {
					r.setRavenclaw(housePoints);
					System.out.println("Det är Ravenclaw!");

				}
				if (houseType.equals("Gryffindor")) {
					r.setGryffindor(housePoints);
					System.out.println("Det är Gryffindor!");

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Metod som hämtar och returnerar elevhemspoäng.
	 * @return
	 */
	public int getHousePoints(String houseType){
		int housePoints = 0;


		System.out.println("Database: getHousePointsDB() aktuellt hus som poäng skall slås ihop med:" + houseType);
		try {
			conn = connectToDB();
			stat = conn.createStatement();
			String sql = "select * from house WHERE HouseName=" + "'" + houseType + "'";
			rs = stat.executeQuery(sql);
			
			while (rs.next()) {
			housePoints = rs.getInt("Points");
			System.out.println(housePoints);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return housePoints;
	}
/**
 * Metod som placerar in den totala poängen i ett elevhem.
 * @param housename
 * @param points
 */
	public void setPointsDB(String housename, int points) {
		System.out.println("Database: setpoints:"  + housename + points);

		System.out.println("Database: setPointsDB()");
		try {
			conn = connectToDB();
			stat = conn.createStatement();
			String sql = "update house set Points=" + "'" + points + "'"
					+ "where HouseName= " + "'" + housename + "'";
			stat.executeUpdate(sql);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
/**
 * Metod som placerar alla frågor i en LinkedList.
 * @return
 */
	public LinkedList<Question> returnQuestions() {
		System.out.println("Database: returnQuestions()" + allQuestions.size());
		return allQuestions;
	}
	
	public LinkedList<BossQuestion> returnBQuestions(){
		return allBossQuestions;
	}
/**
 * Metod som placerar sorteringsfrågor i en LinkedList.
 * @return
 */
	public LinkedList<SortingQuestion> returnSortingQuestions() {
		System.out.println("Database: returnSortingQuestions()");
		return allSortingQuestions;
	}

	public static void main(String[] args) {
		DatabaseConnector d = new DatabaseConnector();
		 d.getQuestionDB();
		// d.connectToDB();
		// d.setUserDB("Evelyn", "evelyn");
//		d.getSortingQuestionDB();
		// System.out.println(d.returnSortingQuestions());
		// System.out.println(d.checkUserDB("Emma", "emma"));
		// d.setPointsDB("Slytherin", 20);
		//d.getPointsDB();
//		d.getHousePoints("Slytherin");
	}
}