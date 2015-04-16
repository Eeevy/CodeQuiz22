package codequiz;

public class User {
	
	private int lives = 2;
	private String name;
	private String password;
	private int userID;
	private int userPoints = 0;
	private HogwartsHouse house;
	
	public User(String name, String password, int userID) {
		}
	
	public User() {
		
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
	
	public void removeUserPoints(long userPoints) {
		
	}
}
