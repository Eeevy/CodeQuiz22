package codequiz;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

class ResultUI extends JPanel {

	private JLabel labelImageSly = new JLabel(new ImageIcon("src/media/Slytherin1.png"));
	private JLabel labelImageGryff = new JLabel(new ImageIcon("src/media/Gryffindor1.png"));
	private JLabel labelImageHuff = new JLabel(new ImageIcon("src/media/hufflepuff1.png"));
	private JLabel labelImageRav = new JLabel(new ImageIcon("src/media/Ravenclaw1.png"));
	private JLabel background = new JLabel(new ImageIcon("src/media/bakgrund7.jpg"));
	
	private JLabel lblGryff = new JLabel("0");
	private JLabel lblRav = new JLabel("0");
	private JLabel lblHuff = new JLabel("0");
	private JLabel lblSly = new JLabel("0");
	private JLabel lblTitle = new JLabel(" - Poängställning - ");
	
	private JPanel pnl1 = new JPanel(new BorderLayout());
	private JPanel pnl2 = new JPanel(new BorderLayout());
	private JPanel pnl3 = new JPanel(new BorderLayout());
	private JPanel pnl4 = new JPanel(new BorderLayout());
	private JPanel mainPanel = new JPanel(new BorderLayout());
	private JPanel gridPanel = new JPanel(new GridLayout(2, 2));
	
	private int slytherin = 0, gryffindor = 0, hufflepuff = 0, ravenclaw = 0;
	
	private QuizController controller;
	
	public int getSlytherin() {
		return slytherin;
	}

	public void setSlytherin(int slytherin) {
		this.slytherin = slytherin;
		lblSly.setText("" + slytherin);
	}

	public int getGryffindor() {
		return gryffindor;
	}

	public void setGryffindor(int gryffindor) {
		this.gryffindor = gryffindor;
		lblGryff.setText("" + gryffindor);
	}

	public int getHufflepuff() {
		return hufflepuff;
	}

	public void setHufflepuff(int hufflepuff) {
		this.hufflepuff = hufflepuff;
		lblHuff.setText("" + hufflepuff);
	}

	public int getRavenclaw() {
		return ravenclaw;
	}

	public void setRavenclaw(int ravenclaw) {
		this.ravenclaw = ravenclaw;
		lblRav.setText("" + ravenclaw);
	}

	public ResultUI() {
		background.setLayout(new BorderLayout());
		add(background);
		
		lblTitle.setOpaque(false);
		lblTitle.setFont(new Font("Serif", Font.PLAIN, 28));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setVerticalAlignment(SwingConstants.CENTER);
		lblTitle.setForeground(Color.WHITE);
		
		pnl1.add(labelImageSly, BorderLayout.CENTER);
		lblSly.setFont(new Font("Serif", Font.PLAIN, 20));
		lblSly.setForeground(Color.WHITE);
		lblSly.setHorizontalAlignment(SwingConstants.CENTER);
		lblSly.setVerticalAlignment(SwingConstants.CENTER);
		lblSly.setOpaque(false);
		pnl1.add(lblSly, BorderLayout.SOUTH);
		
		
		pnl2.add(labelImageGryff, BorderLayout.CENTER);
		lblGryff.setHorizontalAlignment(SwingConstants.CENTER);
		lblGryff.setForeground(Color.WHITE);
		lblGryff.setVerticalAlignment(SwingConstants.CENTER);
		lblGryff.setFont(new Font("Serif", Font.PLAIN, 20));
		pnl2.add(lblGryff, BorderLayout.SOUTH);
		
		pnl3.add(labelImageHuff, BorderLayout.CENTER);
		lblHuff.setFont(new Font("Serif", Font.PLAIN, 20));
		lblHuff.setHorizontalAlignment(SwingConstants.CENTER);
		lblHuff.setVerticalAlignment(SwingConstants.CENTER);
		lblHuff.setForeground(Color.WHITE);
		pnl3.add(lblHuff, BorderLayout.SOUTH);
		
		pnl4.add(labelImageRav, BorderLayout.CENTER);
		lblRav.setFont(new Font("Serif", Font.PLAIN, 20));
		lblRav.setHorizontalAlignment(SwingConstants.CENTER);
		lblRav.setVerticalAlignment(SwingConstants.CENTER);
		lblRav.setForeground(Color.WHITE);
		pnl4.add(lblRav, BorderLayout.SOUTH);
		
		pnl1.setOpaque(false);
		pnl2.setOpaque(false);
		pnl3.setOpaque(false);
		pnl4.setOpaque(false);
		
		gridPanel.add(pnl1);
		gridPanel.add(pnl2);
		gridPanel.add(pnl3);
		gridPanel.add(pnl4);
		gridPanel.setOpaque(false);
		
		mainPanel.add(lblTitle, BorderLayout.NORTH);
		mainPanel.add(gridPanel, BorderLayout.CENTER);
		mainPanel.setOpaque(false);
		
		background.add(mainPanel);
		
		
	}
	public void setController(QuizController controller){
		this.controller = controller;
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setTitle("Background Color for JFrame");
		frame.setSize(400, 400);
		frame.setLocationRelativeTo(null);
		frame.add(new ResultUI());
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
}