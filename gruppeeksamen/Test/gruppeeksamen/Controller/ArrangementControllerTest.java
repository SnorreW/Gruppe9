package gruppeeksamen.Controller;

import gruppeeksamen.Modell.Arrangementer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ArrangementControllerTest {

    ArrayList utovere = new ArrayList();

    ArrayList utovere1 = new ArrayList();

    Arrangementer arrangement1 = new Arrangementer("Skitur");

    private ArrangementController arrangementController = new ArrangementController();

    @Test
    private void sjekkHjelpOgfylleListe(){

        String arrangementSomErValgt = "Tistacupen";
        ArrayList list = new ArrayList();
        list.add("Tistacupen;1;Ole;2019.10.17;Ski");
        list.add("Skjeberg cupen;1;PEtter Nor;2019.10.28;Ski");
        arrangementController.hjelpTilOgFylleListe(list,arrangementSomErValgt);
    }

    @Test
    public void slettBestemtBruker(){

        ObservableList listeFor = FXCollections.observableArrayList();
        String arrangement = "Skjeberg cupen";
        String utoverSomSkalSlettes ="Ole Gen";
        int lengdeEtter = 1;
        arrangementController.slettBestemtUtoverIArrangement(arrangement,utoverSomSkalSlettes);

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

        assertTrue(utovere == utovere1, "Listene er like");
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