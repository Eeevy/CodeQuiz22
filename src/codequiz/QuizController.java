package codequiz;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
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
	private File musicFilename;
	private MainUI mainUI;
	private QuizUI quizUI;
	private int i = 0;
	private int index = -1;

	public QuizController() {
		
		System.out.println("QuizController: Konstruktor");
		musicFilename = new File("src/media/HarryPotterThemeSong.wav");
		quizUI = new QuizUI();
		mainUI = new MainUI();
		panel = mainUI;
		quizUI.setController(this);
		mainUI.setController(this);
		showGUI();
		playSoundClip();
		

	}
	
	public void showGUI() {
		mainFrame = new JFrame("");
		mainFrame.setTitle("Code Quiz");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.add(panel);
		mainFrame.pack();
		mainFrame.setResizable(false);
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
	}
	
	public void closeWindow(){
		mainFrame.dispose();
	}
	
	public void stopMusic(){
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

	public void getQuestion() {
	//	game = new Game();//ta bort
		this.question = game.getQuestion(i);
		quizUI.setQuestion(question.getQuestion());
		quizUI.setAlternatives(question.getAnswer1(), question.getAnswer2(), question.getAnswer3(), question.getAnswer4());
		i++;

	}
	
	public String getCorrectAnswer(){
		return question.getCorrectanswer(); //ändrat till instansvariabeln
	}

	public void play() {
		System.out.println("Controller: play()");
		setPanel(quizUI);
		try {
			client = new Client("127.0.0.1", 3453, this);
			System.out.println("klient skapad");
		} catch (IOException e) {
			System.out.println("Klienten kunde intekoppla upp");
		}
		

	}
	
	public void increaseIndex(){
		index++;
	}
	
	public ImageIcon setQuestionScenario(){
		scenario = game.getScenario(index);
		ImageIcon QPic = scenario.getStoryPic();
		return QPic;
//		 ImageIcon QPic = new ImageIcon("src/media/diary.jpg");//fusk
//		 return QPic;
	}
	
	public ImageIcon setCorrectScenario(){
		scenario = game.getScenario(index);
		ImageIcon CPic = scenario.getCorrectPic();
		return CPic;
//		ImageIcon CPic = new ImageIcon("src/media/Correct.jpg");
//		return CPic;
	}
	
	public ImageIcon setIncorrectScenario(){
		scenario = game.getScenario(index);
		ImageIcon IPic = scenario.getIncorrectPic();
		return IPic;
//		ImageIcon IPic = new ImageIcon("src/media/Incorrect.jpg");
//		return IPic;
	}
	
	
	
	
	public void setGame(Game game) {
		System.out.println("QuizController: setGame()");
		this.game = game;
	}

	
	 public void showUI() {
	
	 }
	//
	// public void setUI(JFrame ui) {
	//
	// }
	 
	 public static void main(String[] args) {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					new QuizController();
				}
			});
		}

}
