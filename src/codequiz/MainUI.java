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
 * @author Code Quiz
 *
 */
public class MainUI extends JPanel {
	private QuizController cont;
	private JLabel lblTitle = new JLabel(" Code Quiz");
	private JLabel lblSlogan = new JLabel(
			"  - Ett magiskt sätt att lära sig programmering");
	private JLabel lblIcon = new JLabel(new ImageIcon(
			"src/media/HahaHogwarts.jpg"));
	private JButton btnPlay = new JButton("Spela");
	private JButton btnSignIn = new JButton("Logga in");
	private JButton btnCreateAccount = new JButton("Skapa konto");
	private JButton btnHowToPlay = new JButton("Om spelet");
	private JButton btnHighScore = new JButton("High score");

	public MainUI() {
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(800, 600));
		lblIcon.setLayout(new FlowLayout());
		lblIcon.setPreferredSize(new Dimension(800, 600));
		add(lblIcon);
		lblIcon.add(lblTitle);
		lblTitle.setForeground(Color.BLACK);
		lblTitle.setFont(new Font("SansSerif", Font.BOLD, 55));
		lblTitle.setPreferredSize(new Dimension(800, 60));
		lblIcon.add(lblSlogan);
		lblSlogan.setForeground(Color.BLACK);
		lblSlogan.setFont(new Font("Serif", Font.ITALIC, 16));
		lblSlogan.setPreferredSize(new Dimension(800, 20));
		lblIcon.add(Box.createRigidArea(new Dimension(800, 100)));
		lblIcon.add(Box.createRigidArea(new Dimension(630, 40)));
		lblIcon.add(btnPlay);
		btnPlay.setPreferredSize(new Dimension(150, 30));
		btnPlay.setBackground(Color.white);
		lblIcon.add(Box.createRigidArea(new Dimension(630, 40)));
		lblIcon.add(btnSignIn);
		btnSignIn.setPreferredSize(new Dimension(150, 30));
		btnSignIn.setBackground(Color.white);
		lblIcon.add(Box.createRigidArea(new Dimension(630, 40)));
		lblIcon.add(btnCreateAccount);
		btnCreateAccount.setPreferredSize(new Dimension(150, 30));
		btnCreateAccount.setBackground(Color.white);
		lblIcon.add(Box.createRigidArea(new Dimension(630, 40)));
		lblIcon.add(btnHowToPlay);
		btnHowToPlay.setPreferredSize(new Dimension(150, 30));
		btnHowToPlay.setBackground(Color.white);
		lblIcon.add(Box.createRigidArea(new Dimension(630, 40)));
		lblIcon.add(btnHighScore);
		btnHighScore.setPreferredSize(new Dimension(150, 30));
		btnHighScore.setBackground(Color.white);
		addActionListeners();
		btnPlay.setVisible(false);
	}

	public void enableMenu(boolean tof) {
		btnPlay.setVisible(tof);
	}

	public void login(String inName, String inText) {
		int result = 0;
		String name, password;
		JTextField nameField = new JTextField(8);
		JPasswordField passwordField = new JPasswordField(8);
		nameField.setText(inName);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 1));
		panel.add(new JLabel("Namn:"));
		panel.add(nameField);
		panel.add(new JLabel("Lösenord:"));
		panel.add(passwordField);

		result = JOptionPane.showConfirmDialog(null, panel, inText,
				JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			name = nameField.getText();
			password = passwordField.getText();
			cont.login(name, password);
		}
	}

	public void newUser(String inName, String inText) {
		int result = 0;
		String name, password, passwordConf;
		JTextField nameField = new JTextField(5);
		JPasswordField passwordField = new JPasswordField(7);
		JPasswordField passwordFieldConf = new JPasswordField(7);
		nameField.setText(inName);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 1));
		panel.add(new JLabel("Namn:"));
		panel.add(nameField);
		panel.add(new JLabel("Lösenord:"));
		panel.add(passwordField);
		panel.add(new JLabel("Upprepa lösenord:"));
		panel.add(passwordFieldConf);

		result = JOptionPane.showConfirmDialog(null, panel, inText,
				JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			name = nameField.getText();
			password = passwordField.getText();
			passwordConf = passwordFieldConf.getText();
			cont.newUser(name, password, passwordConf);
		}
	}

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
				cont.getSortingQuestion();
				cont.setPanel(cont.getSortingCeremonyUI());
			}
			if (e.getSource() == btnCreateAccount) {
				newUser(null, "Mata in namn och lösenord");
			}
			if (e.getSource() == btnSignIn) {
				login(null, "Mata in namn och lösenord");
			}
			if (e.getSource() == btnHowToPlay) {
				cont.setPanel(cont.getHowToPlayUI());
			}
		}
	}

	public void setController(QuizController controller) {
		this.cont = controller;
	}
}