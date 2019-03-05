package jeu;

import fonctionnement.RecupererProperties;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import java.util.Scanner;

@SuppressWarnings({"ALL", "SpellCheckingInspection"})
public class Mastermind extends Jeu{
    private final Scanner sc = new Scanner(System.in);
    private String combinaison;
    private String proposition;
    boolean egalite;
    private String reponse = "";
    private int nombreUtilisable;
    private String numerique;
    private String alphabetique;
    private String choixNumAlpha;
    private String plageUtilisation;
    private static final Logger logger = LogManager.getLogger(Mastermind.class);

    public Mastermind(int nbEssai, int difficulte, String modeJeu, String debug) {
        super(nbEssai, difficulte, modeJeu, debug);
    }

    @Override
    public void defenseur() {
        logger.debug("Lancement methode defenseur() du mode Mastermind");
        recupererPropertiesSpecifique();
        combinaison = proposition("");
        deroulementJeuModeDefenseur(combinaison);
    }

    @Override
    public void challengeur() {
        logger.debug("Lancement methode challengeur() du mode Mastermind");
        recupererPropertiesSpecifique();
        combinaison = genererCombinaison(this.getDifficulte());
        deroulementJeuModeChallengeur(combinaison);
    }

    @Override
    public void duel() {
        logger.debug("Lancement methode duel() du mode Mastermind");
        recupererPropertiesSpecifique();
        deroulementJeuModeDuel();
    }

    @Override
    public String genererCombinaison(int difficulte) {
        logger.debug("Lancement de la methode genererCombinaison()");
        logger.info("Générer combinaison en fonction d'un certain nombre de caractère");
        String combinaison = "";
        if (this.getChoixNumAlpha().equals("numerique")) {
            int nombreUtilisable = this.nombreUtilisable;
            for (int i = 0; i < difficulte; i++) {
                int nombre = (int) (Math.random() * (nombreUtilisable));
                combinaison = combinaison + nombre;
            }
            logger.info("La combinaison obtenue est numerique et est égale à : " + combinaison);
        }else if(this.getChoixNumAlpha().equals("alphabetique")) {
            int nombreUtilisable = this.nombreUtilisable;
            for (int i = 0; i < difficulte; i++) {
                int nombre = (int) (Math.random() * (nombreUtilisable));
                combinaison = combinaison + this.alphabetique.charAt(nombre);
            }
            logger.info("La combinaison obtenue est alphabétique et est égale à : " + combinaison);
        }
        return combinaison;
    }

    @Override
    public boolean egalite(String combinaison, String proposition) {
        logger.debug("Lancement de la methode egalite()");
        if(combinaison.equals(proposition)){
            logger.info("La combinaison et la proposition sont identiques");
            return true;
        }
        logger.info("La combinaison et la proposition sont différentes");
        return false;
    }

    @Override
    public String reponse(String combinaison, String proposition,String joueur) {
        logger.debug("Lancement de la methode reponse()");
        logger.info("Générer une réponse en fonction de la proposition faite et de la combinaison à trouver");
        int bienPlace = 0;
        int present = 0;
        int caractereCombinaison;
        int caractereProposition;
        if (choixNumAlpha.equals("alphabetique")) {
            plageUtilisation = alphabetique.substring(0, nombreUtilisable);
        } else {
            plageUtilisation = numerique.substring(0, nombreUtilisable);
        }
        if (joueur.equals("humain")) {
            for (int i = 0; i < combinaison.length(); i++) {
                if (combinaison.charAt(i) == proposition.charAt(i)) {
                    bienPlace = bienPlace + 1;
                } else {
                    for (int j = 0; j < combinaison.length(); j++) {
                        if (combinaison.charAt(i) == proposition.charAt(j)) {
                            present = present + 1;
                        }
                    }
                }
            }
            reponse = present + " présent, " + bienPlace + " bien placé";
            logger.info("la réponse généré est : " + reponse);
        } else if (joueur.equals("ordinateur")) {
            reponse = "";
            for (int i = 0; i < combinaison.length(); i++) {
                caractereCombinaison = positionCaractere(combinaison.charAt(i));
                caractereProposition = positionCaractere(proposition.charAt(i));
                if (caractereCombinaison < caractereProposition) {
                    reponse = reponse + "-";
                } else if (caractereCombinaison > caractereProposition) {
                    reponse = reponse + "+";
                } else if (caractereCombinaison == caractereProposition) {
                    reponse = reponse + "=";
                }
            }
            logger.info("la réponse généré est : " + reponse);
        }
        return reponse;
    }

