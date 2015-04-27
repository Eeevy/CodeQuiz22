package codequiz;

import java.net.*;
import java.io.*;

import CodeQuizServer.Game;
/**
 * Klassen hanterar kommunikation med servern. 
 *
 */
public class Client {
	private QuizController controller;
	private Socket socket;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
/**
 * Konstruerar ett Client - objekt med strömmar och socket för kommunikation med servern samt
 * instansierar det objekt (controller) som hanterar logiken
 * @param ip - server ip 
 * @param port - server port
 * @param controller - Controller klassen som hanterar logik
 * @throws IOException
 */
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
/**
 * 
 * @param controller - referens till det objekt som hanterar logiken
 */
	public void setClientController(QuizController controller) {
		this.controller = controller;
	}
/**
 * Metoden avslutar kommunikationen med servern
 * @throws IOException
 */
	public void exit() throws IOException {
		if (socket != null) {
			socket.close();
		}
	}
/**
 * 
 *Klassen hanterar kommunikationen med servern och tar emot objekt.
 *Dessa skickas vidare till controller för att hanteras vidare
 */
	private class Listener extends Thread {
		public void run() {
			System.out.println("Listener: run()");
			Game game;
			try {
				oos.writeUTF("Welcome");//onödig?
				oos.flush();//onödig?
				game = (Game)ois.readObject();
					//System.out.println(game.getQuestion().getQuestion());
					System.out.print("klientObj");
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
