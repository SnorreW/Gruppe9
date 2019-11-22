package gruppeeksamen.Controller;

import gruppeeksamen.Data.NyDataHandler;
import gruppeeksamen.Modell.Utover;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NyBrukerControllerTest {

    private NyBrukerController nyBrukerController = new NyBrukerController();
    private String brukernavn;
    private String passord;
    private String fornavn;
    private String etternavn;
    private String alder;

    //Krav: 3.1.2, 3.1.3, 3.1.4, 3.1.5
    @Test
    public void trueDersomAlleInputFelterErFyltUt() {
        brukernavn = "Aleksander";
        passord = "Aleksander123";
        fornavn = "Aleksander";
        etternavn = "Sandnes";
        alder = "20";
        assertTrue(nyBrukerController.sjekkOmInputErGyldig(brukernavn, passord, fornavn, etternavn, alder));
    }

    //Krav: 3.1.2, 3.1.3, 3.1.4, 3.1.5
    @Test
    public void falseDersomAlleInputFelterIkkeErFyltUt() {
        brukernavn = "";
        passord = "";
        fornavn = "";
        etternavn = "";
        alder = "0";
        assertFalse(nyBrukerController.sjekkOmInputErGyldig(brukernavn, passord, fornavn, etternavn, alder));
    }

    //Krav: 3.1.2, 3.1.3, 3.1.4, 3.1.5
    @Test
    public void falseDersomBrukernavnIkkeErFyltUt() {
        brukernavn = "";
        passord = "Aleksander123";
        fornavn = "Aleksander";
        etternavn = "Sandnes";
        alder = "20";
        assertFalse(nyBrukerController.sjekkOmInputErGyldig(brukernavn, passord, fornavn, etternavn, alder));
    }

    //Krav: 3.1.2, 3.1.3, 3.1.4, 3.1.5
    @Test
    public void falseDersomPassordIkkeErFyltUt() {
        brukernavn = "Aleksander";
        passord = "";
        fornavn = "Aleksander";
        etternavn = "Sandnes";
        alder = "20";
        assertFalse(nyBrukerController.sjekkOmInputErGyldig(brukernavn, passord, fornavn, etternavn, alder));
    }

    //Krav: 3.1.2, 3.1.3, 3.1.4, 3.1.5
    @Test
    public void falseDersomFornavnIkkeErFyltUt() {
        brukernavn = "Aleksander";
        passord = "Aleksander123";
        fornavn = "";
        etternavn = "Sandnes";
        alder = "20";
        assertFalse(nyBrukerController.sjekkOmInputErGyldig(brukernavn, passord, fornavn, etternavn, alder));
    }

    //Krav: 3.1.2, 3.1.3, 3.1.4, 3.1.5
    @Test
    public void falseDersomEtternavnIkkeErFyltUt() {
        brukernavn = "Aleksander";
        passord = "Aleksander123";
        fornavn = "Aleksander";
        etternavn = "";
        alder = "20";
        assertFalse(nyBrukerController.sjekkOmInputErGyldig(brukernavn, passord, fornavn, etternavn, alder));
    }

    //Krav: 3.1.2, 3.1.3, 3.1.4, 3.1.5
    @Test
    public void falseDersomAlderIkkeErFyltUt() {
        brukernavn = "Aleksander";
        passord = "Aleksander123";
        fornavn = "Aleksander";
        etternavn = "Sandnes";
        alder = "0";
        assertFalse(nyBrukerController.sjekkOmInputErGyldig(brukernavn, passord, fornavn, etternavn, alder));
    }

    //Krav: 3.1.2, 3.1.3, 3.1.4, 3.1.5
    @Test
    public void falseDersomAlderErMerEnn130Aar() {
        brukernavn = "Aleksander";
        passord = "Aleksander123";
        fornavn = "Aleksander";
        etternavn = "Sandnes";
        alder = "150";
        assertFalse(nyBrukerController.sjekkOmInputErGyldig(brukernavn, passord, fornavn, etternavn, alder));
    }

    //Krav: 3.1.2, 3.1.3, 3.1.4, 3.1.5
    @Test
    public void sjekkBrukerData() {
        Utover enUtover = NyDataHandler.hentUtoverData().get(0);

        assertEquals(enUtover.getBrukenavn(), "admin1");
        assertEquals(enUtover.getPassord(), "admin4");
        assertEquals(enUtover.getNavn(), "Admin");
        assertEquals(enUtover.getEtternavn(), "Admin");
        assertEquals(enUtover.getAlder(), 0);
    }
}
