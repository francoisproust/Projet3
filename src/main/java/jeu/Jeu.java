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

    protected void deroulementJeuModeChallengeur(String combinaison){
        boolean egalite = false;
        String proposition;
        String reponse ="";
        combinaison = genererCombinaison(this.getDifficulte());
        if (this.getDebug().equals("true")){
            System.out.println("La solution est : " + combinaison);
        }
        while(this.getNbEssai() >0 && egalite == false){
            proposition = proposition("");
            if(egalite(combinaison,proposition) == true){
                System.out.println("Bravo vous avez trouvé la solution");
                break;
            }else{
                reponse = reponse(combinaison,proposition);
                System.out.println("Proposition : " + proposition + " -> Réponse : " + reponse);
                setNbEssai(getNbEssai() - 1);
            }
            if(getNbEssai()== 0){
                System.out.println("Vous avez perdu, la solution était : " + combinaison);
            }
        }
    }

    protected void deroulementJeuModeDefenseur(String combinaison){
        boolean egalite = false;
        String reponse ="";
        String proposition = genererCombinaisonOrdinateur(this.getDifficulte());
        while(this.getNbEssai() >0 && egalite == false){
            if(egalite(combinaison,proposition) == true){
                System.out.println("Bravo vous avez trouvé la solution");
                break;
            }else{
                reponse = reponse(combinaison,proposition);
                proposition = genererCombinaisonReponseOrdinateur(reponse, proposition);
                setNbEssai(getNbEssai() - 1);
                reponse = "";
            }
        }
        if(getNbEssai()== 0){
            System.out.println("L'ordinateur n'as pas trouvé la bonne combinaison");
        }
    }

    protected void deroulementJeuModeDuel(){
        String combinaisonPourJoueur = genererCombinaison(this.getDifficulte());
        String combinaisonPourOrdinateur = proposition("ordinateur");
        String propositionOrdinateur = genererCombinaisonOrdinateur(this.getDifficulte());
        while(this.getNbEssai() >0){
            // partie ou le joueur cherche la combinaison
            String propositionJoueur = proposition("");
            if(egalite(combinaisonPourJoueur,propositionJoueur) == true){
                System.out.println("Bravo vous avez trouvé la solution");
                break;
            }else{
                String reponseJoueur = reponse(combinaisonPourJoueur,propositionJoueur);
                System.out.println("Proposition : " + propositionJoueur + " -> Réponse : " + reponseJoueur);
            }
            // partie ou c est l'ordinateur qui cherche la combinaison
            if(egalite(combinaisonPourOrdinateur,propositionOrdinateur) == true){
                System.out.println("L'ordinateur a trouvé la solution");
                break;
            }else{
                String reponseOrdinateur = reponse(combinaisonPourOrdinateur,propositionOrdinateur);
                propositionOrdinateur = genererCombinaisonReponseOrdinateur(reponseOrdinateur, combinaisonPourOrdinateur);
                reponseOrdinateur = "";
            }
            setNbEssai(getNbEssai() - 1);
        }
        if(getNbEssai()== 0){
            System.out.println("Vous avez perdu tous les deux");
            System.out.println("La solution pour le joueur etait : " + combinaisonPourJoueur);
        }
    }

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
