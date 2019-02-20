package jeu;

import java.util.ArrayList;

public class RecherchePlusMoins extends Jeu{
    String propositionString;
    boolean trouve = false;
    int nbEssai =3;
    @Override
    public void challengeur() {
        ArrayList nombreGenere = new ArrayList();
        ArrayList proposition = new ArrayList();
        nombreGenere = genererNombre(4);
        while(trouve == false && nbEssai >=0){
            proposition = recupererProposition(4);
            comparaison(nombreGenere,proposition,4);
            nbEssai = nbEssai - 1;
        }
    }

    @Override
    public void defenseur() {

    }

    @Override
    public void duel() {

    }

    @Override
    public boolean comparaison(ArrayList<Integer> combinaison, ArrayList<Integer> combinaisonProposee, int difficulte) {
        String reponse = "";
        String combinaisonProposeString = "";
        String combinaisonString = "";
        for (int i = 0 ; i < difficulte; i++){
            if(combinaison.get(i) == combinaisonProposee.get(i)){
                reponse = reponse + "=";
                combinaisonProposeString = combinaisonProposeString + combinaisonProposee.get(i);
                combinaisonString = combinaisonString +combinaison.get(i);
            }else if (combinaison.get(i) > combinaisonProposee.get(i)){
                reponse = reponse + "+";
                combinaisonString = combinaisonString +combinaison.get(i);
                combinaisonProposeString = combinaisonProposeString + combinaisonProposee.get(i);
            }else if (combinaison.get(i)< combinaisonProposee.get(i)){
                reponse = reponse + "-";
                combinaisonString = combinaisonString +combinaison.get(i);
                combinaisonProposeString = combinaisonProposeString + combinaisonProposee.get(i);
            }
        }
        System.out.println("Proposition : " + combinaisonProposeString +" -> Réponse : " + reponse);
        if (combinaisonProposeString.equals(combinaisonString)){
            trouve = true;
        }else{
            trouve = false;
        }
        return trouve;
    }
}
