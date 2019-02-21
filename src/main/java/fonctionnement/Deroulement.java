package fonctionnement;

import jeu.Mastermind;
import jeu.RecherchePlusMoins;

public class Deroulement {
    private String choixJeu;
    private String modeJeu;
    private int nbEssai;
    private int difficulte;
    public void start() {
        lancerMenu();
        creerJeu();

    }

    private void lancerMenu(){
        Menu nouveau = new Menu();
        choixJeu = nouveau.lancementJeu();
        modeJeu = nouveau.modeJeu();
    }

    private void creerJeu(){
        if (choixJeu.equals("Recherche")){
            RecherchePlusMoins partie = new RecherchePlusMoins(10, 2);
            switch (modeJeu){
                case "challenger":
                    partie.challengeur();
                    break;
                case "defenseur":
                    partie.defenseur();
                    break;
                case "duel":
                    partie.duel();
                    break;
            }
        }else {
            Mastermind partie = new Mastermind(nbEssai, difficulte);
            switch (modeJeu){
                case "challengeur":
                    partie.challengeur();
                    break;
                case "defenseur":
                    partie.defenseur();
                    break;
                case "duel":
                    partie.duel();
                    break;
            }
        }
    }
}