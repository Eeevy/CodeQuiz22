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
	private JPanel panel;
	private User user;
	private HogwartsHouse house;
	private Client client;
	private MainUI ui;
	private QuizUI quizUI;

	public QuizController() {
		
		System.out.println("QuizController: Konstruktor");
		ui = new MainUI(this);
		quizUI = new QuizUI();
		

	}

	public void getQuestion() {
		game = new Game();//kommer sedan tas emot av servern
		Question question;
		question = game.getQuestion();
		quizUI.setQuestion(question.getQuestion());
		quizUI.setAlternatives(question.getAnswer1(), question.getAnswer2(), question.getAnswer3(), question.getAnswer4());

	}
	
	public String getCorrectAnswer(){
		return game.getQuestion().getCorrectanswer(); 
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
