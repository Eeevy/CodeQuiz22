package codequiz;

import java.net.*;
import java.io.*;

import CodeQuizServer.Game;

public class Client {
	private QuizController controller;
	private Socket socket;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;

	public Client(String ip, int port, QuizController controller) throws IOException {
		System.out.println("client konstruktor");
		socket = new Socket(ip, port);
		oos = new ObjectOutputStream(new BufferedOutputStream(
				socket.getOutputStream()));
		ois = new ObjectInputStream(new BufferedInputStream(
				socket.getInputStream()));
		setClientController(controller);
		
		new Listener().start();
	}

	public void setClientController(QuizController controller) {
		this.controller = controller;
	}

	public void exit() throws IOException {
		if (socket != null) {
			socket.close();
		}
	}

	private class Listener extends Thread {
		public void run() {
			System.out.println("Listener: run()");
			Game game;
			try {
				oos.writeUTF("Welcome");//onödig?
				oos.flush();//onödig?
				game = (Game)ois.readObject();
					//System.out.println(game.getQuestion().getQuestion());

					controller.setGame(game);

			} catch (IOException e) {
				System.out.println("Clienten kunde inte ta emot objektet");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			try {
				exit();
			} catch (IOException e) {
				System.out.println("Kienten kopplar ner..");
			}
		}
	}

}
