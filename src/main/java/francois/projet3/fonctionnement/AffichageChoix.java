package francois.projet3.fonctionnement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

class AffichageChoix {

    private final Scanner sc = new Scanner(System.in);
    private String mode;
    private static final Logger logger = LogManager.getLogger(AffichageChoix.class);
    /**
     * Méthode permettant la sélection du jeu souhaité
     * @return le nom du jeu
     */
    public String lancementJeu(){
        int saisie;
        System.out.println("Veuillez sélectionner le jeu souhaité");
        System.out.println("1 : Recherche +/-");
        System.out.println("2 : Mastermind");
        saisie = sc.nextInt();
        logger.debug("Lancement de la méthode lancementJeu()");
        logger.info("L'utilisateur choisi le jeu entre Recherche +/- et Mastermind");
        while (saisie != 1 && saisie != 2){
            System.out.println("Le choix ne correspond pas à ce qui est proposé");
            saisie = sc.nextInt();
        }
        String jeu;
        if (saisie == 1){
            jeu = Outils.RECHERCHE;
            logger.info("L'utilisateur a choisi le jeu Recherche +/-");
        }else{
            jeu = Outils.MASTERMIND;
            logger.info("L'utilisateur a choisi le jeu Mastermind");
        }
        return jeu;
    }

    /**
     * Méthode déterminant le mode du jeu
     * @return mode de jeu sélectionné
     */
    public String modeJeu(){
        int saisie;
        System.out.println("Veuillez choisir le mode de jeu:");
        System.out.println("1 : challengeur");
        System.out.println("2 : defenseur");
        System.out.println("3 : duel");
        saisie = recupererSaisie();
        logger.debug("Lancement de la méthode modeJeu()");
        logger.info("Choix du mode de jeu Challengeur / Defenseur / Duel");
        switch (saisie){
            case 1:
                mode = Outils.CHALLENGEUR;
                logger.info("L'utilisateur a choisi le mode challengeur");
                break;
            case 2:
                mode = Outils.DEFENSEUR;
                logger.info("L'utilisateur a choisi le mode defenseur");
                break;
            case 3:
                mode = Outils.DUEL;
                logger.info("L'utilisateur a choisi le mode duel");
                break;
        }
        return mode;
    }

    /**
     * Merthode permettant à la fin de la partie de savoir ce que nous faisons
     * @return le choix de l'utilisateur
     */
    public int finDePartie(){
        int choix;
        System.out.println("Que souhaitez-vous faire?");
        System.out.println("1 : Rejouer au même jeu");
        System.out.println("2 : Lancer un autre jeu");
        System.out.println("3 : Quitter l'application");
        choix = recupererSaisie();
        logger.debug("Lancement de la méthode finDePartie()");
        logger.info("En fin de partie, on invite le joueur a choisir ce qu'il souhaite faire Rejouer au meme jeu(1) / Lancer un autre jeu(2) / quitter l'application(3) " + choix);
        return choix;
    }

    /**
     * récupère la saisie au clavier de l'utilisateur
     * @return le choix de saisie
     */
    private int recupererSaisie(){
        int saisie;
        saisie = sc.nextInt();
        while (saisie != 1 && saisie != 2 && saisie != 3){
            System.out.println("Le choix ne correspond pas à ce qui est proposé");
            saisie = sc.nextInt();
            logger.debug("Lancement de la méthode recupererSaisie()");
            logger.debug("L'utilisateur a fait un choix non disponible");
        }
        return saisie;
    }
}