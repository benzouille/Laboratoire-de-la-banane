package test.divers;

import main.java.model.Configuration;

public class TestRandom {

	public TestRandom() {

	}

	/**
	 * Génère une combinaison aléatoire d'une longueur égale à combiPlusMoins de l'objet configuration
	 * @param nbreChiffre
	 * @return random
	 */
	public String random(int nbreChiffre) {
		int [] combi = new int[nbreChiffre];
		for (int i = 0; i<nbreChiffre; i++) {
			combi[i] = (int)(Math.random()*10);
		}
		String random = convertTabIntToString(combi);
		return random;
	}

	/**
	 * Génère une combinaison aléatoire d'une longueur égale à combiMast avec des chiffres compris entre 0 et couleurMast de l'objet configuration
	 * @param nbreChiffre, nbreCouleur
	 * @return random
	 */
	public String random(int nbreChiffre, int nbreCouleur) {
		int [] combi = new int[nbreChiffre];
		for (int i = 0; i<nbreChiffre; i++) {
			combi[i] = (int)(Math.random()*nbreCouleur);
		}
		String random = convertTabIntToString(combi);
		return random;
	}

	/**
	 * Convertit un tableau d'int en string
	 * @param tab
	 * @return
	 */
	private String convertTabIntToString(int [] tab) {
		String str= "";
		for(int i = 0; i<tab.length; i++) {
			str += Integer.toString(tab[i]);
		}
		return str;
	}

	public static void main(String[] args) {
		TestRandom testRandom = new TestRandom();
		System.out.println(testRandom.random(10, 4));
		System.out.println(testRandom.random(10));
	}
}
