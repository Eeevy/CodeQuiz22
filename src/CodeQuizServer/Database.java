package CodeQuizServer;
import java.io.Serializable;
import java.sql.*;
import java.util.LinkedList;

import codequiz.Question;

public class Database implements Serializable{
private LinkedList<Question> allQuestions = new LinkedList<Question>();

private static final String DATABASE_URL = "jdbc:mysql://94.254.94.236:51515/codequiz";
private static final String USERNAME = "Bob";
private static final String PASSWORD = "bob";

	/**
	 * databas
	 * @param args
	 */
	public void startQuestionDB() {
		System.out.println("Database: DBSTart()");
		try{
			Connection conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("select * from question");
			while(rs.next()){
//				System.out.println(rs.getString("Question"));
//				String question = rs.getString("Question");
//				System.out.println(question);
				
				Question question1 = new Question(rs.getInt("QuestionID"), rs.getString("Question"), rs.getString("Answer1"), 
						rs.getString("Answer2"), rs.getString("Answer3"), rs.getString("Answer4"), rs.getString("CorrectAnswer"), 
						rs.getInt("Level"));
				System.out.println(question1.getCorrectanswer());
				allQuestions.add(question1);
				System.out.println("Fråga tillagd");
				System.out.println("allQuestions: " + allQuestions.size());			
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
//	public void startSortingQuestionDB() {
//		System.out.println("Database: startSortingQuestion()");
//		try{
//			Connection conn2 = DriverManager.getConnection("jdbc:mysql://94.254.94.236:51515/codequiz", "Bob", "bob");
//			Statement stat = conn2.createStatement();
//			ResultSet rs1 = stat.executeQuery("select * from question");
//			while(rs.next()){
////				System.out.println(rs.getString("Question"));
////				String question = rs.getString("Question");
////				System.out.println(question);
//				
//				Question question1 = new Question(rs.getInt("QuestionID"), rs.getString("Question"), rs.getString("Answer1"), 
//						rs.getString("Answer2"), rs.getString("Answer3"), rs.getString("Answer4"), rs.getString("CorrectAnswer"), 
//						rs.getInt("Level"));
//				System.out.println(question1.getCorrectanswer());
//				allQuestions.add(question1);
//				System.out.println("Fråga tillagd");
//				System.out.println("allQuestions: " + allQuestions.size());			
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}
	
	public LinkedList<Question> returnQuestions(){
		System.out.println("Database: returnQuestions()" + allQuestions.size() );
		return allQuestions;		
	}
}