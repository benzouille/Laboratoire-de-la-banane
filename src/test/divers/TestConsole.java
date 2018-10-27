package test;

import main.java.controller.Controller;
import main.java.model.Configuration;
import main.java.model.ModeDeJeu;
import main.java.model.Model;
import main.java.model.Partie;
import main.java.observer.Observateur;
import test.avant_implementation.Jeu;

public class TestConsole implements Observateur {
	
	private Partie partie;
	private Configuration configuration;
	private Controller controller;
	private Model model;
	private Jeu jeu;
	
	public TestConsole() {
		partie = new Partie();
		partie.setSolution("123789");
		partie.setModeDeJeu(ModeDeJeu.PLUS_DUEL);
		configuration = new Configuration();
		jeu = new Jeu(partie, configuration, this);
		model = new Model(configuration, partie, this);
		controller = new Controller(configuration, partie, model, null);
		autoPartie2();
	}
	
	public void autoPartie() {
		model.initOrdinateur();
		System.out.println(model.resolve(partie));
		System.out.println(model.propositionOrdinateur(partie));
		System.out.println(model.resolve(partie));
		System.out.println(model.propositionOrdinateur(partie));
		System.out.println(model.resolve(partie));
		System.out.println(model.propositionOrdinateur(partie));
		System.out.println(model.resolve(partie));
		System.out.println(model.propositionOrdinateur(partie));
	}
	
	public void autoPartie2() {
		//TODO cycle de resolution avec l'objet jeu
		//jeu.initOrdi(partie);
		System.out.println(partie.toString());
		jeu.indiceOrdi(partie);
		System.out.println(partie.toString());
		jeu.propOrdi(partie);
		System.out.println(partie.toString());
		jeu.indiceOrdi(partie);
		System.out.println(partie.toString());
		jeu.propOrdi(partie);
		System.out.println(partie.toString());
		jeu.indiceOrdi(partie);
		System.out.println(partie.toString());
		jeu.propOrdi(partie);
		System.out.println(partie.toString());
		jeu.indiceOrdi(partie);
		System.out.println(partie.toString());
		jeu.propOrdi(partie);
		System.out.println(partie.toString());
		jeu.indiceOrdi(partie);
		System.out.println(partie.toString());
	}
	
	public static void main(String[] args) {TestConsole testConsole = new TestConsole();}

	public void update(Configuration config) {
		
	}

	public void update(Partie partie) {
		
	}

	public void update(String choixFinJeu) {
		
	}

	@Override
	public void update(boolean test) {
		// TODO Auto-generated method stub
		
	}
}
