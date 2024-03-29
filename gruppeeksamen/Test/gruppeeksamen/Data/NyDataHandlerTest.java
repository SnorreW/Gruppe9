package gruppeeksamen.Data;

import gruppeeksamen.Modell.Utover;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NyDataHandlerTest {
    private NyDataHandler nyDataHandler = new NyDataHandler();

    @Test
    public void girTrueHvisFilstiErUlik() {
        File forventetRiktig = new File("src/main/java/gruppeeksamen/brukere.csv");
        File forventetIkkeRiktig = new File("src/main/java/gruppeeksamen/bruk.csv");

        assertNotEquals(forventetRiktig, forventetIkkeRiktig);
    }

    @Test
    public void hentUtoverDataTrue(){
        Utover enUtover = NyDataHandler.hentUtoverData().get(0);


        assertEquals(enUtover.getBrukenavn(), "admin1");
        assertEquals(enUtover.getPassord(), "admin4");
        assertEquals(enUtover.getNavn(), "Admin");
        assertEquals(enUtover.getEtternavn(), "Admin");
        assertEquals(enUtover.getAlder(), 0);
    }

    @Test
    public void hentUtoverDataFalse() {
        Utover enUtover = NyDataHandler.hentUtoverData().get(0);

        assertNotEquals(enUtover.getBrukenavn(), "Pettersen");
        assertNotEquals(enUtover.getPassord(), "ingenAdminHer");
        assertNotEquals(enUtover.getNavn(), "Petter");
        assertNotEquals(enUtover.getEtternavn(), "Pettersen");
        assertNotEquals(enUtover.getAlder(), 43);
    }

    @Test
    public void SkrivTilCSVFilTrue() {
        ArrayList<Utover> utovere = new ArrayList<>();
        ArrayList<Utover> testliste = new ArrayList<>();
        utovere.add(new Utover("Sandnes", "admin3", "Aleksander", "Sandnes", 20));

        NyDataHandler.skrivTilCSVFil(utovere, new File("src/main/java/gruppeeksamen/brukere.csv"));

        Utover utover = NyDataHandler.hentUtoverData().get(6);

        assertTrue(utovere.size() > testliste.size());

        assertEquals(utover.getBrukenavn(), "Sandnes");
        assertEquals(utover.getPassord(), "admin3");
        assertEquals(utover.getNavn(), "Aleksander");
        assertEquals(utover.getEtternavn(), "Sandnes");
        assertEquals(utover.getAlder(), 20);
    }
}
