package gruppeeksamen.Modell;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class AktivitetsTest {
    @Test
    @DisplayName("tid")
    public void getTid(){

        Aktivitet aktivitet = new Aktivitet("Løping",1600, "1/1/2019","Jon");
        assertEquals(1600, aktivitet.getTid(), "tester tid");
    }
    @Test
    @DisplayName("Aktivitet")
    public void getNavnPaaAktivitet(){

        Aktivitet aktivitet = new Aktivitet("Løping",1600, "1/1/2019","Jon");
        assertEquals("Løping", aktivitet.getTid(), "tester navn");
    }
    @Test
    @DisplayName("dato")
    public void getDato(){

        Aktivitet aktivitet = new Aktivitet("Løping",1600, "1/1/2019","Jon");
        assertEquals("1/1/2019", aktivitet.getTid(), "tester dato");
    }
    @Test
    @DisplayName("utover")
    public void getUtover(){

        Aktivitet aktivitet = new Aktivitet("Løping",1600, "1/1/2019","Jon");
        assertEquals("Jon", aktivitet.getTid(), "tester utover");
    }
}