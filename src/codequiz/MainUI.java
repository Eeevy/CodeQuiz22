package codequiz;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.*;

/**
 * Klassen består av en panel med en huvudmeny.
 * 
 * @author Evelyn Gustavsson
 *
 */
public class MainUI extends JPanel {
	private QuizController cont;

	
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

	// Image
	private ImageIcon iconHogwarts;

	// public MainGUI() {
	public MainUI() {

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
		iconHogwarts = new ImageIcon("src/media/Hogwarts.png");
		centerPanel.add(new JLabel(iconHogwarts));
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
		btnPlay.setForeground(Color.GRAY);
		btnSignIn.setBackground(Color.BLACK);
		btnSignIn.setForeground(Color.GRAY);
		btnCreateAccount.setBackground(Color.BLACK);
		btnCreateAccount.setForeground(Color.GRAY);
		btnHowToPlay.setBackground(Color.BLACK);
		btnHowToPlay.setForeground(Color.GRAY);
		btnHighScore.setBackground(Color.BLACK);
		btnHighScore.setForeground(Color.GRAY);

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
				cont.closeWindow();
				cont.stopMusic();
				cont.play();
			}
		}
	}

	public void setController(QuizController controller) {
		this.cont = controller;
	}
}
