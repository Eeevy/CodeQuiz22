package codequiz;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuizUI extends JPanel {
	private QuizController controller;

	private JPanel gamePanel;
	private JPanel questionPanel;
	private JPanel eastPanel;
	private JPanel westPanel;
	private JPanel southPanel;
	private JPanel northPanel;
	private JLabel lblQuestion = new JLabel();

//	private JTextField tfAnswers = new JTextField();
	private JButton btnSubmit = new JButton("OK");
	private JButton btnNewQuestion = new JButton("Ny fråga");
	private ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rb1 = new JRadioButton(" Alternativ 1");
	private JRadioButton rb2 = new JRadioButton(" Alternativ 2");
	private JRadioButton rb3 = new JRadioButton(" Alternativ 3");
	private JRadioButton rb4 = new JRadioButton(" Alternativ 4");
	private JLabel lblPoints;
	private JLabel lblLives;
	private JLabel lblResult = new JLabel("");
	private JLabel lblBackground = new JLabel(new ImageIcon(
			"src/media/Story1.jpg"));
	private int lives = 5;
	private int points = 0;
	private int id;

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
		westPanel.setPreferredSize(new Dimension(30, 20));
		westPanel.setOpaque(false);

		return westPanel;
	}

	/**
	 * opaque panel
	 * 
	 * @return
	 */
	public JPanel eastPanel() {
		eastPanel = new JPanel(new FlowLayout());
		eastPanel.setPreferredSize(new Dimension(430, 150));
		eastPanel.setOpaque(false);

		return eastPanel;
	}

	/**
	 * Contains game questions
	 * 
	 * @return
	 */
	public JPanel questionPanel() {
		questionPanel = new JPanel(new GridLayout(8, 1));
		questionPanel.setPreferredSize(new Dimension(420, 20));
		questionPanel.setOpaque(false);
		questionPanel.add(lblQuestion);

		lblQuestion.setPreferredSize(new Dimension(300, 50));
		lblQuestion.setOpaque(false);

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

		questionPanel.add(new JLabel("Välj ett svar:"));
		questionPanel.add(rb1);
		questionPanel.add(rb2);
		questionPanel.add(rb3);
		questionPanel.add(rb4);
		questionPanel.add(lblResult);
		questionPanel.add(btnSubmit);
		btnSubmit.addActionListener(new SubmitListener());

		return questionPanel;
	}

	public JPanel southPanel() {
		southPanel = new JPanel(new GridLayout(2, 2));
		southPanel.setPreferredSize(new Dimension(100, 50));
		southPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		southPanel.add(lblPoints = new JLabel("POÄNG: " + points));
		southPanel.add(lblLives = new JLabel("Liv: " + lives));
		southPanel.add(btnNewQuestion);
		btnNewQuestion.setOpaque(false);
		southPanel.setOpaque(false);
		btnNewQuestion.addActionListener(new QuestionListener());

		return southPanel;
	}

	public void setController(QuizController controller) {
		this.controller = controller;
	}

	private class QuestionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//controller.nextQuestion();
			controller.increaseIndex();
			ImageIcon icon = controller.setQuestionScenario();
			setBackground(icon);
			controller.getQuestion();
			btnSubmit.setEnabled(true);
			lblResult.setText("");
		}
	}

	private class SubmitListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			btnSubmit.setEnabled(false);
			String answer = null;
			String correctAnswer = controller.getCorrectAnswer();
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
				points += 10;// skall skapas en metod i Controllern
				lblResult.setText("RÄTT!");
				lblResult.setForeground(Color.GREEN);
				lblPoints.setText("POÄNG: " + points);

			} else {
				ImageIcon icon = controller.setIncorrectScenario();
				setBackground(icon);
				lblResult.setText("FEL!");
				lblResult.setForeground(Color.RED);
				points -= 10;
				lives -= 1;
				lblPoints.setText("POÄNG: " + points);
				lblLives.setText("LIV: " + lives);
			}
		}
	}

	public void setQuestion(String question) {
		lblQuestion.setText(question);
	}
	
	public void setBackground(ImageIcon inIcon) {
		lblBackground.setIcon(inIcon);
	}

	public void setAlternatives(String al1, String al2, String al3, String al4) {
		rb1.setText(al1);
		rb2.setText(al2);
		rb3.setText(al3);
		rb4.setText(al4);
	}
}