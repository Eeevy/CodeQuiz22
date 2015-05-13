package codequiz;

import java.awt.Color;
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

import CodeQuizServer.Database;
import CodeQuizServer.Game;
import CodeQuizServer.QuizScenario;
import CodeQuizServer.SortingCeremonyGame;

/**
 * Klassen hanterar programmets logik i bland annat UIs.
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
	private String house;
	private Client client;
	private ResultUI resultui;
	private File musicFilename;
	private File correctFilename;
	private File inCorrectFilename;
	private File winFilename;
	private File loseFilename;
	private File beginFilename;
	private MainUI mainUI;
	private QuizUI quizUI;
	private WinUI winUI;
	private HowToPlayUI howToUI;
	private SortingCeremonyUI sortUI;
	private HouseUI houseUI;
	private int i = 0;
	private int index = -1;
	private Hashtable logininformation = new Hashtable();
	private SortingQuestion sortingQuestion;
	private SortingCeremonyGame sortingCeremonyGame;
	private Database dbKlass;

	public QuizController() {

		user = new User();
		int temp = getlives();
		System.out.println(temp);
		// System.out.println("" + getlives());
		System.out.println("QuizController: Konstruktor");
		musicFilename = new File("src/media/HarryPotterThemeSong.wav");
		correctFilename = new File("src/media/Chime.wav");
		inCorrectFilename = new File("src/media/gasp.wav");
		winFilename = new File("src/media/fanfare3.wav");
		loseFilename = new File("src/media/coming.wav");
		quizUI = new QuizUI();
		mainUI = new MainUI(this);
		howToUI = new HowToPlayUI();
		createHouseUI();
		dbKlass = new Database();
		sortUI = new SortingCeremonyUI(houseUI);
		sortingCeremonyGame = new SortingCeremonyGame();
		panel = mainUI;
		quizUI.setController(this);
//		mainUI.setController(this);
		howToUI.setController(this);
		sortUI.setController(this);
		houseUI.setController(this);
		dbKlass.setController(this);
		showGUI();
		playSoundClip(musicFilename);
		play();// kopplar upp till servern här istället
	}
	
	public void createHouseUI() {
		houseUI = new HouseUI();
		houseUI.setController(this);
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
	public void playSoundClip(File inFilename) {
		try {
			AudioInputStream audioInputStream;
			AudioFormat audioFormat;
			DataLine.Info info;

			audioInputStream = AudioSystem.getAudioInputStream(inFilename);
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
		this.question = game.getQuestion(i);
		quizUI.setQuestion(question.getQuestion());
		quizUI.setAlternatives(question.getAnswer1(), question.getAnswer2(),
				question.getAnswer3(), question.getAnswer4());
		i++;
	}
	 
	public void getSortingQuestion() {
		this.sortingQuestion = sortingCeremonyGame.getSortingQuestion(i);
		sortUI.clearButtons();
		sortUI.setQuestion(sortingQuestion.getQuestion());
		sortUI.setAlternatives(sortingQuestion.getAnswer1(),
				sortingQuestion.getAnswer2(), sortingQuestion.getAnswer3(),
				sortingQuestion.getAnswer4());
		i++;
	}
	
	public void setHouse(String house){
		this.house = house;
	}
	public void disableGamebutton(){
		howToUI.enableGamebutton(false);
	}

	/**
	 * Sköter registrering av nya användare.
	 * 
	 * @param name
	 * @param password
	 * @param passConf
	 */
	public void newUser(String name, String password, String passConf) {
		
		if (!(password.equals(passConf))) {
			mainUI.newUser(name, "Dina angivna lösenord matchar inte");
		} else if (logininformation.contains(name)) {
			mainUI.newUser(name, "Användarnamnet är upptaget");
		} else if ((name.isEmpty()) || (password.isEmpty())
				|| (passConf.isEmpty())) {
			mainUI.newUser(name, "Det saknas uppgifter");
		} 
		else if(dbKlass.checkUser(name, password)==true){
			mainUI.newUser(name, "En användare med det här namnet finns redan");
		}
		else if(dbKlass.checkUser(name, password)==false){
			System.out.print(password + name);
			dbKlass.setUserDB(name, password);
			howToUI.enableGamebutton(true);	//OBS Måste läggas till att om användarnamn redan finns, 
										//bes denne att logga in igen, i skrivande stund kommer du vidare i alla fall
			setPanel(howToUI);
			howToUI.setWelcome(name);
			user.setName(name);
			mainUI.setUser(name);
			playSoundClip(beginFilename);
		}	
		
	}

	/**
	 * Sköter inloggning.
	 * 
	 * @param inName
	 * @param inPass
	 */
	public void login(String inName, String inPass) {

		if (!dbKlass.checkUserlogin(inName, inPass)) {
			mainUI.login(inName, "Användarnamnet saknas eller lösen är fel");
		} else if ((inName.isEmpty()) || (inPass.isEmpty())) {////////funkar ej
			mainUI.login(inName, "Det saknas uppgifter");
		} else {
//			if (inPass.equals(logininformation.get(inName))) {
//				System.out.println(logininformation.get(inName));
			if ((dbKlass.checkUserlogin(inName, inPass)) == true) {
				System.out.println((inName + " är inloggad"));
				user.setName(inName);
				mainUI.setUser(inName);
				getSortingQuestion();
//				setPanel(getSortingCeremonyUI());
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

	public String getAnswerRavenclaw() {
		return sortingQuestion.getRavenclawAnswer();
	}

	public String getAnswerGryffindor() {
		return sortingQuestion.getGryffindorAnswer();
	}

	public String getAnswerSlytherin() {
		return sortingQuestion.getSlytherinAnswer();
	}

	public String getAnswerHufflepuff() {
		return sortingQuestion.getHufflepuffAnswer();
	}

	/**
	 * Metoden skapar ett Client-objekt som i sin tur kopplar upp med servern.
	 */
	public void play() {
		System.out.println("Controller: play()");
		// setPanel(quizUI);
		enablePlayBtn(false);
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
	
	public void nextQuestion() {
		quizUI.nextQuestion();
	}

	/**
	 * 
	 * @return - hur spelar man-panelen
	 */
	public JPanel getHowToPlayUI() {
		return howToUI;
	}

	public JPanel getSortingCeremonyUI() {
		return sortUI;
	}

	public JPanel getHouseUI() {
		return houseUI;
	}

	/**
	 * Metoden startar ett nytt spel, återställer index och spelar ljudklipp
	 */
	public void newGame() {
		play();
		quizUI = new QuizUI();
		quizUI.setController(this);
		index = -1;
		i = 0;
		System.out.print(index);
		playSoundClip(musicFilename);
		user.setUserPoints(0);
		disableGamebutton();
	}

	/**
	 * 
	 * @param game
	 *            - ett spel-objekt
	 */
	public void setGame(Game game) {
		System.out.println("QuizController: setGame()");
		this.game = game;
		enablePlayBtn(true);
		mainUI.setBtnPlay();
	}
	
	public void setSortingCeremonyGame(SortingCeremonyGame sortingCeremonyGame) {
		System.out.println("QuizController: setSortingCeremonyGame()");
		this.sortingCeremonyGame = sortingCeremonyGame;
	}

	public void enablePlayBtn(boolean tof) {
		mainUI.enableMenu(tof);
	}
	
	public void setBtnPlay() {
		mainUI.setBtnPlay();
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
		System.out.println("QuizController: newResultUI()");

		resultui = new ResultUI();
		resultui.setController(this);
	}
	
	/**
	 * Kontrollerar om spelet är slut
	 * @return
	 */
	public boolean getScenarioIndex() {
		boolean res = false;
		if (index == (game.getScenarioListSize()-1)) {
			res = true;
		}
		return res;
		
	}

	/**
	 * Ökar användarens poäng
	 */
	public void increasePoints() {
		System.out.println("QuizController: increasePoints");

		user.setUserPoints(user.getUserPoints() + 10);
		playSoundClip(correctFilename);
	}

	/**
	 * Minskar spelares poäng
	 * @throws IOException 
	 */
	public void decreasePoints() {
		System.out.println("QuizController: decreasePoints");

		user.setUserPoints(user.getUserPoints() - 10);
		playSoundClip(inCorrectFilename);
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
		System.out.println("QuizController: getPoints: användarens poäng:" + points);

		Integer.toString(points);
		
		return points;
	}
	/**
	 * Adderar anv. poäng med elevhemmets poäng.
	 */
	public void userpoints(){
		System.out.println("QuizController: userPoints");
		int total = 0;
		int userpoints = getPoints();
		int housepoints = dbKlass.getHousePoints(house);
		System.out.println("QuizController: userPoints:" + userpoints + housepoints);

		total = userpoints + housepoints;
		dbKlass.setPointsDB(house,total);
	}
	/**
	 * Hämtar elevhemmens poäng och placerar ResultUI i den aktuella panelen.
	 */
	public void fetchhousepoints(){
		System.out.println("QuizController: fetchhousepoints");

		dbKlass.getPointsDB(getResultUI());
		setPanel(getResultUI());
	}
	
	
	/**
	 * minskar en spelares mängd liv
	 */
	public void decreaseLives() {
		user.setLives(user.getLives() - 1);
	}
	
	/**
	 * Metod som aktiveras vid vinst.
	 */
	public void win() {
		winUI = new WinUI(this);
		setPanel(winUI);
		playSoundClip(winFilename);
	}
	
	/**
	 * Metod som aktiveras vid förlust.
	 */
	public void lose() {
		playSoundClip(loseFilename);
	}

	/**
	 * Återställer en spelares liv till initiell mängd
	 */
	public void resetLives() {
		user.setLives(3);
	}
	
	/**
	 * Sätter användare som är inloggad.
	 * @param name
	 */
	public void setUser(String name) {
		user.setName(name);
		mainUI.setUser("");
	}
	
	/**
	 * Returnerar vilken användare som är inloggad.
	 * @return
	 */
	public String getUser() {
		String currentUser = user.getUser();
		return currentUser;
	}

	/**
	 * 
	 * @return- returnerar true om max antal scenarion är uppnådda
	 */
	public boolean maxScenario() {
		return (index + 1) == game.getScenarioListSize();
	}
	
	/**
	 * Sköter radbrytning.
	 * @param str
	 * @return
	 */
	public String changeStringB(String str) {
		int i = 0;
		String inStr = str;
		StringBuffer newString = new StringBuffer();
		newString.append("<html>");
		while (i < inStr.length()) {
			newString.append(inStr.charAt(i));
			i++;
		}
		newString.append("</html>");
		String hej = newString.toString();
		return hej;
	}
	

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new QuizController();
			}
		});
	}
}