package codequiz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
/**
 * Klassen är ett GUI som används när spelaren möter bossen.
 * @author gustavbodestad
 *
 */
public class BossUI extends JPanel {
	private QuizController controller;
	private JLabel labelBack = new JLabel();
	private ImageIcon icon = new ImageIcon("src/media/vold12.jpg");
	private ImageIcon iconCode = new ImageIcon("src/media/fråga1.png");
	private JPanel panelEast = new JPanel();
	private JPanel gridPanel = new JPanel(new GridLayout(4, 1));
	private JLabel labelTitle = new JLabel("", SwingConstants.CENTER);
	private JLabel labelSubTitle = new JLabel("", SwingConstants.CENTER);
	private JLabel labelCode = new JLabel("", SwingConstants.CENTER);
	private JLabel lblLives = new JLabel();
	private JLabel lblPoints = new JLabel();
	private JLabel lblResult = new JLabel("");
	private JPanel emptyPanel = new JPanel();
	private JPanel panelSouth = new JPanel();
	private JPanel southPanel = new JPanel(new GridLayout(1, 4));
	private JPanel deadPanel = new JPanel();
	private JLabel labelDead = new JLabel(
			"<html><Font Color=white>Du är död.</Font><html>");
	private JButton buttonOK = new JButton("OK");
	private JButton buttonBack = new JButton("Huvudmeny");
	private JButton buttonResult = new JButton("Resultat");
	private JButton buttonNext = new JButton("Ny fråga");
	private JRadioButton rb1 = new JRadioButton(
			"<html><Font color=white> GustavEmmaJohan</font><html>");
	private JRadioButton rb2 = new JRadioButton(
			"<html><Font color=white> Gustav Emma Johan</font><html>");
	private JRadioButton rb3 = new JRadioButton(
			"<html><Font color=white> Gustav Emma</font><html>");
	private JRadioButton rb4 = new JRadioButton(
			"<html><Font color=white> Emma Johan</font><html>");
	private ButtonGroup buttonGroup = new ButtonGroup();
	private Listener listener;

