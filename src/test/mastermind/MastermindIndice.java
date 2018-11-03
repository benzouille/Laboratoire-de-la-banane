package test.mastermind;

import classesNeccessaires.Configuration;
import classesNeccessaires.Partie;

import java.util.ArrayList;

public class MastermindIndice {

	private Partie partie;
	private Configuration config;
	private String proposition, solution, indices;

	public MastermindIndice() {
		/*
		 * TODO renvoyer des indices suite à une proposition :s
		 * si bon chiffre mais pas à la bonne place indice blanc
		 * si bon chiffre à la bonne place indice noir
		 */
		config = new Configuration();

	}

	/**
	 * Compare les differences entre la proposition et la solution puis modifie dans l'objet partie l'indices avec les couleur noir et blanc
	 * @param partie
	 * @return String indice 
	 */
	public String resolve(Partie partie) {
		this.partie = partie;
		solution = partie.getSolution();
		proposition = partie.getProposition();

		ArrayList<Integer> prop = new ArrayList<Integer>();
		ArrayList<Integer> sol = new ArrayList<Integer>();
		ArrayList<String> result = new ArrayList<String>();
		for (int i = 0; i<proposition.length(); i++) {
			prop.add(Integer.valueOf(proposition.substring(i, i+1)));
			sol.add(Integer.valueOf(solution.substring(i, i+1)));	
		}
		//-- Boucle pour trouver les noirs
		for (int i = 0; i<prop.size(); i++) {
			if(prop.get(i).equals(sol.get(i))) {
				prop.remove(i);
				sol.remove(i);
				result.add("noir");
			}
		}
		//-- Boucle pour trouver les blancs
		for (int i = 0; i<prop.size(); i++) {
			for(int y = 0; y<sol.size(); y++) {
				if(prop.get(i).equals(sol.get(y))) {
					prop.remove(i); 
					sol.remove(y); 
					result.add("blanc");
				}
			}
		}
		String str = result.toString();

		return str;
	}

	public static void main(String[] args) {
		Partie partie = new Partie("partie1");
		partie.setSolution("012345");
		partie.setProposition("042003");
		MastermindIndice mastIndice = new MastermindIndice();

		System.out.println(mastIndice.resolve(partie));
	}
}
