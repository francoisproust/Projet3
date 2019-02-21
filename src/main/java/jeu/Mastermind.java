package jeu;

public class Mastermind extends Jeu{
    public Mastermind(int nbEssai, int difficulte) {
        super(nbEssai, difficulte);
    }

    @Override
    public void defenseur() {

    }

    @Override
    public void challengeur() {

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
    public String proposition() {
        return null;
    }

    @Override
    public boolean propositionEstNumerique(String proposition) {
        return false;
    }

}
