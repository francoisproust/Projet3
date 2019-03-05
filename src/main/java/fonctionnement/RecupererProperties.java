package fonctionnement;

import java.io.InputStream;
import java.util.Properties;
import java.io.IOException;
import java.io.FileInputStream;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class RecupererProperties {
    private int nombreCases;
    private int nbEssai;
    private String debug;
    private int nombreUtilisable;
    private String numerique;
    private String alphabetique;
    private String choixNumAlpha;
    private static Logger logger = LogManager.getLogger(RecupererProperties.class);

    /**
     * Récupération des properties pour le jeu recherche +/-
     */
    public void configurationRecherche(){
        logger.debug("Lancement de la methode configurationRecherche()");
        Properties prop = new Properties();
        InputStream input = null;
        try{
            input = new FileInputStream("src/main/ressources/config.properties");
            prop.load(input);
            this.nombreCases = Integer.valueOf(prop.getProperty("recherche.nombreCases"));
            this.nbEssai = Integer.valueOf(prop.getProperty("recherche.nbEssais"));
            this.debug = prop.getProperty("recherche.debug");
            logger.debug("On récupère les propriétés suivantes: nombre de cases " + nombreCases + " ,  nombre d'essais " + nbEssai + " , mode debug (true or null) " + debug);
        }catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Récupération des properties pour le jeu Mastermind
     */
    public void configurationMastermind(){
        logger.debug("Lancement de la methode configurationMastermind()");
        Properties prop = new Properties();
        InputStream input = null;
        try{
            input = new FileInputStream("src/main/ressources/config.properties");
            prop.load(input);
            this.nombreCases = Integer.valueOf(prop.getProperty("mastermind.nombreCases"));
            this.nbEssai = Integer.valueOf(prop.getProperty("mastermind.nbEssais"));
            this.debug = prop.getProperty("mastermind.debug");
            logger.debug("On récupère les propriétés suivantes: nombre de cases " + nombreCases + " ,  nombre d'essais " + nbEssai + " , mode debug (true or null) " + debug);
        }catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     *  Récupération des propeties spécifiques au jeu mastermind tel que:
     *  le nombre de valeur utilisable
     *  les différentes plages d'utilisation numériques et alphabétiques
     *  si l'on prend des valeurs numériques ou alphabétiques
     */
    public void configurationSpecifiqueMastermind(){
        logger.debug("Lancement de la methode configurationSpecifiqueMastermind()");
        Properties prop = new Properties();
        InputStream input = null;
        try{
            input = new FileInputStream("src/main/ressources/config.properties");
            prop.load(input);
            this.nombreUtilisable = Integer.parseInt(prop.getProperty("mastermind.nombreUtilisable"));
            this.numerique = prop.getProperty("mastermind.numerique");
            this.alphabetique = prop.getProperty("mastermind.alphabetique");
            this.choixNumAlpha = prop.getProperty("mastermind.choixNumAlpha");
            logger.debug("On récupère les propriétés spécifiques suivantes : nombre utilisables " + nombreUtilisable + " , liste de nombre numerique " + numerique + " , liste de lettres " + alphabetique + " , choix numerique ou alphabetique " + choixNumAlpha);
        }catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    public int getNombreCases() {
        return nombreCases;
    }

    public void setNombreCases(int nombreCases) {
        this.nombreCases = nombreCases;
    }

    public int getNbEssai() {
        return nbEssai;
    }

    public void setNbEssai(int nbEssai) {
        this.nbEssai = nbEssai;
    }

    public String getDebug() {
        return debug;
    }

    public void setDebug(String debug) {
        this.debug = debug;
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

    public String getChoixNumAlpha() {
        return choixNumAlpha;
    }

    public void setChoixNumAlpha(String choixNumAlpha) {
        this.choixNumAlpha = choixNumAlpha;
    }

    public int getNombreUtilisable() {
        return nombreUtilisable;
    }

    public void setNombreUtilisable(int nombreUtilisable) {
        this.nombreUtilisable = nombreUtilisable;
    }
}