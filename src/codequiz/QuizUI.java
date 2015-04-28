package codequiz;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Klassen består av en panel som visar spelet och dess händelser
 * 
 * @author CodeQuiz team
 *
 */
public class QuizUI extends JPanel {
	private QuizController controller;

	private JPanel gamePanel;
	private JPanel questionPanel;
	private JPanel eastPanel;
	private JPanel westPanel;
	private JPanel southPanel;
	private JPanel northPanel;
	private JLabel lblQuestion = new JLabel();

	// private JTextField tfAnswers = new JTextField();
	private JButton btnSubmit = new JButton("OK");
	private JButton btnNewQuestion = new JButton("Starta spelet");
	private ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rb1 = new JRadioButton(" Alternativ 1");
	private JRadioButton rb2 = new JRadioButton(" Alternativ 2");
	private JRadioButton rb3 = new JRadioButton(" Alternativ 3");
	private JRadioButton rb4 = new JRadioButton(" Alternativ 4");
	private JButton btnback = new JButton("Huvudmeny");
	private JButton btnresult = new JButton("Resultat");
	private JLabel lblPoints;
	private JLabel lblLives;
	private JLabel lblResult = new JLabel("");

	private JLabel lblBackground = new JLabel(new ImageIcon(
			"src/media/background.jpg"));//bör ej vara denna bild

	/**
	 * Konstruerar en bakgrund
	 */
	public QuizUI() {
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(800, 600));
		lblBackground.setLayout(new BorderLayout());
		add(lblBackground);

