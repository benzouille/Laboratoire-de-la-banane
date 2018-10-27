package test.avant_implementation;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

import main.java.model.Configuration;
import main.java.model.ModeDeJeu;
import main.java.observer.Observateur;
import main.java.view.game.plusMoins.PopUpCombi;
import test.GamePanelPlusMoins;

public class PanelLink extends JPanel {

	private Jeu jeu;
	private	ControllerV2 controller, controller2;
	private GamePanelPlusMoins jpChal, jpDef;
	private Configuration configuration;
	private Observateur obs;
	private Dimension bigSize = new Dimension (1710, 1050), smallSize = new Dimension (845, 1040);

	public PanelLink(Jeu jeu, Configuration configuration, Observateur obs) {
		this.jeu = jeu;
		this.configuration = configuration;

		init();
	}

	public void init() {
		if(jeu.getModeDeJeu().equals(ModeDeJeu.PLUS_CHAL)){
			this.setPreferredSize(smallSize);
			controller = new ControllerV2(configuration,jeu.getPartie1(), jeu);
			jpChal = new GamePanelPlusMoins(configuration, jeu.getPartie1(), controller);
			jpChal.setPreferredSize(smallSize);
			this.add(jpChal);
		}
		else if(jeu.getModeDeJeu().equals(ModeDeJeu.PLUS_DEF)) {
			this.setPreferredSize(smallSize);
			controller = new ControllerV2(configuration,jeu.getPartie1(), jeu);
			PopUpCombi popUpCombi = new PopUpCombi(null, "choix de la combinaison", true, configuration, jeu.getPartie1(), obs);
			jpDef = new GamePanelPlusMoins(configuration, jeu.getPartie1(), controller);
			jpDef.setPreferredSize(smallSize);
			this.add(jpDef);
		}
		else if(jeu.getModeDeJeu().equals(ModeDeJeu.PLUS_DUEL)) {
			this.setPreferredSize(bigSize);
			controller = new ControllerV2(configuration,jeu.getPartie1(), jeu);
			jpChal = new GamePanelPlusMoins(configuration, jeu.getPartie1(), controller);
			jpChal.setPreferredSize(smallSize);
			JPanel jpEspace = new JPanel();
			jpEspace.setPreferredSize(new Dimension(10, 1040));
			controller2 = new ControllerV2(configuration,jeu.getPartie2(), jeu);
			PopUpCombi popUpCombi = new PopUpCombi(null, "choix de la combinaison", true, configuration, jeu.getPartie2(), obs);
			jeu.getPartie2().setActif(false);
			jpDef = new GamePanelPlusMoins(configuration, jeu.getPartie2(), controller);
			jpDef.setPreferredSize(smallSize);

			this.setLayout(new BorderLayout());
			this.add(jpChal, BorderLayout.WEST);
			this.add(jpEspace, BorderLayout.CENTER);
			this.add(jpDef, BorderLayout.EAST);
		}
	}

	public void defTurn() {
		System.out.println("defTurn() de PanelLink");
		jpDef.newTurn();
		jpChal.stopTurn();
	}
	public void chalTurn() {
		System.out.println("chalTurn() de PanelLink");
		jpChal.newTurn();
		jpDef.stopTurn();
	}
}


