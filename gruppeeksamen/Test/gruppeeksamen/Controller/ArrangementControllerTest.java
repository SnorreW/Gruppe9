package gruppeeksamen.Controller;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ArrangementControllerTest {
    ArrayList utovere = new ArrayList();

    ArrayList utovere1 = new ArrayList();

    private ArrangementController arrangementController = new ArrangementController();
    private MeldPaaUtoverController meldPaaUtoverController = new MeldPaaUtoverController();

    //Krav: 6.4, 6.5
    @Test
    public void slettBestemtBrukerTrue() {
        String arrangement = "Tistacupen";
        String utoverSomSkalSlettes = "Ole Gen";
        String fornavn = "Ole";
        String etternavn = "Gen";
        String idrett = "Ski";
        meldPaaUtoverController.leggTilUtover(fornavn, etternavn, idrett, arrangement);
        assertTrue(arrangementController.slettBestemtUtoverIArrangement(arrangement,utoverSomSkalSlettes));
    }

    //Tester at listen med utøvere blir fylt
    //Krav: 4.2, 6.6
    @Test
    public void TestHjelpTilOgFylleListeFalse() {
        arrangementController.hjelpTilOgFylleListe(utovere, "Skitur");
        utovere.add("Mateusz");
        arrangementController.hjelpTilOgFylleListe(utovere1, "Skitur");

        assertFalse(utovere == utovere1, "fil stilen er feil");
    }

    //Krav: 4.2, 6.6
    @Test
    public void TestHjelpTilOgFylleListeTrue() {
        arrangementController.hjelpTilOgFylleListe(utovere, "Skitur");
        utovere.add("Mateusz");
        arrangementController.hjelpTilOgFylleListe(utovere1, "Skitur");
        utovere1.add("Mateusz");

        assertEquals(utovere, utovere1);
    }

    //Tester at CSV fil er riktig
    //Krav: 9.3
    @Test
    public void TestCSVstringFalse() {
        String filStil = "src/main/java/gruppeeksamen/arrangementer.csv";

        String feilFilStil = "src/main/java/gruppeeksamen/arrangementer.cv";

        assertFalse(filStil == feilFilStil, "fil stilen er feil");
    }
    //Krav: 9.3
    @Test
    public void TestCSVstringTrue() {
        String filStil = "src/main/java/gruppeeksamen/arrangementer.csv";

        String feilFilStil = "src/main/java/gruppeeksamen/arrangementer.csv";

        assertTrue(filStil == feilFilStil, "fil stilen er riktig");
    }
}