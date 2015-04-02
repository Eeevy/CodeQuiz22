package codequiz;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.*;

/**
 * Visar ett startfönster med huvudmeny. To be continued...
 * 
 * @author Evelyn Gustavsson
 *
 */
public class MainUI {
	private MainGUI mainGUI;
	private JPanel panel;
	private JFrame mainFrame;
	private Clip clip;
	private File musicFilename;
	private QuizController cont;
	
	public MainUI(QuizController inController) {
		mainGUI = new MainGUI();
		cont = inController;
		panel = (JPanel) mainGUI;
		musicFilename = new File("C:/Code Quiz/HarryPotterThemeSong.wav");
		showGUI();
		playSoundClip();
	}

	public void showGUI() {
		mainFrame = new JFrame("");
		mainFrame.setTitle("Code Quiz");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.add(panel);
		mainFrame.pack();
		mainFrame.setVisible(true);

	}

	/**
	 * Metoden tar bort det aktuella fönstret och skapar ett nytt fönster
	 * 
	 * @param panel
	 */
	public void setPanel(JPanel panel) {
		mainFrame.dispose();
		this.panel = panel;
		clip.stop();
		playSoundClip();
		showGUI();

	}

	/**
	 * Spelar upp en ljudfil. Hittas på Dropbox.
	 */
	public void playSoundClip() {
		try {
			File filename = new File("C:/Code Quiz/HarryPotterThemeSong.wav");

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

	private class MainGUI extends JPanel {
		// Paneler
		private JPanel northPanel;
		private JPanel centerPanel;
		private JPanel eastPanel;

		// Labels
		private JLabel lblTitle = new JLabel("Code Quiz");
		// Lite gay slogan, men har kvar den sålänge..
		private JLabel lblSlogan = new JLabel(
				"- Ett magiskt sätt att lära sig programmering");
		private JLabel lblMenu = new JLabel("Meny");
		// Några osynliga labels som inte ska finnas egentligen...
		private JLabel lblInvisible = new JLabel();
		private JLabel lblInvisible2 = new JLabel();
		private JLabel lblInvisible3 = new JLabel();
		private JLabel lblInvisible4 = new JLabel();
		private JLabel lblInvisible5 = new JLabel();

		// Knappar
		private JButton btnPlay = new JButton("Spela");
		private JButton btnSignIn = new JButton("Logga in");
		private JButton btnCreateAccount = new JButton("Skapa ett nytt konto");
		private JButton btnHowToPlay = new JButton("Om spelet");
		private JButton btnHighScore = new JButton("High score");
		// private ButtonGroup btnGroup = new ButtonGroup();

		// Image
		private ImageIcon iconHogwarts;

		public MainGUI() {
			setLayout(new BorderLayout());
			setPreferredSize(new Dimension(900, 500));

			add(northPanel(), BorderLayout.NORTH);
			add(centerPanel(), BorderLayout.CENTER);
			add(eastPanel(), BorderLayout.EAST);
			addActionListeners();
		}

		/**
		 * Visar titel och slogan.
		 * 
		 * @return
		 */
		public JPanel northPanel() {
			northPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
			northPanel.setPreferredSize(new Dimension(100, 100));
			northPanel.setBackground(Color.BLACK);

			northPanel.add(Box.createRigidArea(new Dimension(150, 20)));
			northPanel.add(lblTitle);
			lblTitle.setForeground(Color.WHITE);
			lblTitle.setFont(new Font("SansSerif", Font.BOLD, 50));

			northPanel.add(Box.createRigidArea(new Dimension(220, 20)));
			northPanel.add(lblSlogan);
			lblSlogan.setForeground(Color.WHITE);
			lblSlogan.setFont(new Font("SansSerif", Font.ITALIC, 15));

			return northPanel;
		}

		/**
		 * Visar en bild på Hogwarts logga. Bilden finns på Dropbox.
		 * 
		 * @return
		 */
		public JPanel centerPanel() {
			centerPanel = new JPanel(new FlowLayout());
			centerPanel.setPreferredSize(new Dimension(400, 150));
			centerPanel.setBackground(Color.BLACK);

			centerPanel.add(Box.createRigidArea(new Dimension(500, 25)));
			iconHogwarts = new ImageIcon("C:/Code Quiz/Hogwarts.png");
			centerPanel.add(new JLabel(iconHogwarts));
			// iconFont = new ImageIcon("C:/Code Quiz/HP.png");
			// iconFont = new ImageIcon("C:/Code Quiz/Ron.gif");

			// centerPanel.add(new JLabel(iconFont));

			return centerPanel;
		}

		/**
		 * Visar menyn.
		 * 
		 * @return
		 */
		public JPanel eastPanel() {
			eastPanel = new JPanel(new GridLayout(15, 1));
			eastPanel.setPreferredSize(new Dimension(150, 10));
			eastPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
			eastPanel.setBackground(Color.BLACK);

			eastPanel.add(lblMenu);
			lblMenu.setForeground(Color.WHITE);
			lblMenu.setFont(new Font("SansSerif", Font.BOLD, 26));

			eastPanel.add(lblInvisible);
			eastPanel.add(btnPlay);
			eastPanel.add(lblInvisible2);
			eastPanel.add(btnSignIn);
			eastPanel.add(lblInvisible3);
			eastPanel.add(btnCreateAccount);
			eastPanel.add(lblInvisible4);
			eastPanel.add(btnHowToPlay);
			eastPanel.add(lblInvisible5);
			eastPanel.add(btnHighScore);

			btnPlay.setBackground(Color.BLACK);
			btnPlay.setForeground(Color.WHITE);
			btnSignIn.setBackground(Color.BLACK);
			btnSignIn.setForeground(Color.WHITE);
			btnCreateAccount.setBackground(Color.BLACK);
			btnCreateAccount.setForeground(Color.WHITE);
			btnHowToPlay.setBackground(Color.BLACK);
			btnHowToPlay.setForeground(Color.WHITE);
			btnHighScore.setBackground(Color.BLACK);
			btnHighScore.setForeground(Color.WHITE);

			return eastPanel;
		}

		/**
		 * Metod som lägger till lyssnare till klassen
		 */
		public void addActionListeners() {
			MenuListener menuListener = new MenuListener();
			btnPlay.addActionListener(menuListener);
			btnSignIn.addActionListener(menuListener);
			btnCreateAccount.addActionListener(menuListener);
			btnHowToPlay.addActionListener(menuListener);
			btnHighScore.addActionListener(menuListener);

		}

		public class MenuListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnPlay) {
					QuizUI quizPanel = new QuizUI();
//					cont.play();
					// musicFilename = new
					// File("C:/Code Quiz/MagicWandNoice.mp3");
					setPanel(quizPanel);
					// playSoundClip();

				}
			}
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MainUI(new QuizController());
			}
		});
	}
}