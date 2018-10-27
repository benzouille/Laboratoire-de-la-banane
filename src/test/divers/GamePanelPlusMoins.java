package test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import main.java.model.Configuration;
import main.java.model.ModeDePartie;
import main.java.model.Partie;
import test.avant_implementation.CenterGamePanel;
import test.avant_implementation.ControllerV2;


public class GamePanelPlusMoins extends JPanel {

	private Configuration config;
	private Partie partie;
	private ControllerV2 controller;

	private Container contentPane = this;

	private JPanel jpSouth, jpGauche, jpGaucheHaut, jpGaucheBas, jpCentre, jpCentreHaut, jpCentreBas, jpDroit, jpDroitHaut, jpDroitBas;
	private JLabel jlTitreSolution, jlSolution, jlTitreProposition, jlTitreIndice;
	private JTextField jtfProposition, jtfIndice;
	private JButton okButton, cancelButton;

	private JPanel jpTop = new JPanel(), jpRight = new JPanel();
	private JLabel jlModeDeJeu = new JLabel();

	private CenterGamePanel centerGamePanel;

	private Font font = new Font("Sego UI",Font.PLAIN,24), fontTitre = new Font("Sego UI",Font.PLAIN,40);

	private boolean devMode;

	/**
	 * Constructeur
	 */
	public GamePanelPlusMoins(Configuration config, Partie partie, ControllerV2 controller) {
		this.partie = partie;
		this.config = config;
		this.controller = controller;
		devMode = config.isDevModEnJeu();

		this.setSize(800, 1000);
		this.setBorder(BorderFactory.createLineBorder(Color.GREEN, 15, true));

		jpTop.setPreferredSize(new Dimension(750, 90));
		//jpTop.setBorder(BorderFactory.createLineBorder(Color.YELLOW));

		jpRight.setPreferredSize(new Dimension(125, 700));
		//jpRight.setBorder(BorderFactory.createLineBorder(Color.MAGENTA));
		initPanelSouth();

		if (partie.getModeDePartie() == ModeDePartie.PLUS_CHAL) {
			jlModeDeJeu.setText("Mode challenger");
			jlModeDeJeu.setForeground(Color.RED);
		}
		else if (partie.getModeDePartie() == ModeDePartie.PLUS_DEF) {
			jlModeDeJeu.setText("Mode défenseur");
			jlModeDeJeu.setForeground(Color.BLUE);
		}
		jlModeDeJeu.setFont(fontTitre);
		jpTop.add(jlModeDeJeu);

		contentPane.add(jpTop, BorderLayout.NORTH);
		
		centerGamePanel = new CenterGamePanel(config, partie);
		centerGamePanel.add(jpRight, BorderLayout.EAST);
		contentPane.add(centerGamePanel, BorderLayout.CENTER);
		
		contentPane.add(jpSouth, BorderLayout.SOUTH);
		
		if(partie.isActif() == false) {
			stopTurn();
		}
	}

	/**
	 * Panel sud avec les entrées pour les propositions en mode challanger et les indices en mode defenseur
	 */
	public void initPanelSouth() {

		jpSouth = new JPanel();
		jpSouth.setPreferredSize(new Dimension(750,150));
		jpSouth.setLayout(new BorderLayout());

		initPanelGauche();
		initPanelCentre();
		initPanelDroit();
	}

	private void initPanelGauche() {
		jpGauche = new JPanel();
		//jpGauche.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
		jpGauche.setPreferredSize(new Dimension(125, 150));

		jpGaucheHaut = new JPanel();
		//jpGaucheHaut.setBorder(BorderFactory.createLineBorder(Color.RED));
		jpGaucheHaut.setPreferredSize(new Dimension(125, 75));

		jlTitreProposition = new JLabel("Proposition");
		jlTitreProposition.setFont(font);
		jlTitreProposition.setVerticalTextPosition(JLabel.CENTER);

		jlTitreIndice = new JLabel("Indice");
		jlTitreIndice.setFont(font);
		jlTitreIndice.setVerticalTextPosition(JLabel.CENTER);

		if(partie.getModeDePartie() == ModeDePartie.PLUS_CHAL) {
			jpGaucheHaut.add(jlTitreProposition);
		}
		else if (partie.getModeDePartie() == ModeDePartie.PLUS_DEF) {
			jpGaucheHaut.add(jlTitreIndice);
		}

		jpGaucheBas = new JPanel();
		//jpGaucheBas.setBorder(BorderFactory.createLineBorder(Color.GREEN));
		jpGaucheBas.setPreferredSize(new Dimension(125, 75));

		jlTitreSolution = new JLabel("Solution");
		jlTitreSolution.setFont(font);

		if (partie.getModeDePartie() == ModeDePartie.PLUS_DEF || devMode == true) {
			jpGaucheBas.add(jlTitreSolution);
			jpGaucheBas.setVisible(true);
		}

		jpGauche.setLayout(new BorderLayout());
		jpGauche.add(jpGaucheHaut, BorderLayout.NORTH);
		jpGauche.add(jpGaucheBas, BorderLayout.CENTER);

		jpSouth.add(jpGauche, BorderLayout.WEST);
	}

