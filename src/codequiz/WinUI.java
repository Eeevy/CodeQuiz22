package codequiz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WinUI {
	private JFrame mainFrame;
	private JLabel lblBackground = new JLabel(new ImageIcon(
			"src/media/win.jpg"));
//	private JPanel panelEast = new JPanel(new GridLayout(13,1));
	private JPanel panelEast = new JPanel(new FlowLayout());
	private JButton buttonMenu = new JButton("Huvudmeny");
	private JButton buttonRes = new JButton("Resultat");
	
	
	public WinUI() {
		lblBackground.setLayout(new BorderLayout());
//		buttonMenu.setPreferredSize(new Dimension(70, 10));
		panelEast.setPreferredSize(new Dimension(130, 500));
		buttonMenu.setPreferredSize(new Dimension(110, 30));
		buttonRes.setPreferredSize(new Dimension(110, 30));
		panelEast.add(buttonMenu);
		panelEast.add(buttonRes);
		panelEast.setOpaque(false);
		lblBackground.add(panelEast, BorderLayout.EAST);
		
		
		mainFrame = new JFrame("");
		mainFrame.setTitle("Code Quiz");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.add(lblBackground);
		mainFrame.pack();
		mainFrame.setResizable(false);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
	}
	
	public static void main(String[] args) {
		WinUI win = new WinUI();
	}

}
