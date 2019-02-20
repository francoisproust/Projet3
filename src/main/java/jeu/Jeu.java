package jeu;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class Jeu {
    Scanner sc = new Scanner(System.in);

    public ArrayList<Integer> genererNombre(int difficulte){
        ArrayList<Integer> combinaison = new ArrayList();
        for(int i = 0; i < difficulte; i++){
            int nombre = (int) (Math.random()*(10-0)); //on génère un nombre entre 0 et 10
            combinaison.add(i,nombre);
            System.out.print(combinaison.get(i));
        }
        System.out.println();
        return combinaison;
    }

    public ArrayList<Integer> recupererProposition(int difficulte){
        System.out.print("Proposition : ");
        String valeurRecuperer = sc.next();
        ArrayList<Integer> proposition = new ArrayList();
        for(int i =0;i<difficulte;i++){
            proposition.add(i,Character.getNumericValue(valeurRecuperer.charAt(i)));
        }
        return proposition;
    }

    public abstract void challengeur();

    public abstract void defenseur();

    public abstract void duel();

    public abstract boolean comparaison(ArrayList<Integer> combinaison, ArrayList<Integer> combinaisonProposee, int difficulte);
}
