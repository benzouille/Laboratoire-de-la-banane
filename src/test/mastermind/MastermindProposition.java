package test.mastermind;

import java.util.ArrayList;

public class MastermindProposition {

    //TODO positionCouleur1 ne doit pas être sur les index du verrou
    private String indices, derniereAction;
    private int tour = 1;
    private int tailleIndice;
    private int tailleCombinaison = 4;
    private int nbreCouleur = 6;
    private int nbreNoir, nbreBlanc;

    private int positionCouleur1 = 0, valeurDefaut = -1;

    private ArrayList<Integer> couleur = new ArrayList<>();
    private ArrayList<Integer> poubelle = new ArrayList<>();
    private ArrayList<Integer> verrou = new ArrayList<>();
    private ArrayList<Integer> proposition = new ArrayList<>();
    private ArrayList<Integer> solution = new ArrayList<>();
    private ArrayList<Integer> indice = new ArrayList<>();

    private MastermindProposition() {
        //ajout des couleurs dans l'arraylist couleur.
        for(int i = 0; i<nbreCouleur; i++) {
            couleur.add(i);
        }
        //ajout des -1 dans l'arrayList proposition et verrou.
        for (int i = 0; i<tailleCombinaison; i++) {
            proposition.add(-1);
            verrou.add(-1);
        }
    }

    /**
     * Initialise la proposition en la remplissant avec la première couleur.
     */
    private void initProposition() {
        //if(proposition.isEmpty()) {
        if(tour == 1){
            monoChrome(couleur.get(0));
            tour ++;
        }
    }

    /**
     * Retourne une proposition en fonction de l'indice reçu
     * @param indices int
     * @return String
     */
    public String proposition(String indices) {

	    /*
	    --stringToList(indices) pour convertir un string en arrayList
	    --noirIgnore(nbreVerrou()) pour elever le nombre d'indice noir dont on connais deja les emplacements/couleurs
	    --lecteurIndice() pour compter le nombre de noir et de blanc dans l'indice
	    --Formattage avec un monoChrome avec des -1
	    --verrouillé() qui va ajouter à la proposition les emplacements/valeurs que l'on a verouillé auparavant
	    --Interpretation de l'indices
	     */

        /* Formattage de l'indice */
        indice = stringToList(indices);
        tailleIndice = indice.size();
        noirIgnore(nbreVerrou());
        lecteurIndice();

        /* Formattage de la proposition */
        for (int i = 0; i<tailleCombinaison; i++) {
            proposition.set(i, -1);
        }
        verrouille();

        /*
         ********************************
         *  Interpretation de l'indice  *
         ********************************
         */

        /* verifie que les conditions de victoire soient bien réunis */
        if(victoire()){
            tour++;
            //TODO ajout de quoi faire en cas de victoire.
            String str = "Victoire";
            System.out.println(str);
            return str;
        }


        /* verifie si l'indice modifié par noirIgnore() est vide */
        if(nbreBlanc == 0 && nbreNoir == 0) {
            aLaPoubelle(0);
            monoChrome(couleur.get(0));
            /* On ajoute un tour et on retourne la proposition en un string */
            tour++;
            return listToString(proposition);
        }

        /* Si la derniere action est un monoChrome() */
        if(derniereAction.equals("monoChrome")){
            if(nbreNoir == 1) {
                /* remise à zéro de la positionCouleur1 */
                positionCouleur1 = 0;
                biChrome(couleur.get(0),positionCouleur1,couleur.get(1));
            }
            else{

                /* le nombre de noir -1 donne le nombre de fois la couleur à ajouter à l'arrayList couleur */
                positionCouleur1 = 0;
                biChrome(couleur.get(0),positionCouleur1,couleur.get(1));
                ajoutCouleur(nbreNoir-1, couleur.get(0));
            }
            tour++;
            return listToString(proposition);
        }
        /* Si la derniere action est un biChrome() */

        /* Si pas de blanc */
        if(nbreBlanc == 0) {
            /* Si 1 noir */
            if (nbreNoir == 1) {
                //couleur bonne -> verrouillage et la couleur va à la poubelle, couleur2 absente => poubelle, monoChrome(couleur 0).

                ajoutAuVerrou(positionCouleur1, couleur.get(0));
                verrouille();
                aLaPoubelle(0);
                aLaPoubelle(0);
                monoChrome(couleur.get(0));
            }
            /* Si plusieurs noirs */
            else if (nbreNoir > 1) {
                //couleur1 bonne position bonne -> couleur1 verrouillage puis poubelle, biChrome couleur2.
                ajoutAuVerrou(positionCouleur1, couleur.get(0));
                verrouille();
                //TODO voir la position couleur mal placé
                aLaPoubelle(0);
                ajoutCouleur(nbreNoir, couleur.get(0));
                positionCouleur1 = 0;
                biChrome(couleur.get(0),positionCouleur1,couleur.get(1));
            }
            tour++;
            return listToString(proposition);
        }
        /* Si pas de noir */
        if (nbreNoir == 0) {
            /* Si 1 blanc */
            if (nbreBlanc == 1) {
                //couleur bonne, position mauvaise -> bichrome position +1, et couleur2 poubelle, biChrome()

                aLaPoubelle(1);
                positionCouleur1++;
                biChrome(couleur.get(0),positionCouleur1,couleur.get(1));
            }
            /* Si plusieurs blancs */
            else if (nbreBlanc == 2) {
                    //couleur1 bonne, position mauvaise La couleur 2 ne peux etre que à la place de la couleur 1

                ajoutAuVerrou(positionCouleur1, couleur.get(1));
                verrouille();
                aLaPoubelle(1);
                biChrome(couleur.get(0), positionCouleur1, couleur.get(1));
            }
            tour++;
            return listToString(proposition);
        }

        if (nbreNoir == 1 || nbreBlanc == 1){
            //rien a la poubelle couleur 1 présente à la mauvaise place couleur 2 présente, positionCouleur++ faire un biChrome avec les deux meme couleurs

            positionCouleur1++;
            biChrome(couleur.get(0), positionCouleur1, couleur.get(1));

        }
        else if (nbreNoir > 1 || nbreBlanc > 1){
            //couleur1 présente mauvaise place et couleur2 présente : positionCouleur1++; biChrome(couleur1, positionCouleur1, couleur2);
            positionCouleur1++;
            biChrome(couleur.get(0), positionCouleur1, couleur.get(1));
        }
        tour++;
        return listToString(proposition);
    }

