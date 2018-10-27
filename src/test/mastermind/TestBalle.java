package test.mastermind;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import classesNeccessaires.Balle;
import classesNeccessaires.TypeCouleur;

public class TestBalle extends JFrame {

	private JPanel container = new JPanel();
	private JPanel jpBouton, jpAffichage;
	private Dimension size = new Dimension(125, 700);

	private JLabel jlBleu, jlBrun, jlCyan, jlJaune, jlOrange, jlRose, jlRouge, jlVert, jlVertClair, jlViolet;
	private JLabel label[] = {jlBleu, jlBrun, jlCyan, jlJaune, jlOrange, jlRose, jlRouge, jlVert, jlVertClair, jlViolet};

	private Balle bleu = new Balle(TypeCouleur.BLEU),
			brun = new Balle(TypeCouleur.BRUN),
			cyan = new Balle(TypeCouleur.CYAN),
			jaune = new Balle(TypeCouleur.JAUNE),
			orange = new Balle(TypeCouleur.ORANGE),
			rose = new Balle(TypeCouleur.ROSE),
			rouge = new Balle(TypeCouleur.ROUGE),
			vert = new Balle(TypeCouleur.VERT),
			vertClair = new Balle(TypeCouleur.VERT_CLAIR),
			violet = new Balle(TypeCouleur.VIOLET);

	private Balle balles[] = {bleu, brun, cyan, jaune, orange, rose, rouge, vert, vertClair, violet};

	public TestBalle() {

		this.setTitle("test des boutons");
		this.setSize(new Dimension(500, 800));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setContentPane(container);

		this.init();
		this.setVisible(true);     
	}

	public void init() {

		container.setSize(size);
		container.setBackground(Color.white);
		container.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
		container.setLayout(new BorderLayout());

		jpAffichage = new JPanel();
		jpAffichage.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
		jpAffichage.setPreferredSize(new Dimension(300, 150));

		jpBouton = new JPanel();
		jpBouton.setPreferredSize(size);
		
		initBouton();

		container.add(jpAffichage, BorderLayout.WEST);
		container.add(jpBouton, BorderLayout.EAST);
	}


	public void initBouton() {

		for (int i = 0; i < label.length; i++) {
			label[i] = new JLabel(balles[i].getImageIcon());
			label[i].setBackground(Color.WHITE);
			label[i].setPreferredSize(new Dimension(60, 60));
			label[i].addMouseListener(new CouleurListener<TestBalle>(balles[i], label[i]));
			jpBouton.add(label[i]);
		}
	}

	class CouleurListener<TestBall> implements MouseListener {
		private Balle balle;
		private JLabel jLabel;
		public CouleurListener(Balle balle, JLabel jLabel) {
			this.balle = balle;
			this.jLabel = jLabel;
			
		}

		public void smallSize() {
			jLabel.setIcon(balle.getImageIconSmall());
			jLabel.revalidate();
		}

		public void bigSize() {
			jLabel.setIcon(balle.getImageIcon());
			jLabel.revalidate();
		}

		public void mouseClicked(MouseEvent arg0) {
			System.out.println("ajout de la balle "+ balle.getTypeCouleur().getCouleur() + ".");
			JLabel jLabel = new JLabel(balle.getImageIcon());
			jpAffichage.add(jLabel);
			jpAffichage.revalidate();
		}

		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}

		public void mousePressed(MouseEvent e) {
			smallSize();
		}

		public void mouseReleased(MouseEvent e) {
			bigSize();
		}	
	}

	public JLabel[] getLabel() {return label;}
	public void setLabel(JLabel[] label) {this.label = label;}

	public static void main(String[] args){
		TestBalle testBalle = new TestBalle();
	}  
}
