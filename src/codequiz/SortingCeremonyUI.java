package codequiz;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * Klassen är ett UI som hanterar sorteringsscermonin.
 * @author gustavbodestad
 *
 */
public class SortingCeremonyUI extends JPanel {
	private QuizController controller;
	private HouseUI houseUI;

	private JPanel northPanel;
	private JPanel westPanel;
	private JPanel centerPanel;
	private JPanel eastPanel;
	private JPanel southPanel;

	private JLabel lblSortingCeremony = new JLabel(new ImageIcon(
			"src/media/SortingCeremony.png"));
	private JLabel lblHat = new JLabel(new ImageIcon(
			"src/media/TryAHatHarry.jpg"));

	private JLabel lblQuestion = new JLabel("Fråga");
	private JLabel lblInfo = new JLabel(
			"<html>Svara på frågan för att ta reda på vilket elevhem<br>du passar bäst in i.</html>");

	private JRadioButton rb1 = new JRadioButton("1");
	private JRadioButton rb2 = new JRadioButton("2");
	private JRadioButton rb3 = new JRadioButton("3");
	private JRadioButton rb4 = new JRadioButton("4");
	private ButtonGroup btnGroup = new ButtonGroup();
	private JButton btnOK = new JButton("OK");

	public SortingCeremonyUI(HouseUI houseUI) {
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(800, 600));
		setLayout(new BorderLayout());

		add(northPanel(), BorderLayout.NORTH);
		add(westPanel(), BorderLayout.WEST);
		add(centerPanel(), BorderLayout.CENTER);
		add(eastPanel(), BorderLayout.EAST);
		add(southPanel(), BorderLayout.SOUTH);
		this.houseUI = houseUI;

		ButtonListener listener = new ButtonListener();
		btnOK.addActionListener(listener);
	}

	public void setController(QuizController controller) {
		this.controller = controller;
	}

	public JPanel northPanel() {
		northPanel = new JPanel(new FlowLayout());
		northPanel.setPreferredSize(new Dimension(300, 100));
		northPanel.setBackground(Color.BLACK);
		northPanel.add(lblSortingCeremony);
		return northPanel;
	}

	public JPanel westPanel() {
		westPanel = new JPanel(new FlowLayout());
		westPanel.setPreferredSize(new Dimension(20, 10));
		westPanel.setBackground(Color.BLACK);
		return westPanel;
	}

	public JPanel centerPanel() {
		centerPanel = new JPanel(new GridLayout(6, 1));
		centerPanel.setPreferredSize(new Dimension(400, 225));
		centerPanel.setBackground(Color.BLACK);
		centerPanel.add(lblInfo);
		lblInfo.setForeground(Color.GREEN);
		centerPanel.add(lblQuestion);
		lblQuestion.setForeground(Color.WHITE);
		centerPanel.add(rb1);
		rb1.setForeground(Color.WHITE);
		rb1.setBackground(Color.BLACK);
		centerPanel.add(rb2);
		rb2.setForeground(Color.WHITE);
		rb2.setBackground(Color.BLACK);
		centerPanel.add(rb3);
		rb3.setForeground(Color.WHITE);
		rb3.setBackground(Color.BLACK);
		centerPanel.add(rb4);
		rb4.setForeground(Color.WHITE);
		rb4.setBackground(Color.BLACK);
		rb1.setOpaque(false);
		rb2.setOpaque(false);
		rb3.setOpaque(false);
		rb4.setOpaque(false);
		btnGroup.add(rb1);
		btnGroup.add(rb2);
		btnGroup.add(rb3);
		btnGroup.add(rb4);

		return centerPanel;
	}

	public JPanel eastPanel() {
		eastPanel = new JPanel(new FlowLayout());
		eastPanel.setPreferredSize(new Dimension(380, 225));
		eastPanel.setBackground(Color.BLACK);
		eastPanel.add(lblHat);
		return eastPanel;
	}
	/**
	 * Sert till att ingen knapp är markerad.
	 */
	public void clearButtons() {
		btnGroup.clearSelection();
	}

	public JPanel southPanel() {
		southPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		southPanel.setPreferredSize(new Dimension(800, 50));
		southPanel.setBackground(Color.BLACK);
		southPanel.add(Box.createRigidArea(new Dimension(150, 30)));
		southPanel.add(btnOK);
		btnOK.setPreferredSize(new Dimension(70, 30));
		return southPanel;
	}
	/**
	 * Placerar en fråga i UI't
	 * @param question
	 */
	public void setQuestion(String question) {
		lblQuestion.setText(question);
	}
	/**
	 * Sätter alternativ i UI't.
	 * @param al1
	 * @param al2
	 * @param al3
	 * @param al4
	 */
	public void setAlternatives(String al1, String al2, String al3, String al4) {
		rb1.setText(controller.changeStringB(al1));
		rb2.setText(controller.changeStringB(al2));
		rb3.setText(controller.changeStringB(al3));
		rb4.setText(controller.changeStringB(al4));
	}
	/**
	 * Klassen innehåller en lyssnare.
	 * @author gustavbodestad
	 *
	 */
	private class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			btnOK.setEnabled(true);
			String answer = null;
			String answerRavenclaw = controller.changeStringB(controller
					.getAnswerRavenclaw());
			String answerGryffindor = controller.changeStringB(controller
					.getAnswerGryffindor());
			String answerSlytherin = controller.changeStringB(controller
					.getAnswerSlytherin());
			String answerHufflepuff = controller.changeStringB(controller
					.getAnswerHufflepuff());

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

			if (answer.equals(answerRavenclaw)) {
				System.out.println("Ravenclaw");
				((HouseUI) controller.getHouseUI()).ravenclaw();
				controller.setPanel(controller.getHouseUI());
				controller.setHouse("Ravenclaw");
				controller.createHouseUI();
			}

			if (answer.equals(answerGryffindor)) {
				System.out.println("Gryffindor");
				((HouseUI) controller.getHouseUI()).gryffindor();
				controller.setPanel(controller.getHouseUI());
				controller.setHouse("Gryffindor");
				controller.createHouseUI();
			}

			if (answer.equals(answerSlytherin)) {
				System.out.println("Slytherin");
				((HouseUI) controller.getHouseUI()).slytherin();
				controller.setPanel(controller.getHouseUI());
				controller.setHouse("Slytherin");
				controller.createHouseUI();
			}

			if (answer.equals(answerHufflepuff)) {
				System.out.println("Hufflepuff");
				((HouseUI) controller.getHouseUI()).hufflepuff();
				controller.setPanel(controller.getHouseUI());
				controller.setHouse("Hufflepuff");
				controller.createHouseUI();
			}
		}
	}
}