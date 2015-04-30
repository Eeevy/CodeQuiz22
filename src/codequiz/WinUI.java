package codequiz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WinUI extends JPanel implements ActionListener {
	private JLabel lblBackground = new JLabel(new ImageIcon(
			"src/media/win.jpg"));
	private JPanel panelEast = new JPanel(new FlowLayout());
	private JButton buttonMenu = new JButton("Huvudmeny");
	private JButton buttonRes = new JButton("Resultat");
	private QuizController controller;
	
	public WinUI(QuizController controller) {
		this.controller = controller;
		lblBackground.setLayout(new BorderLayout());
		panelEast.setPreferredSize(new Dimension(130, 500));
		buttonMenu.setPreferredSize(new Dimension(110, 30));
		buttonRes.setPreferredSize(new Dimension(110, 30));
		panelEast.add(buttonMenu);
		panelEast.add(buttonRes);
		panelEast.setOpaque(false);
		lblBackground.add(panelEast, BorderLayout.EAST);
		buttonRes.addActionListener(this);
		buttonMenu.addActionListener(this);
		add(lblBackground);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == buttonMenu) {
			controller.setPanel(controller.getMainUI());
			controller.newGame();
		}
		
		if (e.getSource() == buttonRes) {
			controller.newResultUI();
			controller.setPanel(controller.getResultUI());
			controller.setScore(controller.getPoints());
		}		
	}
	
//	public static void main(String[] args) {
//		JFrame mainFrame = new JFrame("");
//		mainFrame.setTitle("Code Quiz");
//		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		mainFrame.add(new WinUI(new QuizController()));
//		mainFrame.pack();
//		mainFrame.setResizable(false);
//		mainFrame.setLocationRelativeTo(null);
//		mainFrame.setVisible(true);
//	}
}