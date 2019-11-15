package gruppeeksamen.Controller;

import gruppeeksamen.Modell.Utover;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class NyBrukerControllerTest {
    private Utover nyUtover;

    @BeforeEach
    void setUp() {
        nyUtover = new Utover("TestBrukernavn", "TestPassord", "TestNavn", "TestEtternavn", 20);
    }

    @Test
    void brukernavn() {
        assertNotNull(nyUtover.getBrukenavn());
    }

    @Test
    void passord() {
        assertNotNull(nyUtover.getPassord());
    }

    @Test
    void name() {
        assertNotNull(nyUtover.getNavn());
    }

    @Test
    void etternavn() {
        assertNotNull(nyUtover.getEtternavn());
    }

    @Test
    void alder() {
        assertNotNull(nyUtover.getAlder());


    }


}
