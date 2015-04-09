package codequiz;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuizUI extends JPanel {
	private QuizController controller;

	private JPanel westPanel;
	private JPanel eastPanel;
	private JPanel southPanel;
	private JTextField tfQuestion = new JTextField();
	private JTextField tfAnswers = new JTextField();
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
	private int lives = 5;
	private int points = 0;
	private Question question;
	private int id;

	public QuizUI() {

		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(900, 500));

		add(eastPanel(), BorderLayout.EAST);
		add(westPanel(), BorderLayout.WEST);
		add(southPanel(), BorderLayout.SOUTH);

	}

	public JPanel westPanel() {
		westPanel = new JPanel(new GridLayout(7, 1));
		westPanel.setPreferredSize(new Dimension(420, 20));
		westPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		buttonGroup.add(rb1);
		buttonGroup.add(rb2);
		buttonGroup.add(rb3);
		buttonGroup.add(rb4);
		btnSubmit.setEnabled(false);
		westPanel.add(new JLabel("Välj ett svar:"));
		westPanel.add(rb1);
		westPanel.add(rb2);
		westPanel.add(rb3);
		westPanel.add(rb4);
		westPanel.add(lblResult);
		westPanel.add(btnSubmit);

		btnSubmit.addActionListener(new SubmitListener());

		return westPanel;
	}

	public JPanel eastPanel() {
		eastPanel = new JPanel(new FlowLayout());
		eastPanel.setPreferredSize(new Dimension(420, 150));
		eastPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		eastPanel.add(new JLabel("Fråga"));
		tfQuestion.setPreferredSize(new Dimension(400, 250));
		tfQuestion.setEnabled(false);
		eastPanel.add(tfQuestion);
		eastPanel.add(btnNewQuestion);
		tfQuestion.addActionListener(new QuestionListener());
		btnNewQuestion.addActionListener(new QuestionListener());

		return eastPanel;

	}

	public JPanel southPanel() {
		southPanel = new JPanel(new GridLayout(2, 1));
		southPanel.setPreferredSize(new Dimension(100, 50));
		southPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		southPanel.add(lblPoints = new JLabel("POÄNG: " + points));
		southPanel.add(lblLives = new JLabel("Liv: " + lives));
		return southPanel;

	}

	private class QuestionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			setQuestion();
			btnSubmit.setEnabled(true);
			lblResult.setText("");
			tfQuestion.setText(question.getQuestion());
			rb1.setText(question.getAnswer1());
			rb2.setText(question.getAnswer2());
			rb3.setText(question.getAnswer3());
			rb4.setText(question.getAnswer4());
		}
	}

	private class SubmitListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			btnSubmit.setEnabled(false);
			String answer = null;
			String correctAnswer = question.getCorrectanswer();
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
				points += 10;
				lblResult.setText("RÄTT");
				lblPoints.setText("POÄNG: " + points);

			} else {
				lblResult.setText("FEL");
				points -= 10;
				lives -= 1;
				lblPoints.setText("POÄNG: " + points);
				lblLives.setText("LIV: " + lives);
			}
		}
	}

	public void setQuestion() {
		this.question = controller.getQuestion();
		this.id = question.getID();
	}

	
}