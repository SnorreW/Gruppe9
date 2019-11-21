package gruppeeksamen.Modell;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrangementerTest {

    @Test
    public void sjekkAtGetNavnFungerer() {
        Arrangementer arrangement = new Arrangementer("navn");

        assertEquals("navn",arrangement.getNavn());

        assertNotEquals("", arrangement.getNavn());

        assertNotEquals("Navn",arrangement.getNavn());
    }

    @Test
    public void sjekkAtSetNavnFungerer() {
        Arrangementer arrangement = new Arrangementer("navn");

        arrangement.setNavn("hei");

        assertEquals("hei",arrangement.getNavn());

        assertNotEquals("Hei", arrangement.getNavn());

        assertTrue(arrangement.getNavn() == "hei");
    }

    @Test
    public void sjekkMinimumAntallUtovere(){

        int under = -1;
        int over = 2;
        int nuLL = 1;

        assertEquals(0,Arrangementer.minimumAntallUtovere(under));
        assertEquals(1,Arrangementer.minimumAntallUtovere(over));
        assertEquals(0,Arrangementer.minimumAntallUtovere(nuLL));
    }

}