	private void initPanelCentre() {

		jpCentre = new JPanel();
		jpCentre.setLayout(new BorderLayout());
		//jpCentre.setBorder(BorderFactory.createLineBorder(Color.GREEN));
		jpCentre.setPreferredSize(new Dimension(300, 150));

		jpCentreHaut = new JPanel();
		jpCentreHaut.setPreferredSize(new Dimension(350, 75));
		//jpCentreHaut.setBorder(BorderFactory.createLineBorder(Color.CYAN));

		jtfProposition = new JTextField();
		jtfProposition.setPreferredSize(new Dimension(350, 70));
		jtfProposition.setFont(font);
		jpCentreHaut.add(jtfProposition);

		jpCentreBas = new JPanel();
		jpCentreBas.setPreferredSize(new Dimension(350, 75));
		//jpCentreBas.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
		jlSolution = new JLabel(partie.getSolution());
		jlSolution.setFont(font);
		jlSolution.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		jlSolution.setBackground(Color.WHITE);
		jlSolution.setPreferredSize(new Dimension(350, 70));
		jpCentreBas.add(jlSolution);
		jpCentreBas.setVisible(devMode);
		if (partie.getModeDePartie() == ModeDePartie.PLUS_DEF) {
			jpCentreBas.setVisible(true);
		}

		jpCentre.add(jpCentreHaut, BorderLayout.NORTH);
		jpCentre.add(jpCentreBas, BorderLayout.CENTER);

		jpSouth.add(jpCentre, BorderLayout.CENTER);
	}

	private void initPanelDroit() {

		jpDroit = new JPanel();
		//jpDroit.setBorder(BorderFactory.createLineBorder(Color.RED));
		jpDroit.setPreferredSize(new Dimension(125, 150));

		jpDroitHaut = new JPanel();
		jpDroitHaut.setPreferredSize(new Dimension(125, 75));
		//jpDroitHaut.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
		okButton = new JButton("Ok");
		okButton.setPreferredSize(new Dimension(125, 70));
		okButton.setHorizontalAlignment(JButton.CENTER);
		okButton.setVerticalAlignment(JButton.CENTER);
		okButton.addActionListener(new OkButtonListener());
		jpDroitHaut.add(okButton);

		jpDroitBas = new JPanel();
		jpDroitBas.setPreferredSize(new Dimension(125, 75));
		//jpDroitBas.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
		cancelButton = new JButton("Annuler");
		cancelButton.setPreferredSize(new Dimension(125, 70));
		cancelButton.setHorizontalAlignment(JButton.CENTER);
		cancelButton.setVerticalAlignment(JButton.CENTER);
		cancelButton.addActionListener(new CancelButtonListener());
		jpDroitBas.add(cancelButton);
		jpDroitBas.setVisible(false);

		jpDroit.setLayout(new BorderLayout());
		jpDroit.add(jpDroitHaut, BorderLayout.NORTH);
		jpDroit.add(jpDroitBas, BorderLayout.CENTER);

		jpSouth.add(jpDroit, BorderLayout.EAST);

	}

	public void newTurn() {
		this.setBorder(BorderFactory.createLineBorder(Color.GREEN, 15, true));
		jtfProposition.setEditable(true);
		okButton.setEnabled(true);
	}

	public void stopTurn() {
		this.setBorder(BorderFactory.createLineBorder(Color.RED, 15, true));
		jtfProposition.setEditable(false);
		okButton.setEnabled(false);
	}

	public void okPlusChal() {
		System.out.println("okPlusChal() de GamePanelPlusMoins");
		String proposition = jtfProposition.getText();
		partie.setProposition(proposition);
		partie.setActif(false);
		controller.sendProposition(partie);
		centerGamePanel.addDataLine(partie);
		jtfProposition.setText("");
		stopTurn();
		this.revalidate();
	}

	public void okPlusDef() {
		System.out.println("okPlusDef() de GamePanelPlusMoins");
		String indice = jtfProposition.getText();
		partie.setIndice(indice);
		partie.setActif(false);
		controller.sendIndice(partie);
		centerGamePanel.addDataLine(partie);
		if(config.isDevModEnJeu() == true && partie.getModeDePartie() == ModeDePartie.PLUS_DEF) {
			controller.sendProposition(partie);
			jtfProposition.setText(partie.getIndice());
		}
		else {
			jtfProposition.setText("");
		}
		stopTurn();
		this.revalidate();
	}

	class OkButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			/*
			 * TODO remplir l'action du bouton, voir sur la classe ModeGamePanel.
			 * utiliser un regex pour les chiffre plutôt qu'un JFomattedTextField
			 */
			System.out.println("boutton ok de GamePanelPlusMoins");
			if (partie.getModeDePartie() == ModeDePartie.PLUS_CHAL) {
				System.out.println("if de boutton ok de  GamePanelPlusMoins");
				okPlusChal();
			}
			else if (partie.getModeDePartie() == ModeDePartie.PLUS_DEF) {
				System.out.println("else if de boutton ok de GamePanelPlusMoins");
				okPlusDef();
				
			}
			else {
				System.out.println("else de boutton ok de GamePanelPlusMoins");
			}
		}
	}

	class CancelButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			/*
			 * TODO remplir l'action du bouton, voir sur la classe ModeGamePanel.
			 * utiliser un regex pour les chiffre plutôt qu'un JFomattedTextField
			 */
		}	
	}

	public void updateTableau() {
		System.out.println("methode updateTable de TestGridLayout");
		centerGamePanel.addDataLine(partie);
	}
}
