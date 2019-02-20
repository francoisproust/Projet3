package fonctionnement;

import jeu.Jeu;
import jeu.Mastermind;
import jeu.RecherchePlusMoins;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Deroulement {
    String choixJeu;
    String modeJeu;
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
            RecherchePlusMoins partie = new RecherchePlusMoins();
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
            Mastermind partie = new Mastermind();
            switch (modeJeu){
                case "challengeur":

                    break;
                case "defenseur":

                    break;
                case "duel":

                    break;
            }
        }
    }
}