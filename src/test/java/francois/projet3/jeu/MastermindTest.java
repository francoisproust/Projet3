package francois.projet3.jeu;


import org.junit.Test;
import static org.junit.Assert.*;

public class MastermindTest {

    @Test
    public void CombinaisonUnBienPlaceUnPresentNumerique(){
        Mastermind jeu = new Mastermind(2,3,"","");
        jeu.setChoixNumAlpha("numerique");
        jeu.setNombreUtilisable(4);
        jeu.setNumerique("0123456789");
        assertEquals("1 présent, 1 bien placé",jeu.reponse("563","538","humain"));
    }
    @Test
    public void CombinaisonUnBienPlaceUnPresentAlphabetique(){
        Mastermind jeu = new Mastermind(2,3,"","");
        jeu.setChoixNumAlpha("alphabetique");
        jeu.setNombreUtilisable(4);
        jeu.setAlphabetique("ABCDEFGHIJ");
        assertEquals("1 présent, 1 bien placé",jeu.reponse("ABC","ACD","humain"));
    }
    @Test
    public void combinaisonIdentiqueAlphabetique(){
        Mastermind jeu = new Mastermind(2,3,"","");
        jeu.setChoixNumAlpha("alphabetique");
        jeu.setNombreUtilisable(4);
        jeu.setAlphabetique("ABCDEFGHIJ");
        assertTrue(jeu.egalite("ABC", "ABC"));
    }
    @Test
    public void combinaisonIdentiqueNumerique(){
        Mastermind jeu = new Mastermind(2,3,"","");
        jeu.setChoixNumAlpha("numerique");
        jeu.setNombreUtilisable(4);
        jeu.setNumerique("0123456789");
        assertTrue(jeu.egalite("123", "123"));
    }
    @Test
    public void combinaisonDifferenteNumerique(){
        Mastermind jeu = new Mastermind(2,3,"","");
        jeu.setChoixNumAlpha("numerique");
        jeu.setNombreUtilisable(4);
        jeu.setNumerique("0123456789");
        assertFalse(jeu.egalite("255", "123"));
    }
    @Test
    public void combinaisonDifferenteAlphabetique(){
        Mastermind jeu = new Mastermind(2,3,"","");
        jeu.setChoixNumAlpha("alphabetique");
        jeu.setNombreUtilisable(4);
        jeu.setAlphabetique("ABCDEFGHIJ");
        assertFalse(jeu.egalite("ABC", "CBA"));
    }
    @Test
    public void genererCombinaisonNumerique(){
        Mastermind jeu = new Mastermind(2,3,"","");
        jeu.setChoixNumAlpha("numerique");
        jeu.setNombreUtilisable(4);
        jeu.setNumerique("0123456789");
        assertEquals("222",jeu.genererCombinaisonOrdinateur(3));
    }
    @Test
    public void genererCombinaisonAlphabetique(){
        Mastermind jeu = new Mastermind(2,3,"","");
        jeu.setChoixNumAlpha("alphabetique");
        jeu.setNombreUtilisable(4);
        jeu.setAlphabetique("ABCDEFGHIJ");
        assertEquals("CCC",jeu.genererCombinaisonOrdinateur(3));
    }
}
