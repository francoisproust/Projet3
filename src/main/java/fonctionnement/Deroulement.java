package fonctionnement;

import jeu.Mastermind;
import jeu.RecherchePlusMoins;

public class Deroulement {
    private String choixJeu;
    private String modeJeu;
    private int nbEssai;
    private int difficulte;
    private int rejeu;

    /**
     * Déroulement du jeu
     */
    public void start() {
        lancerMenu();
        lancerJeu();
        AffichageChoix rejouer = new AffichageChoix();
        rejeu = rejouer.finDePartie();
        while(rejeu == 1 || rejeu == 2){
            switch (rejeu){
                case 1:
                    lancerJeu();
                    rejeu = rejouer.finDePartie();
                    break;
                case 2:
                    lancerMenu();
                    lancerJeu();
                    rejeu = rejouer.finDePartie();
                    break;
            }
        }
        System.out.println("A bientôt");
    }

    /**
     * Methode permettant de récupérer le choix du jeu ainsi que son mode de jeu
     */
    private void lancerMenu(){
        AffichageChoix lancementAffichage = new AffichageChoix();
        choixJeu = lancementAffichage.lancementJeu();
        modeJeu = lancementAffichage.modeJeu();
    }

    /**
     * Lance le jeu souhaité ainsi que le mode demandé
     */
    private void lancerJeu(){
        RecupererProperties properties = new RecupererProperties();
        if (choixJeu.equals("Recherche")){
            properties.configurationRecherche();
            RecherchePlusMoins partie = new RecherchePlusMoins(properties.getNbEssai(), properties.getNombreCases(),modeJeu, properties.getDebug());
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
            properties.configurationMastermind();
            Mastermind partie = new Mastermind(properties.getNbEssai(), properties.getNombreCases(),modeJeu, properties.getDebug());
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
        }
    }
}