package codequiz;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class BossUITemp extends JPanel {

	private QuizController controller;
	private JLabel lblTitle = new JLabel("Vad blir utskriften?");
	private JLabel lblCodeEx = new JLabel("Här skall det vara en bild");

	private JRadioButton rb1 = new JRadioButton(" Alternativ 1");
	private JRadioButton rb2 = new JRadioButton(" Alternativ 2");
	private JRadioButton rb3 = new JRadioButton(" Alternativ 3");
	private JRadioButton rb4 = new JRadioButton(" Alternativ 4");
	private ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel lblResult = new JLabel("Hej");

	private JButton btnSubmit = new JButton("OK");
	private JButton btnNewQuestion = new JButton("Nästa fråga");

	public BossUITemp(QuizController c) {

		controller = c;
		setPreferredSize(new Dimension(500, 600));
		lblTitle.setPreferredSize(new Dimension(400, 30));
		add(lblTitle);

		//add(lblCodeEx);

		//add(altPanel());
		
		ButtonListener listener = new ButtonListener();
		btnSubmit.addActionListener(listener);
		btnNewQuestion.addActionListener(listener);



	}
	
	public void addCode(){
		this.add(lblCodeEx);
	}

	/**
	 * Konstruktor för att testa- skall tas bort
	 */
	public BossUITemp() {
	//	setQuestion("src/media/Boss1.png", "1", "2", "3", "4");
		setPreferredSize(new Dimension(500, 600));
		lblTitle.setPreferredSize(new Dimension(400, 30));
		add(lblTitle);
		add(lblCodeEx);
		add(altPanel());
		add(btnSubmit);
		btnSubmit.addActionListener(new ButtonListener());

	}

	public void setController(QuizController c) {
		controller = c;

	}

	

	public void nextQuestion() {
		controller.getBossQuestion();
	}

	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource()==btnNewQuestion){
				controller.getBossQuestion();
			}

			if (e.getSource() == btnSubmit) {
				String answer = null;
				String cAnswer = controller.getCorrectBossAnswer();
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
				if (answer.equals(cAnswer)) {
					lblResult.setText("RÄTT!");
					lblResult.setForeground(Color.GREEN);
					btnNewQuestion.setEnabled(true);
				} else {
					lblResult.setText("FEL!");
					lblResult.setForeground(Color.RED);
					btnNewQuestion.setEnabled(true);


				}

			}
		}

	}

	public JPanel altPanel() {
		JPanel altPanel = new JPanel(new GridLayout(4, 1));
		altPanel.setPreferredSize(new Dimension(420, 200));
		
		buttonGroup.add(rb1);
		buttonGroup.add(rb2);
		buttonGroup.add(rb3);
		buttonGroup.add(rb4);
		rb1.setPreferredSize(new Dimension(400, 30));
		rb2.setPreferredSize(new Dimension(400, 30));
		rb3.setPreferredSize(new Dimension(400, 30));
		rb4.setPreferredSize(new Dimension(400, 30));

		altPanel.add(rb1);
		altPanel.add(rb2);
		altPanel.add(rb3);
		altPanel.add(rb4);

		return altPanel;
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.add(new BossUITemp());
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
