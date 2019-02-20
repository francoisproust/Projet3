package fonctionnement;

public class Deroulement {
    String choixJeu;
    String modeJeu;
    public void start() {
        Menu nouveau = new Menu();
        choixJeu = nouveau.lancementJeu();
        modeJeu = nouveau.modeJeu();
    }
}