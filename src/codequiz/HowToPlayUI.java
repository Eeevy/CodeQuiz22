package codequiz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class HowToPlayUI extends JPanel {
	private QuizController controller;
	
	private JButton btnStartGame = new JButton("Spela!");
	private JButton btnMain = new JButton("Huvudmeny");
	private JLabel lblBackground = new JLabel(new ImageIcon("src/media/howToPlay.png"));
	private JLabel lblWelcomeUser = new JLabel("");
	
	public HowToPlayUI(){
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(800, 600));
		lblBackground.setLayout(null);
		add(lblBackground);
		lblBackground.add(btnMain);
		lblBackground.add(lblWelcomeUser);
		lblBackground.add(btnStartGame);
		lblWelcomeUser.setFont(new Font("Times New Roman", Font.BOLD,20));
		lblWelcomeUser.setForeground(Color.BLACK);

		btnMain.setBounds(450, 500, 100, 25);
		btnStartGame.setBounds(620, 500, 100, 25);
		lblWelcomeUser.setBounds(35, 40, 300, 50);
		
		btnStartGame.setVisible(false);

		ButtonListener listener = new ButtonListener();
		btnMain.addActionListener(listener);
		btnStartGame.addActionListener(listener);
	}
	
	public void setController(QuizController controller){
		this.controller = controller;
	}
	
	public void setWelcome(String user){
		lblWelcomeUser.setText(controller.changeStringQ("Välkommen till Hogwarts " + user + "!"));
	}
	
	public void enableGamebutton(boolean state){
		btnStartGame.setVisible(state);
	}
	
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==btnMain){
				controller.setPanel(controller.getMainUI());
				controller.newGame();

			}
			if(e.getSource()==btnStartGame){
				controller.setPanel(controller.getSortingCeremonyUI());	
				controller.getSortingQuestion();
			}
		}
	}
	
	public static void main(String[] args){
		JFrame frame = new JFrame();
		frame.add(new HowToPlayUI());
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);		
	}
}