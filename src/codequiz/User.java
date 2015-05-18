package codequiz;

/**
 * Klassen lagrar information om användaren.
 * @author gustavbodestad
 *
 */
public class User {
	
	private int lives;
	private String name = "Ingen inloggad";
	private String password;
	private int userID;
	private int userPoints;
	
	public User(String name, String password, int userID) {
		}
	
	public User() {
		System.out.println("User: newUser()");
		this.lives = 3;
		this.userPoints = 0;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPassword(String password) {
		this.password = password;	
	}
	
	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	public void setUserPoints(int userPoints) {
		this.userPoints = userPoints;
	}
	
	public void setLives(int lives) {
		this.lives = lives;
	}
	public int getLives(){
		return lives;
	}
	
	public int getUserPoints() {
		return userPoints;
	}
	
	public String getUser() {
		return name;
		
	}
	
	public void removeUserPoints(long userPoints) {
		
	}
}