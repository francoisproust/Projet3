package jeu;

import org.junit.Test;
import static org.junit.Assert.*;

public class RecherchePlusMoinsTest {
    @Test
   public void propositionSuperieurCombinaison(){
        RecherchePlusMoins jeu = new RecherchePlusMoins(1,1,"");
        assertEquals("-",jeu.reponse("1","3"));
    }
    @Test
    public void propositionInferieurCombinaison(){
        RecherchePlusMoins jeu = new RecherchePlusMoins(1,1,"");
        assertEquals("+",jeu.reponse("7","3"));
    }
    @Test
    public void propositionEgalCombinaison(){
        RecherchePlusMoins jeu = new RecherchePlusMoins(1,1,"");
        assertEquals("=",jeu.reponse("7","7"));
    }
    @Test
    public void propositionPlusieursValeursSupEgalMoins(){
        RecherchePlusMoins jeu = new RecherchePlusMoins(1,1,"");
        assertEquals("-=+",jeu.reponse("258","651"));
    }
    @Test
    public void combinaisonIdentique(){
        RecherchePlusMoins jeu = new RecherchePlusMoins(1,1,"");
        assertEquals(true,jeu.egalite("123","123"));
    }
    @Test
    public void combinaisonDiffente(){
        RecherchePlusMoins jeu = new RecherchePlusMoins(1,1,"");
        assertEquals(false,jeu.egalite("255","123"));
    }
    @Test
    public void propositionNonNumerique(){
        RecherchePlusMoins jeu = new RecherchePlusMoins(1,1,"");
        assertEquals(false,jeu.propositionEstNumerique("TOTO123"));
    }
    @Test
    public void propositionNumerique(){
        RecherchePlusMoins jeu = new RecherchePlusMoins(1,1,"");
        assertEquals(true,jeu.propositionEstNumerique("9876543"));
    }
    @Test
    public void nombreAleatoirePlus(){
        RecherchePlusMoins jeu = new RecherchePlusMoins(1,1,"");
        assertEquals(9,jeu.nombreAleatoire("plus",'5'));
    }

}