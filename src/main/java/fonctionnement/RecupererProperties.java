package fonctionnement;

import java.io.InputStream;
import java.util.Properties;
import java.io.IOException;
import java.io.FileInputStream;


public class RecupererProperties {
    private int nombreCases;
    private int nbEssai;
    private String debug;
    private int nombreUtilisable;
    private String numerique;
    private String alphabetique;
    private String choixNumAlpha;


    public void configurationRecherche(){
        Properties prop = new Properties();
        InputStream input = null;
        try{
            input = new FileInputStream("src/main/java/ressources/config.properties");
            prop.load(input);
            this.nombreCases = Integer.valueOf(prop.getProperty("recherche.nombreCases"));
            this.nbEssai = Integer.valueOf(prop.getProperty("recherche.nbEssais"));
            this.debug = prop.getProperty("recherche.debug");

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

    public void configurationMastermind(){
        Properties prop = new Properties();
        InputStream input = null;
        try{
            input = new FileInputStream("src/main/java/ressources/config.properties");
            prop.load(input);
            this.nombreCases = Integer.valueOf(prop.getProperty("mastermind.nombreCases"));
            this.nbEssai = Integer.valueOf(prop.getProperty("mastermind.nbEssais"));
            this.debug = prop.getProperty("mastermind.debug");

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

    public void configurationSpecifiqueMastermind(){
        Properties prop = new Properties();
        InputStream input = null;
        try{
            input = new FileInputStream("src/main/java/ressources/config.properties");
            prop.load(input);
            this.nombreUtilisable = Integer.parseInt(prop.getProperty("mastermind.nombreUtilisable"));
            this.numerique = prop.getProperty("mastermind.numerique");
            this.alphabetique = prop.getProperty("mastermind.alphabetique");
            this.choixNumAlpha = prop.getProperty("mastermind.choixNumAlpha");
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