    /**
     * Methode permettant de connaitre en fonction d'un caractère de la combinaison sa position de la plage d'utilisation
     * @param caractere valeur du caractère
     * @return sa position dans la plage d'utilisation
     */
    private int positionCaractere(char caractere){
        logger.debug("Lancement de la methode positionCaractere()");
        logger.info("a partir d'un caractère, on détermine sa position dans la liste de la plage d'utilisation");
        int position;
        int i=0;
        while(caractere != plageUtilisation.charAt(i)){
            i = i + 1;
        }
        position = i;

        return position;
    }

    @Override
    public String proposition(String joueur) {
        logger.debug("Lancement de la methode proposition()");
        String proposition;
        if (joueur.equals("ordinateur")){
            System.out.print("Faites votre proposition de combinaison à trouver par l'ordinateur sur " + this.getDifficulte() + ": ");
        }else if(joueur.equals("")){
            System.out.println("Faites votre proposition de solution sur " + this.getDifficulte() + " caractères " + choixNumAlpha + " : ");
        }
        proposition = sc.next();
        while(proposition.length() != this.getDifficulte()){
            System.out.println("Votre proposition est trop courte ou trop longue, elle doit être sur : " + this.getDifficulte()  + " caractères");
            logger.debug("La proposition faite n'est pas au bon format");
            proposition = sc.next();
        }
        if(this.getChoixNumAlpha().equals("numerique")){
            //boolean estNumerique = propositionEstNumerique(proposition);
            while(proposition.matches("-?[0-9]+") == false){
                System.out.println("Votre proposition n'est pas numérique!");
                logger.debug("la proposition n'est pas numerique");
                proposition = sc.next();
            }
        }
        logger.info("En fonction du type de joueur on demande à l'utilisateur soit de donner une combinaison à trouver par l'ordinateur ou on lui demande une proposition de réponse, " + proposition);
        return proposition;
    }

    @Override
    public String genererCombinaisonReponseOrdinateur(String reponse, String proposition) {
        logger.debug("Lancement de la methode genererCombinaisonReponseOrdinateur()");
            int position;
            String propositionReponse = "";
        for (int i = 0;i<proposition.length();i++){
            position = positionCaractere(proposition.charAt(i));
            if(reponse.charAt(i) == '+'){
                propositionReponse = propositionReponse + plageUtilisation.charAt(position + 1);
            }else if(reponse.charAt(i) == '-'){
                propositionReponse = propositionReponse + plageUtilisation.charAt(position - 1);
            }else if(reponse.charAt(i) == '='){
                propositionReponse = propositionReponse + proposition.charAt(i);
            }
        }
        logger.info("On génère une combinaison réponse pour l'ordinateur : " + propositionReponse);
        return propositionReponse;
    }

    @Override
    public String genererCombinaisonOrdinateur(int difficulte) {
        logger.debug("Lancement de la methode genererCombinaisonOrdinateur()");
        proposition = "";
        switch(choixNumAlpha){
            case "numerique":
                for (int i=0; i<difficulte;i++){
                    proposition = proposition + numerique.charAt(nombreUtilisable / 2);
                }
                break;
            case "alphabetique":
                for (int i=0; i<difficulte;i++){
                    proposition = proposition + alphabetique.charAt(nombreUtilisable / 2);
                }
                break;
        }
        logger.info("on génère une combinaison à trouver par le joueur : " + proposition);
        return proposition;
    }

    /**
     * Metode récupérant les propriétés spécifiques du Mastermind
     */
    private void recupererPropertiesSpecifique(){
        RecupererProperties properties = new RecupererProperties();
        properties.configurationSpecifiqueMastermind();
        setAlphabetique(properties.getAlphabetique());
        setNombreUtilisable(properties.getNombreUtilisable());
        setNumerique(properties.getNumerique());
        setChoixNumAlpha(properties.getChoixNumAlpha());
    }

    public int getNombreUtilisable() {
        return nombreUtilisable;
    }

    public void setNombreUtilisable(int nombreUtilisable) {
        this.nombreUtilisable = nombreUtilisable;
    }

    public String getNumerique() {
        return numerique;
    }

    public void setNumerique(String numerique) {
        this.numerique = numerique;
    }

    public String getAlphabetique() {
        return alphabetique;
    }

    public void setAlphabetique(String alphabetique) {
        this.alphabetique = alphabetique;
    }

    private String getChoixNumAlpha() {
        return choixNumAlpha;
    }

    public void setChoixNumAlpha(String choixNumAlpha) {
        this.choixNumAlpha = choixNumAlpha;
    }
}