package jeu;

import java.util.Random;
import java.util.Scanner;

public class RecherchePlusMoins extends Jeu{
    Scanner sc = new Scanner(System.in);
    String combinaison;
    String proposition;
    boolean egalite;
    String reponse = "";
    public RecherchePlusMoins(int nbEssai, int difficulte, String modeJeu) {
        super(nbEssai, difficulte,modeJeu);
    }

    @Override
    public void defenseur() {
        combinaison = proposition("ordinateur");
        proposition = genererCombinaisonOrdinateur(this.getDifficulte());
        while(this.getNbEssai() >0 && egalite == false){
            if(egalite(combinaison,proposition) == true){
                System.out.println("Bravo vous avez trouvé la solution");
                break;
            }else{
                reponse = reponse(combinaison,proposition);
                proposition = genererCombinaisonReponseOrdinateur(reponse, proposition);
                setNbEssai(getNbEssai() - 1);
                reponse = "";
            }
        }
        if(getNbEssai()== 0){
            System.out.println("L'ordinateur n'as pas trouvé la bonne combinaison");
        }
    }

    @Override
    public void challengeur() {
        combinaison = genererCombinaison(this.getDifficulte());
        while(this.getNbEssai() >0 && egalite == false){
            proposition = proposition("");
            if(egalite(combinaison,proposition) == true){
                System.out.println("Bravo vous avez trouvé la solution");
                break;
            }else{
                reponse(combinaison,proposition);
                System.out.println("Proposition : " + proposition + " -> Réponse : " + reponse);
                setNbEssai(getNbEssai() - 1);
            }
        }
        if(getNbEssai()== 0){
            System.out.println("Vous avez perdu, la solution était : " + combinaison);
        }
    }

    @Override
    public void duel() {
        String combinaisonPourJoueur = genererCombinaison(this.getDifficulte());
        String combinaisonPourOrdinateur = proposition("ordinateur");
        String propositionOrdinateur = genererCombinaisonOrdinateur(this.getDifficulte());
        while(this.getNbEssai() >0){
            // partie ou le joueur cherche la combinaison
            String propositionJoueur = proposition("");
            if(egalite(combinaisonPourJoueur,propositionJoueur) == true){
                System.out.println("Bravo vous avez trouvé la solution");
                break;
            }else{
                String reponseJoueur = reponse(combinaisonPourJoueur,propositionJoueur);
                System.out.println("Proposition : " + propositionJoueur + " -> Réponse : " + reponseJoueur);
            }
            // partie ou c est l'ordinateur qui cherche la combinaison
            if(egalite(combinaisonPourOrdinateur,propositionOrdinateur) == true){
                System.out.println("L'ordinateur a trouvé la solution");
                break;
            }else{
                String reponseOrdinateur = reponse(combinaisonPourOrdinateur,propositionOrdinateur);
                propositionOrdinateur = genererCombinaisonReponseOrdinateur(reponseOrdinateur, proposition);
                reponseOrdinateur = "";
            }
            setNbEssai(getNbEssai() - 1);
        }
        if(getNbEssai()== 0){
            System.out.println("Vous avez perdu tous les deux");
            System.out.println("La solution pour le joueur etait : " + combinaisonPourJoueur);
        }
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
    public String reponse(String combinaison, String proposition) {
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
        while(propositionEstNumerique(proposition) == false){
            System.out.println("Votre proposition n'est pas numérique!");
            proposition = sc.next();
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
    }

    @Override
    public String nombreAleatoire(String plusMoins, char chiffre) {
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
}
