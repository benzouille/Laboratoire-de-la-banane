package test.mastermind;

import java.util.ArrayList;


public class MastermindProposition {
	//private String solution;
	//private String proposition;
	private String indices;
	private int tour = 1;
	private int tailleCombinaison = 4;
	private int nbreCouleur = 6;
	private int couleurNum = 0;
	private int noir = 2, blanc = 1;

	private int positionCouleur1 = 0, valeurDefaut = -1;

	private ArrayList<Integer> couleur = new ArrayList<>();
	private ArrayList<Integer> poubelle = new ArrayList<>();
	private ArrayList<Integer> verrou = new ArrayList<>();
	private ArrayList<Integer> proposition = new ArrayList<>();
	private ArrayList<Integer> solution = new ArrayList<>();
	private ArrayList<Integer> indice = new ArrayList<>();

	private MastermindProposition() {
		for(int i = 0; i<nbreCouleur; i++) {
			couleur.add(i);
		}
	}

	/**
	 * Initialise la proposition en la remplissant avec la première couleur.
	 */
    private void initProposition() {
		if(proposition.isEmpty()) {
			monoChrome(couleurNum);
			tour ++;
		}
	}

	/**
	 * Retourne une proposition en fonction de l'indice reçu
	 * @param indices int
	 * @return String
	 */
	public String proposition(String indices) {
		indice = stringToList(indices);


		if(indices.isEmpty()) {
			indiceVide(couleurNum);
			monoChrome(couleurNum);
		}
		//Verifie la présence de noir
		else if (indice.contains(2)) {
			/* TODO verifier le nombre de noir présent et le nombre de couleurs présentes
	        if (1 seule couleur présente && 1 seul noir dans l'indice) {faire un bichrome() pour verifier la position de la couleur}
	        else if (+1 noir) {ajouter dans la liste la couleur*nombre de noir présent dans l'indice apres le noirIgnore() fait.
	        				   faire un biChrome jusqu'a trouver 1 noir de nouveau le verrouiller

			 */
			//action du tour précedent est un monochrome
			if(tour == 2) {
				biChrome(couleurNum, positionCouleur1, couleurNum+1);
			}
			else {
				// verrouiller si noir present, et faire abstraction de la bille noir du verrouillage
				ajoutAuVerrou(proposition.get(positionCouleur1), couleurNum);
				biChrome(couleurNum, positionCouleur1, couleurNum+1);
			}
		}
		//Verifie la présence de blanc
		else if(indice.contains(1)) {
			/*TODO verifier si indice contient 1 blanc unique ==> bichrome mm couleurs
			 * 				   longueur indice == longueur proposition ==> le bichrome se fera avec les couleurs utilisé au tour précédent.
			 *
			 *
			 */
			positionCouleur1 ++;
			biChrome(couleurNum, positionCouleur1, couleurNum+1);
		}
		tour++;
		return listToString(proposition);
	}

	/**
	 * Retire une couleur de l'ArrayList couleur la met dans l'ArrayList poubelle, puis passe à la couleur suivante.
	 * @param color int
	 */
	private void indiceVide(int color){
		couleur.remove(color);
		poubelle.add(color);
		couleurNum ++;
	}

	/**
	 * Retourne un string rempli de la couleur
	 * @param couleur int
	 */
    private void monoChrome(int couleur) {
		proposition.clear();
		for (int i = 0; i<tailleCombinaison; i++) {
			proposition.add(couleur);
		}
	}

	/**
	 * Place couleur1 à la position et remplie le reste avec la couleur2
	 * @param couleur1 int
	 * @param positionCouleur1 int
	 * @param couleur2 int
	 */
    private void biChrome(int couleur1, int positionCouleur1, int couleur2) {
		monoChrome(couleur2);
		proposition.set(positionCouleur1, couleur1);
	}

	/**
	 * Verouille la couleur dans la position de la proposition appel les methodes noirIgnore() et nbreVerrou().
	 * @param index int.
	 * @param chiffre int.
	 */
    private void ajoutAuVerrou(int index, int chiffre) {
		verrou.set(index, chiffre);
		noirIgnore(nbreVerrou());
		comparaison();
	}

	/**
	 * pour mettre dans la proposition les couleurs/index qui sont verrouillées
	 * à mettre après les mono/bi chrome
	 */
	public void verrouillage() { //TODO Comment faire un biChrome demarrant à 0 avec un verrou dessus ?
		for(int i = 0; i < tailleCombinaison ; i++) {
			if (verrou.get(i) != valeurDefaut) {
				System.out.println("verouillage() L' index " + i + ", la valeur : " + verrou.get(i));
				proposition.set(i, verrou.get(i));
			}
		}
	}

	/**
	 * Comptabilise le nombre d'éléments verouillés présent dans l'arrayList verrou.
	 * @return nbre le nombre d'éléments verouillés.
	 */
    private int nbreVerrou() {
		int nbre = 0;
		for(int i = 0; i < tailleCombinaison ; i++) {
            if (!verrou.get(i).equals(-1)) {nbre++;}
        }
		return nbre;
	}

	/**
	 * Compare la liste verrouillée et la solution et retourne un true si identique.
	 */
    private boolean comparaison() {
		if (verrou == solution) {
			System.out.println("comparaison() : " + verrou + "|" + solution + " égalité entre verrou et solution");
			return true;
		}
		else {
			System.out.println("comparaison() : " + verrou + "|" + solution + " difference entre verrou et solution");
			return false;
		}
	}

	/**
	 * Ignore 1 boule noire dans l'indice lorsque la couleur est dans le verrou
	 * @param nombre nombre de noir contenu dans l'indice a ignorer.
	 */
    private void noirIgnore(int nombre) {
		//Si on est sûr de la position/couleur on verrouille et on ignore cette boule noire
		//donc indice -1 noir
		for(int i = 0 ; i < nombre; i++) {
			indice.remove(indice.lastIndexOf(1));
		}
		System.out.println("indice = " + indice);
	}

	private ArrayList<Integer> stringToList(String string) {
		ArrayList<Integer> list = new ArrayList<>();
		for(int i = 0 ; i < string.length(); i++) {
			list.add(Character.getNumericValue(string.charAt(i)));
		}
		return list;
	}

	private String listToString(ArrayList<Integer> list) {
		String string = "";
		for(int i = 0 ; i < list.size(); i++) {
			string += list.get(i);
		}
		return string;
	}

	//-- GETTER SETTER
	public String getSolution() {return listToString(solution);}
	public void setSolution(String solution) {this.solution = stringToList(solution);}

	public String getProposition() {return listToString(proposition);}
	public void setProposition(String proposition) {this.proposition = stringToList(proposition);}

	public String getIndices() {return indices;}
	public void setIndices(String indices) {this.indices = indices;}

	public int getCouleurNum() {return couleurNum;}
	public void setCouleurNum(int couleur) {this.couleurNum = couleur;}

	public static void main(String[] args) {
		MastermindProposition test = new MastermindProposition();
		test.setSolution("6666");
		test.setIndices("");
		System.out.println("la solution : " + test.getSolution());
		test.initProposition();
		//System.out.println("verifie l'init : " + test.getProposition());
		//test.monoChrome(1);
		//System.out.println("le remplissage : " + test.getProposition());
		//test.biChrome(0,1,5);
		//System.out.println("test 2 couleur : " + test.getProposition());
		test.proposition(test.getIndices());
		System.out.println("test proposition : " + test.getProposition());
		test.proposition(test.getIndices());
		System.out.println("test proposition 2 : " + test.getProposition());

	}
}
