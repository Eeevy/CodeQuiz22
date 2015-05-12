package codequiz;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;

import CodeQuizServer.Database;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Klassen består av en panel som visar spelet och dess händelser
 * 
 * @author CodeQuiz team
 *
 */
public class QuizUI extends JPanel {
	private QuizController controller;
	private JPanel questionPanel;
	private JPanel eastPanel;
	private JPanel westPanel;
	private JPanel southPanel;
	private JPanel northPanel;
	private JLabel lblQuestion = new JLabel();

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
	private Database db;
	private JLabel lblLives;
	private JLabel lblResult = new JLabel("");
	private JLabel lblBackground = new JLabel(new ImageIcon(
			"src/media/background.jpg"));	// Bör ej vara denna bild

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
		questionPanel = new JPanel(new FlowLayout());
		questionPanel.setPreferredSize(new Dimension(420, 400));
		questionPanel.setOpaque(false);
		questionPanel.add(lblQuestion);
		lblQuestion.setPreferredSize(new Dimension(300, 100));
		lblQuestion.setOpaque(false);
		lblQuestion.setFont((new Font("Serif", Font.BOLD, 16)));
		lblQuestion.setForeground(Color.BLACK);
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
		rb1.setFont((new Font("Serif", Font.BOLD, 12)));
		rb2.setFont((new Font("Serif", Font.BOLD, 12)));
		rb3.setFont((new Font("Serif", Font.BOLD, 12)));
		rb4.setFont((new Font("Serif", Font.BOLD, 12)));
		btnSubmit.addActionListener(new SubmitListener());
		return questionPanel;
	}

	/**
	 * 
	 * @return - panelen innehållande poäng, liv och nästa fråga-knappen
	 */
	public JPanel southPanel() {
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
	 * Tar fram nästa fråga/scenario.
	 */
	public void nextQuestion() {
		lblPoints.setVisible(true);
		rb1.setEnabled(true);
		rb2.setEnabled(true);
		rb3.setEnabled(true);
		rb4.setEnabled(true);
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

	/**
	 * 
	 * Händelse då huvudmenyknappen aktiveras
	 */
	private class ButtonBackListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			controller.setPanel(controller.getMainUI());
			controller.newGame();
			controller.disableGamebutton();
		}
	}

	/**
	 * 
	 * Händelse då resultatknappen aktiveras
	 */
	private class ButtonResultListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
//			controller.newResultUI();
//			controller.setPanel(controller.getResultUI());
			controller.userpoints();
			controller.newResultUI();
			controller.fetchhousepoints();
		}
	}

	/**
	 * 
	 * Händelse då Nästa fråga knappen aktiveras
	 */
	private class QuestionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			nextQuestion();
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
			String correctAnswer = controller.changeStringB(controller.getCorrectAnswer());
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
			if (answer == null) {
				JOptionPane.showMessageDialog(null, "Välj ett alternativ, tack");
				btnSubmit.setEnabled(true);
			} else {
			if (answer.equals(correctAnswer)) {
				if (controller.getScenarioIndex() == true) {
					controller.increasePoints();
					controller.win();
				} else {
				ImageIcon icon = controller.setCorrectScenario();
				setBackground(icon);
				controller.increasePoints();
				lblResult.setText("RÄTT!");
				lblResult.setForeground(Color.GREEN);
				lblPoints.setText("Poäng: " + controller.getPoints());
				btnNewQuestion.setEnabled(true);
				}
			} else {
				if (controller.getScenarioIndex() == true) {
					controller.decreasePoints();
					controller.win();
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
			}

			if (controller.getlives() == 0) {
				setBackground(new ImageIcon("src/media/dead.jpg"));
				controller.lose();
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
			}
		} buttonGroup.clearSelection();
			rb1.setEnabled(false);
			rb2.setEnabled(false);
			rb3.setEnabled(false);
			rb4.setEnabled(false);
			}
	}

	/**
	 * 
	 * @param question
	 *            - den fråga som skall visas för användaren
	 */
	public void setQuestion(String question) {
		lblQuestion.setText(controller.changeStringB(question));
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
		rb1.setText(controller.changeStringB(al1));
		System.out.println(rb1.getText());
		rb2.setText(controller.changeStringB(al2));
		System.out.println(rb2.getText());
		rb3.setText(controller.changeStringB(al3));
		System.out.println(rb3.getText());
		rb4.setText(controller.changeStringB(al4));
		System.out.println(rb4.getText());
	}
}