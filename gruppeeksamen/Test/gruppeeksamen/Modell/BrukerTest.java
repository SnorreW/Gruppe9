package gruppeeksamen.Modell;

import gruppeeksamen.Controller.LoggInnController;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static gruppeeksamen.Modell.Bruker.sjekkAtBrukerInputErFylt;

public class BrukerTest {

    String brukernavn;
    String passord;
    @Test
    public void sjekkAtInputErFyltUTest(){
        brukernavn = "";
        passord = "";
        Assert.assertFalse(sjekkAtBrukerInputErFylt(brukernavn,passord));

        brukernavn ="hei";
        passord = "";
        Assert.assertFalse(sjekkAtBrukerInputErFylt(brukernavn,passord));

        brukernavn ="";
        passord = "hei";
        Assert.assertFalse(sjekkAtBrukerInputErFylt(brukernavn,passord));

        brukernavn ="hei";
        passord = "hei";
        Assert.assertTrue(sjekkAtBrukerInputErFylt(brukernavn,passord));

    }

    @Test
    public void navnTest() {
        //Given
        String brukernavn = "brukernavn";
        String passord = "passord";

        //When
        Bruker bruker = new Utover(brukernavn, passord);

        //Then
        Assert.assertEquals(bruker.getBrukenavn(), brukernavn);
        Assert.assertEquals(bruker.getPassord(), passord);
    }

    @Test
    public void konstruktor1() {
        //Given
        String brukernavn = "brukernavn";
        String passord = "passord";
        int alder = 15;
        String navn = "navn";
        String etternavn = "etternavn";

        //When
        Bruker bruker = new Utover(brukernavn, passord, navn, etternavn, alder);

        //Then
        Assert.assertEquals(bruker.getBrukenavn(), brukernavn);
        Assert.assertEquals(bruker.getPassord(), passord);
        Assert.assertEquals(bruker.getAlder(), alder);
        Assert.assertEquals(bruker.getNavn(), navn);
        Assert.assertEquals(bruker.getEtternavn(), etternavn);
    }

    @Test
    public void skalLageBrukerOgReturnereBruker() {
        //Given
        String brukernavn = "brukernavn";
        String passord = "passord";
        String navn = "navn";
        String etternavn = "etternavn";
        int alder = 20;

        //When
        Bruker bruker = new Utover(brukernavn, passord, navn, etternavn, alder);

        //Then
        Assert.assertEquals(bruker.getBrukenavn(), brukernavn);
        Assert.assertEquals(bruker.getPassord(), passord);
        Assert.assertEquals(bruker.getNavn(), navn);
        Assert.assertEquals(bruker.getEtternavn(), etternavn);
        Assert.assertEquals(bruker.getAlder(), alder);

    }

}
