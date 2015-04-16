package codequiz;

public class User {
	
	private int lives;
	private String name;
	private String password;
	private int userID;
	private int userPoints;
	private HogwartsHouse house;
	
	public User(String name, String password, int userID) {
		}
	
	public User() {
		this.lives = 2;
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
	
	public void removeUserPoints(long userPoints) {
		
	}
}
