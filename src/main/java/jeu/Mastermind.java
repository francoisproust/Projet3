package jeu;

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
    public String reponse(String combinaison, String proposition) {
        bienPlace = 0;
        present = 0;
        for(int i = 0; i<combinaison.length();i++){
            if(combinaison.charAt(i) == proposition.charAt(i)){
                bienPlace = bienPlace + 1;
            }else{
                for(int j=0; j< combinaison.length();j++){
                    if(combinaison.charAt(i) == proposition.charAt(j)){
                        present = present + 1;
                    }
                }
            }
        }
        reponse = present + " présent, " + bienPlace + " bien placé";
        return reponse;
    }

    @Override
    public String proposition(String joueur) {
        String proposition;
        if (joueur.equals("ordinateur")){
            System.out.print("Faites votre proposition de combinaison à trouver par l'ordinateur : ");
        }else if(joueur.equals("")){
            System.out.println("Faites votre proposition de solution : ");
        }
        proposition = sc.next();
        if(this.getChoixNumAlpha().equals("numerique")){
            while(propositionEstNumerique(proposition) == false){
                System.out.println("Votre proposition n'est pas numérique!");
                proposition = sc.next();
            }
        }
        return proposition;
    }

    @Override
    public boolean propositionEstNumerique(String proposition) {
        try {
            Integer.parseInt(proposition);
        } catch (NumberFormatException e){
            return false;
        }
        return true;
    }

    @Override
    public String genererCombinaisonReponseOrdinateur(String reponse, String proposition) {
        return null;
    }

    @Override
    public String nombreAleatoire(String plusMoins, char chiffre) {
        return null;
    }

    @Override
    public String genererCombinaisonOrdinateur(int difficulte) {
        proposition = "";
        switch(choixNumAlpha){
            case "numerique":
                for (int i=0; i<difficulte;i++){
                    proposition = proposition + numerique.charAt(i);
                }
                break;
            case "alphabetique":
                for (int i=0; i<difficulte;i++){
                    proposition = proposition + alphabetique.charAt(i);
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
