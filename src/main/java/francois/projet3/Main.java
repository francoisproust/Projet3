package francois.projet3;

import francois.projet3.fonctionnement.Deroulement;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
    public static void main(String[] args) {
      System.out.println("Démarrage du jeu");
      Deroulement demarrer = new Deroulement();
      demarrer.start();
    }
}