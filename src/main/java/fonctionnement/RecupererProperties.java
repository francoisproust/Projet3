package fonctionnement;

import java.io.InputStream;
import java.util.Properties;
import java.io.IOException;
import java.io.FileInputStream;


public class RecupererProperties {
    private int nombreCases;
    private int nbEssai;
    private String debug;


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
}
