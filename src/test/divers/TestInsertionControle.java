package test.divers;

import java.util.ArrayList;

public class TestInsertionControle {

    private ArrayList<String> verrou = new ArrayList<>();
    private ArrayList<String> proposition = new ArrayList<>();
    private ArrayList<String>couleur = new ArrayList<>();
    private int tailleCombinaison = 6, positionCouleur1 = 0;
    private String valeurDefaut = "-1";

    public TestInsertionControle(){
        //ajout des couleurs dans l'arraylist couleur.
        couleur.add("rouge");
        couleur.add("jaune");
        couleur.add("bleu");
        couleur.add("vert");
        couleur.add("orange");
        couleur.add("brun");

        for (int i = 0; i<tailleCombinaison; i++) {
            verrou.add("-1");
        }

        resetProp();
        System.out.println(proposition);
        biChrome(couleur.get(0), positionCouleur1, couleur.get(1));
        System.out.println(proposition);
        ajoutAuVerrou(0, "ROUGE");
        ajoutAuVerrou(1, "ROUGE");
        ajoutAuVerrou(3,"BLEU");
        ajoutAuVerrou(4,"BLEU");
        resetProp();
        verrouillé();
        System.out.println(proposition);
        biChrome(couleur.get(0), positionCouleur1, couleur.get(1));
        System.out.println(proposition);
    }

    private void resetProp(){
        //ajout des -1 dans l'arrayList proposition
        proposition.clear();
        for (int i = 0; i<tailleCombinaison; i++) {
            proposition.add("-1");
        }
    }

    /**
     * Retourne un string rempli de la couleur donnée au emplacement dont la valeur est -1
     * @param couleur int
     */
    private void monoChrome(String couleur) {
        for (int i = 0; i<tailleCombinaison; i++) {
            if(proposition.get(i).equals("-1")) {
                proposition.set(i, couleur);
            }
        }
    }

    /**
     * Place couleur1 à la position et remplie le reste avec la couleur2
     * @param couleur1 int
     * @param positionCouleur1 int
     * @param couleur2 int
     */
    private void biChrome(String couleur1, int positionCouleur1, String couleur2) {
        int a = 0;
        int ajout =0;
        while(verrou.get(a++) != "-1"){
            ajout++;
        }
        proposition.set(positionCouleur1+ajout, couleur1);
        monoChrome(couleur2);
    }

    private void positionDisponible(){
        for (int i = 0; i<tailleCombinaison; i++) {
            if(verrou.get(i) != "-1"){
                System.out.println("occupé en : "+i);
            }
        }
        int a = 0;
        while(verrou.get(a++) != "-1"){
            System.out.println("nbre avant -1 : " + a);
        }

        //if(positionCouleur1 == )
    }

    /**
     * Verouille la couleur dans la position de la proposition appel les methodes noirIgnore() et nbreVerrou().
     * @param index int.
     * @param color String.
     */
    private void ajoutAuVerrou(int index, String color) {
        verrou.set(index, color);
    }

    /**
     * pour mettre dans la proposition les couleurs/index qui sont verrouillées
     * à mettre avant les mono/bi chrome
     */
    private void verrouillé() {
        for(int i = 0; i < tailleCombinaison ; i++) {
            if (verrou.get(i) != valeurDefaut) {
                System.out.println("verouillage() L' index " + i + ", la valeur : " + verrou.get(i));
                proposition.set(i, verrou.get(i));
            }
        }
    }

    public static void main(String[] args) {
        TestInsertionControle testInsertionControle = new TestInsertionControle();
    }
}
