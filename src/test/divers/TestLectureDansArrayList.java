package test.divers;

import java.util.ArrayList;

public class TestLectureDansArrayList {

    //TODO tester l'insertion dans l'arrayList couleur entre deux index

    private ArrayList<String>proposition = new ArrayList<>();
    private ArrayList<String>couleur = new ArrayList<>();
    private ArrayList<String>poubelle = new ArrayList<>();
    private int tailleCombinaison = 4, positionCouleur1 = 0;
    private String derniereAction;

    public TestLectureDansArrayList(){
        //ajout des couleurs dans l'arraylist couleur.
        couleur.add("rouge");
        couleur.add("jaune");
        couleur.add("bleu");
        couleur.add("vert");
        couleur.add("orange");
        couleur.add("brun");
        resetProp();
        System.out.println(proposition);
        biChrome(couleur.get(0), positionCouleur1, couleur.get(1));
        System.out.println(proposition);
        resetProp();
        aLaPoubelle(couleur.get(0));
        System.out.println("La liste couleur : " + couleur + " La liste poubelle" + poubelle);
        biChrome(couleur.get(0), positionCouleur1, couleur.get(1));
        System.out.println(proposition);
        aLaPoubelle(couleur.get(0));
        System.out.println("La liste couleur : " + couleur + " La liste poubelle" + poubelle);
        insertColor(2, "gris");
        System.out.println("La liste couleur : " + couleur + " La liste poubelle" + poubelle);
        System.out.println(couleur.get(0));

    }

    private void resetProp(){
        //ajout des -1 dans l'arrayList proposition
        proposition.clear();
        for (int i = 0; i<tailleCombinaison; i++) {
            proposition.add("-1");
        }
    }

    private void insertColor(int index, String color){
        couleur.add(index, color);
    }

    /**
     * Retire une couleur de l'ArrayList couleur la met dans l'ArrayList poubelle, puis passe à la couleur suivante.
     * @param color int
     */
    private void aLaPoubelle(String color){
        couleur.remove(color);
        poubelle.add(color);
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
        derniereAction = "monoChrome";
    }

    /**
     * Place couleur1 à la position et remplie le reste avec la couleur2
     * @param couleur1 int
     * @param positionCouleur1 int
     * @param couleur2 int
     */
    private void biChrome(String couleur1, int positionCouleur1, String couleur2) {
        proposition.set(positionCouleur1, couleur1);
        monoChrome(couleur2);
        derniereAction = "biChrome";
    }

    public static void main(String[] args) {
        TestLectureDansArrayList TestLectureDansArrayList = new TestLectureDansArrayList();
    }
}
