package jeu;

import com.sun.deploy.util.StringUtils;
import fonctionnement.RecupererProperties;

import java.util.Scanner;

public class Mastermind extends Jeu{
    Scanner sc = new Scanner(System.in);
    String combinaison;
    String proposition;
    boolean egalite;
    String reponse = "";
    private int nombreUtilisable;
    private String numerique;
    private String alphabetique;
    private String choixNumAlpha;
    private int bienPlace = 0;
    private int present = 0;
    String plageUtilisation;

    public Mastermind(int nbEssai, int difficulte, String modeJeu, String debug) {
        super(nbEssai, difficulte, modeJeu, debug);
    }

    @Override
    public void defenseur() {
        recupererPropertiesSpecifique();
        combinaison = proposition("");
        deroulementJeuModeDefenseur(combinaison);
    }

    @Override
    public void challengeur() {
        recupererPropertiesSpecifique();
        deroulementJeuModeChallengeur(combinaison);
    }

    @Override
    public void duel() {
        recupererPropertiesSpecifique();
        deroulementJeuModeDuel();
    }

    @Override
    public String genererCombinaison(int difficulte) {
        String combinaison = "";
        if (this.getChoixNumAlpha().equals("numerique")) {
            int nombreUtilisable = this.nombreUtilisable;
            for (int i = 0; i < difficulte; i++) {
                int nombre = (int) (Math.random() * (nombreUtilisable - 0));
                combinaison = combinaison + nombre;
            }
        }else if(this.getChoixNumAlpha().equals("alphabetique")) {
            int nombreUtilisable = this.nombreUtilisable;
            for (int i = 0; i < difficulte; i++) {
                int nombre = (int) (Math.random() * (nombreUtilisable - 0));
                combinaison = combinaison + this.alphabetique.charAt(nombre);
            }
        }
        return combinaison;
    }

    @Override
    public boolean egalite(String combinaison, String proposition) {
        if(combinaison.equals(proposition)){
            return true;
        }
        return false;
    }

    @Override
    public String reponse(String combinaison, String proposition,String joueur) {
        bienPlace = 0;
        present = 0;
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
        }
        return reponse;
    }

    private int positionCaractere(char caractere){
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
        String proposition;
        if (joueur.equals("ordinateur")){
            System.out.print("Faites votre proposition de combinaison à trouver par l'ordinateur sur " + this.getDifficulte() + ": ");
        }else if(joueur.equals("")){
            System.out.println("Faites votre proposition de solution sur " + this.getDifficulte() + " caractères " + choixNumAlpha + " : ");
        }
        proposition = sc.next();
        while(proposition.length() != this.getDifficulte()){
            System.out.println("Votre proposition est trop courte ou trop longue, elle doit être sur : " + this.getDifficulte()  + " caractères");
            proposition = sc.next();
        }
        if(this.getChoixNumAlpha().equals("numerique")){
            //boolean estNumerique = propositionEstNumerique(proposition);
            while(proposition.matches("-?[0-9]+") == false){
                System.out.println("Votre proposition n'est pas numérique!");
                proposition = sc.next();
            }
        }
        return proposition;
    }

    @Override
    public String genererCombinaisonReponseOrdinateur(String reponse, String proposition) {
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
        return propositionReponse;
    }

    public String nombreAleatoire(String plusMoins, char chiffre) {
        return null;
    }

    @Override
    public String genererCombinaisonOrdinateur(int difficulte) {
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
        return proposition;
    }

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

    public String getChoixNumAlpha() {
        return choixNumAlpha;
    }

    public void setChoixNumAlpha(String choixNumAlpha) {
        this.choixNumAlpha = choixNumAlpha;
    }
}