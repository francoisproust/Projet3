package fonctionnement;

import java.io.InputStream;
import java.util.Properties;
import java.io.IOException;
import java.io.FileInputStream;


public class RecupererProperties {
    private int nombreCases;
    private int nbEssai;


    public void configurationRecherche(){
        Properties prop = new Properties();
        InputStream input = null;
        try{
            input = new FileInputStream("src/main/java/ressources/config.properties");
            prop.load(input);
            this.nombreCases = Integer.valueOf(prop.getProperty("recherche.nombreCases"));
            this.nbEssai = Integer.valueOf(prop.getProperty("recherche.nbEssais"));
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
}
