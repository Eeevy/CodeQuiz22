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
	private JButton btnNewQuestion = new JButton("Starta spelet");
	private ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rb1 = new JRadioButton(" Alternativ 1");
	private JRadioButton rb2 = new JRadioButton(" Alternativ 2");
	private JRadioButton rb3 = new JRadioButton(" Alternativ 3");
	private JRadioButton rb4 = new JRadioButton(" Alternativ 4");
	private JButton btnback = new JButton("Huvudmeny");
	private JLabel lblPoints;
	private JLabel lblLives;
	private JLabel lblResult = new JLabel("");
	private JLabel lblBackground = new JLabel(new ImageIcon(
			"src/media/howToPlay.png"));
	private int lives = 2;
	private int points = 0;

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
		btnback.setVisible(false);
		eastPanel.add(btnback);
		
		return eastPanel;
	}

	/**
	 * Contains game questions
	 * 
	 * @return
	 */
	public JPanel questionPanel() {
//		questionPanel = new JPanel(new GridLayout(8, 1));
//		questionPanel = new JPanel(new GridBagLayout());
		
		questionPanel = new JPanel(new GridLayout(9, 1));
//		questionPanel.setLayout(new GridBagLayout());
//		questionPanel.setPreferredSize(new Dimension(420, 20));

		questionPanel.setPreferredSize(new Dimension(420, 400));
		questionPanel.setOpaque(false);
		questionPanel.add(lblQuestion);

		lblQuestion.setPreferredSize(new Dimension(300, 50));
		lblQuestion.setOpaque(false);
		lblQuestion.setFont((new Font("Serif", Font.BOLD, 16)));
		lblQuestion.setForeground(Color.GRAY);
		
		
		// TESTA NÅGOT LIKNANDE - IGNORERA TILLS VIDARE...
		// http://tutiez.com/multiline-text-for-swing-components-tutorial.html
		
//		String text = " This is how we can achieve <br>multiline text ";
		
//		JRadioButton radioEx = new JRadioButton("<font face="\"Arial\"" size="\"3\"">Radio button<br>text example</font>");"
		
//		JRadioButton radioEx = new JRadioButton("Radio button text example LALALALALALALALALALALALALALALALALA");
//		multiLineFrame.getContentPane().setLayout(new GridBagLayout());
		
//		questionPanel.setLayout(new GridBagLayout());
		
//		int y = 0;
		 
//		JLabel radioLabel = new JLabel("Radio Button with multiline text");
		 
//		multiLineFrame.getContentPane().add(radioLabel, new GridBagConstraints(0,y,1,1,0,0,
//		                GridBagConstraints.WEST, GridBagConstraints.WEST,new Insets(25,25,5,5),0,0));
		
//		questionPanel.add(radioLabel, new GridBagConstraints(0,y,1,1,0,0,
//                GridBagConstraints.WEST, GridBagConstraints.WEST,new Insets(25,25,5,5),0,0));
//		
//		y++;
		
//		multiLineFrame.getContentPane().add(radioEx, new GridBagConstraints(0,y,1,1,0,0,
//		                GridBagConstraints.WEST, GridBagConstraints.WEST,new Insets(5,25,5,5),0,0));
//		
//		questionPanel.add(radioEx, new GridBagConstraints(0,y,1,1,0,0,
//                GridBagConstraints.WEST, GridBagConstraints.WEST,new Insets(5,25,5,5),0,0));
		 

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

//		questionPanel.add(new JLabel("Välj ett svar:"));
		
//		rb1.setLayout(new FlowLayout());
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
//		southPanel = new JPanel(new GridLayout(2, 2));
		southPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		southPanel.setPreferredSize(new Dimension(100, 50));
		southPanel.add(Box.createRigidArea(new Dimension(20, 20)));
		southPanel.add(lblPoints = new JLabel("Poäng: " + points));
		southPanel.add(Box.createRigidArea(new Dimension(60, 20)));
		southPanel.add(lblLives = new JLabel("Liv: " + lives));
		southPanel.add(Box.createRigidArea(new Dimension(60, 20)));
		southPanel.add(btnNewQuestion);
		btnNewQuestion.setOpaque(false);
		southPanel.setOpaque(false);
		btnNewQuestion.addActionListener(new QuestionListener());
		lblPoints.setForeground(Color.GREEN);
		lblLives.setForeground(Color.RED);
	
		return southPanel;
	}
	
	private class ButtonBackListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			controller.setPanel(controller.getMainUI());
			controller.newGame();
			}
	}

	private class QuestionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//controller.nextQuestion();
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
				lblPoints.setText("Poäng: " + points);
				btnNewQuestion.setEnabled(true);


			} else {
				ImageIcon icon = controller.setIncorrectScenario();
				setBackground(icon);
				lblResult.setText("FEL!");
				lblResult.setForeground(Color.RED);
				points -= 10;
				lives -= 1;
				lblPoints.setText("Poäng: " + points);
				lblLives.setText("Liv: " + lives);
				btnNewQuestion.setEnabled(true);

			}
			if(lives==0){
				setBackground(new ImageIcon("src/media/dead.jpeg"));
				JLabel lbldead = new JLabel(new ImageIcon("src/media/DropDead.gif"));
				eastPanel.add(Box.createRigidArea(new Dimension(60,150)));
				eastPanel.add(lbldead, BorderLayout.SOUTH);
				questionPanel.setVisible(false);
				btnNewQuestion.setVisible(false);
				btnback.setVisible(true);
				btnback.addActionListener(new ButtonBackListener());
				btnNewQuestion.setEnabled(true);

			}
		}
	}

	public void setQuestion(String question) {
		lblQuestion.setText(question);
	}
	
	public void setBackground(ImageIcon inIcon) {
		lblBackground.setIcon(inIcon);
	}
	
	public void setLives() {
		lives = 2;
	}

	public void setAlternatives(String al1, String al2, String al3, String al4) {
		rb1.setText(al1);
		rb2.setText(al2);
		rb3.setText(al3);
		rb4.setText(al4);
	}
}