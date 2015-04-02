package codequiz;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Hanterar kommunikationen med databasen.
 * 
 * @author
 *
 */
public class QuizServer {

	private QuizController controller;

	public QuizServer(QuizController controller, int port) {
		this.controller = controller;
		new Connection(port).start();
	}

	private class Connection extends Thread {
		private int port;

		public Connection(int port) {
			this.port = port;
		}

		public void run() {
			Socket socket = null;
			System.out.println("Server startad");
			try (ServerSocket serversocket = new ServerSocket(port)) {
				while (!Thread.interrupted()) {
					try {
						socket = serversocket.accept();
						new ClientHandler(socket);
					} catch (IOException ioe) {
						System.out.println(ioe);
						if (socket != null) {
							socket.close();
						}
					}
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	private class ClientHandler extends Thread {
		private Socket socket;
		private ObjectInputStream ois;
		private ObjectOutputStream oos;
		private Game game;

		public ClientHandler(Socket socket) throws IOException {
			this.socket = socket;
			ois = new ObjectInputStream(new BufferedInputStream(
					socket.getInputStream()));
			oos = new ObjectOutputStream(new BufferedOutputStream(
					socket.getOutputStream()));
			start();
		}
		public void newGame(){
			game = new Game();
			game.CreateGame();
		}

		public void run() {
			try{
				while(true){
					try{
						newGame();
						
					}catch(IOException ioe){
						System.out.println(ioe);
					}
				}
			}catch(Exception e){
				System.out.println(e);
			}
		}
	}

	public static void main(String[] args) {
		new QuizServer(new QuizController(), 3453);
	}

}