    /**
     * Methode dénombrant le nombre d'indice noir(int = 1) et le nombre d'indice blanc(int = 2) contenu dans l'ArrayList.
     */
    private void lecteurIndice(){
        nbreBlanc = 0;
        nbreNoir = 0;

        for(int i = 0; i<indice.size(); i++) {
            if (indice.get(i).equals(1)) {
                nbreNoir++;
            } else {
                nbreBlanc++;
            }
        }
        indice = new ArrayList<>();
    }

    /**
     * Retire une couleur de l'ArrayList couleur la met dans l'ArrayList poubelle, puis passe à la couleur suivante.
     * @param indexColor int
     */
    private void aLaPoubelle(int indexColor){
        poubelle.add(couleur.get(indexColor));
        couleur.remove(indexColor);

        System.out.println("couleur : "+couleur);
        System.out.println("poubelle : "+poubelle);
    }

    /**
     * Insert une ou plusieurs fois une couleur dans l'arrayList couleur juste après la couleur en cours d'utilisation.
     * @param nbreAjout int nombre de fois qu'il faut inserer la couleur
     * @param color int couleur à inserer
     */
    private void ajoutCouleur(int nbreAjout, int color){
        for(int i = 0; i<nbreAjout; i++) {
            couleur.add(couleur.size(), color);
        }
    }

    /**
     * Retourne un string rempli de la couleur donnée au emplacement dont la valeur est -1
     * Modifie le String derniereAction en monoChrome si le paramètre d'entrée est différent de -1.
     * @param couleur int
     */
    private void monoChrome(int couleur) {
        for (int i = 0; i<tailleCombinaison; i++) {
            if(proposition.get(i) == -1) {
                proposition.set(i, couleur);
            }
        }
        if(!(couleur == -1)) {
            derniereAction = "monoChrome";
        }
    }

