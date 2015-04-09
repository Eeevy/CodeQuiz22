package codequiz;

import java.awt.*;

import javax.swing.*;

public class SkaEjFinnasUI extends JPanel {
	
	private JPanel westPanel; // För frågan
	private JPanel southPanel; // För ikoner (liv etc).
	
	JButton btnAnswer = new JButton("OK");
	
	public void setFrame() {
		JFrame riddleFrame = new JFrame();
		riddleFrame.setTitle("Code Quiz");
		riddleFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		riddleFrame.setVisible(true);
		JPanel jp = new JPanel(new FlowLayout());
		jp.setBackground(Color.BLACK);
		jp.setPreferredSize(new Dimension(1000, 700));
		riddleFrame.add(jp);
		ImageIcon ii = new ImageIcon("C:/Users/Evelyn/Desktop/Scenario/Tom Riddle's Diary.jpg");
		jp.add(new JLabel(ii));
		riddleFrame.pack();
		jp.add(westPanel());
//		jp.add(southPanel(), new GridLayout());
	}
	
	public void getIcon() {
		
	}
	
	public JPanel westPanel() {
		westPanel = new JPanel();
		westPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		westPanel.setPreferredSize(new Dimension(500, 500));
		westPanel.add(btnAnswer);
		
		return westPanel;
	}
	
	public JPanel southPanel() {
		
		return southPanel;
	}
	
	public static void main(String[] args) {
		SkaEjFinnasUI ui = new SkaEjFinnasUI();
		ui.setFrame();
	}
	
	/**
	 *  För att få en label osynlig. Till en fråga t.ex.
	 *  jLabel(i).setVisible(false);
	 */
}
