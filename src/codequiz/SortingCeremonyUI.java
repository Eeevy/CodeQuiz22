package codequiz;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * Såhär ska det absolut inte vara egentligen, bara ett första utkast.
 * Fönstervisning ska flyttas till QuizController, ingen mainmetod i den här
 * klassen etc. Ville bara testa.
 * 
 * @author Hemligt
 *
 */
public class SortingCeremonyUI extends JPanel {
	private QuizController controller;
	private HouseUI houseUI;
	private JFrame frame;

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
			"Svara på frågan för att ta reda på vilket elevhem du passar bäst in i.");

	private JRadioButton rb1 = new JRadioButton("1");
	private JRadioButton rb2 = new JRadioButton("2");
	private JRadioButton rb3 = new JRadioButton("3");
	private JRadioButton rb4 = new JRadioButton("4");
	private JButton btnOK = new JButton("OK");

	public SortingCeremonyUI() {
		frame = new JFrame("");
		frame.setBackground(Color.BLACK);
		frame.setTitle("Code Quiz");
		frame.setPreferredSize(new Dimension(800, 600));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setLayout(new BorderLayout());

		frame.add(northPanel(), BorderLayout.NORTH);
		frame.add(westPanel(), BorderLayout.WEST);
		frame.add(centerPanel(), BorderLayout.CENTER);
		frame.add(eastPanel(), BorderLayout.EAST);
		frame.add(southPanel(), BorderLayout.SOUTH);

		// setBackground(Color.BLACK);
		// setPreferredSize(new Dimension(800, 600));
		// setLayout(new BorderLayout());

		// add(northPanel(), BorderLayout.NORTH);
		// add(westPanel(), BorderLayout.WEST);
		// add(centerPanel(), BorderLayout.CENTER);
		// add(eastPanel(), BorderLayout.EAST);
		// add(southPanel(), BorderLayout.SOUTH);
		// addActionListeners();
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
		return centerPanel;
	}

	public JPanel eastPanel() {
		eastPanel = new JPanel(new FlowLayout());
		eastPanel.setPreferredSize(new Dimension(380, 225));
		eastPanel.setBackground(Color.BLACK);
		eastPanel.add(lblHat);
		return eastPanel;
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

//	private class ButtonListener implements ActionListener {
//
//		public void actionPerformed(ActionEvent e) {
//			btnOK.setEnabled(false);
//			String answer = null;
//			String ravenclaw = controller.getRavenclawAnswer();
//			String gryffindor = controller.getGryffindorAnswer();
//			String slytherin = controller.getSlytherinAnswer();
//			String hufflepuff = controller.getHufflepuffAnswer();
//
//			if (answer.equals(ravenclaw)) {
//				houseUI.ravenclaw();
//			}
//
//			if (answer.equals(gryffindor)) {
//				houseUI.gryffindor();
//			}
//
//			if (answer.equals(slytherin)) {
//				houseUI.slytherin();
//			}
//
//			if (answer.equals(hufflepuff)) {
//				houseUI.hufflepuff();
//			}
//		}
//	}

	public static void main(String[] args) {
		SortingCeremonyUI sortingCeremony = new SortingCeremonyUI();
	}
}