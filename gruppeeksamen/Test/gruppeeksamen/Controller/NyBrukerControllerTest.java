package gruppeeksamen.Controller;

import gruppeeksamen.Data.NyDataHandler;
import gruppeeksamen.Modell.Utover;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*public class NyBrukerControllerTest {

    private NyBrukerController nyBrukerController = new NyBrukerController();
    private String brukernavn;
    private String passord;
    private String fornavn;
    private String etternavn;
    private String alder;

    @Test
    public void trueDersomAlleInputFelterErFyltUt() {
        brukernavn = "Aleksander";
        passord = "Aleksander123";
        fornavn = "Aleksander";
        etternavn = "Sandnes";
        alder = "20";
        assertTrue(nyBrukerController.sjekkOmInputErGyldig(brukernavn, passord, fornavn, etternavn, alder));
    }

    @Test
    public void falseDersomAlleInputFelterIkkeErFyltUt() {
        brukernavn = "";
        passord = "";
        fornavn = "";
        etternavn = "";
        alder = "0";
        assertFalse(nyBrukerController.sjekkOmInputErGyldig(brukernavn, passord, fornavn, etternavn, alder));
    }

    @Test
    public void falseDersomBrukernavnIkkeErFyltUt() {
        brukernavn = "";
        passord = "Aleksander123";
        fornavn = "Aleksander";
        etternavn = "Sandnes";
        alder = "20";
        assertFalse(nyBrukerController.sjekkOmInputErGyldig(brukernavn, passord, fornavn, etternavn, alder));
    }

    @Test
    public void falseDersomPassordIkkeErFyltUt() {
        brukernavn = "Aleksander";
        passord = "";
        fornavn = "Aleksander";
        etternavn = "Sandnes";
        alder = "20";
        assertFalse(nyBrukerController.sjekkOmInputErGyldig(brukernavn, passord, fornavn, etternavn, alder));
    }

    @Test
    public void falseDersomFornavnIkkeErFyltUt() {
        brukernavn = "Aleksander";
        passord = "Aleksander123";
        fornavn = "";
        etternavn = "Sandnes";
        alder = "20";
        assertFalse(nyBrukerController.sjekkOmInputErGyldig(brukernavn, passord, fornavn, etternavn, alder));
    }

    @Test
    public void falseDersomEtternavnIkkeErFyltUt() {
        brukernavn = "Aleksander";
        passord = "Aleksander123";
        fornavn = "Aleksander";
        etternavn = "";
        alder = "20";
        assertFalse(nyBrukerController.sjekkOmInputErGyldig(brukernavn, passord, fornavn, etternavn, alder));
    }

    @Test
    public void falseDersomAlderIkkeErFyltUt() {
        brukernavn = "Aleksander";
        passord = "Aleksander123";
        fornavn = "Aleksander";
        etternavn = "Sandnes";
        alder = "0";
        assertFalse(nyBrukerController.sjekkOmInputErGyldig(brukernavn, passord, fornavn, etternavn, alder));
    }

    @Test
    public void falseDersomAlderErMerEnn130Aar() {
        brukernavn = "Aleksander";
        passord = "Aleksander123";
        fornavn = "Aleksander";
        etternavn = "Sandnes";
        alder = "150";
        assertFalse(nyBrukerController.sjekkOmInputErGyldig(brukernavn, passord, fornavn, etternavn, alder));
    }

    @Test
    public void sjekkBrukerData() {
        Utover enUtover = NyDataHandler.hentUtoverData().get(0);

        assertEquals(enUtover.getBrukenavn(), "Sandnes");
        assertEquals(enUtover.getPassord(), "admin2");
        assertEquals(enUtover.getNavn(), "Aleksander");
        assertEquals(enUtover.getEtternavn(), "Sandnes");
        assertEquals(enUtover.getAlder(), 20);

    }

    */
//}
