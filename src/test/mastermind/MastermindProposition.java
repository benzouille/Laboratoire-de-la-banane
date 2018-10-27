package test.mastermind;

import java.util.ArrayList;

public class MastermindProposition {

	private String solution;
	private String proposition;
	private String indices;
	private int tour = 1;
	private int tailleCombinaison = 3;
	private int nbreCouleur = 6;
	private int couleurNum = 0;
	private String noir = "noir", blanc = "blanc";

	private int positionCouleur1 = 0, positionCouleur2 = 0; 
	
	private ArrayList<Integer> couleur = new ArrayList<Integer>();
	private ArrayList<Integer> poubelle = new ArrayList<Integer>();
	private ArrayList<Integer> couleurVerrouillé = new ArrayList<Integer>();
	private ArrayList<Integer> prop = new ArrayList<Integer>();
	private ArrayList<Integer> sol = new ArrayList<Integer>();
	private ArrayList<Integer> ind = new ArrayList<Integer>();
	
	//-- test
	private ArrayList<Character> test = new ArrayList<Character>();
	private String testStr;
	
	public MastermindProposition() {
		test.add('t');
		test.add('e');
		test.add('s');
		test.add('t');
		
		System.out.println(listToString(test, testStr));
		testStr = "test2";
		System.out.println(stringToList(testStr, test));
	}
	
	/**
	 * Initialise la proposition en la remplissant avec la première couleur.
	 */ 
	public void initProposition() {
		if(prop == null) {
			monoChrome(couleurNum);
			tour ++;
			}
		}
	
	/**
	 * Retourne une proposition en fonction de l'indice reçu
	 * @param indices
	 * @return 
	 */
	public void proposition(String indices) {
		stringToList(indices, ind);
		String str = null;
		if(indices.isEmpty()) {
			couleurNum ++;
			monoChrome(couleurNum);
		}
		else if (indices.contains(noir)) {
			if(tour == 2) {
			biChrome(couleurNum, positionCouleur1, couleurNum+1);
			}
			else {
				// verrouiller si noir present, et faire abstraction de la bille noir du verrouillage
				verrou(position0, couleur);
				biChrome(couleurNum, positionCouleur1, couleurNum+1);	
			}
		}
		
		else if(indices.contains(blanc)) {
			positionCouleur1 ++;
			biChrome(couleurNum, positionCouleur1, couleurNum+1);
		}
		tour++;
	}
	
	/**
	 * Retourne un string rempli de la couleur
	 * @param couleur
	 * @return
	 */
	public void monoChrome(int couleur) {
		prop.clear();
		for (int i = 0; i<tailleCombinaison; i++) {
			prop.add(couleur);
		}
	}
	
	/**
	 * Place couleur1 à la position et remplie le reste avec la couleur2
	 * @param couleur1
	 * @param positionCouleur1
	 * @param couleur2
	 * @return
	 */
	public void biChrome(int couleur1, int positionCouleur1, int couleur2) {
		monoChrome(couleur2);
		prop.add(positionCouleur1, couleur1);
	}
	
	/**
	 * Verouille la couleur dans la position de la proposition
	 * @param position
	 * @param couleur
	 */
	public void verrou(int position, int couleur) {
		//TODO inserer la position/couleur à chaque fois dans la proposition suivante
		// 
			couleurVerrouillé.add(position, couleur);
	}
	
	public ArrayList stringToList(String string, ArrayList list) {
		list.clear();
		for(int i = 0 ; i < string.length(); i++) {
			list.add(string.charAt(i));
		}
		return list;
	}
	
	public String listToString(ArrayList list, String string) {
		string = "";
		for(int i = 0 ; i < list.size(); i++) {
			string += list.get(i);
		}
		return string;
	}
	
	//-- GETTER SETTER
	public String getSolution() {return solution;}
	public void setSolution(String solution) {this.solution = solution;}

	public String getProposition() {return proposition;}
	public void setProposition(String proposition) {this.proposition = proposition;}

	public String getIndices() {return indices;}
	public void setIndices(String indices) {this.indices = indices;}

	public int getCouleurNum() {return couleurNum;}
	public void setCouleurNum(int couleur) {this.couleurNum = couleur;}

	public static void main(String[] args) {
		MastermindProposition test = new MastermindProposition();
		test.initProposition();
		System.out.println("verifie l'init : " + test.getProposition());
		//System.out.println("le remplissage" + test.monoChrome(5));
		//System.out.println("test 2 couleur" + test.biChrome(0,1,5));
	}
}
