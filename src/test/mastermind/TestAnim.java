package test.mastermind;

import classesNeccessaires.Balle;
import classesNeccessaires.TypeCouleur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TestAnim extends JFrame {

	private JPanel container = new JPanel();
	private JPanel jpBouton, jpAffichage;
	private Dimension size = new Dimension(125, 700);
	private Thread thread;
	
	private JLabel jlBleu;
	private Balle bleu = new Balle(TypeCouleur.BLEU);
	
	public static void main(String[] args) {
		TestAnim testanim = new TestAnim();
	}
	
	public TestAnim() {
		this.setTitle("test des animations");
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
		jlBleu = new JLabel(bleu.getImageIcon());
		jlBleu.addMouseListener(new LabelListener(bleu, jlBleu));
		jpBouton.add(jlBleu);
	}
	
	public void anime() {
		JLabel jLabel = new JLabel(new ImageIcon("resources/images/mastermind/bleuAnim2.gif"));
		jpAffichage.add(jLabel);
		jpAffichage.revalidate();
	}
	
	/**
	 * Mettre à jour le focus du second panneau de jeu via un Thread Indépendant.
	 * @param jpAffichage
	 */
	public static void updateAnim(JPanel jpAffichage) {
		new Thread(new Runnable() {
			public void run() {
				
				//-- Modification de notre composant dans l'EDT
				Thread t = new Thread(new Runnable() {
					public void run() {
						JLabel jlTarget = new JLabel(new ImageIcon("resources/images/mastermind/bleuAnim2.gif"));
						jpAffichage.add(jlTarget);
						jpAffichage.revalidate();
					}
				});
				//-- Si l'EDT est actif, le Thread est lancée sinon il le sera par l'EDT plus tard
				if (SwingUtilities.isEventDispatchThread())
					t.start();
				else {
					SwingUtilities.invokeLater(t);
				}
			}
		}).start();
	}


	class LabelListener implements MouseListener{
		private Balle balle;
		private JLabel jLabel;
		
		public LabelListener(Balle balle, JLabel jLabel) {
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
		
		public void mouseClicked(MouseEvent e) {
			System.out.println("ajout de la balle "+ balle.getTypeCouleur().getCouleur() + ".");
			
			//JLabel jLabel = new JLabel(balle.getImageIcon());
			//anime();
			updateAnim(jpAffichage);
			//jLabel = new JLabel(new ImageIcon("resources/images/mastermind/bleuAnim"));
			//jpAffichage.revalidate();
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
}
