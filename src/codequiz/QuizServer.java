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


	public QuizServer(int port) {
		System.out.println("QuizServer: konstruktor");
		new Connection(port).start();
	}

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
						System.out.println("Servern: väntar på klient...");
						socket = serversocket.accept();
						System.out.println("Servern: uppkopplad...");
						new ClientHandler(socket);
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

	private class ClientHandler extends Thread {
		private Socket socket;
		private ObjectInputStream ois;
		private ObjectOutputStream oos;
		private Game game;

		public ClientHandler(Socket socket) throws IOException {
			System.out.println("ClientHandler: Konstruktor");
			this.socket = socket;
			System.out.println("En socket");
			
			oos = new ObjectOutputStream(
					socket.getOutputStream());
			ois = new ObjectInputStream(
					socket.getInputStream());
			System.out.println("Fel på strömmarna");
			start();
		}
		public void newGame(){
			System.out.println("ClientHandler: newGame()");
			game = new Game();

		}

		public void run() {
			System.out.println("ClientHandler: run()");
			try{
				String mess = ois.readUTF();
				System.out.println("Servern säger:" + mess);

				//while(){
					try{
						newGame();
						System.out.println("nu skickar vi objekt!");
						oos.writeObject(game);
						oos.flush();
					}catch(Exception ioe){
						System.out.println(ioe);
					}
				//}
			}catch(Exception e){
				System.out.println(e);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		new QuizServer(3453);
	}

}
