package test.mastermind;

import java.util.ArrayList;

public class MastermindLecteurIndice {

    private int nbreNoir, nbreBlanc;
    private ArrayList<Integer> indice = new java.util.ArrayList<>();

    public MastermindLecteurIndice(){
        //les noirs
        indice.add(1);
        indice.add(1);
        indice.add(1);
        //les blancs
        indice.add(2);
        indice.add(2);

        lecteurIndice();
        System.out.println("nombre de blanc : "+nbreBlanc+" nombre de noir : "+nbreNoir);
    }

    /**
     * Methode dénombrant le nombre d'indice noir et le nombre d'indice blanc contenu dans l'ArrayList.
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

    public static void main(String[] args) {
        MastermindLecteurIndice mastermindLecteurIndice = new MastermindLecteurIndice();
    }
}