	public BossUI(QuizController inController) {
		this.controller = inController;
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(800, 600));
		labelBack.setIcon(icon);
		labelBack.setLayout(new GridLayout(1, 2));
		add(labelBack);
		emptyPanel.setLayout(new BorderLayout());
		emptyPanel.setOpaque(false);
		emptyPanel.add(getPanelSouth(), BorderLayout.SOUTH);
		labelBack.add(emptyPanel);
		labelBack.add(getEastPanel());
		gridPanel.setOpaque(false);
		labelDead.setOpaque(false);
		deadPanel.setOpaque(false);
	}
	
	/**
	 * Sätter en bild(fråga) på plats.
	 */
	public void setCode() {
		labelBack.add(labelCode);
	}

	// public JPanel getNorthPanel(){
	//
	// }

	public JPanel getEastPanel() {
		// labelCode.setOpaque(true);
		// labelCode.setBackground(Color.DARK_GRAY);
		listener = new Listener();
		buttonOK.setPreferredSize(new Dimension(150, 30));
		labelTitle.setFont(new Font("Serif", Font.ITALIC, 18));
		labelSubTitle.setFont(new Font("Serif", Font.ITALIC, 18));
		panelEast.setLayout(new FlowLayout());
		panelEast.setOpaque(false);
		panelEast.setAlignmentX(CENTER_ALIGNMENT);
		labelTitle.setHorizontalTextPosition(JLabel.CENTER);
		labelTitle
				.setText("<HTML><Font color=white><center>Du möter Lord Voldemort. <br><b> Kämpa!</b><br>"
						+ "Vad blir utskriften?</center></b></Font></HTML>");
		labelTitle.setPreferredSize(new Dimension(400, 100));
		labelSubTitle
				.setText("<HTML><Font color=white><center>Vad blir utskriften?</center></b></Font></HTML>");
		panelEast.add(labelTitle);
		labelCode.setPreferredSize(new Dimension(300, 220));
		panelEast.add(labelCode);
		buttonGroup.add(rb1);
		buttonGroup.add(rb2);
		buttonGroup.add(rb3);
		buttonGroup.add(rb4);
		rb1.setOpaque(false);
		rb2.setOpaque(false);
		rb3.setOpaque(false);
		rb4.setOpaque(false);
		rb1.setFont((new Font("Serif", Font.BOLD, 12)));
		rb2.setFont((new Font("Serif", Font.BOLD, 12)));
		rb3.setFont((new Font("Serif", Font.BOLD, 12)));
		rb4.setFont((new Font("Serif", Font.BOLD, 12)));
		rb1.setForeground(Color.WHITE);
		rb2.setForeground(Color.WHITE);
		rb3.setForeground(Color.WHITE);
		rb4.setForeground(Color.WHITE);
		gridPanel.setPreferredSize(new Dimension(400, 180));
		gridPanel.add(rb1);
		gridPanel.add(rb2);
		gridPanel.add(rb3);
		gridPanel.add(rb4);
		gridPanel.setLocation(200, 300);
		buttonOK.addActionListener(listener);
		buttonBack.addActionListener(listener);
		buttonResult.addActionListener(listener);
		buttonNext.addActionListener(listener);
		panelEast.add(gridPanel);
		southPanel.setOpaque(false);
		southPanel.add(buttonOK);
		southPanel.add(buttonNext);
		southPanel.add(buttonResult);
		southPanel.add(buttonBack);
		southPanel.setPreferredSize(new Dimension(400, 30));
		buttonNext.setEnabled(false);
		buttonResult.setEnabled(false);
		buttonBack.setEnabled(false);
		panelEast.add(southPanel);
		return panelEast;
	}

	private JPanel getDeadPanel() {
		labelDead.setFont(new Font("Serif", Font.ITALIC, 22));
		deadPanel.add(labelDead);
		return deadPanel;
	}

	public JPanel getPanelSouth() {
		panelSouth = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelSouth.setPreferredSize(new Dimension(100, 80));
		panelSouth.add(Box.createRigidArea(new Dimension(20, 20)));
		panelSouth.add(lblPoints = new JLabel(new ImageIcon(
				"src/media/EmblemSml.png")));
		panelSouth.setVisible(true);
		panelSouth.add(Box.createRigidArea(new Dimension(20, 20)));
		panelSouth.add(lblLives = new JLabel(new ImageIcon(
				"src/media/heartSml.png")));
		panelSouth.add(Box.createRigidArea(new Dimension(20, 20)));
		panelSouth.add(lblResult);
		lblLives.setVisible(true);
		panelSouth.add(Box.createRigidArea(new Dimension(10, 20)));
		panelSouth.setOpaque(false);
		lblPoints.setForeground(Color.WHITE);
		lblLives.setForeground(Color.WHITE);
		lblPoints.setText("   Poäng: " + controller.getPoints());
		lblLives.setText("   " + controller.getlives());
		return panelSouth;
	}
	/**
	 * Hanterar funktioner för hur UI förändras när man dör.
	 */
	private void die() {
		panelEast.add(getDeadPanel(), BorderLayout.CENTER);
		labelCode.setVisible(false);
		labelTitle.setVisible(false);
		buttonBack.setEnabled(true);
		buttonResult.setEnabled(true);
		buttonNext.setEnabled(false);
		gridPanel.setVisible(false);
	}
	
	/**
	 * Placerar en fråga i UI't.
	 * @param picUrl
	 * @param a1
	 * @param a2
	 * @param a3
	 * @param a4
	 */
	public void setQuestion(String picUrl, String a1, String a2, String a3,
			String a4) {
		ImageIcon i = new ImageIcon(picUrl);
		labelCode.setIcon(i);
		rb1.setText(a1);
		rb2.setText(a2);
		rb3.setText(a3);
		rb4.setText(a4);

	}
	/**
	 * Klassen innehåller en lyssnare.
	 * @author gustavbodestad
	 *
	 */
	private class Listener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == buttonBack) {
				controller.setPanel(controller.getMainUI());
				controller.newGame();
				controller.disableGamebutton();
			}

			if (e.getSource() == buttonResult) {
				controller.userpoints();
				controller.newResultUI();
				controller.fetchhousepoints();
			}

			if (e.getSource() == buttonNext) {
				controller.getBossQuestion();
				buttonNext.setEnabled(false);
				rb1.setEnabled(true);
				rb2.setEnabled(true);
				rb3.setEnabled(true);
				rb4.setEnabled(true);
				buttonGroup.clearSelection();
			}

			if (e.getSource() == buttonOK) {
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
				rb1.setEnabled(false);
				rb2.setEnabled(false);
				rb3.setEnabled(false);
				rb4.setEnabled(false);
				buttonGroup.clearSelection();
				if (answer == null) {
					JOptionPane.showMessageDialog(null,
							"Välj ett alternativ, tack");
					buttonOK.setEnabled(true);
				} else {
					if (answer.equals(cAnswer)) {
						if (controller.getbossIndex() == 5) {// om rätt svar och
																// boss är
																// besegrad
							controller.increasePoints();
							controller.win();
						} else {
							lblResult.setText("RÄTT");
							lblResult.setForeground(Color.GREEN);
							controller.increasePoints();
							lblPoints.setText("Poäng: "
									+ controller.getPoints());
							buttonNext.setEnabled(true);
						}

					} else {
						if (controller.getbossIndex() == 5) {// om fel svar men
																// liv finns
																// kvar och boss
																// är besegrad
							controller.decreasePoints();
							controller.win();
						} else {
							lblResult.setText("FEL!");
							lblResult.setForeground(Color.RED);
							controller.decreasePoints();
							controller.decreaseLives();
							lblPoints.setText(" Poäng: "
									+ controller.getPoints());
							lblLives.setText(" " + controller.getlives());
							buttonNext.setEnabled(true);
						}

					}
					if (controller.getlives() == 0) {
						die();
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new BossUI(new QuizController()));
		frame.pack();
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}