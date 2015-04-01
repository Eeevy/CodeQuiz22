package codequiz;

import java.io.*;
import java.net.ServerSocket;

/**
 * Hanterar kommunikationen med databasen.
 * @author 
 *
 */
public class QuizServer extends Thread {

	private QuizController controller;
	private ServerSocket ss;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private int port;
	
	public QuizServer(QuizController controller, int port) {
		
	}
	
	public void run() {
		
	}
	
}
