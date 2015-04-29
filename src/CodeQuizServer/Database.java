package CodeQuizServer;

import java.io.Serializable;
import java.sql.*;
import java.util.LinkedList;

import codequiz.Question;
import codequiz.SortingQuestion;

public class Database implements Serializable {
	private LinkedList<Question> allQuestions = new LinkedList<Question>();
	private LinkedList<SortingQuestion> allSortingQuestions = new LinkedList<SortingQuestion>();

	private static Connection conn;
	private static Statement stat;
	private static ResultSet rs;
	private static final String DATABASE_URL = "jdbc:mysql://94.254.94.236:51515/codequiz";
	private static final String USERNAME = "Bob";
	private static final String PASSWORD = "bob";

	/**
	 * databas
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

	public void getQuestionDB() {
		System.out.println("Database: getQuestionDB()");
		try {
			conn = connectToDB();
			stat = conn.createStatement();
			rs = stat.executeQuery("select * from question");
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
	
	public void getSortingQuestionDB() {
		System.out.println("Database: getSortingQuestionDB()");
		try {
			conn = connectToDB();
			stat = conn.createStatement();
			rs = stat.executeQuery("select * from sortingquestion");
			while (rs.next()) {
				SortingQuestion sortingQuestion = new SortingQuestion(rs.getInt("SortingQuestionID"),
						rs.getString("Question"), rs.getString("Answer1"),
						rs.getString("Answer2"), rs.getString("Answer3"),
						rs.getString("Answer4"), rs.getString("RavenclawAnswer"),
						rs.getString("GryffindorAnswer"), rs.getString("SlytherinAnswer"), rs.getString("HufflepuffAnswer"));
				System.out.println(sortingQuestion.getQuestion());
				allSortingQuestions.add(sortingQuestion);
				System.out.println("Fråga tillagd");
				System.out.println("allSortingQuestions: " + allSortingQuestions.size());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * För att kolla existerande kunders namn stämmer med lösen
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean checkUserDB(String username, String password) {
		System.out.println("Database: checkUser()");
		boolean res = false;
		try {
			conn = connectToDB();
			Statement stat = conn.createStatement();
			rs = stat.executeQuery("select * from player");
			while (rs.next()) {
				if ((password.equals(rs.getString("Password")))
						&& (username.equals(rs.getString("Username")))) {
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

	public LinkedList<Question> returnQuestions() {
		System.out.println("Database: returnQuestions()" + allQuestions.size());
		return allQuestions;
	}
	
	public LinkedList<SortingQuestion> returnSortingQuestions() {
		System.out.println("Database: returnSortingQuestions()");
		return allSortingQuestions;
	}

	public static void main(String[] args) {
		Database d = new Database();
		//d.getQuestionDB();
		// d.connectToDB();
		// d.setUserDB("Evelyn", "evelyn");
		d.getSortingQuestionDB();
		System.out.println(d.returnSortingQuestions());
//		System.out.println(d.checkUserDB("Emma", "emma"));
	}
}