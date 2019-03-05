package jeu;

import java.util.Scanner;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@SuppressWarnings({"ALL", "StringConcatenationInLoop", "PointlessBooleanExpression"})
public class RecherchePlusMoins extends Jeu{
    private final Scanner sc = new Scanner(System.in);
    private String combinaison;
    private String proposition;
    boolean egalite;
    private String reponse = "";
    private static final Logger logger = LogManager.getLogger(RecherchePlusMoins.class);
    public RecherchePlusMoins(int nbEssai, int difficulte, String modeJeu,String debug) {
        super(nbEssai, difficulte,modeJeu, debug);
    }

    @Override
    public void defenseur() {
        logger.debug("Lancement methode defenseur() du mode Recherche +/-");
        combinaison = proposition("ordinateur");
        // proposition = genererCombinaisonOrdinateur(this.getDifficulte());
        deroulementJeuModeDefenseur(combinaison);
    }

    @Override
    public void challengeur() {
        logger.debug("Lancement methode challengeur() du mode Recherche +/-");
        combinaison = genererCombinaison(this.getDifficulte());
        deroulementJeuModeChallengeur(combinaison);
    }

    @Override
    public void duel() {
        logger.debug("Lancement methode duel() du mode Recherche +/-");
        deroulementJeuModeDuel();
    }

    @SuppressWarnings("PointlessArithmeticExpression")
    @Override
    public String genererCombinaison(int difficulte) {
        logger.debug("Lancement de la methode genererCombinaison()");
        logger.info("Générer combinaison en fonction d'un certain nombre de caractère");
        String combinaison = "";
        for(int i=0;i<difficulte;i++){
            int nombre = (int) (Math.random()*(10-0));
            combinaison =   combinaison + nombre;
        }
        logger.info("La combinaison obtenue est égale à : " + combinaison);
        return combinaison;
    }

    @Override
    public boolean egalite(String combinaison, String proposition) {
        logger.debug("Lancement de la methode egalite()");
        boolean egalite;
        if (combinaison.equals(proposition)){
            logger.info("La combinaison et la proposition sont identiques");
            return egalite = true;
        }else{
            logger.info("La combinaison et la proposition sont différentes");
            //noinspection UnusedAssignment
            return egalite = false;
        }
    }

    @Override
    public String reponse(String combinaison, String proposition,String joueur) {
        logger.debug("Lancement de la methode reponse()");
        logger.info("Générer une réponse en fonction de la proposition faite et de la combinaison à trouver");
        reponse = "";
        for(int i = 0; i<combinaison.length();i++){
            if (combinaison.charAt(i) == proposition.charAt(i)){
                reponse = reponse + "=";
            }else if(combinaison.charAt(i) < proposition.charAt(i)){
                reponse = reponse + "-";
            }else if(combinaison.charAt(i) > proposition.charAt(i)){
                reponse = reponse + "+";
            }
        }
        logger.info("la réponse généré est : " + reponse);
        return reponse;
    }

    @Override
    public String proposition(String joueur) {
        logger.debug("Lancement de la methode proposition()");
        String proposition;
        if (joueur.equals("ordinateur")){
            System.out.print("Faites votre proposition de combinaison à trouver par l'ordinateur : ");
        }else if(joueur.equals("")){
            System.out.println("Faites votre proposition de solution : ");
        }
        proposition = sc.next();
        while(proposition.matches("-?[0-9]+") == false){
            logger.debug("la proposition n'est pas numerique");
            System.out.println("Votre proposition n'est pas numérique!");
            proposition = sc.next();
        }
        logger.info("En fonction du type de joueur on demande à l'utilisateur soit de donner une combinaison à trouver par l'ordinateur ou on lui demande une proposition de réponse, " + proposition);
        return proposition;
    }

    /**
     *  Methode permettant d'ajouter ou soustraire 1 pour générer une nouvelle combinaison
     * @param plusMoins plus ou moins
     * @param chiffre valeur d'un caractère de la combinaison
     * @return caractère valorisé à +1 ou -1
     */
    private String nombreAleatoire(String plusMoins, char chiffre) {
        logger.debug("Lancement de la methode nombreAleatoire()");
        int nombre = 0;
        String chiffreString;
        chiffreString = Character.toString(chiffre);
        switch (plusMoins){
            case "plus":
                nombre = Integer.parseInt(chiffreString )+ 1 ;
                logger.info("on ajoute 1 au nombre " + chiffreString + "pour obtenir le chiffre " + nombre);
                break;
            case "moins":
                nombre = Integer.parseInt(chiffreString)- 1;
                logger.info("on soustrait 1 au nombre " + chiffreString + "pour obtenir le chiffre " + nombre);
                break;
        }
        return String.valueOf(nombre);
    }

    @Override
    public String genererCombinaisonOrdinateur(int difficulte) {
        logger.debug("Lancement de la methode genererCombinaisonOrdinateur()");
        proposition = "";
        for(int i = 0; i<difficulte; i++){
            proposition = proposition + "5";
        }
        logger.info("On génère une première proposition pour l'ordinateur à base de 5, ce qui donne la proposition : " +proposition);
        return proposition;
    }

    @Override
    public String genererCombinaisonReponseOrdinateur (String reponse, String proposition){
        logger.debug("Lancement de la methode genererCombinaisonReponseOrdinateur()");
        String nouvelleReponse = "";
        for (int i=0; i < reponse.length(); i++){
            if (reponse.charAt(i) == '+'){
                nouvelleReponse = nouvelleReponse + nombreAleatoire("plus",proposition.charAt(i));
            }else if (reponse.charAt(i) == '-'){
                nouvelleReponse = nouvelleReponse + nombreAleatoire("moins",proposition.charAt(i));
            }else{
                nouvelleReponse = nouvelleReponse + proposition.charAt(i);
            }
        }
        logger.info("On génère la proposition de l'ordinateur en fonction de la réponse faite soit la proposition suivante : " + nouvelleReponse);
        return nouvelleReponse;
    }
}