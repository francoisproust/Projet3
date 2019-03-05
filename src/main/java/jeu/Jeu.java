package jeu;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@SuppressWarnings("ALL")
abstract class Jeu {
    private int nbEssai;
    private int difficulte;
    private String modeJeu;
    private String debug;
    private int nbCoup;
    private static final Logger logger = LogManager.getLogger(Jeu.class);
    /**
     * Constructeur permettant la création du Jeu
     * @param nbEssai nombre d'essai pour trouver la possibilité
     * @param difficulte difficulté de jeu, longueur de la combinaison à trouver
     * @param modeJeu mode de jeu utilisé (Defenseur, Challengeur, Duel)
     * @param debug mode debug affichant la combinaison à trouver
     */
    Jeu(int nbEssai, int difficulte, String modeJeu, String debug){
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
     * @return combinaison généré
     */
    protected abstract String genererCombinaison(int difficulte);

    /**
     * Teste l'égalité entre deux propositions
     * @param combinaison combinaison à trouver
     * @param proposition proposition faite par le joueur ou l'ordinateur
     * @return boolean
     */
    protected abstract boolean egalite(String combinaison, String proposition);

    /**
     * génère une réponse en fonction de la combinaison à trouver, de la proposition faite et du type de joueur
     * @param combinaison  combinaison à trouver
     * @param proposition proposition faite par le joueur ou l'ordinateur
     * @param joueur ordinateur / humain
     * @return reponse pour affiner son résultat
     */
    protected abstract String reponse(String combinaison, String proposition, String joueur);

    /**
     * génère une première combinaison de solution pour l'ordinateur
     * @param difficulte nombre de caractères de la combinaison à trouver
     * @return proposition de combinaison initiale (au milieu des possibilités)
     */
    protected abstract String genererCombinaisonOrdinateur(int difficulte);

    /**
     * Déroulement du mode Challengeur
     * @param combinaison généré automatiquement en fonction de la difficulté
     */
    @SuppressWarnings("UnusedAssignment")
    void deroulementJeuModeChallengeur(String combinaison){
        logger.debug("Lancement de la methode deroulementJeuModeChallengeur()");
        boolean egalite = false;
        String proposition;
        //noinspection UnusedAssignment
        String reponse ="";
        nbCoup = 0;
        if (debug.equals("true")){
            System.out.println("La solution est : " + combinaison);
            logger.debug("Le mode debug est à true, nous affichons la solution");
        }
        while(nbEssai >0 && egalite == false){
            proposition = proposition("");
            nbCoup =  nbCoup + 1;
            if(egalite(combinaison,proposition) == true){
                logger.info("L'utilisateur a trouvé la combinaison en " + nbCoup + " coups");
                System.out.println("Bravo, la solution a été trouvé en " + nbCoup + " coups");
                break;
            }else{
                reponse = reponse(combinaison,proposition,"humain");
                logger.info("Proposition : " + proposition + " -> Réponse : " + reponse);
                System.out.println("Proposition : " + proposition + " -> Réponse : " + reponse);
                nbEssai = nbEssai - 1;
                logger.info("il reste " + nbEssai + " essais");
            }
            if(nbEssai== 0){
                logger.info("Vous avez perdu, la solution était : " + combinaison);
                System.out.println("Vous avez perdu, la solution était : " + combinaison);
            }
        }
    }

    /**
     * déroulement du jeu en mode Défenseur
     * @param combinaison démandé au préalable à l utilisateur
     */
    void deroulementJeuModeDefenseur(String combinaison){
        logger.debug("Lancement de la methode deroulementJeuModeDefenseur()");
        boolean egalite = false;
        String reponse ="";
        nbCoup =  0;
        String proposition = genererCombinaisonOrdinateur(difficulte);
        while(nbEssai >0 && egalite == false){
            nbCoup =  nbCoup + 1;
            if(egalite(combinaison,proposition) == true){
                logger.info("L'utilisateur a trouvé la combinaison en " + nbCoup + " coups");
                System.out.println("Bravo, la solution a été trouvé en " + nbCoup + " coups");
                break;
            }else{
                reponse = reponse(combinaison,proposition,"ordinateur");
                logger.debug("a partir de la combinaison à trouver et de la proposition de l'ordinateur, on fournit un réponse pour générer la prochaine proposition : " +reponse);
                proposition = genererCombinaisonReponseOrdinateur(reponse, proposition);
                logger.debug("a parir de la réponse et de la proposition faite on génère une nouvelle proposition : " + proposition);
                nbEssai = nbEssai - 1;
                logger.debug("on retire un essai sur le nombre de coup, il en reste : " + nbEssai);
                reponse = "";
            }
        }
        if(nbEssai== 0){
            logger.info("L'ordinateur n'as pas trouvé la bonne combinaison");
            System.out.println("L'ordinateur n'as pas trouvé la bonne combinaison");
        }
    }

    /**
     * Déroulement du jeu en mode duel
     */
    @SuppressWarnings("UnusedAssignment")
    void deroulementJeuModeDuel(){
        logger.debug("Lancement de la methode deroulementJeuModeDefenseur()");
        String combinaisonPourJoueur = genererCombinaison(difficulte);
        String combinaisonPourOrdinateur = proposition("ordinateur");
        String propositionOrdinateur = genererCombinaisonOrdinateur(difficulte);
        nbCoup =  0;
        while(nbEssai >0){
            nbCoup =  nbCoup + 1;
            // partie ou le joueur cherche la combinaison
            String propositionJoueur = proposition("");
            if(egalite(combinaisonPourJoueur,propositionJoueur) == true){
                System.out.println("Bravo, la solution a été trouvé en " + nbCoup + " coups");
                logger.info("Le joueur a trouvé la solution en " + nbCoup + " coups");
                break;
            }else{
                String reponseJoueur = reponse(combinaisonPourJoueur,propositionJoueur,"humain");
                logger.info("on fournit une réponse au joueur en fonction de la combinaison à trouver et sa proposition " + reponseJoueur);
                System.out.println("Proposition : " + propositionJoueur + " -> Réponse : " + reponseJoueur);
            }
            // partie ou c est l'ordinateur qui cherche la combinaison
            if(egalite(combinaisonPourOrdinateur,propositionOrdinateur) == true){
                System.out.println("L'ordinateur a été trouvé en " + nbCoup + " coups");
                logger.info("L'ordinateur a trouvé la solution en " + nbCoup + " coups");
                break;
            }else{
                String reponseOrdinateur = reponse(combinaisonPourOrdinateur,propositionOrdinateur,"ordinateur");
                logger.info("on fournit une réponse pour l'ordinateur en fonction de la combinaison à trouver et sa proposition " + reponseOrdinateur);
                propositionOrdinateur = genererCombinaisonReponseOrdinateur(reponseOrdinateur, propositionOrdinateur);
                logger.info("on génère une nouvelle proposition pour l'ordinateur : " + propositionOrdinateur);
                reponseOrdinateur = "";
            }
            nbEssai = nbEssai - 1;
            logger.info("il reste " + nbEssai + " essais");
        }
        if(nbEssai== 0){
            System.out.println("Vous avez perdu tous les deux");
            System.out.println("La solution pour le joueur etait : " + combinaisonPourJoueur);
            logger.info("le joueur et l'ordinateur ont perdu, la combinaison à trouver pour le joueur etait : " + combinaisonPourJoueur);
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

    int getDifficulte() {
        return difficulte;
    }

    public String getDebug() {
        return debug;
    }

    public void setDebug(String debug) {
        this.debug = debug;
    }

    protected abstract String proposition(String joueur);

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