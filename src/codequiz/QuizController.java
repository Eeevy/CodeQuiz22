package codequiz;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import CodeQuizServer.Game;
import CodeQuizServer.QuizScenario;

/**
 * Klassen hanterar programmets logik i bland annat UIs
 * 
 * @author CodeQuiz team
 *
 */
public class QuizController extends Thread {

	private Game game;
	private Question question = null;
	private QuizScenario scenario;
	private JPanel panel;
	private JFrame mainFrame;
	private Clip clip;
	private User user;
	private HogwartsHouse house;
	private Client client;
	private ResultUI resultui;
	private File musicFilename;
	private MainUI mainUI;
	private QuizUI quizUI;
	private HowToPlayUI howToUI;
	private int i = 0;
	private int index = -1;
	private Hashtable logininformation = new Hashtable();

	public QuizController() {
		play();//kopplar upp till servern här istället

		user = new User();
		int temp = getlives();
		System.out.println(temp);
		// System.out.println("" + getlives());
		System.out.println("QuizController: Konstruktor");
		musicFilename = new File("src/media/HarryPotterThemeSong.wav");
		quizUI = new QuizUI();
		mainUI = new MainUI();
		howToUI = new HowToPlayUI();
		panel = mainUI;
		quizUI.setController(this);
		mainUI.setController(this);
		howToUI.setController(this);
		showGUI();
		playSoundClip();
	}

	/**
	 * Metoden skapar ett fönster
	 */
	public void showGUI() {
		mainFrame = new JFrame("");
		mainFrame.setTitle("Code Quiz");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.add(panel);
		mainFrame.pack();
		mainFrame.setResizable(false);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
	}

	/**
	 * Metoden tar bort det aktuella fönstret och skapar ett nytt fönster
	 * 
	 * @param panel
	 */
	public void setPanel(JPanel panel) {
		closeWindow();
		this.panel = panel;
		clip.stop();
		showGUI();
		resetLives();
	}

	/**
	 * Stänger aktuellt fönster
	 */
	public void closeWindow() {
		mainFrame.dispose();
	}

	/**
	 * avbryter musik-klipp
	 */
	public void stopMusic() {
		clip.stop();
	}

	/**
	 * Spelar upp en ljudfil.
	 */
	public void playSoundClip() {
		try {
			AudioInputStream audioInputStream;
			AudioFormat audioFormat;
			DataLine.Info info;

			audioInputStream = AudioSystem.getAudioInputStream(musicFilename);
			audioFormat = audioInputStream.getFormat();
			info = new DataLine.Info(Clip.class, audioFormat);
			clip = (Clip) AudioSystem.getLine(info);
			clip.open(audioInputStream);
			clip.start();

		} catch (Exception e) {
			System.out.println("Ljudfilen kunde inte spelas upp.");
			e.printStackTrace();
		}
	}

	/**
	 * Metoden hämtar en fråga ur ett game objekt, skickar denna samt
	 * svarsalternativ till quizUI där de visas för användaren
	 */
	public void getQuestion() {
		// game = new Game();//ta bort
		this.question = game.getQuestion(i);
		quizUI.setQuestion(question.getQuestion());
		quizUI.setAlternatives(question.getAnswer1(), question.getAnswer2(),
				question.getAnswer3(), question.getAnswer4());
		i++;
	}
	
	/**
	 * Sköter registrering av nya användare.
	 * @param name
	 * @param password
	 * @param passConf
	 */
	public void newUser(String name, String password, String passConf) {

		if (!(password.equals(passConf))) {
			mainUI.newUser(name, "Dina angivna lösenord matchar inte");
			} else if(logininformation.contains(name)) {
				mainUI.newUser(name, "Användarnamnet är upptaget");
				} else if ((name.isEmpty()) || (password.isEmpty()) || (passConf.isEmpty())) {
					mainUI.newUser(name, "Det saknas uppgifter");
					} else {	
						System.out.print(password + name);
						setPanel(howToUI);//Emma was here
						howToUI.setWelcome(name);/////////////////////////////////////////
						
						logininformation.put(name, password);
					}
		}


		/**
		 * Sköter inloggning.
		 * @param inName
		 * @param inPass
		 */
		public void login(String inName, String inPass) {

		if (!(logininformation.containsKey(inName))) {
			mainUI.login(inName, "Användarnamnet saknas");
			} else if ((inName.isEmpty()) || (inPass.isEmpty())) {
				mainUI.login(inName, "Det saknas uppgifter");
				} else {
					if(inPass.equals(logininformation.get(inName))) {
						System.out.println(logininformation.get(inName));
					} else {
						mainUI.login(inName, "Fel lösenord");
						}
				}
		}

