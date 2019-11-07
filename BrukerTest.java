package gruppeeksamen.Modell;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class BrukerTest {
    @Test
    @DisplayName("Bruker")
    public void getNavn(){

        Bruker bruker = new Bruker("SSK","Jon", "Fredriksen");
        assertEquals("Jon", bruker.getNavn(), "utover");
    }
}