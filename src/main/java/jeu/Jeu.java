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

    public abstract String reponse(String combinaison, String proposition,String joueur);

    public abstract String proposition(String joueur);

    public String getModeJeu() {
        return modeJeu;
    }

    public void setModeJeu(String modeJeu) {
        this.modeJeu = modeJeu;
    }

    public void setDifficulte(int difficulte) {
        this.difficulte = difficulte;
    }

    public abstract String genererCombinaisonOrdinateur(int difficulte);

    protected void deroulementJeuModeChallengeur(String combinaison){
        boolean egalite = false;
        String proposition;
        String reponse ="";
        combinaison = genererCombinaison(difficulte);
        if (debug.equals("true")){
            System.out.println("La solution est : " + combinaison);
        }
        while(nbEssai >0 && egalite == false){
            proposition = proposition("");
            if(egalite(combinaison,proposition) == true){
                System.out.println("Bravo, la solution a été trouvé en " + nbEssai + " coups");
                break;
            }else{
                reponse = reponse(combinaison,proposition,"humain");
                System.out.println("Proposition : " + proposition + " -> Réponse : " + reponse);
                nbEssai = nbEssai - 1;
            }
            if(nbEssai== 0){
                System.out.println("Vous avez perdu, la solution était : " + combinaison);
            }
        }
    }

    protected void deroulementJeuModeDefenseur(String combinaison){
        boolean egalite = false;
        String reponse ="";
        String proposition = genererCombinaisonOrdinateur(difficulte);
        while(nbEssai >0 && egalite == false){
            if(egalite(combinaison,proposition) == true){
                System.out.println("Bravo, la solution a été trouvé en " + nbEssai + " coups");
                break;
            }else{
                reponse = reponse(combinaison,proposition,"ordinateur");
                proposition = genererCombinaisonReponseOrdinateur(reponse, proposition);
                nbEssai = nbEssai - 1;
                reponse = "";
            }
        }
        if(nbEssai== 0){
            System.out.println("L'ordinateur n'as pas trouvé la bonne combinaison");
        }
    }

    protected void deroulementJeuModeDuel(){
        String combinaisonPourJoueur = genererCombinaison(difficulte);
        String combinaisonPourOrdinateur = proposition("ordinateur");
        String propositionOrdinateur = genererCombinaisonOrdinateur(difficulte);
        while(nbEssai >0){
            // partie ou le joueur cherche la combinaison
            String propositionJoueur = proposition("");
            if(egalite(combinaisonPourJoueur,propositionJoueur) == true){
                System.out.println("Bravo vous avez trouvé la solution");
                break;
            }else{
                String reponseJoueur = reponse(combinaisonPourJoueur,propositionJoueur,"humain");
                System.out.println("Proposition : " + propositionJoueur + " -> Réponse : " + reponseJoueur);
            }
            // partie ou c est l'ordinateur qui cherche la combinaison
            if(egalite(combinaisonPourOrdinateur,propositionOrdinateur) == true){
                System.out.println("L'ordinateur a trouvé la solution");
                break;
            }else{
                String reponseOrdinateur = reponse(combinaisonPourOrdinateur,propositionOrdinateur,"ordinateur");
                propositionOrdinateur = genererCombinaisonReponseOrdinateur(reponseOrdinateur, propositionOrdinateur);
                reponseOrdinateur = "";
            }
            nbEssai = nbEssai - 1;
        }
        if(nbEssai== 0){
            System.out.println("Vous avez perdu tous les deux");
            System.out.println("La solution pour le joueur etait : " + combinaisonPourJoueur);
        }
    }

    protected abstract String genererCombinaisonReponseOrdinateur(String reponseOrdinateur, String combinaisonPourOrdinateur);

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