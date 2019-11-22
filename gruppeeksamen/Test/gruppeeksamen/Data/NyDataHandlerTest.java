package gruppeeksamen.Data;

import gruppeeksamen.Modell.Utover;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NyDataHandlerTest {
    @Test
    public void girTrueHvisFilstiErUlik() {
        File forventetRiktig = new File("src/main/java/gruppeeksamen/brukere.csv");
        File forventetIkkeRiktig = new File("src/main/java/gruppeeksamen/bruk.csv");

        assertNotEquals(forventetRiktig, forventetIkkeRiktig);
    }

    //Krav: 10.1
    @Test
    public void hentUtoverDataTrue(){
        Utover enUtover = NyDataHandler.hentUtoverData().get(0);

        assertEquals(enUtover.getBrukenavn(), "admin1");
        assertEquals(enUtover.getPassord(), "admin4");
        assertEquals(enUtover.getNavn(), "Admin");
        assertEquals(enUtover.getEtternavn(), "Admin");
        assertEquals(enUtover.getAlder(), 0);
    }

    //Krav: 10.1
    @Test
    public void hentUtoverDataFalse() {
        Utover enUtover = NyDataHandler.hentUtoverData().get(0);

        assertNotEquals(enUtover.getBrukenavn(), "Pettersen");
        assertNotEquals(enUtover.getPassord(), "ingenAdminHer");
        assertNotEquals(enUtover.getNavn(), "Petter");
        assertNotEquals(enUtover.getEtternavn(), "Pettersen");
        assertNotEquals(enUtover.getAlder(), 43);
    }

    //Krav: 9.2
    @Test
    public void SkrivTilCSVFilTrue() throws IOException {
        ArrayList<Utover> utovere = new ArrayList<>();
        ArrayList<Utover> testliste = new ArrayList<>();
        utovere.add(new Utover("Sandnes", "admin3", "Aleksander", "Sandnes", 20));

        NyDataHandler.skrivTilCSVFil(utovere, new File("src/main/java/gruppeeksamen/brukere.csv"));

        assertTrue(utovere.size() > testliste.size());

        Utover utover = NyDataHandler.hentUtoverData().get(NyDataHandler.hentUtoverData().size() - 1);

        assertEquals(utover.getBrukenavn(), "Sandnes");
        assertEquals(utover.getPassord(), "admin3");
        assertEquals(utover.getNavn(), "Aleksander");
        assertEquals(utover.getEtternavn(), "Sandnes");
        assertEquals(utover.getAlder(), 20);

        //fjernet linjen som er lagt til
        RandomAccessFile f = new RandomAccessFile("src/main/java/gruppeeksamen/brukere.csv", "rw");
        long length = f.length() - 1;
        byte b;
        do {
            length -= 1;
            f.seek(length);
            b = f.readByte();
        } while(b != 10);
        f.setLength(length+1);
        f.close();
    }
}
