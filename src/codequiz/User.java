package codequiz;

public class User {
	
	private int lives;
	private String name;
	private String password;
	private int userID;
	private long userPoints;
	private HogwartsHouse house;
	
	public User(String name, String password, int userID) {
		
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
	
	public long getUserPoints() {
		return userPoints;
	}
	
	public void removeUserPoints(long userPoints) {
		
	}
}