	/**
	 * 
	 * @return - rätt svar på aktuell fråga
	 */
	public String getCorrectAnswer() {
		return question.getCorrectanswer(); // ändrat till instansvariabeln
	}

	/**
	 * Metoden skapar ett Client-objekt som i sin tur kopplar upp med servern.
	 */
	public void play() {
		System.out.println("Controller: play()");
//		setPanel(quizUI);
		try {
			client = new Client("127.0.0.1", 3453, this);
			System.out.println("Klient skapad");
		} catch (IOException e) {
			System.out.println("Klienten kunde intekoppla upp");
		}
	}

	/**
	 * Ökar index för scenario
	 */
	public void increaseIndex() {
		index++;
	}

	/**
	 * Metoden returnerar en fråge-bild
	 * 
	 * @return - en bild på en fråga
	 */
	public ImageIcon setQuestionScenario() {
		scenario = game.getScenario(index);
		ImageIcon QPic = scenario.getStoryPic();
		return QPic;
		// ImageIcon QPic = new ImageIcon("src/media/diary.jpg");//fusk
		// return QPic;
	}

	/**
	 * Metoden returnerar en bild
	 * 
	 * @return - en om-rätt-svar-bild
	 */
	public ImageIcon setCorrectScenario() {
		scenario = game.getScenario(index);
		ImageIcon CPic = scenario.getCorrectPic();
		return CPic;
		// ImageIcon CPic = new ImageIcon("src/media/Correct.jpg");
		// return CPic;
	}

	/**
	 * Metoden returnerar en bild
	 * 
	 * @return - en om-fel-bild
	 */
	public ImageIcon setIncorrectScenario() {
		scenario = game.getScenario(index);
		ImageIcon IPic = scenario.getIncorrectPic();
		return IPic;
		// ImageIcon IPic = new ImageIcon("src/media/Incorrect.jpg");
		// return IPic;
	}

	/**
	 * 
	 * @return - huvudpanel
	 */
	public JPanel getMainUI() {
		return mainUI;
	}

	/**
	 * 
	 * @return - frågepanel
	 */
	public JPanel getQuizUI() {
		return quizUI;
	}
	/**
	 * 
	 * @return - hur spelar man-panelen
	 */
	public JPanel getHowToPlayUI(){
		return howToUI;
	}

	/**
	 * Metoden startar ett nytt spel, återställer index och spelar ljudklipp
	 */
	public void newGame() {
		quizUI = new QuizUI();
		quizUI.setController(this);
		index = -1;
		i = 0;
		System.out.print(index);
		playSoundClip();
	}

	/**
	 * 
	 * @param game
	 *            - ett spel-objekt
	 */
	public void setGame(Game game) {
		System.out.println("QuizController: setGame()");
		this.game = game;
		mainUI.enableMenu();
	}

	/**
	 * 
	 * @return - panel, visar resultat
	 */
	public JPanel getResultUI() {
		return resultui;
	}

	/**
	 * Initierar resultUI (JPanel), och anger vilket objekt som skall hantera
	 * logiken.
	 */
	public void newResultUI() {
		resultui = new ResultUI();
		resultui.setController(this);
	}

	/**
	 * Ökar användarens poäng
	 */
	public void increasePoints() {
		user.setUserPoints(user.getUserPoints() + 10);
	}

	/**
	 * Minskar spelares poäng
	 */
	public void decreasePoints() {
		user.setUserPoints(user.getUserPoints() - 10);
	}

	/**
	 * 
	 * @return - en spelares aktuella mängd liv
	 */
	public int getlives() {
		int lives = user.getLives();
		Integer.toString(lives);
		return lives;
	}

	/**
	 * 
	 * @return - en spelares aktuella mängd poäng
	 */
	public int getPoints() {
		int points = user.getUserPoints();
		Integer.toString(points);
		return points;
	}

	/**
	 * minskar en spelares mängd liv
	 */
	public void decreaseLives() {
		user.setLives(user.getLives() - 1);
	}

	/**
	 * Återställer en spelares liv till initiell mängd
	 */
	public void resetLives() {
		user.setLives(2);
	}

	/**
	 * Visar aktuell poäng i resultatpanelen
	 * 
	 * @param inScore
	 *            - poäng som skall visas
	 */
	public void setScore(int inScore) {
		resultui.setSlytherin(inScore);
	}

	/**
	 * 
	 * @return- returnerar true om max antal scenarion är uppnådda
	 */
	public boolean maxScenario() {
		return (index + 1) == game.getScenarioListSize();
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new QuizController();
			}
		});
	}
}