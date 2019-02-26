package fonctionnement;

import jeu.Mastermind;
import jeu.RecherchePlusMoins;

public class Deroulement {
    private String choixJeu;
    private String modeJeu;
    private int nbEssai;
    private int difficulte;
    private int rejeu;
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

    private void lancerMenu(){
        AffichageChoix lancementAffichage = new AffichageChoix();
        choixJeu = lancementAffichage.lancementJeu();
        modeJeu = lancementAffichage.modeJeu();
    }

    private void lancerJeu(){
        if (choixJeu.equals("Recherche")){
            RecupererProperties properties = new RecupererProperties();
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
            RecupererProperties properties = new RecupererProperties();
            properties.configurationRecherche();
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