package codequiz;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JFrame;

public class QuizController extends Thread {

	// private LinkedList<Question> list = new LinkedList<Question>();
	private Game game;
	private ArrayList<Question> question;
	private JFrame ui;
	private User user;
	private HogwartsHouse house;
	private Socket socket;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private String ip = "127.0.0.1";

	public QuizController(String ip, int port) throws IOException {
		socket = new Socket(ip, port);
		ois = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));// läser från denna socket
		oos = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));// skriver på denna socket
		new Listener().start();

	}
	
	public Question getQuestion() {			
		return game.getQuestion();
	}
	
	public void play(){
		
	}
	

	private class Listener extends Thread {
		public void run() {
			try {
				while (true) {
					game = (Game) ois.readObject();
					
				}
			} catch (IOException | ClassNotFoundException e) {
			}
			try {
				socket.close();
			} catch (IOException e) {
			}
		}
		
//		public void showUI() {
//
//		}
//
//		public void setUI(JFrame ui) {
//
//		}

		
	}

	
}