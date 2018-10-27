package test.divers;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;

import classesNeccessaires.Configuration;
import classesNeccessaires.Partie;
import main.java.view.game.plusMoins.ModelGamePanel;

public class Test extends JFrame {
	private Dimension size = new Dimension (750, 150);
	private Container contentPane;
	private ModelGamePanel gp;
	private Configuration config;
	private Partie partie;
	//private TestCarac signe;
	//private PopUpCombi combi;
	//private Observateur obs;
	//private TypeCouleur couleur;
	

	public Test() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("test");
		this.setSize(size);
		partie = new Partie(null);
		config = new Configuration();
		initPanel();

	}

	private void initPanel() {

		
		contentPane = this.getContentPane();
		contentPane.setBackground(Color.white);
		this.setLocationRelativeTo(null);

	}

	public static void main(String[] args) {
		Test test = new Test();
		test.setVisible(true);
	}

}