    /**
     * Place couleur1 à l'index donnée si l'index n'est pas déjà occupé par une couleur verrouillé et remplie le reste avec la couleur2.
     * @param couleur1 int
     * @param positionCouleur1 int
     * @param couleur2 int
     */
    private void biChrome(int couleur1, int positionCouleur1, int couleur2) {
        int a = positionCouleur1;
        int ajout =0;
        while(verrou.get(a++) != valeurDefaut){ ajout++; }
        proposition.set(positionCouleur1+ajout, couleur1);
        monoChrome(couleur2);
        derniereAction = "biChrome";
    }

    /**
     * Verouille la couleur dans l'index de la proposition si il n'est pas déjà occupé
     * @param index int.
     * @param chiffre int.
     */
    private void ajoutAuVerrou(int index, int chiffre) {
        int a = index;
        int ajout =0;
        //TODO verifier risque de problème avec un écrasement dans le verrou sur un verrou déjà crée.
        while(verrou.get(a++) != -1){ ajout++; }
        verrou.set(index+ajout, chiffre);
    }

    /**
     * pour mettre dans la proposition les couleurs/index qui sont verrouillées
     * à mettre avant les mono/bi chrome
     */
    private void verrouille() {
        for(int i = 0; i < tailleCombinaison ; i++) {
            if (verrou.get(i) != valeurDefaut) {
                System.out.println("le verrou : " + getVerrou());
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
     * Ignore un nombre de noire dans l'indice lorsque la couleur est dans le verrou
     * @param nombre nombre de noir a ignorer contenu dans l'indice.
     */
    private void noirIgnore(int nombre) {
        for(int i = 0 ; i < nombre; i++) {
            indice.remove(indice.lastIndexOf(1));
        }
        System.out.println("indice = " + indice);
    }

    /**
     * Verifie que la longueur du string indices correspond à tailleCombinaison puis verifie que l'integralité du string ne soit que des 1.
     * @return boolean
     */
    private boolean victoire() {
        boolean b = false;
        if (indices.length() == tailleCombinaison) {
            for (int i = 0; i < tailleCombinaison; i++) {
                if (Character.getNumericValue(indices.charAt(i)) == 1) {
                    b = true;
                }
                else {
                    b = false;
                }
            }
        }
        return b;
    }

    /**
     * Methode transformant un string en arrayList
     * @param string string
     * @return ArrayList
     */
    private ArrayList<Integer> stringToList(String string) {
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0 ; i < string.length(); i++) {
            list.add(Character.getNumericValue(string.charAt(i)));
        }
        return list;
    }

    /**
     * Methode transformant un arrayList en string
     * @param list ArrayList<Integer>
     * @return string
     */
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

    public int getTour() { return tour; }
    public void setTour(int tour) { this.tour = tour; }

    public ArrayList<Integer> getVerrou() { return verrou; }
    public void setVerrou(ArrayList<Integer> verrou) { this.verrou = verrou; }

    public static void main(String[] args) {
        MastermindProposition test = new MastermindProposition();
        test.setSolution("5011");
        test.setIndices("");
        System.out.println("la solution : " + test.getSolution());
        test.initProposition();
        System.out.println("tour : " + test.getTour() + " verifie l'init : " + test.getProposition());

        test.setIndices("1");
        System.out.println("tour : " + test.getTour() + " proposition : " + test.proposition(test.getIndices()));

        test.setIndices("211");
        System.out.println("tour : " + test.getTour() + " proposition : " + test.proposition(test.getIndices()));

        test.setIndices("111");
        System.out.println("tour : " + test.getTour() + " proposition : " + test.proposition(test.getIndices()));

        test.setIndices("12");
        System.out.println("tour : " + test.getTour() + " proposition : " + test.proposition(test.getIndices()));

        test.setIndices("11");
        System.out.println("tour : " + test.getTour() + " proposition : " + test.proposition(test.getIndices()));

        test.setIndices("11");
        System.out.println("tour : " + test.getTour() + " proposition : " + test.proposition(test.getIndices()));

        test.setIndices("111");
        System.out.println("tour : " + test.getTour() + " proposition : " + test.proposition(test.getIndices()));

        test.setIndices("1111");
        System.out.println("tour : " + test.getTour() + " proposition : " + test.proposition(test.getIndices()));
    }
}
