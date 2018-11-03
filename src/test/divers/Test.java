package test.divers;

import classesNeccessaires.Partie;

import javax.swing.*;
import java.awt.*;

public class Test extends JFrame {
	private Dimension size = new Dimension (750, 150);
	private Container contentPane;
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
