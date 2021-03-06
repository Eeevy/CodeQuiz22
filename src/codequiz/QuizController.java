package codequiz;

import java.awt.Color;
import java.io.*;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Random;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import CodeQuizServer.DatabaseConnector;
import CodeQuizServer.Game;
import CodeQuizServer.QuizScenario;

/**
 * Klassen hanterar programmets logik i bland annat UIs.
 * 
 * @author CodeQuiz team
 *a
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
	private File musicFilename,correctFilename,inCorrectFilename,winFilename,
	loseFilename,beginFilename;
	private MainUI mainUI;
	private QuizUI quizUI;
	private WinUI winUI;
	private HowToPlayUI howToUI;
	private SortingCeremonyUI sortUI;
	private HouseUI houseUI;
	private BossUI bossUI;
	private BossQuestion bQuestion;
	private int qIndex = 0;
	private int index = -1;
	private int bossIndex = 0;
	private SortingQuestion sortingQuestion;
	private DatabaseConnector dbKlass;

	public QuizController() {

		user = new User();
		int temp = getlives();
		musicFilename = new File("src/media/HarryPotterThemeSong.wav");
		correctFilename = new File("src/media/Chime.wav");
		inCorrectFilename = new File("src/media/gasp.wav");
		winFilename = new File("src/media/fanfare3.wav");
		loseFilename = new File("src/media/scream_male.wav");
		beginFilename = new File("src/media/creak.wav");

		quizUI = new QuizUI();
		mainUI = new MainUI(this);
		howToUI = new HowToPlayUI();
//		bossUI = new BossUI(this);
		createHouseUI();
		dbKlass = new DatabaseConnector();
		sortUI = new SortingCeremonyUI(houseUI);
		panel = mainUI;
		quizUI.setController(this);
		howToUI.setController(this);
		sortUI.setController(this);
		houseUI.setController(this);
		dbKlass.setController(this);
		showGUI();
		playSoundClip(musicFilename);
		play();// kopplar upp till servern här istället
	}
	/**
	 * 
	 */
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
		this.question = game.getQuestion(qIndex);
		quizUI.setQuestion(question.getQuestion());
		quizUI.setAlternatives(question.getAnswer1(), question.getAnswer2(),
				question.getAnswer3(), question.getAnswer4());
		System.out.println("RÄTT SVAR:" + question.getCorrectanswer());
		qIndex++;
	}
	/**
	 * Metod som hämtar en fråga ur ett game objekt, skickar denna samt
	 * svarsalternativ till sortUI där de visas för användaren 
	 */
	public void getSortingQuestion() {
		this.sortingQuestion = game.getSortingQuestion(0);
		System.out.println("Controller: getSortingQuestion");
		sortUI.clearButtons();
		sortUI.setQuestion(sortingQuestion.getQuestion());
		sortUI.setAlternatives(sortingQuestion.getAnswer1(),
				sortingQuestion.getAnswer2(), sortingQuestion.getAnswer3(),
				sortingQuestion.getAnswer4());
	}
	/**
	 * Metod som hämtar en fråga ur ett game objekt, skickar denna samt
	 * svarsalternativ till bossUI där de visas för användaren 
	 */
	public void getBossQuestion() {
		System.out.println("Controller: getBossQuestion");
		this.bQuestion = game.getBossQuestion(bossIndex);
		bossUI.setQuestion(bQuestion.getCodeEx(), bQuestion.getAnswer1(), 
				bQuestion.getAnswer2(), bQuestion.getAnswer3(), bQuestion.getAnswer4());
		System.out.println("pic: " + bQuestion.getCodeEx());
		bossIndex++;
		System.out.println(bossIndex);
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
			stopMusic();
			playSoundClip(beginFilename);
			mainUI.setLoginBtn(false);
			mainUI.setBtnLogout(true);
			mainUI.setBtnNewUser(false);
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
			if ((dbKlass.checkUserlogin(inName, inPass)) == true) {
				System.out.println((inName + " är inloggad"));
				user.setName(inName);
				mainUI.setUser(inName);
				getSortingQuestion();
				mainUI.setLoginBtn(false);
				mainUI.setBtnLogout(true);
				mainUI.setBtnNewUser(false);
			} else {
				mainUI.login(inName, "Fel lösenord");
			}
		}
	}
	/**
	 * 
	 * @return - rätt svar på aktuell bossfråga
	 */
	public String getCorrectBossAnswer(){
		return bQuestion.getCorrectAnswer();
	}

	/**
	 * 
	 * @return - rätt svar på aktuell fråga
	 */
	public String getCorrectAnswer() {
		return question.getCorrectanswer();
	}
	/**
	 * 
	 * @return - rätt svar på aktuell fråga
	 */
	public String getAnswerRavenclaw() {
		return sortingQuestion.getRavenclawAnswer();
	}
	/**
	 * 
	 * @return - rätt svar på aktuell fråga
	 */
	public String getAnswerGryffindor() {
		return sortingQuestion.getGryffindorAnswer();
	}
	/**
	 * 
	 * @return - rätt svar på aktuell fråga
	 */
	public String getAnswerSlytherin() {
		return sortingQuestion.getSlytherinAnswer();
	}
	/**
	 * 
	 * @return - rätt svar på aktuell fråga
	 */
	public String getAnswerHufflepuff() {
		return sortingQuestion.getHufflepuffAnswer();
	}

	/**
	 * Metoden skapar ett Client-objekt som i sin tur kopplar upp med servern.
	 */
	public void play() {
		System.out.println("Controller: play()");
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
	/**
	 * 
	 * @return- BossUI
	 */
	public JPanel getBossUI(){
		bossUI = new BossUI(this);
		return bossUI;
	}
	/**
	 * Tar fram nästa fråga
	 */
	public void nextQuestion() {
		quizUI.nextQuestion();
	}

	/**
	 * 
	 * @return - hur man spelar-panelen
	 */
	public JPanel getHowToPlayUI() {
		return howToUI;
	}
	/**
	 * 
	 * @return - sorteringscermoniUI
	 */
	public JPanel getSortingCeremonyUI() {
		return sortUI;
	}
	/**
	 * 
	 * @return - ElevhemsUI
	 */
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
		qIndex = 0;
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
	/**
	 * Aktiverar spela knappen
	 * @param tof
	 */
	public void enablePlayBtn(boolean tof) {
		mainUI.enableMenu(tof);
	}
	/**
	 * Lägger till text till knappen
	 */
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

		user.setUserPoints(user.getUserPoints() + 10);
		playSoundClip(correctFilename);
	}

	/**
	 * Minskar spelares poäng
	 * @throws IOException 
	 */
	public void decreasePoints() {

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
	 * Ökar index för bossscenario
	 * @return
	 */
	public int getbossIndex(){
		return bossIndex;
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
	 * Adderar anv. poäng med elevhemmets poäng.
	 */
	public void userpoints(){
		int total = 0;
		int userpoints = getPoints();
		int housepoints = dbKlass.getHousePoints(house);

		total = userpoints + housepoints;
		dbKlass.setPointsDB(house,total);
	}
	/**
	 * Hämtar elevhemmens poäng och placerar ResultUI i den aktuella panelen.
	 */
	public void fetchhousepoints(){

		dbKlass.getPointsDB(getResultUI());
		setPanel(getResultUI());
	}
	
	
	/**
	 * Minskar en spelares mängd liv
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