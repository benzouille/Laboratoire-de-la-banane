package test.divers;

import java.util.ArrayList;

public class TestPositionneur {
    private int positionCouleur1 = 0, valeurDefaut = -1, tailleCombinaison = 6;

    private String derniereAction = "";

    private boolean okPosition =  true;

    private ArrayList<Integer> verrou = new ArrayList<>();
    private ArrayList<Integer> proposition = new ArrayList<>();

    TestPositionneur(){
        init();
    }

    private void init(){
        //ajout des -1 dans l'arrayList proposition et verrou.
        for (int i = 0; i<tailleCombinaison; i++) {
            proposition.add(valeurDefaut);
            verrou.add(valeurDefaut);
        }

        verrou.set(0,4);
        verrou.set(1,4);
        verrou.set(3,2);

        System.out.println(verrou);
        for (int i = 0; i<tailleCombinaison; i++) {
            positionneur(i);
        }
        System.out.println(proposition);
    }

    public void positionneur(int position){
        //for (int i = 0; i<tailleCombinaison; i++) {
            if(verrou.get(position).equals(valeurDefaut)){
                proposition.set(position, 9);
                okPosition = true;
                System.out.println(okPosition + "  La position" + position);
            }
            else{
                okPosition = false;
                position++;
                System.out.println((okPosition + " La position" + position));
            }

       // }
    }

    private void monoChrome(int couleur) {
        for (int i = 0; i<tailleCombinaison; i++) {
            if(proposition.get(i) == -1) {
                proposition.set(i, couleur);
            }
        }
    }

    private void biChrome(int couleur1, int positionCouleur1, int couleur2) {
        int a = 0;
        int ajout =0;
        while(verrou.get(a++) != -1){ ajout++; }
        proposition.set(positionCouleur1+ajout, couleur1);
        monoChrome(couleur2);
    }

    private void ajoutAuVerrou(int index, int chiffre) {
        int a = 0;
        int ajout =0;
        while(verrou.get(a++) != -1){ ajout++; }
        verrou.set(index+ajout, chiffre);
    }


    public static void main(String[] args) {
        TestPositionneur testPositionneur = new TestPositionneur();
    }
}
