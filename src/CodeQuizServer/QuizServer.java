package CodeQuizServer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Hanterar kommunikationen med databasen.
 * 
 *
 */
public class QuizServer {

	public QuizServer(int port) {
		System.out.println("QuizServer: konstruktor");
		new Connection(port).start();
	}

	/**
	 * Inre klass, skapar en serverSocket som väntar på att klient skall ansluta
	 *
	 */
	private class Connection extends Thread {
		private int port;

		public Connection(int port) {
			System.out.println("Connection: Konstruktor");
			this.port = port;
		}

		public void run() {
			System.out.println("Connection: run()");
			Socket socket = null;
			System.out.println("Server startad");
			try (ServerSocket serversocket = new ServerSocket(port)) {
				while (!Thread.interrupted()) {
					try {
						System.out.println("Servern: Väntar på klient...");
						socket = serversocket.accept();
						System.out.println("Servern: Uppkopplad...");
						new ClientHandler(socket);// Tråd skapas för varje
													// klient
					} catch (IOException ioe) {
						System.out.println(ioe);
						if (socket != null) {
							socket.close();
						}
						System.out.println("Server avslutad");
					}
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	/**
	 * Tråd som hanterar kommunikationen med uppkopplad klient och skickar denne
	 * objekt
	 *
	 */
	private class ClientHandler extends Thread {
		private Socket socket;
		private ObjectInputStream ois;
		private ObjectOutputStream oos;
		private Game game;
		private DatabaseConnector db;


		/**
		 * Konstruerar socket och objektströmmar
		 * 
		 * @param socket
		 *            - kommunikatione mellan klient och server
		 * @throws IOException
		 */
		public ClientHandler(Socket socket) throws IOException {
			System.out.println("ClientHandler: Konstruktor");
			this.socket = socket;
			System.out.println("En socket");

			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
			start();
			
		}

		/**
		 * Metoden skapar ett nytt game objekt
		 */
		public void newGame() {
			System.out.println("ClientHandler: newGame()");
			game = new Game();
			

		}

		/**
		 * Metoden skickar objekt till klienten
		 */
		public void run() {
			System.out.println("ClientHandler: run()");
			try {
				String mess = ois.readUTF();
				System.out.println("Servern säger:" + mess);

				try {
					newGame();
					System.out.println("Nu skickar vi objekt!");
					oos.writeObject(game);
					oos.flush();
				} catch (Exception ioe) {
					System.out.println(ioe);
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	/**
	 * Startar servern
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		new QuizServer(3453);
	}
}