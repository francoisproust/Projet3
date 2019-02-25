package jeu;

import java.util.Scanner;

public class Mastermind extends Jeu{
    Scanner sc = new Scanner(System.in);
    String combinaison;
    String proposition;
    boolean egalite;
    String reponse = "";

    public Mastermind(int nbEssai, int difficulte, String modeJeu, String debug) {
        super(nbEssai, difficulte, modeJeu, debug);
    }

    @Override
    public void defenseur() {

    }

    @Override
    public void challengeur() {
        combinaison = genererCombinaison(this.getDifficulte());
    }

    @Override
    public void duel() {

    }

    @Override
    public String genererCombinaison(int difficulte) {
        return null;
    }

    @Override
    public boolean egalite(String combinaison, String proposition) {
        return false;
    }

    @Override
    public String reponse(String combinaison, String proposition) {
        return null;
    }

    @Override
    public String proposition(String joueur) {
        return null;
    }

    @Override
    public boolean propositionEstNumerique(String proposition) {
        return false;
    }

    @Override
    public String genererCombinaisonReponseOrdinateur(String reponse, String proposition) {
        return null;
    }

    @Override
    public String nombreAleatoire(String plusMoins, char chiffre) {
        return null;
    }

    @Override
    public String genererCombinaisonOrdinateur(int difficulte) {
        return null;
    }

}
