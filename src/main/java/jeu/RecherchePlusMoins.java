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
        combinaison = proposition();
        proposition = genererCombinaison(this.getDifficulte());
        while(this.getNbEssai() >0 && egalite == false){
            if(egalite(combinaison,proposition) == true){
                System.out.println("Bravo vous avez trouvé la solution");
                break;
            }else{
                reponse = reponse(combinaison,proposition);
                proposition = genererCombinaisonOrdinateur(reponse);
                setNbEssai(getNbEssai() - 1);
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
            proposition = proposition();
            if(egalite(combinaison,proposition) == true){
                System.out.println("Bravo vous avez trouvé la solution");
                break;
            }else{
                reponse(combinaison,proposition);
                setNbEssai(getNbEssai() - 1);
            }
        }
        if(getNbEssai()== 0){
            System.out.println("Vous avez perdu, la solution était : " + combinaison);
        }
    }

    @Override
    public void duel() {

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

        for(int i = 0; i<combinaison.length();i++){
            if (combinaison.charAt(i) == proposition.charAt(i)){
                reponse = reponse + "=";
            }else if(combinaison.charAt(i) < proposition.charAt(i)){
                reponse = reponse + "-";
            }else if(combinaison.charAt(i) > proposition.charAt(i)){
                reponse = reponse + "+";
            }
        }
        System.out.println("Proposition : " + proposition + " -> Réponse : " + reponse);
        return reponse;
    }

    @Override
    public String proposition() {
        String proposition;
        System.out.print("Faites votre proposition : ");
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
    public String genererCombinaisonOrdinateur(String reponse) {
        for (int i=0; i < reponse.length(); i++){
            if (reponse.charAt(i) == '+'){
                reponse = reponse + nombreAleatoire("Plus",(int) proposition.charAt(i));
            }
        }
        return null;
    }

    @Override
    public String nombreAleatoire(String plusMoins, int chiffre) {
        String nombre = "";
        Random r = new Random();
        switch (plusMoins){
            case "plus":
                nombre = String.valueOf(chiffre + 1) ;
                break;
            case "moins":
                nombre = String.valueOf(chiffre - 1);
                break;
        }
        return nombre;
    }
}
