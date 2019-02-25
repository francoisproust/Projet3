package jeu;

public abstract class Jeu {
    private int nbEssai;
    private int difficulte;
    private String modeJeu;
    private String debug;

    public Jeu(int nbEssai,int difficulte, String modeJeu, String debug){
        this.difficulte = difficulte;
        this.nbEssai = nbEssai;
        this.modeJeu = modeJeu;
        this.debug = debug;
    }

    public abstract void defenseur();

    public abstract void challengeur();

    public abstract void duel();

    public abstract String genererCombinaison(int difficulte);

    public abstract boolean egalite(String combinaison, String proposition);

    public abstract String reponse(String combinaison, String proposition);

    public abstract String proposition(String joueur);

    public abstract boolean propositionEstNumerique(String proposition);

    public String getModeJeu() {
        return modeJeu;
    }

    public void setModeJeu(String modeJeu) {
        this.modeJeu = modeJeu;
    }

    public void setDifficulte(int difficulte) {
        this.difficulte = difficulte;
    }

    public abstract String genererCombinaisonReponseOrdinateur (String reponse, String proposition);

    public abstract String nombreAleatoire(String plusMoins, char chiffre);

    public abstract String genererCombinaisonOrdinateur(int difficulte);

    public int getNbEssai() {
        return nbEssai;
    }

    public void setNbEssai(int nbEssai) {
        this.nbEssai = nbEssai;
    }

    public int getDifficulte() {
        return difficulte;
    }

    public String getDebug() {
        return debug;
    }

    public void setDebug(String debug) {
        this.debug = debug;
    }
}