		lblBackground.add(questionPanel(), BorderLayout.CENTER);
		lblBackground.add(eastPanel(), BorderLayout.EAST);
		lblBackground.add(westPanel(), BorderLayout.WEST);
		lblBackground.add(southPanel(), BorderLayout.SOUTH);
		lblBackground.add(northPanel(), BorderLayout.NORTH);
		questionPanel.setVisible(false);

	}

	/**
	 * 
	 * @param controller
	 *            - det objekt som hanterar logiken
	 */
	public void setController(QuizController controller) {
		this.controller = controller;
	}

	/**
	 * opaque panel
	 * 
	 * @return
	 */

	public JPanel northPanel() {
		northPanel = new JPanel(new FlowLayout());
		northPanel.setPreferredSize(new Dimension(30, 20));
		northPanel.setOpaque(false);

		return northPanel;
	}

	/**
	 * opaque panel
	 * 
	 * @return
	 */
	public JPanel westPanel() {
		westPanel = new JPanel(new FlowLayout());
		westPanel.setPreferredSize(new Dimension(10, 20));
		westPanel.setOpaque(false);
		return westPanel;
	}

	/**
	 * 
	 * @return - panel med huvudmenyknapp, resutatknapp
	 */
	public JPanel eastPanel() {
		eastPanel = new JPanel(new FlowLayout());
		eastPanel.setPreferredSize(new Dimension(430, 150));
		eastPanel.setOpaque(false);
		btnback.setVisible(false);
		eastPanel.add(btnback);
		btnresult.setVisible(false);
		eastPanel.add(btnresult);

		return eastPanel;
	}

	/**
	 * 
	 * @return - panel som innehåller information om frågan
	 */
	public JPanel questionPanel() {
		// questionPanel = new JPanel(new GridLayout(8, 1));
		// questionPanel = new JPanel(new GridBagLayout());

//		questionPanel = new JPanel(new GridLayout(9, 1));
		questionPanel = new JPanel(new FlowLayout());
		// questionPanel.setLayout(new GridBagLayout());
		// questionPanel.setPreferredSize(new Dimension(420, 20));

		questionPanel.setPreferredSize(new Dimension(420, 400));
		questionPanel.setOpaque(false);
		questionPanel.add(lblQuestion);

		lblQuestion.setPreferredSize(new Dimension(300, 50));
		lblQuestion.setOpaque(false);
		lblQuestion.setFont((new Font("Serif", Font.BOLD, 16)));
		lblQuestion.setForeground(Color.BLACK);

		// TESTA NÅGOT LIKNANDE - IGNORERA TILLS VIDARE...
		// http://tutiez.com/multiline-text-for-swing-components-tutorial.html

		// String text = " This is how we can achieve <br>multiline text ";

		// JRadioButton radioEx = new JRadioButton("<font face="\"Arial\""
		// size="\"3\"">Radio button<br>text example</font>");"

		// JRadioButton radioEx = new
		// JRadioButton("Radio button text example LALALALALALALALALALALALALALALALALA");
		// multiLineFrame.getContentPane().setLayout(new GridBagLayout());

		// questionPanel.setLayout(new GridBagLayout());

		// int y = 0;

		// JLabel radioLabel = new JLabel("Radio Button with multiline text");

		// multiLineFrame.getContentPane().add(radioLabel, new
		// GridBagConstraints(0,y,1,1,0,0,
		// GridBagConstraints.WEST, GridBagConstraints.WEST,new
		// Insets(25,25,5,5),0,0));

		// questionPanel.add(radioLabel, new GridBagConstraints(0,y,1,1,0,0,
		// GridBagConstraints.WEST, GridBagConstraints.WEST,new
		// Insets(25,25,5,5),0,0));
		//
		// y++;

		// multiLineFrame.getContentPane().add(radioEx, new
		// GridBagConstraints(0,y,1,1,0,0,
		// GridBagConstraints.WEST, GridBagConstraints.WEST,new
		// Insets(5,25,5,5),0,0));
		//
		// questionPanel.add(radioEx, new GridBagConstraints(0,y,1,1,0,0,
		// GridBagConstraints.WEST, GridBagConstraints.WEST,new
		// Insets(5,25,5,5),0,0));

		buttonGroup.add(rb1);
		buttonGroup.add(rb2);
		buttonGroup.add(rb3);
		buttonGroup.add(rb4);
		rb1.setOpaque(false);
		rb2.setOpaque(false);
		rb3.setOpaque(false);
		rb4.setOpaque(false);

		btnSubmit.setEnabled(false);
		btnSubmit.setOpaque(false);

		// questionPanel.add(new JLabel("Välj ett svar:"));

		// rb1.setLayout(new FlowLayout());
		questionPanel.add(rb1);
		questionPanel.add(rb2);
		questionPanel.add(rb3);
		questionPanel.add(rb4);
		questionPanel.add(lblResult);
		questionPanel.add(Box.createRigidArea(new Dimension(300, 20)));
		questionPanel.add(btnSubmit);
		btnSubmit.setPreferredSize(new Dimension(110, 30));
		
		rb1.setPreferredSize(new Dimension(365, 60));
		rb2.setPreferredSize(new Dimension(365, 60));
		rb3.setPreferredSize(new Dimension(365, 60));
		rb4.setPreferredSize(new Dimension(365, 60));
		rb1.setFont((new Font("Serif", Font.BOLD, 11)));
		rb2.setFont((new Font("Serif", Font.BOLD, 11)));
		rb3.setFont((new Font("Serif", Font.BOLD, 11)));
		rb4.setFont((new Font("Serif", Font.BOLD, 11)));

		btnSubmit.addActionListener(new SubmitListener());

		return questionPanel;
	}

	/**
	 * 
	 * @return - panelen innehållande poäng, liv och nästa fråga-knappen
	 */
	public JPanel southPanel() {
//		southPanel = new JPanel(new GridLayout(2, 2));
		southPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		southPanel.setPreferredSize(new Dimension(100, 80));
		southPanel.add(Box.createRigidArea(new Dimension(20, 20)));
		southPanel.add(lblPoints = new JLabel(new ImageIcon(
				"src/media/EmblemSml.png")));
		lblPoints.setVisible(false);
		southPanel.add(Box.createRigidArea(new Dimension(20, 20)));
		southPanel.add(lblLives = new JLabel(new ImageIcon(
				"src/media/heartSml.png")));
		lblLives.setVisible(false);

		southPanel.add(Box.createRigidArea(new Dimension(10, 20)));
		southPanel.add(btnNewQuestion);
		btnNewQuestion.setOpaque(false);
		southPanel.setOpaque(false);
		btnNewQuestion.addActionListener(new QuestionListener());
		lblPoints.setForeground(Color.BLACK);
		lblLives.setForeground(Color.BLACK);
		btnNewQuestion.setPreferredSize(new Dimension(110, 30));

		return southPanel;
	}

	/**
	 * 
	 * Händelse då huvudmenyknappen aktiveras
	 */
	private class ButtonBackListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			controller.setPanel(controller.getMainUI());
			controller.newGame();
		}
	}

	/**
	 * 
	 * Händelse då resultatknappen aktiveras
	 */
	private class ButtonResultListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			controller.newResultUI();
			controller.setPanel(controller.getResultUI());
			controller.setScore(controller.getPoints());
		}
	}

	/**
	 * 
	 * Händelse då Nästa fråga knappen aktiveras
	 */
	private class QuestionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// controller.nextQuestion();
			lblPoints.setVisible(true);
			lblPoints.setText("Poäng: " + controller.getPoints());
			lblLives.setVisible(true);
			lblLives.setText(" " + controller.getlives());
			questionPanel.setVisible(true);
			btnNewQuestion.setText("Nästa fråga");
			btnNewQuestion.setEnabled(false);
			controller.increaseIndex();
			ImageIcon icon = controller.setQuestionScenario();
			setBackground(icon);
			controller.getQuestion();
			buttonGroup.clearSelection();
			btnSubmit.setEnabled(true);
			lblResult.setText("");
		}
	}

	/**
	 * 
	 * Händelse då Ok knappen aktiveras
	 */
	private class SubmitListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			btnSubmit.setEnabled(false);
			String answer = null;
			String correctAnswer = controller.changeStringA(controller.getCorrectAnswer());
			System.out.println(controller.maxScenario());
			System.out.println(correctAnswer);
			if (rb1.isSelected()) {
				answer = rb1.getText();
			}
			if (rb2.isSelected()) {
				answer = rb2.getText();
			}
			if (rb3.isSelected()) {
				answer = rb3.getText();
			}
			if (rb4.isSelected()) {
				answer = rb4.getText();
			}
			
			if (answer.equals(correctAnswer)) {
				ImageIcon icon = controller.setCorrectScenario();
				setBackground(icon);
				controller.increasePoints();
				lblResult.setText("RÄTT!");
				lblResult.setForeground(Color.GREEN);
				lblPoints.setText("Poäng: " + controller.getPoints());
				btnNewQuestion.setEnabled(true);

			} else {
				ImageIcon icon = controller.setIncorrectScenario();
				setBackground(icon);
				lblResult.setText("FEL!");
				lblResult.setForeground(Color.RED);
				controller.decreasePoints();
				controller.decreaseLives();
				lblPoints.setText(" Poäng: " + controller.getPoints());
				lblLives.setText(" " + controller.getlives());
				btnNewQuestion.setEnabled(true);
			}

			if (controller.getlives() == 0) {
				setBackground(new ImageIcon("src/media/dead.jpg"));
				eastPanel.add(Box.createRigidArea(new Dimension(20, 130)));
				eastPanel.add(btnback);
				eastPanel.add(Box.createRigidArea(new Dimension(10, 30)));
				eastPanel.add(btnresult);
				btnback.setVisible(true);
				btnback.addActionListener(new ButtonBackListener());
				btnNewQuestion.setEnabled(false);
				btnresult.setVisible(true);
				btnresult.addActionListener(new ButtonResultListener());
				btnback.setPreferredSize(new Dimension(100, 30));
				btnresult.setPreferredSize(new Dimension(100, 30));
//				eastPanel.add(lbldead, BorderLayout.SOUTH);
			}
			if (controller.getScenarioIndex() == true) {
				controller.win();
			}
			

			// if (controller.maxScenario() == true) {
			// controller.setScore(controller.getPoints());
			//
			// // ÄNDRA BILDSÖKVÄGAR
			// setBackground(new ImageIcon("src/media/background.jpg"));
			// JLabel lbldead = new JLabel(new ImageIcon(
			// "src/media/beatrixCorrect.png"));
			//
			// eastPanel.add(Box.createRigidArea(new Dimension(60, 150)));
			// eastPanel.add(lbldead, BorderLayout.SOUTH);
			// questionPanel.setVisible(false);
			// btnNewQuestion.setVisible(false);
			// btnback.setVisible(true);
			// btnback.addActionListener(new ButtonBackListener());
			// btnNewQuestion.setEnabled(true);
			// btnresult.setVisible(true);
			// btnresult.addActionListener(new ButtonResultListener());
			// }
		}
	}

	/**
	 * 
	 * @param question
	 *            - den fråga som skall visas för användaren
	 */
	public void setQuestion(String question) {
		lblQuestion.setText(controller.changeStringQ(question));
	}

	/**
	 * 
	 * @param inIcon
	 *            - den bakgrund som skall visas
	 */
	public void setBackground(ImageIcon inIcon) {
		lblBackground.setIcon(inIcon);
	}

	/**
	 * Visar svarsalternativ
	 * 
	 * @param al1
	 *            -svarsalternativ1
	 * @param al2
	 *            -svarsalternativ2
	 * @param al3
	 *            -svarsalternativ3
	 * @param al4
	 *            -svarsalternativ4
	 */
	public void setAlternatives(String al1, String al2, String al3, String al4) {
		rb1.setText(controller.changeStringA(al1));
		rb2.setText(controller.changeStringA(al2));
		rb3.setText(controller.changeStringA(al3));
		rb4.setText(controller.changeStringA(al4));
	}
}