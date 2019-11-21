package gruppeeksamen.Controller;

import gruppeeksamen.Data.DataHandler;
import gruppeeksamen.Modell.Arrangementer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ArrangementControllerTest {
    ArrayList utovere = new ArrayList();

    ArrayList utovere1 = new ArrayList();

    //Arrangementer arrangement1 = new Arrangementer("Skitur");

    private ArrangementController arrangementController = new ArrangementController();
    private MeldPaaUtoverController meldPaaUtoverController = new MeldPaaUtoverController();

    @Test
    private void sjekkHjelpOgfylleListe(){

        String arrangementSomErValgt = "Tistacupen";
        ArrayList list = new ArrayList();
        list.add("Tistacupen;1;Ole;2019.10.17;Ski");
        list.add("Skjeberg cupen;1;Petter Nor;2019.10.28;Ski");
        //arrangementController.hjelpTilOgFylleListe(list,arrangementSomErValgt);
    }

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
    @Test
    public void TestHjelpTilogFylleListeMetode() {
        arrangementController.hjelpTilOgFylleListe(utovere, "Skitur");
        utovere.add("Mateusz");
        arrangementController.hjelpTilOgFylleListe(utovere, "Skitur");

        assertEquals(utovere, utovere);
    }
    @Test
    public void TestHjelpTilOgFylleListeFalse() {

        arrangementController.hjelpTilOgFylleListe(utovere, "Skitur");
        utovere.add("Mateusz");
        arrangementController.hjelpTilOgFylleListe(utovere1, "Skitur");

        assertFalse(utovere == utovere1, "fil stilen er feil");
    }
    @Test
    public void TestHjelpTilOgFylleListeTrue() {
        arrangementController.hjelpTilOgFylleListe(utovere, "Skitur");
        utovere.add("Mateusz");
        arrangementController.hjelpTilOgFylleListe(utovere1, "Skitur");
        utovere1.add("Mateusz");

        assertEquals(utovere, utovere1);
    }

    //Tester at CSV fil er riktig
    @Test
    public void TestCSVstringFalse() {

        String filStil = "src/main/java/gruppeeksamen/arrangementer.csv";

        String feilFilStil = "src/main/java/gruppeeksamen/arrangementer.cv";

        assertFalse(filStil == feilFilStil, "fil stilen er feil");
    }
    @Test
    public void TestCSVstringTrue() {

        String filStil = "src/main/java/gruppeeksamen/arrangementer.csv";

        String feilFilStil = "src/main/java/gruppeeksamen/arrangementer.csv";

        assertTrue(filStil == feilFilStil, "fil stilen er riktig");
    }




}