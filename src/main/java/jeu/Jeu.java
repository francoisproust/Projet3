package jeu;

public abstract class Jeu {
    private int nbEssai;
    private int difficulte;

    public Jeu(int nbEssai,int difficulte){
        this.difficulte = difficulte;
        this.nbEssai = nbEssai;
    }

    public abstract void defenseur();

    public abstract void challengeur();

    public abstract void duel();

    public abstract String genererCombinaison(int difficulte);

    public abstract boolean egalite(String combinaison, String proposition);

    public abstract String reponse(String combinaison, String proposition);

    public abstract String proposition();

    public abstract boolean propositionEstNumerique(String proposition);

    public int getNbEssai() {
        return nbEssai;
    }

    public void setNbEssai(int nbEssai) {
        this.nbEssai = nbEssai;
    }

    public int getDifficulte() {
        return difficulte;
    }

    public void setDifficulte(int difficulte) {
        this.difficulte = difficulte;
    }
}
