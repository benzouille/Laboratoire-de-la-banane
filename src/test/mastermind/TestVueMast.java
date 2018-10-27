package test.mastermind;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;

import classesNeccessaires.Configuration;
import classesNeccessaires.Jeu;
import classesNeccessaires.Observateur;
import classesNeccessaires.Partie;
import main.java.model.ModeDeJeu;
import main.java.view.game.PanelJeu;
import test.avant_implementation.GamePanelMastermind;

public class TestVueMast extends JFrame implements Observateur {

	private Configuration config;
	private PanelJeu panelJeu;
	private Jeu jeu;
	private Container container = this.getContentPane();
	private Observateur obs;
	private GamePanelMastermind gamePanelMastermind;
	
	private Dimension bigSize = new Dimension (1710, 1050), smallSize = new Dimension (845, 1040);
	
	public TestVueMast() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Test switch");
		this.setSize(smallSize);
		obs = this;
		config = new Configuration();
		config.setDevModEnJeu(false);
		jeu = new Jeu(ModeDeJeu.MAST_CHAL, config, obs);
		init();
	}
	
	public void init() {
		gamePanelMastermind = new GamePanelMastermind(config, ModeDeJeu.MAST_CHAL, jeu.getPartie1(), null);
		container.add(gamePanelMastermind);
	}

	@Override
	public void update(Configuration config) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Jeu jeu) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Partie partie) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(boolean test) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(String choixFinJeu) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		TestVueMast test = new TestVueMast();
		test.setVisible(true);
	}
}
