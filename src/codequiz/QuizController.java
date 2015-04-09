package codequiz;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class QuizController extends Thread {

	private Game game;
	private Question question = null;
	private JPanel panel;
	private User user;
	private HogwartsHouse house;
	private Client client;
	private MainUI ui;
	private QuizUI quizUI;
	private int i = 0;

	public QuizController() {
		
		System.out.println("QuizController: Konstruktor");
		ui = new MainUI(this);
		quizUI = new QuizUI();
		quizUI.setController(this);
		

	}

	public void getQuestion() {
	//	game = new Game();//kommer sedan tas emot av servern
		
		//Question question;
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
		ui.setPanel(quizUI);
		try {
			client = new Client("127.0.0.1", 3453, this);
			System.out.println("klient skapad");
		} catch (IOException e) {
			System.out.println("Klienten kunde intekoppla upp");
		}
		

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

}
