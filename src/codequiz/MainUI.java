package codequiz;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * Visar ett startfönster med huvudmeny. To be continued...
 * 
 * @author Evelyn Gustavsson
 *
 */
public class MainUI {
	private MainGUI mainGUI;

	public MainUI() {
		showGUI();
	}

	public void showGUI() {
		mainGUI = new MainGUI();
		JFrame mainFrame = new JFrame("MainUI");
		mainFrame.setTitle("Code Quiz");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.add(mainGUI);
		mainFrame.pack();
		mainFrame.setVisible(true);
	}

	private class MainGUI extends JPanel {
		// Paneler
		private JPanel northPanel;
		private JPanel centerPanel;
		private JPanel eastPanel;

		// Labels
		private JLabel lblTitle = new JLabel("Code Quiz");
		// Lite gay slogan, men har kvar den sålänge..
		private JLabel lblSlogan = new JLabel(
				"- Ett magsikt sätt att lära sig om programmering");
		private JLabel lblMenu = new JLabel("Meny");
		// Några osynliga labels som inte ska finnas egentligen...
		private JLabel lblInvisible = new JLabel();
		private JLabel lblInvisible2 = new JLabel();
		private JLabel lblInvisible3 = new JLabel();
		private JLabel lblInvisible4 = new JLabel();
		private JLabel lblInvisible5 = new JLabel();
		// Bildens placering visas som en label sålänge.
		private JLabel lblHogwarts = new JLabel("Här ska bilden visas!");

		// Knappar
		private JButton btnPlay = new JButton("Spela");
		private JButton btnSignIn = new JButton("Logga in");
		private JButton btnCreateAccount = new JButton("Skapa ett nytt konto");
		private JButton btnHowToPlay = new JButton("Om spelet");
		private JButton btnHighScore = new JButton("High score");
		// private ButtonGroup btnGroup = new ButtonGroup();

		public MainGUI() {
			setLayout(new BorderLayout());
			setPreferredSize(new Dimension(900, 500));

			add(northPanel(), BorderLayout.NORTH);
			add(centerPanel(), BorderLayout.CENTER);
			add(eastPanel(), BorderLayout.EAST);
		}

		/**
		 * Visar titel och slogan.
		 * 
		 * @return
		 */
		public JPanel northPanel() {
			northPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
			northPanel.setPreferredSize(new Dimension(100, 100));
			northPanel.setBackground(Color.BLACK);

			northPanel.add(Box.createRigidArea(new Dimension(150, 20)));
			northPanel.add(lblTitle);
			lblTitle.setForeground(Color.WHITE);
			lblTitle.setFont(new Font("SansSerif", Font.BOLD, 50));

			northPanel.add(Box.createRigidArea(new Dimension(220, 20)));
			northPanel.add(lblSlogan);
			lblSlogan.setForeground(Color.WHITE);
			lblSlogan.setFont(new Font("SansSerif", Font.ITALIC, 15));

			return northPanel;
		}

		/**
		 * Ska visa en bild på Hogwarts logga (inte än dock), men jag tror att jag
		 * har hittat den "perfekta" bilden i alla fall! ^^
		 * 
		 * @return
		 */
		public JPanel centerPanel() {
			centerPanel = new JPanel(new FlowLayout());
			centerPanel.setPreferredSize(new Dimension(400, 150));
			centerPanel.setBackground(Color.BLACK);

			centerPanel.add(Box.createRigidArea(new Dimension(100, 100)));
			centerPanel.add(lblHogwarts);
			lblHogwarts.setForeground(Color.GREEN);
			lblHogwarts.setFont(new Font("SansSerif", Font.BOLD, 20));

			return centerPanel;
		}

		/**
		 * Visar menyn.
		 * 
		 * @return
		 */
		public JPanel eastPanel() {
			eastPanel = new JPanel(new GridLayout(15, 1));
			eastPanel.setPreferredSize(new Dimension(150, 10));
			eastPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
			eastPanel.setBackground(Color.BLACK);

			eastPanel.add(lblMenu);
			lblMenu.setForeground(Color.WHITE);
			lblMenu.setFont(new Font("SansSerif", Font.BOLD, 26));

			eastPanel.add(lblInvisible);
			eastPanel.add(btnPlay);
			eastPanel.add(lblInvisible2);
			eastPanel.add(btnSignIn);
			eastPanel.add(lblInvisible3);
			eastPanel.add(btnCreateAccount);
			eastPanel.add(lblInvisible4);
			eastPanel.add(btnHowToPlay);
			eastPanel.add(lblInvisible5);
			eastPanel.add(btnHighScore);

			btnPlay.setBackground(Color.BLACK);
			btnPlay.setForeground(Color.WHITE);
			btnSignIn.setBackground(Color.BLACK);
			btnSignIn.setForeground(Color.WHITE);
			btnCreateAccount.setBackground(Color.BLACK);
			btnCreateAccount.setForeground(Color.WHITE);
			btnHowToPlay.setBackground(Color.BLACK);
			btnHowToPlay.setForeground(Color.WHITE);
			btnHighScore.setBackground(Color.BLACK);
			btnHighScore.setForeground(Color.WHITE);

			return eastPanel;
		}

		private class MenuListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {

			}
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MainUI();
			}
		});
	}
}