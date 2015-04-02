package codequiz;

import java.awt.*;

import javax.swing.*;

public class SkaEjFinnasUI extends JPanel {
	
	public void showScenario() {
		JFrame riddleFrame = new JFrame();
		riddleFrame.setTitle("Code Quiz");
		riddleFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		riddleFrame.setVisible(true);
		JPanel jp = new JPanel(new FlowLayout());
		jp.setBackground(Color.BLACK);
		jp.setPreferredSize(new Dimension(1000, 700));
//		riddleFrame.setPreferredSize(new Dimension(900, 500));
		riddleFrame.add(jp);
		ImageIcon ii = new ImageIcon("C:/Users/Evelyn/Desktop/Scenario/Tom Riddle's Diary.jpg");
		jp.add(new JLabel(ii));
		riddleFrame.pack();

	}
	
	public static void main(String[] args) {
		SkaEjFinnasUI ui = new SkaEjFinnasUI();
		ui.showScenario();
	}
	
	/**
	 *  För att få en label osynlig. Till en fråga t.ex.
	 *  jLabel(i).setVisible(false);
	 */
}
