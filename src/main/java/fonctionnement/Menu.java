package fonctionnement;

import java.util.Scanner;

public class Menu {

    Scanner sc = new Scanner(System.in);
    private String jeu;
    private String mode;

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
        while (saisie != 1 && saisie != 2){
            System.out.println("Le choix ne correspond pas à ce qui est proposé");
            saisie = sc.nextInt();
        }
        if (saisie == 1){
            jeu = "Recherche";
        }else{
            jeu = "Mastermind";
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
        switch (saisie){
            case 1:
                mode = "challenger";
                break;
            case 2:
                mode = "defenseur";
                break;
            case 3:
                mode = "duel";
                break;
        }
        return mode;
    }

    public int finDePartie(){
        int choix;
        System.out.println("Que souhaitez-vous faire?");
        System.out.println("1 : Rejouer au même jeu");
        System.out.println("2 : Lancer un autre jeu");
        System.out.println("3 : Quitter l'application");
        choix = recupererSaisie();
        return choix;
    }

    private int recupererSaisie(){
        int saisie;
        saisie = sc.nextInt();
        while (saisie != 1 && saisie != 2 && saisie != 3){
            System.out.println("Le choix ne correspond pas à ce qui est proposé");
            saisie = sc.nextInt();
        }
        return saisie;
    }
}