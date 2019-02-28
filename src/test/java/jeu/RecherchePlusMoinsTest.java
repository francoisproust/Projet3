package jeu;

import org.junit.Test;
import static org.junit.Assert.*;

public class RecherchePlusMoinsTest {
    @Test
    public void propositionSuperieurCombinaison(){
        RecherchePlusMoins jeu = new RecherchePlusMoins(1,1,"","");
        assertEquals("-",jeu.reponse("1","3"));
    }
    @Test
    public void propositionInferieurCombinaison(){
        RecherchePlusMoins jeu = new RecherchePlusMoins(1,1,"","");
        assertEquals("+",jeu.reponse("7","3"));
    }
    @Test
    public void propositionEgalCombinaison(){
        RecherchePlusMoins jeu = new RecherchePlusMoins(1,1,"","");
        assertEquals("=",jeu.reponse("7","7"));
    }
    @Test
    public void propositionPlusieursValeursSupEgalMoins(){
        RecherchePlusMoins jeu = new RecherchePlusMoins(1,1,"","");
        assertEquals("-=+",jeu.reponse("258","651"));
    }
    @Test
    public void combinaisonIdentique(){
        RecherchePlusMoins jeu = new RecherchePlusMoins(1,1,"","");
        assertEquals(true,jeu.egalite("123","123"));
    }
    @Test
    public void combinaisonDifferente(){
        RecherchePlusMoins jeu = new RecherchePlusMoins(1,1,"","");
        assertEquals(false,jeu.egalite("255","123"));
    }
    @Test
    public void propositionNonNumerique(){
        RecherchePlusMoins jeu = new RecherchePlusMoins(1,1,"","");
        assertEquals(false,jeu.propositionEstNumerique("TOTO123"));
    }
    @Test
    public void propositionNumerique(){
        RecherchePlusMoins jeu = new RecherchePlusMoins(1,1,"","");
        assertEquals(true,jeu.propositionEstNumerique("9876543"));
    }
    @Test
    public void genererPremierePropositionOrdinateur(){
        RecherchePlusMoins jeu = new RecherchePlusMoins(1,3,"","");
        assertEquals("555",jeu.genererCombinaisonOrdinateur(3));
    }
    @Test
    public void nombreAleatoirePlus(){
        RecherchePlusMoins jeu = new RecherchePlusMoins(1,1,"","");
        assertEquals("6",jeu.genererCombinaisonReponseOrdinateur("+","5"));
    }
    @Test
    public void nombreAleatoireMoins(){
        RecherchePlusMoins jeu = new RecherchePlusMoins(1,1,"","");
        assertEquals("4",jeu.genererCombinaisonReponseOrdinateur("-","5"));
    }
    @Test
    public void nombreAleatoireEgal(){
        RecherchePlusMoins jeu = new RecherchePlusMoins(1,1,"","");
        assertEquals("6",jeu.genererCombinaisonReponseOrdinateur("=","6"));
    }
    @Test
    public void reponseOrdinateur(){
        RecherchePlusMoins jeu = new RecherchePlusMoins(1,3,"","");
        assertEquals("664",jeu.genererCombinaisonReponseOrdinateur("+=-","565"));
    }
}
