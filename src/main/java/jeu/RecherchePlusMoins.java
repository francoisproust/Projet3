package jeu;

import java.util.Random;
import java.util.Scanner;

public class RecherchePlusMoins extends Jeu{
    Scanner sc = new Scanner(System.in);
    String combinaison;
    String proposition;
    boolean egalite;
    String reponse = "";
    public RecherchePlusMoins(int nbEssai, int difficulte, String modeJeu,String debug) {
        super(nbEssai, difficulte,modeJeu, debug);
    }

    @Override
    public void defenseur() {
        combinaison = proposition("ordinateur");
        // proposition = genererCombinaisonOrdinateur(this.getDifficulte());
        deroulementJeuModeDefenseur(combinaison);
    }

    @Override
    public void challengeur() {
        deroulementJeuModeChallengeur(combinaison);
    }

    @Override
    public void duel() {

        deroulementJeuModeDuel();
    }

    @Override
    public String genererCombinaison(int difficulte) {
        String combinaison = "";
        for(int i=0;i<difficulte;i++){
            int nombre = (int) (Math.random()*(10-0));
            combinaison =   combinaison + nombre;
        }
        return combinaison;
    }

    @Override
    public boolean egalite(String combinaison, String proposition) {
        boolean egalite;
        if (combinaison.equals(proposition)){
            return egalite = true;
        }else{
            return egalite = false;
        }
    }

    @Override
    public String reponse(String combinaison, String proposition,String joueur) {
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
        while(proposition.matches("-?[0-9]+") == false){
            System.out.println("Votre proposition n'est pas numérique!");
            proposition = sc.next();
        }
        return proposition;
    }

    private String nombreAleatoire(String plusMoins, char chiffre) {
        int nombre = 0;
        String chiffreString;
        chiffreString = Character.toString(chiffre);
        switch (plusMoins){
            case "plus":
                nombre = Integer.parseInt(chiffreString )+ 1 ;
                break;
            case "moins":
                nombre = Integer.parseInt(chiffreString)- 1;
                break;
        }
        return String.valueOf(nombre);
    }

    @Override
    public String genererCombinaisonOrdinateur(int difficulte) {
        proposition = "";
        for(int i = 0; i<difficulte; i++){
            proposition = proposition + "5";
        }
        return proposition;
    }

    @Override
    public String genererCombinaisonReponseOrdinateur (String reponse, String proposition){
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
        return nouvelleReponse;
    };
}