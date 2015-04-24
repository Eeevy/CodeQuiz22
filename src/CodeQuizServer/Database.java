package CodeQuizServer;
import java.sql.*;

public class Database {

	/**
	 * databas
	 * @param args
	 */
	public static void main(String [] args) {
		try{
			Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.0.101/:3306/Question", "root", "qweasd123");
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("select * from Question");
			
			while(rs.next()){
				System.out.println(rs.getString("QuestionID") + rs.getString("Question")+ "\n"
				+ rs.getString("Answer1"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
