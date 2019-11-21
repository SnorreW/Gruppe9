package gruppeeksamen.Controller;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoggInnControllerTest {

    String brukernavn;
    String passord;
    String brukernavn2;
    String passord2;

    @Test
    public void sjekkPassordOgBrukernavnTestTrue(){

        brukernavn = "hei";
        brukernavn2 = "hei";
        passord = "hei";
        passord2 = "hei";
        assertFalse(LoggInnController.brukernavnOgpassordSjekk(brukernavn,passord,brukernavn2,passord2));
    }

    @Test
    public void sjekkPassordOgBrukernavnTestFeilBrukernavnFalse(){

        brukernavn = "hei";
        brukernavn2 = "he";
        passord = "hei";
        passord2 = "hei";
        assertTrue(LoggInnController.brukernavnOgpassordSjekk(brukernavn,passord,brukernavn2,passord2));
    }

    @Test
    public void sjekkPassordOgBrukernavnTestFeilPassordfalse(){

        brukernavn = "hei";
        brukernavn2 = "hei";
        passord = "hei";
        passord2 = "he";
        assertTrue(LoggInnController.brukernavnOgpassordSjekk(brukernavn,passord,brukernavn2,passord2));
    }
  }