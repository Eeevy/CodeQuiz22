package codequiz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class BossUI extends JPanel {
	private QuizController controller;
	private JLabel labelBack = new JLabel();
	private ImageIcon icon = new ImageIcon("src/media/vold12.jpg");
	private ImageIcon iconCode = new ImageIcon("src/media/fråga1.png");
	private JPanel panelEast = new JPanel();
	private JPanel gridPanel = new JPanel(new GridLayout(5, 1));
	private JLabel labelTitle = new JLabel("", SwingConstants.CENTER);
	private JLabel labelSubTitle = new JLabel("", SwingConstants.CENTER);
	private JLabel labelCode = new JLabel("", SwingConstants.CENTER);
	private JPanel emptyPanel = new JPanel();
	private JPanel southPanel = new JPanel(new GridLayout(1, 4));
	private JPanel deadPanel = new JPanel();
	private JLabel labelDead = new JLabel("<html><Font Color=white>Du är död.</Font><html>");
	private JButton buttonOK = new JButton("Ok");
	private JButton buttonBack = new JButton("Huvudmeny");
	private JButton buttonResult = new JButton("Resultat");
	private JButton buttonNext = new JButton("Nästa Fråga");
	private JRadioButton rb1 = new JRadioButton("<html><Font color=white> GustavEmmaJohan</font><html>");
	private JRadioButton rb2 = new JRadioButton("<html><Font color=white> Gustav Emma Johan</font><html>");
	private JRadioButton rb3 = new JRadioButton("<html><Font color=white> Gustav Emma</font><html>");
	private JRadioButton rb4 = new JRadioButton("<html><Font color=white> Emma Johan</font><html>");
	private ButtonGroup buttonGroup = new ButtonGroup();
	private Listener listener;
	
	private String code = "<html><pre><Font Color=white>String[] namn = { \"Gustav\", \"Emma\", \"Johan\" } "
			+ "<br>     for(int i=0; i < namn.length(); i++) {<br>     "
			+ "    System.out.print(namn[i])<br>           }</pre></Font></html>";
	
	
	public BossUI(QuizController inController) {
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(800, 600));
		labelBack.setIcon(icon);
		setCode(code);
		labelBack.setLayout(new GridLayout(1,2));
		add(labelBack);
		emptyPanel.setOpaque(false);
		labelBack.add(emptyPanel);
		labelBack.add(getEastPanel());
		this.controller = inController;
		gridPanel.setOpaque(false);
		labelDead.setOpaque(false);
		deadPanel.setOpaque(false);
	}
	
	public void setCode(String inCode) {
		labelCode.setText(inCode);	
	}
	
	public JPanel getEastPanel() {
		listener = new Listener();
		buttonOK.setPreferredSize(new Dimension(150, 30));
		labelTitle.setFont(new Font("Serif", Font.ITALIC, 18));
		labelSubTitle.setFont(new Font("Serif", Font.ITALIC, 18));
		panelEast.setLayout(new BorderLayout());
		panelEast.setOpaque(false);
		panelEast.setAlignmentX(CENTER_ALIGNMENT);
		labelTitle.setHorizontalTextPosition(JLabel.CENTER);
		labelTitle.setText("<HTML><Font color=white><center>Du möter Lord Voldemort. <br><b> Kämpa!</b><br>"
				+ "Vad blir utskriften?</center></b></Font></HTML>");
		labelSubTitle.setText("<HTML><Font color=white><center>Vad blir utskriften?</center></b></Font></HTML>");
		panelEast.add(labelTitle, BorderLayout.NORTH);
		gridPanel.add(labelCode);
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
		gridPanel.add(rb1);
		gridPanel.add(rb2);
		gridPanel.add(rb3);
		gridPanel.add(rb4);
		buttonOK.addActionListener(listener);
		buttonBack.addActionListener(listener);
		buttonResult.addActionListener(listener);
		panelEast.add(gridPanel, BorderLayout.CENTER);
		southPanel.setOpaque(false);
		southPanel.add(buttonOK);
		southPanel.add(buttonNext);
		southPanel.add(buttonResult);
		southPanel.add(buttonBack);
		buttonNext.setEnabled(false);
		buttonResult.setEnabled(false);
		buttonBack.setEnabled(false);
		panelEast.add(southPanel, BorderLayout.SOUTH);
		
		return panelEast;
		
	}
	
	private JPanel getDeadPanel() {
		labelDead.setFont(new Font("Serif", Font.ITALIC, 22));
		deadPanel.add(labelDead);
		return deadPanel;
		
	}
	
	private void die() {
		panelEast.add(getDeadPanel(), BorderLayout.CENTER);
		labelCode.setVisible(false);
		labelTitle.setVisible(false);
		buttonBack.setEnabled(true);
		buttonResult.setEnabled(true);
		gridPanel.setVisible(false);
	}
	
	public void setAnswers(String a1, String a2, String a3, String a4) {
		rb1.setText(a1);
		rb2.setText(a2);
		rb3.setText(a3);
		rb4.setText(a4);
	}
	
	private class Listener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == buttonOK) {
				die();
			}
			
			if (e.getSource() == buttonBack) {
				
			}
			
			if (e.getSource() == buttonResult) {
				
			}
			
			if (e.getSource() == buttonNext) {
				
			}
			
		}
		
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new BossUI(new QuizController()));
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
