package jeu;

import java.util.Scanner;

public class RecherchePlusMoins extends Jeu{
    Scanner sc = new Scanner(System.in);
    String combinaison;
    String proposition;
    boolean egalite;

    public RecherchePlusMoins(int nbEssai, int difficulte) {
        super(nbEssai, difficulte);
    }

    @Override
    public void defenseur() {


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
        String reponse = "";
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
}
