package jeu;

public abstract class Jeu {
    private int nbEssai;
    private int difficulte;
    private String modeJeu;
    private String debug;

    /**
     * Constructeur permettant la création du Jeu
     * @param nbEssai nombre d'essai pour trouver la possibilité
     * @param difficulte difficulté de jeu, longueur de la combinaison à trouver
     * @param modeJeu mode de jeu utilisé (Defenseur, Challengeur, Duel)
     * @param debug mode debug affichant la combinaison à trouver
     */
    public Jeu(int nbEssai,int difficulte, String modeJeu, String debug){
        this.difficulte = difficulte;
        this.nbEssai = nbEssai;
        this.modeJeu = modeJeu;
        this.debug = debug;
    }

    /**
     * lance le jeu en mode défenseur
     */
    public abstract void defenseur();

    /**
     * lance le jeu en mode Challengeur
     */
    public abstract void challengeur();

    /**
     *
     * lance le jeu en mode duel
     */
    public abstract void duel();

    /**
     * génère une combinaison en fonction de la difficulté demandé
     * @param difficulte nombre de caractère de la combinaison à trouver
     * @return
     */
    public abstract String genererCombinaison(int difficulte);

    /**
     * Teste l'égalité entre deux propositions
     * @param combinaison combinaison à trouver
     * @param proposition proposition faite par le joueur ou l'ordinateur
     * @return boolean
     */
    public abstract boolean egalite(String combinaison, String proposition);

    /**
     * génère une réponse en fonction de la combinaison à trouver, de la proposition faite et du type de joueur
     * @param combinaison  combinaison à trouver
     * @param proposition proposition faite par le joueur ou l'ordinateur
     * @param joueur ordinateur / humain
     * @return reponse pour affiner son résultat
     */
    public abstract String reponse(String combinaison, String proposition,String joueur);

    /**
     * Methode permettant au joueur de proposer une combinaison à trouver par l ordinateur ou une proposition de solution
     * @param joueur ordinateur / ""
     * @return proposition de combinaison
     */

    /**
     * génère une première combinaison de solution pour l'ordinateur
     * @param difficulte nombre de caractères de la combinaison à trouver
     * @return proposition de combinaison initiale (au milieu des possibilités)
     */
    public abstract String genererCombinaisonOrdinateur(int difficulte);

    /**
     * Déroulement du mode Challengeur
     * @param combinaison généré automatiquement en fonction de la difficulté
     */
    protected void deroulementJeuModeChallengeur(String combinaison){
        boolean egalite = false;
        String proposition;
        String reponse ="";
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

    /**
     * déroulement du jeu en mode Défenseur
     * @param combinaison démandé au préalable à l utilisateur
     */
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

    /**
     * Déroulement du jeu en mode duel
     */
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

    /**
     * Methode permettant de générer une réponse de la part de l'ordinateur
     * @param reponseOrdinateur réponse calculé a base de + - =
     * @param combinaisonPourOrdinateur ancienne proposition faite par l'ordinateur
     * @return une nouvelle proposition à tester
     */
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
}