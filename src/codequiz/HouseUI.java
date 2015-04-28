package codequiz;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class HouseUI extends JPanel {
	private QuizController controller;

	private JPanel titlePanel;
	private JPanel housePanel;
	private JPanel iconPanel;

	private JLabel lblRav = new JLabel(
			new ImageIcon("src/media/Ravenclaw1.png"));
	private JLabel lblGry = new JLabel(new ImageIcon(
			"src/media/Gryffindor1.png"));
	private JLabel lblSly = new JLabel(
			new ImageIcon("src/media/Slytherin1.png"));
	private JLabel lblHuf = new JLabel(new ImageIcon(
			"src/media/Hufflepuff1.png"));
	private JLabel lblRavenclaw = new JLabel(new ImageIcon(
			"src/media/RavenclawHouse.png"));
	private JLabel lblGryffindor = new JLabel(new ImageIcon(
			"src/media/GryffindorHouse.png"));
	private JLabel lblSlytherin = new JLabel(new ImageIcon(
			"src/media/SlytherinHouse.png"));
	private JLabel lblHufflepuff = new JLabel(new ImageIcon(
			"src/media/HufflepuffHouse.png"));

	private JLabel lblHouseFounder = new JLabel(" Grundare: ");
	private JLabel lblFounder = new JLabel("Grundare...");
	private JLabel lblHouseColors = new JLabel(" Färger: ");
	private JLabel lblColors = new JLabel("Färger...");
	private JLabel lblHouseEmblem = new JLabel(" Djur på vapensköld: ");
	private JLabel lblEmblem = new JLabel("Djur...");
	private JLabel lblHouseElement = new JLabel(" Element: ");
	private JLabel lblElement = new JLabel("Element...");
	private JLabel lblHouseMagicalItem = new JLabel(" Magiskt föremål: ");
	private JLabel lblMagicalItem = new JLabel("Magiskt föremål...");
	private JLabel lblHouseGhost = new JLabel(" Elevhemsspöke: ");
	private JLabel lblGhost = new JLabel("Spöke...");
	private JLabel lblHouseHead = new JLabel(" Elevhemsföreståndare: ");
	private JLabel lblHead = new JLabel("Föreståndare...");
	private JLabel lblHouseCharacteristics = new JLabel(" Egenskaper: ");
	private JLabel lblCharacteristics = new JLabel("Egenskaper...");

	private JButton btnPlay = new JButton("Spela");
	
	public HouseUI() {
		System.out.println("HouseUI: Konstruktor");
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(800, 600));
		setLayout(new BorderLayout());

		add(titlePanel(), BorderLayout.NORTH);
		add(housePanel(), BorderLayout.WEST);
		add(iconPanel(), BorderLayout.EAST);
		
//		ravenclaw(); // Bara ett exempel.
//		gryffindor();
//		slytherin();
//		hufflepuff();
		
		ButtonPlayListener listener = new ButtonPlayListener();
		btnPlay.addActionListener(listener);
	}
	
	public void setController(QuizController controller) {
		this.controller = controller;
	}

	public JPanel titlePanel() {
		titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		titlePanel.setPreferredSize(new Dimension(800, 150));
		titlePanel.setBackground(Color.BLACK);
		return titlePanel;
	}

	public JPanel housePanel() {
		housePanel = new JPanel(new GridLayout(8, 2));
		housePanel.setPreferredSize(new Dimension(500, 450));
		housePanel.setBackground(Color.WHITE);
		housePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 20));
		housePanel.add(lblHouseFounder);
		housePanel.add(lblFounder);
		housePanel.add(lblHouseColors);
		housePanel.add(lblColors);
		housePanel.add(lblHouseEmblem);
		housePanel.add(lblEmblem);
		housePanel.add(lblHouseElement);
		housePanel.add(lblElement);
		housePanel.add(lblHouseMagicalItem);
		housePanel.add(lblMagicalItem);
		housePanel.add(lblHouseGhost);
		housePanel.add(lblGhost);
		housePanel.add(lblHouseHead);
		housePanel.add(lblHead);
		housePanel.add(lblHouseCharacteristics);
		housePanel.add(lblCharacteristics);
		return housePanel;
	}

	public JPanel iconPanel() {
		iconPanel = new JPanel(new FlowLayout());
		iconPanel.setPreferredSize(new Dimension(300, 450));
		iconPanel.setBackground(Color.BLACK);
		iconPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 20));
		iconPanel.add(btnPlay);
		iconPanel.add(Box.createRigidArea(new Dimension(300, 20)));
		btnPlay.setPreferredSize(new Dimension(70, 30));
		return iconPanel;
	}

	public void ravenclaw() {///////////////////////////////////////
		titlePanel.add(lblRavenclaw);
		housePanel.setBackground(Color.CYAN);
		iconPanel.add(lblRav);
		lblFounder.setText("Rowena Ravenclaw");
		lblColors.setText("Blått och brons");
		lblEmblem.setText("Örn");
		lblElement.setText("Luft");
		lblMagicalItem.setText("Diadem");
		lblGhost.setText("Den Grå Damen");
		lblHead.setText("Filius Flitwick");
		lblCharacteristics
				.setText("Intelligens, kvicktänkthet och kreativitet");
	}

	public void gryffindor() {
		titlePanel.add(lblGryffindor);
		housePanel.setBackground(Color.RED);
		iconPanel.add(lblGry);
		lblFounder.setText("Godric Gryffindor");
		lblColors.setText("Scharlakansrött och guld");
		lblEmblem.setText("Lejon");
		lblElement.setText("Eld");
		lblMagicalItem.setText("Svärd");
		lblGhost.setText("Nästan Huvudlöse Nick");
		lblHead.setText("Minerva McGonagall");
		lblCharacteristics.setText("Mod, ridderskap och styrka");
	}

	public void slytherin() {
		titlePanel.add(lblSlytherin);
		housePanel.setBackground(Color.GREEN);
		iconPanel.add(lblSly);
		lblFounder.setText("Salazar Slytherin");
		lblColors.setText("Grönt och silver");
		lblEmblem.setText("Orm");
		lblElement.setText("Vatten");
		lblMagicalItem.setText("Medaljong");
		lblGhost.setText("Den Blodige Baronen");
		lblHead.setText("Severus Snape");
		lblCharacteristics.setText("Ambition, påhittighet och list");
	}

	public void hufflepuff() {
		System.out.println("HouseUI: hufflepuff");
		titlePanel.add(lblHufflepuff);
		housePanel.setBackground(Color.YELLOW);
		iconPanel.add(lblHuf);
		lblFounder.setText("Helga Hufflepuff");
		lblColors.setText("Gult och svart");
		lblEmblem.setText("Grävling");
		lblElement.setText("Jord");
		lblMagicalItem.setText("Guldbägare");
		lblGhost.setText("Den Tjocke Munkbrodern");
		lblHead.setText("Pomona Sprout");
		lblCharacteristics.setText("Tålamod, vänskap och lojalitet");
	}
	
	private class ButtonPlayListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnPlay) {
				controller.setPanel(controller.getQuizUI());
			}
		}
	}
}