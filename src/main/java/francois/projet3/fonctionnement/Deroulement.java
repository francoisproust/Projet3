package francois.projet3.fonctionnement;

import francois.projet3.jeu.Mastermind;
import francois.projet3.jeu.RecherchePlusMoins;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Deroulement {
    private String choixJeu;
    private String modeJeu;
    private int nbEssai;
    private int difficulte;

    private static final Logger logger = LogManager.getLogger(Deroulement.class);

    /**
     * Déroulement du jeu
     */
    public void start() {
        logger.debug("Lancement de la methode start()");
        lancerMenu();
        lancerJeu();
        AffichageChoix rejouer = new AffichageChoix();
        int rejeu = rejouer.finDePartie();
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
        logger.debug("Lancement de la methode lancerMenu()");
        AffichageChoix lancementAffichage = new AffichageChoix();
        choixJeu = lancementAffichage.lancementJeu();
        modeJeu = lancementAffichage.modeJeu();
    }

    /**
     * Lance le jeu souhaité ainsi que le mode demandé
     */
    private void lancerJeu(){
        logger.debug("Lancement de la methode lancerJeu()");
        RecupererProperties properties = new RecupererProperties();
        if (choixJeu.equals(Outils.RECHERCHE)) {
            properties.configurationRecherche();
            RecherchePlusMoins partie = new RecherchePlusMoins(properties.getNbEssai(), properties.getNombreCases(), modeJeu, properties.getDebug());
            switch (modeJeu){
                case "challengeur":
                    logger.info("Lancement du jeu " + Outils.RECHERCHE + " en mode " + modeJeu );
                    partie.challengeur();
                    break;
                case "defenseur":
                    logger.info("Lancement du jeu " + Outils.RECHERCHE + " en mode " + modeJeu );
                    partie.defenseur();
                    break;
                case "duel":
                    logger.info("Lancement du jeu " + Outils.RECHERCHE + " en mode " + modeJeu );
                    partie.duel();
                    break;
            }
        }else if(choixJeu.equals(Outils.MASTERMIND)) {
            properties.configurationMastermind();
            Mastermind partie = new Mastermind(properties.getNbEssai(), properties.getNombreCases(),modeJeu, properties.getDebug());
            switch (modeJeu){
                case "challengeur":
                    logger.info("Lancement du jeu " + Outils.MASTERMIND + " en mode " + modeJeu );
                    partie.challengeur();
                    break;
                case "defenseur":
                    logger.info("Lancement du jeu " + Outils.MASTERMIND + " en mode " + modeJeu );
                    partie.defenseur();
                    break;
                case "duel":
                    logger.info("Lancement du jeu " + Outils.MASTERMIND + " en mode " + modeJeu );
                    partie.duel();
                    break;
            }
        }
    }
}